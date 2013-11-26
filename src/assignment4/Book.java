/**
 * Description: Assignment #4
 * @author Jennfier Winer
 * @since 2011-10-17
 * Created: October 17, 2011
 * Last Modified: December 3, 2011 
 */
package assignment4;

/**
 * This class contains or inherits all of the methods and variables associated with Books. 
 * This class is a subclass of the Entry Class
 * 
 * @author Jennifer 
 * @see Entry
 */
public class Book extends Entry{
    private String author;      // no restrictions
    
    /**
     * A no-argument constructor
     */
    public Book(){
        super();
        author = "";
    }
    
    /**
     * A constructor for use with previously divided data.
     * It exists in case the future implementations of MetaSearch divides the data for us.
     * 
     * @param title The title of the book
     * @param author The author of the book
     * @param type The type of book (eg. paperback)
     * @param releaseDate The date (or approximate date) that the book was released
     * @param price The price of the book in dollars
     * 
     * @throws NullPointerException If the Date object passed in was null
     * @throws Exception If the title, author, type, releaseDate or price are not within the restrictions of the Book class
     */
    public Book(String title, String author, String type, Date releaseDate, int price) throws NullPointerException, Exception{
        super(title, type, releaseDate, price);
        
        if(!validateAuthor(author))
            throw new Exception("Invalid author");
        
        this.author = author.trim();
    }
    
    /**
     * A constructor for use with previously divided data.
     * It exists in case the future implementations of MetaSearch divides the data for us.
     * 
     * @param title The title of the book
     * @param author The author of the book
     * @param type The type of book (eg. paperback)
     * @param releaseDate The date (or approximate date) that the book was released
     * @param price The price of the book in cents
     * 
     * @throws NullPointerException If the Date object passed in was null
     * @throws Exception If the title, author, type, releaseDate or price are not within the restrictions of the Book class
     */
    public Book(String title, String author, String type, Date releaseDate, double price) throws NullPointerException, Exception{
        super(title, type, releaseDate, price);
        
        if(!validateAuthor(author))
            throw new Exception("Invalid author");
        
        this.author = author.trim();
    }
    
    /**
     * A constructor for use with an unaltered (undivided) string of userInput (one entire record)
     * 
     * @param userInput The user's input from the keyboard (a record of a book in its entirety)
     * @throws NumberFormatException If the day or year of the date is not an integer
     * @throws Exception If the title, author, type, releaseDate or price are not within the restrictions of the Book class
     */
    public Book(String userInput) throws NumberFormatException, Exception{
        super(userInput);
        
        String[] tokens = userInput.split("[:;]");
        if(!validateAuthor(tokens[2]))
            throw new Exception("Invalid author");
        
        author = tokens[2].trim();
    }
    
    /**
     * A constructor for use with tokenized record input
     * 
     * @param userInput The user's input, previously tokenized by ; and :
     * @throws NumberFormatException If the day or year of the date is not an integer
     * @throws Exception If the title, author, type, releaseDate or price are not within the restrictions of the Book class
     */
    public Book(String[] userInput) throws NumberFormatException, Exception{
        super(userInput);
        
        if(!validateAuthor(userInput[2]))
            throw new Exception("Invalid author");
        
        author = userInput[2].trim();
    }
    
    /**
     * A copy constructor - creates a copy of the the object passed in
     * @param anotherBook The Book object that we want a copy of
     * @throws NullPointerException If the Book object passed in is null
     */
    public Book(Book anotherBook) throws NullPointerException{
        super(anotherBook);
        this.author = anotherBook.author;
    }
    
    
    
    /**
     * Gets Author from a Book object
     * @return The author variable of a Book 
     */
    public String getAuthor(){
        return author;
    }
    
    /**
     * Sets a value to the Author variable of a Book object
     * @param userInput the String that you would like inserted into the author variable
     * @throws Exception If the String does not meet the requirements of the company in the Book Class
     */
    public void setAuthor(String userInput) throws Exception{
        if(!validateAuthor(userInput))
            throw new Exception("Invalid author");
            
        author = userInput.trim();
    }

    

    /**
     * Checks to see if the record that the user input adheres to the restrictions of the Book class
     * @param userInput A String that contains all the information needed to create one record of the Book class
     * @return true if the record meets all of the requirements of the Book class, false otherwise
     */
    public static boolean validateBook(String userInput){
        String[] tokens;
        String delimiters = "[:;]";

        tokens = userInput.split(delimiters);
        return (validateBook(tokens));
    }
    
    /**
     * Checks to see if the tokens that the user input adheres to the restrictions of the Book class
     * @param userInput An array of String that contains all the information needed to create one record of the Book class
     * @return true if the record meets all of the requirements of the Book class, false otherwise
     */
    public static boolean validateBook(String[] userInput){
        return (validateEntry(userInput) && validateAuthor(userInput[2]));
    }
    
    /**
     * Checks if the input parameters adhere to the restrictions of the Book class
     * 
     * @param title The title of the book
     * @param author The author of the book
     * @param type The type of book
     * @param releaseDate The date that the book was released
     * @param price The price of the book in cents
     * @return True if all the elements meet the requirements of the Book class, false otherwise
     */
    public static boolean validateBook(String title, String author, String type, Date releaseDate, int price){
        return(validateEntry(title, type, releaseDate, price) && validateAuthor(author));
    }
    
    /**
     * Checks if the input parameters adhere to the restrictions of the Book class
     * 
     * @param title The title of the book
     * @param author The author of the book
     * @param type The type of book
     * @param releaseDate The date that the book was released
     * @param price The price of the book in dollars
     * @return True if all the elements meet the requirements of the Book class, false otherwise
     */
    public static boolean validateBook(String title, String author, String type, Date releaseDate, double price){
        return(validateEntry(title, type, releaseDate, price) && validateAuthor(author));
    }
    
    /**
     * Checks to see if the author String adheres to the restrictions of the Book class
     * @param userInput A String that contains the author variable
     * @return true if the author meets all of the requirements of the Book class, false otherwise
     */
    public static boolean validateAuthor(String userInput){
        return true;
    }
        


    /**
     * Creates a copy of the calling Book
     * @return An exact deep copy of the calling Book object 
     * @throws NullPointerException If the Book object passed in is null
     */
    public Book clone() throws NullPointerException{
        return new Book(this);
    }
    
    /**
     * Compares two objects of the Book class to see if they contain identical data
     * @param anotherObject The Object that you wish to compare your current Book to
     * @return True if the objects are two identical Book objects, false otherwise
     */
    public boolean equals(Object anotherObject){
        if(anotherObject == null)
            return false;
        if(getClass() != anotherObject.getClass())
            return false;
        
        Book anotherBook = (Book)anotherObject;
        return (getTitle().equals(anotherBook.getTitle())
            && author.equals(anotherBook.author)
            && getType().equals(anotherBook.getType()) 
            && getReleaseDate().equals(anotherBook.getReleaseDate()) 
            && getPrice() == anotherBook.getPrice());
    }

    /**
     * Converts all the data contained in a Book object into a string
     * @return A String containing all the data contained in a Book object
     */
    public String toString(){
        String theAuthor = "";
        String theType = "";
        String dollars;
        String cents;
        
        dollars = Integer.toString(getPrice() / 100);
        cents = Integer.toString(getPrice() % 100);
        
        if(cents.length() == 1)
            cents = cents + "0";
        
        if(author.length() > 0)
            theAuthor = author + "; ";
        if(getType().length() > 0)
            theType = getType() + "; ";
        
        return ("Book: " + getTitle() + "; " + theAuthor + theType + getReleaseDate().toString() + "; $" + dollars + "." + cents);
    }

    /**
     * Converts all of the information in a Book object into a string that can later be properly parsed by a Book constructor
     * @return All the information stored in a Book class, with each field separated by semicolons.
     */
    public String toFormattedString() {
        String dollars;
        String cents;
        
        dollars = Integer.toString(getPrice() / 100);
        cents = Integer.toString(getPrice() % 100);
        
        if(cents.length() == 1)
            cents = cents + "0";
        
        return ("Book; " + getTitle() + "; " + author + "; " + getType() + "; " + getReleaseDate().toString() + "; $" + dollars + "." + cents);
    }
}
