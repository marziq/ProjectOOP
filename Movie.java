package Project;

public class Movie extends MediaItem{
    private String director;
    private int duration;

    Movie(){
    }
    Movie(String title, double rating, String genre, int yearRelease, String director, int duration){
        super(title, rating, genre, yearRelease);
        this.director = director;
        this.duration = duration;
    }
    String getDirector(){
        return director;
    }
    int getDuration(){
        return duration;
    }
    void setDirector(String director){
        this.director = director;
    }
    void setDuration(int duration){
        this.duration = duration;
    }
    @Override
    public String toString() {
        return super.toString() + ", Director: " + director + ", Duration(minutes): " + duration;
    }
}
