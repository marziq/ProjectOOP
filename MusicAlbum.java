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
public class MusicAlbum extends MediaItem{
    private StringProperty artist;
    private StringProperty numTracks;

    MusicAlbum(){
    }
    MusicAlbum(String title, String genre, String year, String rating, String artist, String numTracks){
        super(title,rating,genre,year);
        this.artist = new SimpleStringProperty(artist);
        this.numTracks = new SimpleStringProperty(numTracks);
    }
    
    public String getArtist() {
        return artist.get();
    }

    public String getNumTracks() {
        return numTracks.get();
    }
    
    public void setArtist(String artist) {
        this.artist.set(artist);
    }

    public void setNumTracks(String numTracks) {
        this.numTracks.set(numTracks);
    }

     public String toCSVString() {
        return getTitle() + "," + getRating() + "," + getGenre() + "," + getYear() + "," + getArtist() + "," + getNumTracks();
    }
}
