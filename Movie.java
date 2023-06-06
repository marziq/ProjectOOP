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
public class Movie extends MediaItem{
  
    private StringProperty director;
    private StringProperty minutes;

     public Movie(String title, String rating, String genre, String year, String director, String minutes) {
        super(title, rating, genre, year);
        this.director = new SimpleStringProperty(director);
        this.minutes = new SimpleStringProperty(minutes);
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

    public String getDirector() {
        return director.get();
    }

    public String getMinutes() {
        return minutes.get();
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

    public void setDirector(String director) {
        this.director.set(director);
    }

    public void setMinutes(String minutes) {
        this.minutes.set(minutes);
    }
    
 
    public String toCSVString() {
        return getTitle() + "," + getRating() + "," + getGenre() + "," + getYear() + "," + getDirector() + "," + getMinutes();
    }
    
}

