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
public class MovieController extends Application {
    
    private TableView<Movie> tableView = new TableView<>();
    private ObservableList<Movie> movieData = FXCollections.observableArrayList();
    
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        Button removeButton = new Button("Remove");
        removeButton.setPrefWidth(200);
        removeButton.setOnAction(event -> removeMovie());
        Button addButton = new Button("Add");
        addButton.setPrefWidth(200);
        addButton.setOnAction(event -> openAddMovieDialog());
        Scene scene = new Scene(root, 600, 400);


        primaryStage.setTitle("Movie Catalogue");
        primaryStage.setScene(scene);
        primaryStage.show();

        TableColumn<Movie, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Movie, String> ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

        TableColumn<Movie, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Movie, String> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Movie, String> directorColumn = new TableColumn<>("Director");
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));

        TableColumn<Movie, String> minutesColumn = new TableColumn<>("Minutes");
        minutesColumn.setCellValueFactory(new PropertyValueFactory<>("minutes"));

        tableView.getColumns().addAll(titleColumn, ratingColumn, genreColumn, yearColumn, directorColumn, minutesColumn);

        try (BufferedReader reader = new BufferedReader(new FileReader("movie.csv"))) {
             String line;
             while ((line = reader.readLine()) != null) {
             String[] values = line.split(",");
             if (values.length == 6) { // Add a check for the number of values
                  Movie movie = new Movie(values[0], values[1], values[2], values[3], values[4], values[5]);
                  movieData.add(movie);
           } else {
            System.out.println("Invalid data format: " + line); // Handle the invalid line
         }
       }
}      catch (IOException e) {
             e.printStackTrace();
         }
        tableView.setItems(movieData);
        root.getChildren().addAll(tableView, addButton, removeButton);
        root.setAlignment(Pos.CENTER);
    }

    private void removeMovie() {
        Movie selectedMovie = tableView.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            movieData.remove(selectedMovie);
            updateMovieDataFile();
        }
    }

    private void updateMovieDataFile() {
        try (FileWriter writer = new FileWriter("movie.csv")) {
            for (Movie movie : movieData) {
                writer.write(movie.toCSVString() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void loadMovieData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("movie.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 6) {
                    Movie movie = new Movie(values[0], values[1], values[2], values[3], values[4], values[5]);
                    movieData.add(movie);
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void openAddMovieDialog() {
        Stage addMovieStage = new Stage();
        addMovieStage.setTitle("Add Movie");

        TextField titleField = new TextField();
        TextField ratingField = new TextField();
        TextField genreField = new TextField();
        TextField yearField = new TextField();
        TextField directorField = new TextField();
        TextField minutesField = new TextField();

        Button addButton = new Button("Add");
        addButton.setPrefWidth(200);
        addButton.setOnAction(event -> {
            String title = titleField.getText();
            String rating = ratingField.getText();
            String genre = genreField.getText();
            String year = yearField.getText();
            String director = directorField.getText();
            String minutes = minutesField.getText();

            Movie movie = new Movie(title, rating, genre, year, director, minutes);
            movieData.add(movie);
            updateMovieDataFile();
            
            addMovieStage.close();
        });

        VBox addMovieRoot = new VBox(10);
        addMovieRoot.getChildren().addAll(
                new Label("Title: "), titleField,
                new Label("Rating: "), ratingField,
                new Label("Genre: "), genreField,
                new Label("Year: "), yearField,
                new Label("Director: "), directorField,
                new Label("Duration: "), minutesField,
                addButton
        );
        addMovieRoot.setAlignment(Pos.CENTER);

        Scene addMovieScene = new Scene(addMovieRoot, 800, 500);
        addMovieStage.setScene(addMovieScene);
        addMovieStage.show();
    }
    
      public static void main(String[] args) {
        launch(args);
    }
}
