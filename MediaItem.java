/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCatalogue;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ammar
 */
public class MediaItem {
    SimpleStringProperty title;
    SimpleStringProperty rating;
    SimpleStringProperty genre;
    SimpleStringProperty year;

    MediaItem(){
    }
    MediaItem(String title, String rating, String genre, String year){
        this.title = new SimpleStringProperty(title);
        this.rating = new SimpleStringProperty(rating);
        this.genre = new SimpleStringProperty(genre);
        this.year = new SimpleStringProperty(year);
    }

    //getters
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

    //setters
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
    
    public String toCSVString() {
    return String.format("%s,%s,%s,%s", title, rating, genre, year);
    }
}
