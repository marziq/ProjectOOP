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
public class MusicAlbumController extends Application {
    
    private TableView<MusicAlbum> tableView = new TableView<>();
    private ObservableList<MusicAlbum> musicAlbumData = FXCollections.observableArrayList();
    
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        Button removeButton = new Button("Remove");
        removeButton.setPrefWidth(200);
        removeButton.setOnAction(event -> removeMusicAlbum());
        Button addButton = new Button("Add");
        addButton.setPrefWidth(200);
        addButton.setOnAction(event -> openAddMusicAlbumDialog());
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Music Album Catalogue");
        primaryStage.setScene(scene);
        primaryStage.show();

        TableColumn<MusicAlbum, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<MusicAlbum, String> ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

        TableColumn<MusicAlbum, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<MusicAlbum, String> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<MusicAlbum, String> artistColumn = new TableColumn<>("Artist");
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));

        TableColumn<MusicAlbum, String> numTracksColumn = new TableColumn<>("NumTracks");
        numTracksColumn.setCellValueFactory(new PropertyValueFactory<>("numTracks"));

        tableView.getColumns().addAll(titleColumn, ratingColumn, genreColumn, yearColumn, artistColumn, numTracksColumn);

        try (BufferedReader reader = new BufferedReader(new FileReader("musicAlbum.csv"))) {
             String line;
             while ((line = reader.readLine()) != null) {
             String[] values = line.split(",");
             if (values.length == 6) { // Add a check for the number of values
                  MusicAlbum musicAlbum = new MusicAlbum(values[0], values[1], values[2], values[3], values[4], values[5]);
                  musicAlbumData.add(musicAlbum);
           } else {
            System.out.println("Invalid data format: " + line); // Handle the invalid line
         }
       }
}      catch (IOException e) {
             e.printStackTrace();
         }
        
        tableView.setItems(musicAlbumData);
        root.getChildren().addAll(tableView, addButton, removeButton);
        root.setAlignment(Pos.CENTER);
    }

    private void removeMusicAlbum() {
        MusicAlbum selectedMusicAlbum = tableView.getSelectionModel().getSelectedItem();
        if (selectedMusicAlbum != null) {
            musicAlbumData.remove(selectedMusicAlbum);
            updateMusicAlbumDataFile();
        }
    }
    
    public void openAddMusicAlbumDialog() {
        Stage addMusicAlbumStage = new Stage();
        addMusicAlbumStage.setTitle("Add Music Album");

        TextField titleField = new TextField();
        TextField ratingField = new TextField();
        TextField genreField = new TextField();
        TextField yearField = new TextField();
        TextField artistField = new TextField();
        TextField numTracksField = new TextField();

        Button addButton = new Button("Add");
        addButton.setPrefWidth(200);
        addButton.setOnAction(event -> {
            String title = titleField.getText();
            String rating = ratingField.getText();
            String genre = genreField.getText();
            String year = yearField.getText();
            String artist = artistField.getText();
            String numTracks = numTracksField.getText();

            MusicAlbum musicAlbum = new MusicAlbum(title, rating, genre, year, artist, numTracks);
            musicAlbumData.add(musicAlbum);
            updateMusicAlbumDataFile();
            
            addMusicAlbumStage.close();
        });

        VBox addMusicAlbumRoot = new VBox(10);
        addMusicAlbumRoot.getChildren().addAll(
                new Label("Title: "), titleField,
                new Label("Rating: "), ratingField,
                new Label("Genre: "), genreField,
                new Label("Year: "), yearField,
                new Label("Artist: "), artistField,
                new Label("Number of Tracks: "), numTracksField,
                addButton
        );
        addMusicAlbumRoot.setAlignment(Pos.CENTER);

        Scene addMusicAlbumScene = new Scene(addMusicAlbumRoot, 800, 500);
        addMusicAlbumStage.setScene(addMusicAlbumScene);
        addMusicAlbumStage.show();
    }
    
    private void updateMusicAlbumDataFile() {
        try (FileWriter writer = new FileWriter("musicAlbum.csv")) {
            for (MusicAlbum musicAlbum : musicAlbumData) {
                writer.write(musicAlbum.toCSVString() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void loadMusicAlbumData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("musicAlbum.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 6) {
                    MusicAlbum musicAlbum = new MusicAlbum(values[0], values[1], values[2], values[3], values[4], values[5]);
                    musicAlbumData.add(musicAlbum);
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
      public static void main(String[] args) {
        launch(args);
    }
}
