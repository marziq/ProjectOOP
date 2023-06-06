package MediaCatalogue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 *
 * @author ammar
 */
public class BookController extends Application {
    
    private TableView<Book> tableView = new TableView<>();
    private ObservableList<Book> bookData = FXCollections.observableArrayList();
    
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        Button removeButton = new Button("Remove");
        removeButton.setPrefWidth(200);
        removeButton.setOnAction(event -> removeBook());
        Button addButton = new Button("Add");
        addButton.setPrefWidth(200);
        addButton.setOnAction(event -> openAddBookDialog());
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Book Catalogue");
        primaryStage.setScene(scene);
        primaryStage.show();

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

        TableColumn<Book, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Book, String> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, String> numPagesColumn = new TableColumn<>("NumPages");
        numPagesColumn.setCellValueFactory(new PropertyValueFactory<>("numPages"));

        tableView.getColumns().addAll(titleColumn, ratingColumn, genreColumn, yearColumn, authorColumn, numPagesColumn);

        try (BufferedReader reader = new BufferedReader(new FileReader("book.csv"))) {
             String line;
             while ((line = reader.readLine()) != null) {
             String[] values = line.split(",");
             if (values.length == 6) { // Add a check for the number of values
                  Book book = new Book(values[0], values[1], values[2], values[3], values[4], values[5]);
                  bookData.add(book);
           } else {
            System.out.println("Invalid data format: " + line); // Handle the invalid line
         }
       }
}      catch (IOException e) {
             e.printStackTrace();
         }
        tableView.setItems(bookData);
        root.getChildren().addAll(tableView, removeButton);
        root.setAlignment(Pos.CENTER);
    }

    private void removeBook() {
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            bookData.remove(selectedBook);
            updateBookDataFile();
        }
    }

    private void updateBookDataFile() {
        try (FileWriter writer = new FileWriter("book.csv")) {
            for (Book book : bookData) {
                writer.write(book.toCSVString() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void loadBookData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("book.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 6) {
                    Book book = new Book(values[0], values[1], values[2], values[3], values[4], values[5]);
                    bookData.add(book);
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void openAddBookDialog() {
        Stage addBookStage = new Stage();
        addBookStage.setTitle("Add Book");

        TextField titleField = new TextField();
        TextField ratingField = new TextField();
        TextField genreField = new TextField();
        TextField yearField = new TextField();
        TextField authorField = new TextField();
        TextField numPagesField = new TextField();

        Button addButton = new Button("Add");
        addButton.setPrefWidth(200);
        addButton.setOnAction(event -> {
            String title = titleField.getText();
            String rating = ratingField.getText();
            String genre = genreField.getText();
            String year = yearField.getText();
            String author = authorField.getText();
            String numPages = numPagesField.getText();

            Book book = new Book(title, rating, genre, year, author, numPages);
            bookData.add(book);
            updateBookDataFile();
            
            addBookStage.close();
        });

        VBox addBookRoot = new VBox(10);
        addBookRoot.getChildren().addAll(
                new Label("Title: "), titleField,
                new Label("Rating: "), ratingField,
                new Label("Genre: "), genreField,
                new Label("Year: "), yearField,
                new Label("Director: "), authorField,
                new Label("Duration: "), numPagesField,
                addButton
        );
        addBookRoot.setAlignment(Pos.CENTER);

        Scene addBookScene = new Scene(addBookRoot, 800, 500);
        addBookStage.setScene(addBookScene);
        addBookStage.show();
    }
    
      public static void main(String[] args) {
        launch(args);
    }
}
