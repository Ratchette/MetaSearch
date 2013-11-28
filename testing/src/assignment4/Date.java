/**
 * Description: Assignment #4
 * @author Walter Savitch
 * @since 2011-10-17
 * Created: October 17, 2011
 * Last Modified: December 3, 2011 
 * Notes: The majority of this file was taken from Absolute Java, 4th edition by Walter Savitch.
 *        Anything that was created or augmented by me has been specified as so in its comment
 */
package assignment4;

/**
 * This class contains all of the methods and variables associated with Dates (modified by me)
 * @author Jennifer
 */
public class Date{
    
    private int month;      // a number from 0 to 12
    private int day;        // a number from 0 to 31
    private int year;       // a number from 1000 to 9999
    
    /**
     * A no argument constructor for the Date class (modified from textbook)
     * @author Jennifer Winer
     */
    public Date(){
        month = 0;
        day = 0;
        year = 1000;
    }
    
    /**
     * A constructor for a String that contains all the information to initialize a Date class (created by me)
     * @author Jennifer Winer 
     * @param userInput The string containing the information to create a Date class
     * @throws NumberFormatException If the day or year fields are not integers (thrown originally from Integer.parseInt())
     * @throws Exception If the month, day or year is not within the given range
     */
    public Date(String userInput) throws NumberFormatException, Exception{
        String[] tokens;
        String delimiters = "[ ,:;.]+";
        int index = 0;
        
        tokens = userInput.trim().split(delimiters);
            
        // if the first token is an empty string
        if(tokens[0].trim().length() == 0)
            index++;
        
        // if there is only one token then it must be the year
        if(tokens.length == index + 1){
             if((Integer.parseInt(tokens[index]) < 1000) || (Integer.parseInt(tokens[index]) > 9999))
                throw new Exception("Invalid year");

            this.month = 0;
            this.day = 0;
            this.year = Integer.parseInt(tokens[index]);
        }
        // if two tokens are entered then it must be the month and then the year (day makes no sense....)
        else if(tokens.length == index + 2){
            if(!monthOK(tokens[index]))
                throw new Exception("Invalid month");
            if((Integer.parseInt(tokens[index + 1]) < 1000) || (Integer.parseInt(tokens[index + 1]) > 9999))
                throw new Exception("Invalid year");

            this.month = monthInt(tokens[index]);
            this.day = 0;
            this.year = Integer.parseInt(tokens[index + 1]);
        }
        // if the user entered a day a month and a year
        else if(tokens.length == index + 3){
            if(!monthOK(tokens[index]))
                throw new Exception("Invalid month");
            if((Integer.parseInt(tokens[index + 1]) < 0) || (Integer.parseInt(tokens[index + 1]) > 31))
                throw new Exception("Invalid day");
            if((Integer.parseInt(tokens[index + 2]) < 1000) || (Integer.parseInt(tokens[index + 2]) > 9999))
                throw new Exception("Invalid year");

            this.month = monthInt(tokens[index]);
            this.day = Integer.parseInt(tokens[index + 1]);
            this.year = Integer.parseInt(tokens[index + 2]);
        }
            else
                throw new Exception("Invalid number of words in your date constructor");
    }
    
    /**
     * A constructor that takes the values with which to initialize the date class as integers (modified from textbook)
     * @author Jennifer Winer
     * @param monthInt The month, as a number from 0 to 12
     * @param day The day as a number from 0 to 31
     * @param year The year as a number from 1000 to 9999
     * @throws Exception If the month, day or year is not within the given range
     */
    public Date(int monthInt, int day, int year) throws Exception{
        if((monthInt < 0) || (monthInt > 12))
            throw new Exception("Invalid month");
        if((day < 0) || (day > 31))
            throw new Exception("Invalid day");
        if((year < 1000) || (year > 9999))
            throw new Exception("Invalid year");
            
        this.month = monthInt;
        this.day = day;
        this.year = year;
        
    }
    
    /**
     * A constructor that takes the values with which to initialize the date class as integers (modified from textbook)
     * @author Jennifer Winer
     * @param monthString The month as a 3 letter abreviation, or the month spelled out in full
     * @param day The day as a number from 0 to 31
     * @param year The year as a number from 1000 to 9999
     * @throws Exception If the month, day or year is not within the given range
     */
    public Date(String monthString, int day, int year) throws Exception{
        if(!monthOK(monthString))
            throw new Exception("Invalid month");
        if((day < 0) || (day > 31))
            throw new Exception("Invalid day");
        if((year < 1000) || (year > 9999))
            throw new Exception("Invalid year");
        
        this.month = monthInt(monthString.trim());
        this.day = day;
        this.year = year;
    }
    
    /**
     * A constructor that creates a Date class from only the year. 
     * The day and month are initialized to 0 (modified from textbook)
     * @author Jennifer Winer
     * @param year The year as a number from 1000 to 9999
     * @throws Exception If the month, day or year is not within the given range
     */
    public Date(int year) throws Exception{
        this(0, 0, year);
    }
    
    /**
     * a copy constructor (modified from the textbook)
     * @author Jennifer Winer
     * @param aDate The Date object that we would like to create a copy of
     * @throws NullPointerException If the passed parameter is null 
     */
    public Date(Date aDate) throws NullPointerException{
        if(aDate == null)
            throw new NullPointerException("The date you wish to copy must not be null");
        
        month = aDate.month;
        day = aDate.day;
        year = aDate.year;
    }
    
    
    
    /**
     * Sets the variables inside a Date object the the parameters given (modified from textbook)
     * @author Jennifer Winer
     * @param monthInt The month, as a number from 0 to 12
     * @param day The day as a number from 0 to 31
     * @param year The year as a number from 1000 to 9999
     * @throws Exception If the month, day or year is not within the given range
     */
    public void setDate(int monthInt, int day, int year) throws Exception{
        if((monthInt < 0) || (monthInt > 12))
            throw new Exception("Invalid month");
        if((day < 0) || (day > 31))
            throw new Exception("Invalid day");
        if((year < 1000) || (year > 9999))
            throw new Exception("Invalid year");
            
        this.month = monthInt;
        this.day = day;
        this.year = year;
    }
    
    /**
     * Sets the variables inside a Date object the the parameters given (modified from textbook)
     * @author Jennifer Winer
     * @param monthString The month as a 3 letter abreviation, or the month spelled out in full
     * @param day The day as a number from 0 to 31
     * @param year The year as a number from 1000 to 9999
     * @throws Exception If the month, day or year is not within the given range
     */
    public void setDate(String monthString, int day, int year) throws Exception{
        if(!monthOK(monthString))
            throw new Exception("Invalid month");
        if((day < 0) || (day > 31))
            throw new Exception("Invalid day");
        if((year < 1000) || (year > 9999))
            throw new Exception("Invalid year");
        
        this.month = monthInt(monthString.trim());
        this.day = day;
        this.year = year;
    }
    
    /**
     * Sets the variables inside a Date object the the parameters given (modified from textbook)
     * The year is set to the year parameter, and month and day are set to 0
     * @author Jennifer Winer
     * @param year The year as a number between 1000 and 9999
     * @throws Exception If the year is not within the given range
     */
    public void setDate(int year) throws Exception{
        setDate(0, 0, year);
    }
    
    /**
     * Sets the year member of the Date object to the input parameter (modified from textbook)
     * @author Jennifer Winer
     * @param year The year as a number between 1000 and 9999
     * @throws Exception If the year is not within the given range
     */
    public void setYear(int year) throws Exception{
        if((year < 1000) || (year > 9999))
            throw new Exception("Invalid year");
        
        this.year = year;
    }
    
    /**
     * Sets the month member of a Date object to the parameter (modified from textbook)
     * @author Jennifer Winer
     * @param monthNumber The month, as a number from 0 to 12 
     * @throws Exception If the month is not within the given range
     */
    public void setMonth(int monthNumber) throws Exception{
        if((monthNumber < 0) || (monthNumber > 12))
            throw new Exception("Invalid month");
        
        this.month = monthNumber;
    }
    
    /**
     * Sets the month member of a Date object to the parameter converted into its integer representation (modified from textbook)
     * @author Jennifer Winer
     * @param monthString The month as a 3 letter abreviation, or the month spelled out in full
     * @throws Exception If the month is not within the given range
     */
    public void setMonth(String monthString) throws Exception{
        if(!monthOK(monthString))
            throw new Exception("Invalid month");
        
        this.month = monthInt(monthString);
    }
    
    /**
     * Sets the day member of a Date object to the input parameter (modified from textbook)
     * @author Jennifer Winer
     * @param day The day as a number from 0 to 31
     * @throws Exception If the day is not within the given range
     */
    public void setDay(int day) throws Exception{
        if((day < 0) || (day > 31))
            throw new Exception("Invalid day");
        
        this.day = day;
    }
    
    
    
    /**
     * Gets the month member of a Date object (modified from textbook)
     * @author Jennifer Winer
     * @return The month as a String (the month spelled out in full)
     */
    public String getMonth(){
        return monthString(month);
    }
    
    /**
     * Gets the day member of a Date object
     * @return They day as an integer between 0 and 12
     */
    public int getDay(){
        return day;
    }
    
    /**
     * Gets the year member of a Date object 
     * @return The year as an integer between 1000 and 9999
     */
    public int getYear(){
        return year;
    }
    
    
    
    /**
     * Compares two Date objects to see if the calling Date object is older then the parameter Date object (modified from textbook)
     * @author Jennifer Winer
     * @param anotherDate The Date object that the calling Date object is compared to
     * @return True if the calling object is older then the parameter object, false otherwise
     */
    public boolean preceeds(Date anotherDate){
        return ((year < anotherDate.year) || (year == anotherDate.year && month < anotherDate.month) || (year == anotherDate.year && month == anotherDate.month && day < anotherDate.day));
    }
    
    /**
     * Converts a 3 letter abreviation of a month or a month written out in full to its corresponding integer value (created by me)
     * @author Jennifer Winer
     * @param month The String containing the month to be converted
     * @return The integer representation of the input month parameter. If the month does match any of the Gregorian months, then 0 is returned.
     */
    private int monthInt(String month){
        month = month.trim();
        
        if(month.matches("[0-9]+"))
            return Integer.parseInt(month);
        
        if(month.equalsIgnoreCase("January") || month.equalsIgnoreCase("Jan"))
            return 1;
        if(month.equalsIgnoreCase("February") || month.equalsIgnoreCase("Feb"))
            return 2;
        if(month.equalsIgnoreCase("March") || month.equalsIgnoreCase("Mar"))
            return 3;
        if(month.equalsIgnoreCase("April") || month.equalsIgnoreCase("Apr"))
            return 4;
        if(month.equalsIgnoreCase("May"))
            return 5;
        if(month.equalsIgnoreCase("June") || month.equalsIgnoreCase("Jun"))
            return 6;
        if(month.equalsIgnoreCase("July") || month.equalsIgnoreCase("Jul"))
            return 7;
        if(month.equalsIgnoreCase("August") || month.equalsIgnoreCase("Aug"))
            return 8;
        if(month.equalsIgnoreCase("September") || month.equalsIgnoreCase("Sep"))
            return 9;
        if(month.equalsIgnoreCase("October") || month.equalsIgnoreCase("Oct"))
            return 10;
        if(month.equalsIgnoreCase("November") || month.equalsIgnoreCase("Nov"))
            return 11;
        if(month.equalsIgnoreCase("December") || month.equalsIgnoreCase("Dec"))
            return 12;
        else 
            return 0;
    }
    
    /**
     * Converts the integer representing a month into full name (modified from textbook)
     * @author Jennifer Winer
     * @param month The integer representing a month
     * @return The full name of the month input. If the integer does not match any month, then an empty string is returned
     */
    private String monthString(int month){
        switch (month){
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "";
        }
    }
    
    /**
     * Validate if a String adheres to all of the requirements to create a Date object (created by me)
     * @author Jennifer Winer
     * @param userInput A String that we would like to eventually convert to a Date object
     * @return True if all the String meet the requirements of the Date class
     */
    public static boolean validateDate(String userInput){
        String[] tokens;
        String delimiters = "[ ,:;.]+";
        
        tokens = userInput.trim().split(delimiters);
        
        return(validateDate(tokens));
               
    }
    
    /**
     * Validate if a Series of tokens (Strings) adheres to all of the requirements to create a Date object (created by me)
     * @author Jennifer Winer
     * @param userInput A String array (group of tokens) that we would like to eventually convert to a Date object
     * @return True if all the String meet the requirements of the Date class
     */
    public static boolean validateDate(String[] userInput){
        
        // if the first token is an empty string
        if(userInput[0].trim().length() == 0){
            if(userInput.length == 2)
                return (dateOK(null, null, userInput[1]));
            else if(userInput.length == 3)
                return (dateOK(userInput[1], null, userInput[2]));
            else if(userInput.length == 4)
                return (dateOK(userInput[1], userInput[2], userInput[3]));
        }
        
        // if the first token was not an empty string
        else{
            if(userInput.length == 1)
                return (dateOK(null, null, userInput[0]));
            else if(userInput.length == 2)
                return (dateOK(userInput[0], null, userInput[1]));
            else if(userInput.length == 3)
                return (dateOK(userInput[0], userInput[1], userInput[2]));
        }
        return false;
    }
    
    /**
     * Checks if the input parameters adhere to the restrictions of the Date class (modified from the textbook)
     * @author Jennifer Winer
     * @param monthInt The month as a number
     * @param day The day as a number
     * @param year The year as a number
     * @return True if ALL the parameters meet all of the requirements of the Date class
     */
    private static boolean dateOK(int monthInt, int day, int year){
        return ((monthInt >= 0) && (monthInt <= 12) && (day >= 0) && (day <= 31) && (year >= 1000) && (year <= 9999));
    }
    
    /**
     * Checks if the input parameters adhere to the restrictions of the Date class (modified from the textbook)
     * @author Jennifer Winer
     * @param monthString The month as a 3 letter abreviation, or the month spelled out in full
     * @param day The day as a number
     * @param year The year as a number
     * @return True if ALL the parameters meet all of the requirements of the Date class
     */
    private static boolean dateOK(String monthString, int day, int year){
        return(monthOK(monthString) && (day >= 0) && (day <= 31) && (year >= 1000) && (year <= 9999));
    }
    
    /**
     * Checks if the input parameters adhere to the restrictions of the Date class (created by me)
     * @author Jennifer Winer
     * @param month The month as a String
     * @param day The day as an String
     * @param year The year as a String
     * @return True if ALL the parameters meet all of the requirements of the Date class
     */
    private static boolean dateOK(String month, String day, String year){
        if(year.trim().matches("[0-9]+")){
            if(day == null){
                if(month == null){
                    return dateOK(0, 0, Integer.parseInt(year));
                }
                return dateOK(month, 0, Integer.parseInt(year));
            }
            else if(day.trim().matches("[0-9]+"))
                return dateOK(month, Integer.parseInt(day), Integer.parseInt(year));
        }
        return false;
    }
    
    /**
     * Checks to see if the string contains one of the 12 Gregorian months (modified from textbook)
     * @author Jennifer Winer
     * @param month The String that we want to check is a month
     * @return True if the String contains one of the 12 Gregorian month, false otherwise
     */
    private static boolean monthOK(String month){
        if(month.trim().matches("[0-9]+"))
            return((Integer.parseInt(month) >= 0) && (Integer.parseInt(month) <= 12));
        else
            return(month.equalsIgnoreCase("January") || month.equalsIgnoreCase("Jan") ||
                   month.equalsIgnoreCase("February") || month.equalsIgnoreCase("Feb") ||
                   month.equalsIgnoreCase("March") || month.equalsIgnoreCase("Mar") ||
                   month.equalsIgnoreCase("April") || month.equalsIgnoreCase("Apr") ||
                   month.equalsIgnoreCase("May") || 
                   month.equalsIgnoreCase("June") || month.equalsIgnoreCase("Jun") || 
                   month.equalsIgnoreCase("July") || month.equalsIgnoreCase("Jul") || 
                   month.equalsIgnoreCase("August") || month.equalsIgnoreCase("Aug") || 
                   month.equalsIgnoreCase("September") || month.equalsIgnoreCase("Sep") || 
                   month.equalsIgnoreCase("October") || month.equalsIgnoreCase("Oct") || 
                   month.equalsIgnoreCase("November") || month.equalsIgnoreCase("Nov") || 
                   month.equalsIgnoreCase("December") || month.equalsIgnoreCase("Dec"));
    }
    
    
    /**
     * Creates a copy of the calling Date
     * @return An exact deep copy of the calling Date object
     * @throws NullPointerException If the calling Date was null
     */
    public Date clone() throws NullPointerException{
        return new Date(this);
    }
    
    /**
     * Converts all of the data stored in a Date object into a String (created by me)
     * @author Jennifer Winer
     * @return The String representation of all of the members of the calling Date object
     */
    public String toString(){
        String theMonth = "";
        String theDay = "";
        
        if(month != 0)
            theMonth = monthString(month) + " ";
        if(day != 0)
            theDay = day + ", ";
        
        return (theMonth + theDay + year);
    }
    
    /**
     * Compares two Date objects to see if the contain identical data (created by me)
     * @author Jennifer Winer
     * @param otherDate The Date that you want to compare the calling Date object to
     * @return True if the two Date objects contain the same data, false otherwise
     */
    public boolean equals(Object anotherObject){
    	if(anotherObject == null)
            return false;
        if(getClass() != anotherObject.getClass())
            return false;
        
        Date otherDate = (Date) anotherObject;
        return this.getMonth().equals(otherDate.getMonth())
        		&& this.getDay() == otherDate.getDay()
        		&& this.getYear() == otherDate.getYear();
    }
    
}