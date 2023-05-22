package Project;

public class Game extends MediaItem{
    private String developer;
    private String platform;
    private static int gameCounts = 0;
    public Game(){
    }
    public Game(String title, double rating, String genre, int year, String developer, String platform){
        super(title,rating,genre,year);
        this.developer = developer;
        this.platform = platform;
        gameCounts++;
    }
    public String getName(){
        return getTitle();
    }
    public String searchGenre(){
        return getGenre();
    }
    public String getDeveloper(){
        return developer;
    }
    public String getPlatform(){
        return platform;
    }
    public void setDeveloper(String developer){
        this.developer = developer;
    }
    public void setPlatform(String platform){
        this.platform = platform;
    }
    public static int getGameCounts(){
        return gameCounts;
   }
    @Override
    public String toString(){
        return super.toString() + ", Developer: " + developer + ", Platform: " + platform;
    }
}
