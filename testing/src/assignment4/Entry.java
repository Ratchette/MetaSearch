/**
 * Description: Assignment #4
 * @author Jennfier Winer
 * @since 2011-11-11
 * Created: November 11, 2011
 * Last Modified: December 3, 2011  
 */
package assignment4;

/**
 * This class contains the common methods and variables associated with all entries (a superclass). 
 * For assignment #4 I turned it into an abstract class
 * @author Jennifer Winer
 * @see MetaSearch
 */
public abstract class Entry {
    
    private String title;       // a non-null and non-empty String
    private String type;        // no restrictions on the string
    private Date releaseDate;   // (see the restrictions of the Date class)
    private int price;          // a non-negaive number (the price of the item in cents)
    
    
    /**
     * A no-argument constructor
     */
    public Entry() {
        title = "No Title";
        type = "";
        releaseDate = new Date();
        price = 0;
    }
    
    /**
     * A constructor for use with previously divided data.
     * It exists in case the future implementations of MetaSearch divides the data for us.
     * 
     * @param title The title of the Entry
     * @param type The type of the Entry
     * @param releaseDate The date (or approximate date) that the Entry was released
     * @param price The price of the Entry in cents
     * 
     * @throws NullPointerException If the Date object passed in was null
     * @throws Exception If the title, type, releaseDate or price are not within the restrictions of the Entry class
     */
    public Entry(String title, String type, Date releaseDate, int price) throws NullPointerException, Exception{
        if(!validateTitle(title))
            throw new Exception("Invalid title");
        if(!validateType(type))
            throw new Exception("Invalid type");
        if(!validatePrice(price))
            throw new Exception("Invalid price");
        
        this.title = title.trim();
        this.type = type.trim();
        this.releaseDate = releaseDate.clone();
        this.price = price;
    }
    
    /**
     * A constructor for use with previously divided data.
     * It exists in case the future implementations of MetaSearch divides the data for us.
     * 
     * @param title The title of the Entry
     * @param type The type of the Entry
     * @param releaseDate The date (or approximate date) that the Entry was released
     * @param price The price of the Entry in dollars
     * 
     * @throws NullPointerException If the Date object passed in was null
     * @throws Exception If the title, type, releaseDate or price are not within the restrictions of the Entry class
     */
    public Entry(String title, String type, Date releaseDate, double price) throws NullPointerException, Exception{
        if(!validateTitle(title))
            throw new Exception("Invalid title");
        if(!validateType(type))
            throw new Exception("Invalid type");
        if(!validatePrice(price))
            throw new Exception("Invalid price");
        
        this.title = title.trim();
        this.type = type.trim();
        this.releaseDate = releaseDate.clone();
        this.price = parsePrice(price);
    }
    
    /**
     * A constructor for use with an unaltered (undivided) string of userInput (one entire entry)
     * 
     * @param userInput The user's input from the keyboard (a record of an entry in its entirety)
     * @throws NumberFormatException If the day or year of the date is not an integer
     * @throws Exception If the title, type, releaseDate or price are not within the restrictions of the Entry class
     */
    public Entry(String userInput) throws NumberFormatException, Exception{
        String[] tokens;
        String delimiters = "[:;]";
        
        tokens = userInput.split(delimiters);
        
        if(!validateTitle(tokens[1]))
            throw new Exception("Invalid title");
        if(!validateType(tokens[3]))
            throw new Exception("Invalid type");
        if(!validatePrice(tokens[5]))
            throw new Exception("Invalid price");
        
        title = tokens[1].trim();
        type = tokens[3].trim();
        releaseDate = new Date(tokens[4]);
        price = parsePrice(tokens[5]);
    }
    
    /**
     * A constructor for use with tokenized entry input
     * 
     * @param userInput The user's input, previously tokenized by ; and :
     * @throws NumberFormatException If the day or year of the date is not an integer
     * @throws Exception If the title, type, releaseDate or price are not within the restrictions of the Entry class
     */
    public Entry(String[] userInput) throws NumberFormatException, Exception{
        if(!validateTitle(userInput[1]))
            throw new Exception("Invalid title");
        if(!validateType(userInput[3]))
            throw new Exception("Invalid type");
        if(!validatePrice(userInput[5]))
            throw new Exception("Invalid price");
        
        title = userInput[1].trim();
        type = userInput[3].trim();
        releaseDate = new Date(userInput[4]);
        price = parsePrice(userInput[5]);
    }
    
    /**
     * A copy constructor - creates a copy of the the object passed in
     * @param anotherEntry The Entry object that we want a copy of
     * @throws NullPointerException If the Entry object passed in is null
     */
    public Entry(Entry anotherEntry) throws NullPointerException{
        if(anotherEntry == null)
            throw new NullPointerException();
        
        this.title = anotherEntry.title;
        this.type = anotherEntry.type;
        this.releaseDate = anotherEntry.releaseDate.clone();
        this.price = anotherEntry.price;
    }
    
    
    
    /**
     * Gets Title from an Entry object
     * @return The title variable of a Entry 
     */
    public String getTitle(){
        return title;
    }
    
    /**
     * Gets Type from a Entry object
     * @return The type variable of a Entry 
     */
    public String getType(){
        return type;
    }
    
    /**
     * Gets ReleaseDate from a Entry object
     * @return The releaseDate variable of a Entry 
     */
    public Date getReleaseDate(){
        return releaseDate.clone();
    }
    
    /**
     * Gets Price from a Entry object
     * @return The price variable of a Entry 
     */
    public int getPrice(){
        return price;
    }
    
    
    
    /**
     * Sets a value to the title variable of a Entry object
     * @param userInput the String that you would like inserted into the title variable
     * @throws Exception If the title does not meet the requirements of the Entry Class
     */
    public void setTitle(String userInput) throws Exception{
        if(!validateTitle(title))
            throw new Exception("Invalid title");
        
        title = userInput.trim();
    }
    
    /**
     * Sets a value to the type variable of a Entry object
     * @param userInput the String that you would like inserted into the type variable
     * @throws Exception If the type does not meet the requirements of the Entry Class
     */
    public void setType(String userInput) throws Exception{
        if(!validateType(type))
            throw new Exception("Invalid type");
        
        type = userInput.trim();
    }
    
    /**
     * Sets a value to the releaseDate variable of a Entry object
     * @param userInput the String that you would like converted into a Date object, then inserted into the releaseDate variable
     * @throws Exception If the date does not meet the requirements of the Entry Class and the Date class
     */
    public void setReleaseDate(String userInput) throws Exception{
        releaseDate = new Date(userInput);
    }
    
    /**
     * Sets a value to the releaseDate variable of a Entry object
     * @param userInput The Date object that you would like copied into the releaseDate variable
     * @throws NullPointerException If the date passed as a parameter is null
     */
    public void setReleaseDate(Date userInput) throws NullPointerException{
        releaseDate = new Date(userInput);
    }
    
    /**
     * Sets a value to the price variable of a Entry object
     * @param userInput the String that you would like converted into integer cents, then inserted into the price variable
     * @throws NumberFormatException If the String does not contain an integer or a double only.
     * @throws Exception If the price does not meet the requirements of the Entry Class
     */
    public void setPrice(String userInput) throws NumberFormatException, Exception{
        if(!validatePrice(price))
            throw new Exception("Invalid price");
        
        price = parsePrice(userInput);
    }
    
    /**
     * Sets a value to the price variable of a Entry object
     * @param userInput the integer (the price in cents) that you want to insert into the price variable
     * @throws Exception If the price does not meet the requirements of the Entry Class
     */
    public void setPrice(int userInput) throws Exception{
        if(!validatePrice(price))
            throw new Exception("Invalid price");
        
        price = userInput;
    }
   
    /**
     * Sets a value to the price variable of a Entry object
     * @param userInput the double (price in dollars) that you would like converted into integer cents, then inserted into the price variable
     * @throws Exception If the price does not meet the requirements of the Entry Class
     */
    public void setPrice(double userInput) throws Exception{
        if(!validatePrice(price))
            throw new Exception("Invalid price");
        
        price = parsePrice(userInput);
    }

    

    /**
     * Checks to see if the record that the user input adheres to the restrictions of the Entry class
     * @param userInput A String that contains all the information needed to create one record of the Entry class
     * @return true if the record meets all of the requirements of the Entry class, false otherwise
     */
    public static boolean validateEntry(String userInput){
        String[] tokens;
        String delimiters = "[:;]";

        tokens = userInput.split(delimiters);
        return (validateEntry(tokens));
    }
    
    /**
     * Checks to see if the tokens that the user input adheres to the restrictions of the Entry class
     * @param userInput An array of String that contains all the information needed to create one record of the Entry class
     * @return true if the record meets all of the requirements of the Entry class, false otherwise
     */
    public static boolean validateEntry(String[] userInput){
        return (validateTitle(userInput[1]) && validateType(userInput[3]) 
            && validateReleaseDate(userInput[4]) && validatePrice(userInput[5])); 
    }
    
    /**
     * Checks if the input parameters adhere to the restrictions of the Entry class
     * 
     * @param title The title of the Entry
     * @param type The type of Entry
     * @param releaseDate The date that the Entry was released
     * @param price The price of the Entry in cents
     * @return True if all the elements meet the requirements of the Entry class, false otherwise
     */
    public static boolean validateEntry(String title, String type, Date releaseDate, int price){
        return(validateTitle(title) && validateType(type) 
            && validateReleaseDate(releaseDate) && validatePrice(price));
    }
    
    /**
     * Checks if the input parameters adhere to the restrictions of the Entry class
     * 
     * @param title The title of the Entry
     * @param type The type of Entry
     * @param releaseDate The date that the Entry was released
     * @param price The price of the Entry in dollars
     * @return True if all the elements meet the requirements of the Entry class, false otherwise
     */
    public static boolean validateEntry(String title, String type, Date releaseDate, double price){
        return(validateTitle(title) && validateType(type) 
            && validateReleaseDate(releaseDate) && validatePrice(price));
    }
    
    /**
     * Checks to see if the title String adheres to the restrictions of the Entry class
     * @param userInput A String that contains the title variable
     * @return true if the title meets all of the requirements of the Entry class, false otherwise
     */
    public static boolean validateTitle(String userInput){
        if((userInput != null) && (userInput.trim().length() != 0))
            return true;
        else
            return false;
    }
    
    /**
     * Checks to see if the type String adheres to the restrictions of the Entry class
     * @param userInput A String that contains the type variable
     * @return true if the type meets all of the requirements of the Entry class, false otherwise
     */
    public static boolean validateType(String userInput){
        return true;
    }
    
    /**
     * Checks to see if the date String adheres to the restrictions of the Entry and Date classes
     * @param userInput A String that contains the information needed to create a Date object
     * @return true if the date meets all of the requirements of the Entry and the Date classes, false otherwise
     */
    public static boolean validateReleaseDate(String userInput){
        return (Date.validateDate(userInput));
    }
    
    /**
     * Checks to see if the date object adheres to the restrictions of the Entry class
     * @param releaseDate A Date object
     * @return true if the date meets all of the requirements of the Entry class, false otherwise
     */
    public static boolean validateReleaseDate(Date releaseDate){
        if(releaseDate != null)
            return true;
        else
            return false;
    }
    
    /**
     * Checks if the price string adheres to the price restrictions of the Entry class
     * I re-used one line of the professor's code - the matches method with its delimiters (then improved on it)
     * 
     * @param userInput The string that contains the price of the object in dollars
     * @return true if the price meets all of the requirements of the Entry class, false otherwise
     */
    public static boolean validatePrice(String userInput){
        if((userInput != null) && (userInput.trim().matches("(\\$)?[0-9]+(\\.)?([0-9]{0,2})?")))
            return (validatePrice(parsePrice(userInput)));
        else
            return false;
    }
    
    /**
     * Checks if the input price in cents adheres to the price restrictions of the Entry class
     * @param price The price of the object in cents
     * @return true if the price meets all of the requirements of the Entry class, false otherwise
     */
    public static boolean validatePrice(int price){
        return (price >= 0);
    }
    
    /**
     * Checks if the input price in cents adheres to the price restrictions of the Entry class
     * @param price The price of the object in dollars
     * @return true if the price meets all of the requirements of the Entry class, false otherwise
     */
    public static boolean validatePrice(double price){
        return (validatePrice(parsePrice(price)));
    }
    
    
    
    /**
     * Converts the string representation of the price into an integer representation (in cents)
     * @param dollars A string containing the price in dollars
     * @return The price in cents
     * @throws NumberFormatException If the string does not contain only an integer or only a double
     */
    public static int parsePrice(String dollars) throws NumberFormatException{
        String[] dividedPrice;
        int cents = 0;
        int index = 0;
        
        dividedPrice = dollars.trim().split("[$., ]+");
        
        // if the first token is an empty string, discard it
        if(dividedPrice[0].trim().length() == 0)
            index++;
        
        cents = 100 * Integer.parseInt(dividedPrice[index]);
        // if there is a cents portion to the price as well
        if(dividedPrice.length > index + 1)
            cents = cents + Integer.parseInt(dividedPrice[index + 1]);
        
        return cents;
    }
    
    /**
     * Converts a double representation of the price in dollars into an integer representation (in cents)
     * @param dollars The price of the object in dollars
     * @return The price in cents
     */
    public static int parsePrice(double dollars){
        int cents = 0;
        
        cents = (int)Math.round(dollars * 100);
        return cents;
    }
        
    
    
    /**
     * Creates a deep copy of the calling Entry object
     * I chose to make this an abstract method because I would never want to create an object of this class - let alone make a copy of an object of this class!
     * @return An exact copy of the calling Book 
     */
    public abstract Entry clone();
    
    /**
     * Compares two object of the Entry class to see if they contain identical data
     * @param anotherObject The Object that you wish to compare your current Entry to
     * @return True if the two Entry objects are identical, false otherwise
     */
    public boolean equals(Object anotherObject){
        if(anotherObject == null)
            return false;
        if(getClass() != anotherObject.getClass())
            return false;
        
        Entry anotherEntry = (Entry)anotherObject;
        return (title.equals(anotherEntry.title) 
            && type.equals(anotherEntry.type) 
            && releaseDate.equals(anotherEntry.releaseDate) 
            && price == anotherEntry.price);
    }

    // my late binding / polymorphism example
    /**
     * Converts all the data contained in a Entry object into a string
     * @return A String containing all the data contained in a Entry object
     */
    public String toString(){
        String theType = "";
        String dollars;
        String cents;
        
        dollars = Integer.toString(price / 100);
        cents = Integer.toString(price % 100);
        
        if(cents.length() == 1)
            cents = cents + "0";
        
        if(type != null)
            theType = type + "; ";
        
        return ("Entry: " + title + "; " + theType + releaseDate.toString() + "; $" + dollars + "." + cents);
    }
    
    // my abstract method example (There is no point in defining this method in the Entry class as we would never want to create an object of this class - let alone save an Entry object to a file!)
    /**
     * A variant of the toString method for writing to files
     * @return All of the information in an object of a subclass of Entry (formatted in a way that It can be easily read in from files)
     */
    public abstract String toFormattedString();
}
