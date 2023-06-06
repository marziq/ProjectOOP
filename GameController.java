package MediaCatalogue;

import java.io.BufferedReader;
import java.io.FileReader;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.cell.PropertyValueFactory;

public class GameController extends Application {

    private TableView<Game> tableView = new TableView<>();
    private ObservableList<Game> gameData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        Button removeButton = new Button("Remove");
        removeButton.setPrefWidth(200);
        removeButton.setOnAction(event -> removeGame());
        Button addButton = new Button("Add");
        addButton.setPrefWidth(200);
        addButton.setOnAction(event -> openAddGameDialog());
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Game Catalogue");

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

        // Load existing game data from the CSV file
        loadGameData();
        tableView.setItems(gameData);
        root.getChildren().addAll(tableView, addButton, removeButton);
        root.setAlignment(Pos.CENTER);
    }

    private void removeGame() {
        Game selectedGame = tableView.getSelectionModel().getSelectedItem();
        if (selectedGame != null) {
            gameData.remove(selectedGame);
            updateGameDataFile();
        }
    }

   public void openAddGameDialog() {
        Stage addGameStage = new Stage();
        addGameStage.setTitle("Add Game");

        TextField titleField = new TextField();
        TextField ratingField = new TextField();
        TextField genreField = new TextField();
        TextField yearField = new TextField();
        TextField developerField = new TextField();
        TextField platformField = new TextField();

        Button addButton = new Button("Add");
        addButton.setPrefWidth(200);
        addButton.setOnAction(event -> {
            String title = titleField.getText();
            String rating = ratingField.getText();
            String genre = genreField.getText();
            String year = yearField.getText();
            String developer = developerField.getText();
            String platform = platformField.getText();

            Game game = new Game(title, rating, genre, year, developer, platform);
            gameData.add(game);
            updateGameDataFile();
            
            addGameStage.close();
        });

        VBox addGameRoot = new VBox(10);
        addGameRoot.getChildren().addAll(
                new Label("Title: "), titleField,
                new Label("Rating: "), ratingField,
                new Label("Genre: "), genreField,
                new Label("Year: "), yearField,
                new Label("Developer: "), developerField,
                new Label("Platform: "), platformField,
                addButton
        );
        addGameRoot.setAlignment(Pos.CENTER);

        Scene addGameScene = new Scene(addGameRoot, 800, 500);
        addGameStage.setScene(addGameScene);
        addGameStage.show();
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

    private void loadGameData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("game.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 6) {
                    Game game = new Game(values[0], values[1], values[2], values[3], values[4], values[5]);
                    gameData.add(game);
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
