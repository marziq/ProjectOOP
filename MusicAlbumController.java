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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
        removeButton.setOnAction(event -> removeMusicAlbum());     
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("MusicAlbum Catalogue");

        
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
        root.getChildren().addAll(tableView, removeButton);
        root.setAlignment(Pos.CENTER);
    }

    private void removeMusicAlbum() {
        MusicAlbum selectedMusicAlbum = tableView.getSelectionModel().getSelectedItem();
        if (selectedMusicAlbum != null) {
            musicAlbumData.remove(selectedMusicAlbum);
            updateMusicAlbumDataFile();
        }
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
      public static void main(String[] args) {
        launch(args);
    }
}
