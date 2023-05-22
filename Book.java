package Project;

public class Book extends MediaItem{
    private String author;
    private int numPages;
    Book(){
    }

    Book(String title, double rating, int yearRelease, String genre, String author, int numPages) {
        super(title, rating, genre, yearRelease);
        this.author = author;
        this.numPages = numPages;
    }

    public String getAuthor() {
        return author;
    }
    public int getNumPages(){
        return numPages;
    }

    @Override
    public String toString() {
        return super.toString() + ", Author: " + author;
    }
}
