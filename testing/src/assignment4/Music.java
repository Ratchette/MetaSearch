/**
 * Description: Assignment #4
 * @author Jennfier Winer
 * @since 2011-10-17
 * Created: October 17, 2011
 * Last Modified: December 3, 2011  
 */
package assignment4;

/**
 * This class contains or inherits all of the methods and variables associated with Music. 
 * This class is a subclass of the Entry Class
 * 
 * @author Jennifer
 * @see Entry
 */
public class Music extends Entry{
    private String company;     // no restrictions
    
    /**
     * A no-argument constructor
     */
    public Music(){
        super();
        company = "";
    }
    
    /**
     * A constructor for use with previously divided data.
     * It exists in case the future implementations of MetaSearch divides the data for us.
     * 
     * @param title The title of the music
     * @param company The company that produced the music
     * @param type The type of music
     * @param releaseDate The date (or approximate date) that the music was released
     * @param price The price of the music in cents
     * 
     * @throws NullPointerException If the Date object passed in was null
     * @throws Exception If the title, company, type, releaseDate or price are not within the restrictions of the Music class
     */
    public Music(String title, String company, String type, Date releaseDate, int price) throws NullPointerException, Exception{
        super(title, type, releaseDate, price);
        
        if(!validateCompany(company))
            throw new Exception("Invalid compnay");
        
        this.company = company.trim();
    }
    
    /**
     * A constructor for use with previously divided data.
     * It exists in case the future implementations of MetaSearch divides the data for us.
     * 
     * @param title The title of the music
     * @param company The company that produced the music
     * @param type The type of music
     * @param releaseDate The date (or approximate date) that the music was released
     * @param price The price of the music in dollars
     * 
     * @throws NullPointerException If the Date object passed in was null
     * @throws Exception If the title, company, type, releaseDate or price are not within the restrictions of the Music class
     */
    public Music(String title, String company, String type, Date releaseDate, double price) throws NullPointerException, Exception{
        super(title, type, releaseDate, price);
        
        if(!validateCompany(company))
            throw new Exception("Invalid compnay");
        
        this.company = company.trim();
    }
    
    /**
     * A constructor for use with an unaltered (undivided) string of userInput (one entire record)
     * 
     * @param userInput The user's input from the keyboard (a record of a album in its entirety)
     * @throws NumberFormatException If the day or year of the date is not an integer
     * @throws Exception If the title, company, type, releaseDate or price are not within the restrictions of the Music class
     */
    public Music(String userInput) throws NumberFormatException, Exception{
        super(userInput);
        
        String[] tokens = userInput.split("[:;]");
        if(!validateCompany(tokens[2]))
            throw new Exception("Invalid compnay");
        
        company = tokens[2].trim();
    }
    
    /**
     * A constructor for use with tokenized record input
     * 
     * @param userInput The user's input, previously tokenized by ; and :
     * @throws NumberFormatException If the day or year of the date is not an integer
     * @throws Exception If the title, company, type, releaseDate or price are not within the restrictions of the Music class
     */
    public Music(String[] userInput) throws NumberFormatException, Exception{
        super(userInput);
        
        if(!validateCompany(userInput[2]))
            throw new Exception("Invalid compnay");
        
        company = userInput[2].trim();
    }
    
    /**
     * A copy constructor - creates a copy of the the object passed in
     * @param anotherAlbum The Music object that we want a copy of
     * @throws NullPointerException If the Music object passed in is null
     */
    public Music(Music anotherAlbum) throws NullPointerException{
        super(anotherAlbum);
        this.company = anotherAlbum.company;
    }
    
    
    
    /**
     * Gets Company from a Music object
     * @return The company variable of a Music 
     */
    public String getCompany(){
        return company;
    }
    
    /**
     * Sets a value to the company variable of a Music object
     * @param userInput the String that you would like inserted into the company variable
     * @throws Exception If the String does not meet the requirements of the company in the Music Class
     */
    public void setCompany(String userInput) throws Exception{
        if(!validateCompany(userInput))
            throw new Exception("Invalid compnay");
        
        company = userInput.trim();
    }


    
    /**
     * Checks to see if the record that the user input adheres to the restrictions of the Music class
     * @param userInput A String that contains all the information needed to create one record of the Music class
     * @return true if the record meets all of the requirements of the Music class, false otherwise
     */
    public static boolean validateMusic(String userInput){
        String[] tokens;
        String delimiters = "[:;]";

        tokens = userInput.split(delimiters);
        return (validateMusic(tokens));
    }
    
    /**
     * Checks to see if the tokens that the user input adheres to the restrictions of the Music class
     * @param userInput An array of String that contains all the information needed to create one record of the Music class
     * @return true if the record meets all of the requirements of the Music class, false otherwise
     */
    public static boolean validateMusic(String[] userInput){
        return (validateEntry(userInput) && validateCompany(userInput[2]));
    }
    
    /**
     * Checks if the input parameters adhere to the restrictions of the Music class
     * 
     * @param title The title of the music
     * @param company The company that produced the music
     * @param type The type of music
     * @param releaseDate The date that the music was released
     * @param price The price of the music in cents
     * @return True if all the elements meet the requirements of the Music class, false otherwise
     */
    public static boolean validateMusic(String title, String company, String type, Date releaseDate, int price){
        return(validateEntry(title, type, releaseDate, price) && validateCompany(company));
    }
    
    /**
     * Checks if the input parameters adhere to the restrictions of the Music class
     * 
     * @param title The title of the music
     * @param company The company that produced the music
     * @param type The type of music
     * @param releaseDate The date that the music was released
     * @param price The price of the music in dollars
     * @return True if all the elements meet the requirements of the Music class, false otherwise
     */
    public static boolean validateMusic(String title, String company, String type, Date releaseDate, double price){
        return(validateEntry(title, type, releaseDate, price) && validateCompany(company));
    }
    
    /**
     * Checks to see if the company String adheres to the restrictions of the Music class
     * @param userInput A String that contains the company variable
     * @return true if the company meets all of the requirements of the Music class, false otherwise
     */
    public static boolean validateCompany(String userInput){
        return true;
    }
    
    
    
    /**
     * Creates a copy of the calling Music
     * @return An exact deep copy of the calling Music object 
     * @throws NullPointerException If the Music object passed in is null
     */
    public Music clone() throws NullPointerException{
        return new Music(this);
    }
    
    /**
     * Compares two object of the Music class to see if they contain identical data
     * @param anotherObject The Object that you wish to compare your current Music to
     * @return True if the objects are two identical Music objects, false otherwise
     */
    public boolean equals(Object anotherObject){
        if(anotherObject == null)
            return false;
        if(getClass() != anotherObject.getClass())
            return false;
        
        Music anotherAlbum = (Music)anotherObject;
        return (getTitle().equals(anotherAlbum.getTitle())
            && company.equals(anotherAlbum.company) 
            && getType().equals(anotherAlbum.getType()) 
            && getReleaseDate().equals(anotherAlbum.getReleaseDate()) 
            && getPrice() == anotherAlbum.getPrice());
    }

    /**
     * Converts all the data contained in a Music object into a string
     * @return A String containing all the data contained in a Music object
     */
    public String toString(){
        String theCompany = "";
        String theType = "";
        String dollars;
        String cents;
        
        dollars = Integer.toString(getPrice() / 100);
        cents = Integer.toString(getPrice() % 100);
        
        if(cents.length() == 1)
            cents = cents + "0";
        
        if(company.length() > 0)
            theCompany = company + "; ";
        if(getType().length() > 0)
            theType = getType() + "; ";
        
        return ("Music: " + getTitle() + "; " + theCompany + theType + getReleaseDate().toString() + "; $" + dollars + "." + cents);
    }
    
    /**
     * Converts all of the information in a Music object into a string that can later be properly parsed by a Music constructor
     * @return All the information stored in a Music class, with each field separated by semicolons.
     */
    public String toFormattedString() {
        String dollars;
        String cents;
        
        dollars = Integer.toString(getPrice() / 100);
        cents = Integer.toString(getPrice() % 100);
        
        if(cents.length() == 1)
            cents = cents + "0";
        
        return ("Music; " + getTitle() + "; " + company + "; " + getType() + "; " + getReleaseDate().toString() + "; $" + dollars + "." + cents);
    }
}
