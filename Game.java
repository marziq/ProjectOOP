/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCatalogue;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ammar
 */
public class Game extends MediaItem{
    private StringProperty developer;
    private StringProperty platform;

    public Game(String title, String rating, String genre, String year, String developer, String platform) {
        super(title, rating, genre, year);
        this.developer = new SimpleStringProperty(developer);
        this.platform = new SimpleStringProperty(platform);
    }

    public String getTitle() {
        return title.get();
    }

    public String getRating() {
        return rating.get();
    }

    public String getGenre() {
        return genre.get();
    }

    public String getYear() {
        return year.get();
    }

    public String getDeveloper() {
        return developer.get();
    }

    public String getPlatform() {
        return platform.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setRating(String rating) {
        this.rating.set(rating);
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public void setYear(String year) {
        this.year.set(year);
    }

    public void setDeveloper(String developer) {
        this.developer.set(developer);
    }

    public void setPlatform(String platform) {
        this.platform.set(platform);
    }
    
 
     public String toCSVString() {
        return getTitle() + "," + getRating() + "," + getGenre() + "," + getYear() + "," + getDeveloper() + "," + getPlatform();
    }
}
    

