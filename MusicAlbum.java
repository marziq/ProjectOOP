package Project;

public class MusicAlbum extends MediaItem{
}
package Project;

public class MusicAlbum extends MediaItem{
    private String artist;
    private int numTracks;

    MusicAlbum(){
    }
    MusicAlbum(String title, String genre, int year, double rating, String artist, int numTracks){
        super(title,rating,genre,year);
        this.artist = artist;
        this.numTracks = numTracks;
    }
    String getArtist(){
        return artist;
    }
    int getNumTracks(){
        return numTracks;
    }
    void setArtist(String artist){
        this.artist = artist;
    }
    void setNumTracks(int numTracks){
        this.numTracks = numTracks;
    }

    @Override
    public String toString(){
        return super.toString() + ", Artist: " + artist + ", Number Of Tracks: " + numTracks;
    }
}
