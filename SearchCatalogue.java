package MediaCatalogue;

import java.io.BufferedReader;
import java.io.FileReader;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchCatalogue extends Application {
    private TableView<ObservableList<String>> searchResultView = new TableView<>();
    private TextField searchField;

    @Override
    public void start(Stage primaryStage) {
        searchField = new TextField();
        searchField.setPromptText("Enter search query");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> searchCatalog());
        

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(searchField, searchButton, searchResultView);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Search Catalogue");
        primaryStage.show();
    }

    private void searchCatalog() {
        String query = searchField.getText().toLowerCase();
        ObservableList<ObservableList<String>> searchResults = FXCollections.observableArrayList();

        try (BufferedReader gameReader = new BufferedReader(new FileReader("game.csv"));
                BufferedReader movieReader = new BufferedReader(new FileReader("movie.csv"));
                BufferedReader musicAlbumReader = new BufferedReader(new FileReader("musicAlbum.csv"));
                BufferedReader bookReader = new BufferedReader(new FileReader("book.csv"))) {

            searchResults.addAll(searchFile(gameReader, query, "Platform"));
            searchResults.addAll(searchFile(movieReader, query, "Duration"));
            searchResults.addAll(searchFile(musicAlbumReader, query, "NumTracks"));
            searchResults.addAll(searchFile(bookReader, query, "NumPages"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        populateSearchResults(searchResults);
    }

    private ObservableList<ObservableList<String>> searchFile(BufferedReader reader, String query, String otherInfoColumn) throws IOException {
        ObservableList<ObservableList<String>> results = FXCollections.observableArrayList();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            for (String value : values) {
                if (value.toLowerCase().contains(query)) {
                    ObservableList<String> row = FXCollections.observableArrayList(values);
                    row.add(otherInfoColumn);
                    results.add(row);
                    break;
                }
            }
        }

        return results;
    }

    private void populateSearchResults(ObservableList<ObservableList<String>> searchResults) {
        searchResultView.getItems().clear();
        searchResultView.getColumns().clear();

        if (!searchResults.isEmpty()) {
            for (String columnName : searchResults.get(0)) {
                final int columnIndex = searchResults.get(0).indexOf(columnName);

                if (columnIndex <= 4) {
                    TableColumn<ObservableList<String>, String> column = new TableColumn<>();
                    switch (columnIndex) {
                        case 0:
                            column.setText("Title");
                            break;
                        case 1:
                            column.setText("Rating");
                            break;
                        case 2:
                            column.setText("Genre");
                            break;
                        case 3:
                            column.setText("Year");
                            break;
                        case 4:
                            column.setText("Publisher");
                            break;
                    }

                    column.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(columnIndex)));
                    searchResultView.getColumns().add(column);
                }
            }

            searchResultView.setItems(searchResults);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
