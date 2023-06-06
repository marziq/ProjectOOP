/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCatalogue;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author ammar
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Label welcomeText = new Label();
        welcomeText.setText("WELCOME TO MEDIA CATALOGUE");
        
        Button viewRemove = new Button("View, Remove, Add");
        viewRemove.setOnAction(event -> chooseStage1());
        
       
        Button searchMedia = new Button("Search");
        searchMedia.setOnAction(e -> {
            // Create an instance of the SecondStageController
            SearchCatalogue searchStage = new SearchCatalogue();
            Stage searchInstance = new Stage();
            
            // Start the second stage
            searchStage.start(searchInstance);
        });
        
        Button exit = new Button("Exit");
        exit.setOnAction(event -> primaryStage.close());
        
        viewRemove.setPrefWidth(200);
        searchMedia.setPrefWidth(200);
        exit.setPrefWidth(200);
        
        VBox root = new VBox(10);
        root.getChildren().addAll(welcomeText, viewRemove, searchMedia, exit);
        root.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(root, 400, 300);
        
        primaryStage.setTitle("Media Catalogue");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void chooseStage1() {
    Stage view = new Stage();

    Button game = new Button("Game");
    game.setMinWidth(200); // Set a fixed width for the button
    game.setOnAction(e -> {
        GameController gameViewRemove = new GameController();
        Stage gameViewRemoveStage = new Stage();
        gameViewRemove.start(gameViewRemoveStage);
    });

    Button movie = new Button("Movie");
    movie.setMinWidth(200); // Set a fixed width for the button
    movie.setOnAction(e -> {
        MovieController movieViewRemove = new MovieController();
        Stage movieViewRemoveStage = new Stage();
        movieViewRemove.start(movieViewRemoveStage);
    });
    
    
    Button musicAlbum = new Button("Music Album");
    musicAlbum.setMinWidth(200); // Set a fixed width for the button
    musicAlbum.setOnAction(e -> {
        MusicAlbumController musicAlbumViewRemove = new MusicAlbumController();
        Stage musicAlbumViewRemoveStage = new Stage();
        musicAlbumViewRemove.start(musicAlbumViewRemoveStage);
    });
    
    
    
    Button book = new Button("Book");
    book.setMinWidth(200); // Set a fixed width for the button
    book.setMinWidth(200); // Set a fixed width for the button
    book.setOnAction(e -> {
        BookController bookViewRemove = new BookController();
        Stage bookViewRemoveStage = new Stage();
        bookViewRemove.start(bookViewRemoveStage);
    });
    
    
    Label choose = new Label();
    choose.setLayoutX(50);
    choose.setLayoutY(0);
    choose.setText("Choose Media");

    VBox games = new VBox(10);
    games.getChildren().addAll(choose, game, movie, book, musicAlbum);
    games.setAlignment(Pos.CENTER);

    Scene viewScene = new Scene(games, 250, 250);
    view.setScene(viewScene);
    view.setTitle("Choose Media");
    view.show();
}
 
    
 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
