package Project;

public class MediaItem {
    private String title;
    private String creator;
    private String genre;
    private int yearRelease;

    MediaItem(){
    }
    MediaItem(String title, String creator, String genre, int yearRelease){
        this.title = title;
        this.creator = creator;
        this.genre = genre;
        this.yearRelease = yearRelease;
    }

    //getters
    String getTitle(){
        return title;
    }
    String getCreator(){
        return creator;
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
    void setCreator(String creator){
        this.creator = creator;
    }
    void setGenre(String genre){
        this.genre = genre;
    }
    void setYearRelease(int yearRelease){
        this.yearRelease = yearRelease;
    }

}
