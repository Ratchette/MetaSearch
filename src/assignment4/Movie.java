/**
 * Description: Assignment #4
 * @author Jennfier Winer
 * @since 2011-10-17
 * Created: October 17, 2011
 * Last Modified: December 3, 2011  
 */
package assignment4;

/**
 * This class contains or inherits all of the methods and variables associated with Movies. 
 * This class is a subclass of the Entry Class
 * 
 * @author Jennifer
 * @see Entry
 */
public class Movie extends Entry{
    private String actors;      // no restrictions
    
    /**
     * A no-argument constructor
     */
    public Movie(){
        super();
        actors = "";
    }
    
    /**
     * A constructor for use with previously divided data.
     * It exists in case the future implementations of MetaSearch divides the data for us.
     * 
     * @param title The title of the movie
     * @param actors The actors of the movie
     * @param type The movie's format (eg. VHS, DVD)
     * @param releaseDate The date (or approximate date) that the movie was released
     * @param price The price of the movie in cents
     * 
     * @throws NullPointerException If the Date object passed in was null
     * @throws Exception If the title, actors, type, releaseDate or price are not within the restrictions of the Movie class
     */
    public Movie(String title, String actors, String type, Date releaseDate, int price) throws NullPointerException, Exception{
        super(title, type, releaseDate, price);

        if(!validateActors(actors))
            throw new Exception("Invalid actors");
        
        this.actors = actors.trim();
    }
    
    /**
     * A constructor for use with previously divided data.
     * It exists in case the future implementations of MetaSearch divides the data for us.
     * 
     * @param title The title of the movie
     * @param actors The actors of the movie
     * @param type The movie's format (eg. VHS, DVD)
     * @param releaseDate The date (or approximate date) that the movie was released
     * @param price The price of the movie in dollars
     * 
     * @throws NullPointerException If the Date object passed in was null
     * @throws Exception If the title, actors, type, releaseDate or price are not within the restrictions of the Movie class
     */
    public Movie(String title, String actors, String type, Date releaseDate, double price) throws NullPointerException, Exception{
        super(title, type, releaseDate, price);
        
        if(!validateActors(actors))
            throw new Exception("Invalid actors");
        
        this.actors = actors.trim();
    }
    
    /**
     * A constructor for use with an unaltered (undivided) string of userInput (one entire record)
     * 
     * @param userInput The user's input from the keyboard (a record of a movie in its entirety)
     * @throws NumberFormatException If the day or year of the date is not an integer
     * @throws Exception If the title, actors, type, releaseDate or price are not within the restrictions of the Movie class
     */
    public Movie(String userInput) throws NumberFormatException, Exception{
        super(userInput);
        
        String[] tokens = userInput.split("[:;]");
        if(!validateActors(tokens[2]))
            throw new Exception("Invalid actors");
        
        actors = tokens[2].trim();
    }
    
    /**
     * A constructor for use with tokenized record input
     * 
     * @param userInput The user's input, previously tokenized by ; and :
     * @throws NumberFormatException If the day or year of the date is not an integer
     * @throws Exception If the title, actors, type, releaseDate or price are not within the restrictions of the Movie class
     */
    public Movie(String[] userInput) throws NumberFormatException, Exception{
        super(userInput);
        
        if(!validateActors(userInput[2]))
            throw new Exception("Invalid actors");
        
        actors = userInput[2].trim();
    }
    
    /**
     * A copy constructor - creates a copy of the the object passed in
     * @param anotherMovie The Movie object that we want a copy of
     * @throws NullPointerException If the Movie object passed in is null
     */
    public Movie(Movie anotherMovie) throws NullPointerException{
        super(anotherMovie);
        this.actors = anotherMovie.actors;
    }
    
    
    
    /**
     * Gets Actors from a Movie object
     * @return The actors variable of a Movie
     */
    public String getActors(){
        return actors;
    }
    
    /**
     * Sets a value to the actors variable of a Movie object
     * @param userInput the String that you would like inserted into the actors variable
     * @throws Exception If the String does not meet the requirements of the actors in the Movie Class
     */
    public void setActors(String userInput) throws Exception{
        if(!validateActors(userInput))
            throw new Exception("Invalid actors");
            
        actors = userInput.trim();
    }


    
    /**
     * Checks to see if the record that the user input adheres to the restrictions of the Movie class
     * @param userInput A String that contains all the information needed to create one record of the Movie class
     * @return true if the record meets all of the requirements of the Movie class, false otherwise
     */
    public static boolean validateMovie(String userInput){
        String[] tokens;
        String delimiters = "[:;]";

        tokens = userInput.split(delimiters);
        return (validateMovie(tokens));
    }
    
    /**
     * Checks to see if the tokens that the user input adheres to the restrictions of the Movie class
     * @param userInput An array of String that contains all the information needed to create one record of the Movie class
     * @return true if the record meets all of the requirements of the Movie class, false otherwise
     */
    public static boolean validateMovie(String[] userInput){
        return (validateEntry(userInput) && validateActors(userInput[2]));
    }
    
    /**
     * Checks if the input parameters adhere to the restrictions of the Movie class
     * 
     * @param title The title of the movie
     * @param actors The main actors of the movie
     * @param type The type of movie
     * @param releaseDate The date that the movie was released
     * @param price The price of the movie in cents
     * @return True if all the elements meet the requirements of the Movie class, false otherwise
     */
    public static boolean validateMovie(String title, String actors, String type, Date releaseDate, int price){
        return(validateEntry(title, type, releaseDate, price) && validateActors(actors));
    }
    
    /**
     * Checks if the input parameters adhere to the restrictions of the Movie class
     * 
     * @param title The title of the movie
     * @param actors The main actors of the movie
     * @param type The type of movie
     * @param releaseDate The date that the movie was released
     * @param price The price of the movie in dollars
     * @return True if all the elements meet the requirements of the Movie class, false otherwise
     */
    public static boolean validateMovie(String title, String actors, String type, Date releaseDate, double price){
        return(validateEntry(title, type, releaseDate, price) && validateActors(actors));
    }
    
    /**
     * Checks to see if the actors String adheres to the restrictions of the Movie class
     * @param userInput A String that contains the actors variable
     * @return true if the actors meets all of the requirements of the Movie class, false otherwise
     */
    public static boolean validateActors(String userInput){
        return true;
    }
    
    
    
    /**
     * Creates a copy of the calling Movie
     * @return An exact deep copy of the calling Movie object 
     * @throws NullPointerException If the Movie object passed in is null
     */
    public Movie clone() throws NullPointerException{
        return new Movie(this);
    }
    
    /**
     * Compares two objects of the Movie class to see if they contain identical data
     * @param anotherObject The Object that you wish to compare your current Movie to
     * @return True if the objects are two identical Movie objects, false otherwise
     */
    public boolean equals(Object anotherObject){
        if(anotherObject == null)
            return false;
        if(getClass() != anotherObject.getClass())
            return false;
        
        Movie anotherMovie = (Movie)anotherObject;
        return (getTitle().equals(anotherMovie.getTitle())
            && actors.equals(anotherMovie.actors) 
            && getType().equals(anotherMovie.getType()) 
            && getReleaseDate().equals(anotherMovie.getReleaseDate()) 
            && getPrice() == anotherMovie.getPrice());
    }

    /**
     * Converts all the data contained in a Movie object into a string
     * @return A String containing all the data contained in a Movie object
     */
    public String toString(){
        String theActors = "";
        String theType = "";
        String dollars;
        String cents;
        
        dollars = Integer.toString(getPrice() / 100);
        cents = Integer.toString(getPrice() % 100);
        
        if(cents.length() == 1)
            cents = cents + "0";
        
        if(actors.length() > 0)
            theActors = actors + "; ";
        if(getType().length() > 0)
            theType = getType() + "; ";
        
        return ("Movie: " + getTitle() + "; " + theActors + theType + getReleaseDate().toString() + "; $" + dollars + "." + cents);
    }
    
    /**
     * Converts all of the information in a Movie object into a string that can later be properly parsed by a Movie constructor
     * @return All the information stored in a Movie class, with each field separated by semicolons.
     */
    public String toFormattedString() {
        String dollars;
        String cents;
        
        dollars = Integer.toString(getPrice() / 100);
        cents = Integer.toString(getPrice() % 100);
        
        if(cents.length() == 1)
            cents = cents + "0";
        
        return ("Movie; " + getTitle() + "; " + actors + "; " + getType() + "; " + getReleaseDate().toString() + "; $" + dollars + "." + cents);
    }
}
