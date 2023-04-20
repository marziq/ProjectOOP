import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MovieDatabase {
    private Movie[] movies;
    private int size;
    
    public MovieDatabase() {
        movies = new Movie[100]; // Initialize array with a capacity of 100
        size = 0;
        
        try {
            // Create a FileReader object to read from a file
            FileReader fileReader = new FileReader("movies.csv");
            
            // Create a BufferedReader object to read lines of text from the FileReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            String line;
            
            // Read each line of text from the file
            while ((line = bufferedReader.readLine()) != null) {
                // Split the line into an array of strings based on the comma delimiter
                String[] parts = line.split(",");
                
                // Create a new movie object using the data from the line
                Movie movie = new Movie(parts[0], parts[1], parts[2], parts[3]);
                
                // Add the movie object to the movies array
                movies[size] = movie;
                size++;
            }
            
            // Close the BufferedReader
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    public void searchMovies(String keyword) {
        System.out.println("Search results:");
        
        // Loop through each movie in the movies array
        for (int i = 0; i < size; i++) {
            // Check if the movie matches the keyword in either the title, genre, or director fields
            if (movies[i].getTitle().contains(keyword) || movies[i].getGenre().contains(keyword) || movies[i].getDirector().contains(keyword)) {
                // If the movie matches, display its details
                System.out.println(movies[i].getTitle() + " (" + movies[i].getYear() + ") - " + movies[i].getGenre() + " - " + movies[i].getDirector() + " - " + movies[i].getRating());
            }
        }
    }
}
