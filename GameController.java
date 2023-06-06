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
public class GameController extends Application {
    
    private TableView<Game> tableView = new TableView<>();
    private ObservableList<Game> gameData = FXCollections.observableArrayList();
    
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        Button removeButton = new Button("Remove");
        removeButton.setOnAction(event -> removeGame());     
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Media Catalogue");

        
        primaryStage.setScene(scene);
        primaryStage.show();

        TableColumn<Game, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Game, String> ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

        TableColumn<Game, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Game, String> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Game, String> developerColumn = new TableColumn<>("Developer");
        developerColumn.setCellValueFactory(new PropertyValueFactory<>("developer"));

        TableColumn<Game, String> platformColumn = new TableColumn<>("Platform");
        platformColumn.setCellValueFactory(new PropertyValueFactory<>("platform"));

        tableView.getColumns().addAll(titleColumn, ratingColumn, genreColumn, yearColumn, developerColumn, platformColumn);

        try (BufferedReader reader = new BufferedReader(new FileReader("game.csv"))) {
             String line;
             while ((line = reader.readLine()) != null) {
             String[] values = line.split(",");
             if (values.length == 6) { // Add a check for the number of values
                  Game game = new Game(values[0], values[1], values[2], values[3], values[4], values[5]);
                  gameData.add(game);
           } else {
            System.out.println("Invalid data format: " + line); // Handle the invalid line
         }
       }
}      catch (IOException e) {
             e.printStackTrace();
         }
        tableView.setItems(gameData);
        root.getChildren().addAll(tableView, removeButton);
        root.setAlignment(Pos.CENTER);
    }

    private void removeGame() {
        Game selectedGame = tableView.getSelectionModel().getSelectedItem();
        if (selectedGame != null) {
            gameData.remove(selectedGame);
            updateGameDataFile();
        }
    }

    private void updateGameDataFile() {
        try (FileWriter writer = new FileWriter("game.csv")) {
            for (Game game : gameData) {
                writer.write(game.toCSVString() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      public static void main(String[] args) {
        launch(args);
    }
}
