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
public class Book extends MediaItem{
   
    private StringProperty  author;
    private StringProperty  numPages;
    Book(){
    }

    Book(String title, String rating, String year, String genre, String author, String numPages) {
        super(title, rating, genre, year);
        this.author = new SimpleStringProperty(author);
        this.numPages = new SimpleStringProperty(numPages);
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

    public String getAuthor() {
        return author.get();
    }

    public String getNumPages() {
        return numPages.get();
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

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public void setNumPages(String numPages) {
        this.numPages.set(numPages);
    }
    
 
     public String toCSVString() {
        return getTitle() + "," + getRating() + "," + getGenre() + "," + getYear() + "," + getAuthor() + "," + getNumPages();
    }
}