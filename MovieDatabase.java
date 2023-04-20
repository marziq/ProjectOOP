import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MovieDatabase {
    private Movie[] movies;
    private int numMovies;
    
    public MovieDatabase() {
        movies = new Movie[100]; // Assuming there are no more than 100 movies in the database
        numMovies = 0;
        
        try {
            // Create a FileReader object to read from a file
            FileReader fileReader = new FileReader("movies.csv");
            
            // Create a BufferedReader object to read text from the FileReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            // Read the first line of the file (assuming it contains headers)
            String headers = bufferedReader.readLine();
            
            // Loop through the remaining lines of the file
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Split the line into separate values based on the comma delimiter
                String[] values = line.split(",");
                
                // Create a new Movie object and add it to the movies array
                Movie movie = new Movie(values[0], values[1], Integer.parseInt(values[2]), Double.parseDouble(values[3]));
                movies[numMovies] = movie;
                numMovies++;
            }
            
            // Close the BufferedReader
            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    public Movie[] search(String query) {
        Movie[] results = new Movie[100]; // Assuming there are no more than 100 matches
        
        // Loop through the movies array and check each movie against the query
        int numResults = 0;
        for (int i = 0; i < numMovies; i++) {
            Movie movie = movies[i];
            
            // Check if the movie title, genre, or director contains the query (case-insensitive)
            if (movie.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                movie.getGenre().toLowerCase().contains(query.toLowerCase()) ||
                movie.getDirector().toLowerCase().contains(query.toLowerCase())) {
                
                // If the movie matches the query, add it to the results array
                results[numResults] = movie;
                numResults++;
            }
        }
        
        // If no matches were found, return null
        if (numResults == 0) {
            return null;
        }
        
        // Otherwise, create a new array with the correct size and copy the matches into it
        Movie[] finalResults = new Movie[numResults];
        for (int i = 0; i < numResults; i++) {
            finalResults[i] = results[i];
        }
        
        return finalResults;
    }
}
