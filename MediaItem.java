package Project;

public class MediaItem {
    private String title;
    private double rating;
    private String genre;
    private int yearRelease;

    MediaItem(){
    }
    MediaItem(String title, double rating, String genre, int yearRelease){
        this.title = title;
        this.rating = rating;
        this.genre = genre;
        this.yearRelease = yearRelease;
    }

    //getters
    String getTitle(){
        return title;
    }
    double getRating(){
        return rating;
    }
    String getGenre(){
        return genre;
    }
    int getYearRelease(){
        return yearRelease;
    }

    //setters
    void setTitle(String title){
        this.title = title;
    }
    void setRating(double rating){
        this.rating = rating;
    }
    void setGenre(String genre){
        this.genre = genre;
    }
    void setYearRelease(int yearRelease){
        this.yearRelease = yearRelease;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Genre: " + genre + ", Year: " + yearRelease + ", Rating: " + rating;
    }
}
