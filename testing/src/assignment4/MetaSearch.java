/**
 * Description: Assignment #4
 * @author Jennfier Winer
 * @since 2011-9-23
 * Created: September 23, 2011
 * Last Modified: December 3, 2011
 */
package assignment4;

import java.util.*;
import java.net.*;
import java.io.*;

/**
 * A class that models a search engine and shopping cart
 * @author jwiner
 */
public class MetaSearch {
    private static Scanner keyboard = new Scanner(System.in);
    private static ArrayList<Entry> products = new ArrayList<Entry>();
    private static HashMap<String, ArrayList<Integer>> indexes = new HashMap<String, ArrayList<Integer>>(); 
    private static GUI userInterface = new GUI();
        
    /**
     * contains the looping main menu that calls all other methods
     */
    public static void main(String[] args) {
        userInterface.setVisible(true);
    } 
    
    
    /**
     * Adds a product that the user enters to the end of the list of products
     * @param userInput The information that the user entered into the GUI
     */
    public static void addProducts(String[] userInput){
        String errorMessages = "  Product Successfully Added!";
        Entry newProduct;
        
        /**
         * This is my example of handling incorrect output so the user can try again
         * the loop is not directly visible, but the information that the user enters will only 
         * disappear if ALL of the information was valid 
         * AKA - I allow the user to re-enter their data until they get it right - then add the 
         * product and clear all of the entered data (now ready for a new Entry)
         */
        try{
            if(userInput[0].trim().equalsIgnoreCase("Book"))
                newProduct = new Book(userInput);
            else if(userInput[0].trim().equalsIgnoreCase("Movie"))
                newProduct = new Movie(userInput);
            else
                newProduct = new Music(userInput);
            
            // another example of dynamic binding
            for(Entry e : products)
                if(e.equals(newProduct)){
                    newProduct = null;
                    break;
                }
                    
            if(newProduct == null)
                throw new Exception("The item is already in your list");
                
            products.add(newProduct);
            updateIndexes(userInput[1]);
        }
        catch(NumberFormatException e){
            errorMessages = "ENTRY INPUT ERROR : " + e.getMessage() + "\n\nThis string in the Date field is not valid (or is in the wrong position)\nThe Date fields should be formatted as month, day, year \n(but you can leave the day, or the day and the month blank if you wish)";
        }
        catch(Exception e){
           errorMessages = "ENTRY INPUT ERROR : " + e.getMessage() + "\n\n" + getErrorMessages(e.getMessage());
        }
        finally{
            userInterface.setErrorMessages(errorMessages);
        }
    }
    
    /**
     * Finds the appropriate error message for the given exception
     * @param error A String that is the exact error message contained within the exception that was thrown
     * @return A string that contains more information about the error that occurred
     */
    public static String getErrorMessages(String error){
        String errorMessages;
        
        if(error.equals("Invalid title"))
            errorMessages = "The Title field CANNOT be left empty.";
            
        else if(error.equals("Invalid author"))
             errorMessages = "You have entered an invaild Author.";
        else if(error.equals("Invalid actors"))
             errorMessages = "You have entered some invalid Actors.";
        else if(error.equals("Invalid company"))
             errorMessages = "You have entered an invaild Company.";

        else if(error.equals("Invalid type"))
            errorMessages = "You have entered an invalid type.";
        
        
        else if(error.equals("Invalid number of words in your date constructor")){
            errorMessages = "There is a problem with your Date field.";
            errorMessages = errorMessages + "\nYou seem to have entered too many or too few words.";
            errorMessages = errorMessages + "\nThe Date field should be between 1 and 3 words long";
            errorMessages = errorMessages + "\nThe Date fields should be formatted as month, day, year \n(but you can leave the day, or the day and the month blank if you wish)";
        }
        else if(error.equals("Invalid year")){
            errorMessages = "There is a problem with the year in your Date field.";
            errorMessages = errorMessages + "\nIt CANNOT be left empty.";
            errorMessages = errorMessages + "\nThe year must be a number between 1000 and 9999.";
            errorMessages = errorMessages + "\nThe Date fields should be formatted as month, day, year \n(but you can leave the day, or the day and the month blank if you wish)";
        }
        else if(error.equals("Invalid day")){
            errorMessages = "There is a problem with the day in your Date field.";
            errorMessages = errorMessages + "\nThe day must be a number between 1 and 31, or left empty.";
            errorMessages = errorMessages + "\nThe Date fields should be formatted as month, day, year \n(but you can leave the day, or the day and the month blank if you wish)";
        }
        else if(error.equals("Invalid month")){
            errorMessages = "There is a problem with the month in your Date field.";
            errorMessages = errorMessages + "\nThe month must be a 3 letter abreviation, the month written in full, a number between 1 and 12, or left empty.";
            errorMessages = errorMessages + "\nThe Date fields should be formatted as month, day, year \n(but you can leave day, or day and month black if you wish)";
        }

        else if(error.equals("Invalid price")){
             errorMessages = "The Price field CANNOT be left empty.";
             errorMessages = errorMessages + "\nIt must be a number greater then or equal to zero.";
        }
        
        else if(error.equals("The item is already in your list")){
             errorMessages = "The Item that you are trying to add has already been added to your current collection.";
             errorMessages = errorMessages + "\nChange some of the information about your item and try again";
        }
        
        else if(error.equals("No products in current colleciton")){
            errorMessages = "Your list of products is currently empty.";
            errorMessages = errorMessages + "\nIn order to search your current collection, you must have at least 1 product \nor search the Internet";
        }
        
        else if(error.equals("Invalid Entry Type"))
            errorMessages = "You must select at least one type to search for.";
        
        else if(error.equals("Invalid Range"))
            errorMessages = "Your start date must come before your end date!";
            
        else
            errorMessages = "UNKOWN ERROR!\nOMG YOU BROKE ME!!!";
        
        return errorMessages;
    }
    
    
    
    /**
     * Searches the list of products for particular products based on keyword(s) that the user enters. 
     * It only prints the products that contain ALL of the entered keywords.
     * @param searchParameters All of the information that the user has input (from GUI.getSearchParameters())
     */
    public static void searchForProducts(String[] searchParameters){
        String totalResults = "";
        String searchResults = "";
        
        String delimiters = "[ \\p{Punct}\\.]+";
        String[] types = new String[1];
        String[] titleKeywords = new String[1];
        Date[] range = new Date[2];

        try{
            if(products.isEmpty() && searchParameters[0].equals("array"))
                throw new Exception("No products in current colleciton");

            if(searchParameters[1].length() == 0)
                throw new Exception("Invalid Entry Type");
            
            types = searchParameters[1].split(delimiters);
            titleKeywords = searchParameters[2].trim().split(delimiters);
            
            if(searchParameters[3].length() == 0)
                range[0] = null;
            else
                range[0] = new Date(searchParameters[3]);
            
            if(searchParameters[4].length() == 0)
                range[1] = null;
            else
                range[1] = new Date(searchParameters[4]);

            if(range[0] != null && range[1] != null && !range[0].preceeds(range[1]))
                throw new Exception("Invalid Range");


            if(!searchParameters[0].equals("internet")){
                searchResults = hashMapEntriesMatches(types, titleKeywords, range[0], range[1]);
                if(searchResults.length() > 0)
                    totalResults = totalResults + "Matches from your current colletion : \n" + searchResults + "\n\n";
            }
            if(!searchParameters[0].equals("array")){
                searchResults = getInternetInformation(types, titleKeywords, range[0], range[1]);
                if(searchResults.length() > 0)
                    totalResults = totalResults + "Matches from Chapters.ca : \n" + searchResults;
            }
        }
        catch(NumberFormatException e){
            totalResults = "ENTRY INPUT ERROR : " + e.getMessage() + "\n\nThis string in the Date field is not valid (or is in the wrong position)\nThe Date fields should be formatted as month, day, year \n(but you can leave the day, or the day and the month blank if you wish)";
        }
        catch(Exception e){
            totalResults = "ENTRY INPUT ERROR : " + e.getMessage() + "\n\n" + getErrorMessages(e.getMessage());
        }
        finally{
            userInterface.setSearchResults(totalResults);
        }
    }   
    
    /**
     * Finds prints all entries that match the entered parameters by finding matching title keywords using a hash map. 
     * It prints the toString method of any matching entries to the screen (System.out). 
     * 
     * @param mediaTypes They types of products that the user wishes to search for
     * @param titleKeywords The keywords that the user wishes to find in the title - ALL MUST MATCH to be considered a matching product
     * @param bottomRange The date after which we want the release date of the product to be
     * @param topRange The date before which we want the release date of the product to be
     * @return False if NONE of the products matched the search parameters, True otherwise
     */
    public static String hashMapEntriesMatches(String[] mediaTypes, String[] titleKeywords, Date bottomRange, Date topRange){
        ArrayList<Integer> matchingRecords = getTitleIndexes(titleKeywords);
        String matchingProducts = "";
        Entry nextProduct;
        
        // if the user has no restrictions on the title keywords, search the entire array list
        if(matchingRecords == null){
            for(Entry currentProduct : products)
                if((typesMatch(currentProduct,mediaTypes)) && (withinDateRange(currentProduct.getReleaseDate(), bottomRange, topRange)))
                    matchingProducts = matchingProducts + "\n " + currentProduct.toString(); 
        }

        // if the user did enter title keywords, but there are no Entries that match all of the keywords
        else if(matchingRecords.isEmpty()){
            matchingProducts = matchingProducts + "\nYour chosen title yeilded no matches in your current collection.";
            matchingProducts = matchingProducts + "\nTry reducing the number of (or changing) your title keywords.";
        }
                
        // if there are entries in the product ArrayList that match ALL of the user's title keywords, then only search the matching products for the chosen media type and date range
        // NOTE - bellow is my example of POLYMORPHISM!!! 
        // nextProduct is a referance to an Entry object so through dynamic binding my program finds the toString method of the 
        // appropriate subclass at runtime!
        else{ 
            for(int i=0; i<matchingRecords.size(); i++){
                nextProduct = products.get(matchingRecords.get(i));
                if((typesMatch(nextProduct, mediaTypes)) && (withinDateRange(nextProduct.getReleaseDate(), bottomRange, topRange)))
                    matchingProducts = matchingProducts + "\n " + nextProduct.toString(); 
            }
        }
        
        return matchingProducts;
    }
    
    /**
     * Finds all of the the indexes of the products in the products ArrayList whose titles match all of the user's keywords
     * @param keywords the keywords that the user would like to search for
     * @return null if the user entered no keywords (would like no restrictions on the title), Otherwise the ArrayList containing the indexes of all of the products in the products ArrayList that match ALL of the user's keywords
     */
    public static ArrayList<Integer> getTitleIndexes(String[] keywords){
        ArrayList<Integer> firstList;
        ArrayList<Integer> secondList = null;
        ArrayList<Integer> noMatches = new ArrayList<Integer>();
        
        // if the user want's no restrictions on the title 
        if((keywords.length == 0) || (keywords[0].equals("")))
            return null;
        
        if(indexes.containsKey(keywords[0].toLowerCase()))
                firstList = indexes.get(keywords[0].toLowerCase());
        else 
            return noMatches;
        
        for(int i=1; i<keywords.length; i++){
            if(keywords[i].equals(""))
                continue;
            if(!indexes.containsKey(keywords[i].toLowerCase()))
                return noMatches;

            secondList = indexes.get(keywords[i].toLowerCase());
            firstList = combineArrayLists(firstList, secondList);            
        }
        
        return firstList;
    }
    
    /**
     * Combines the intersection two ArraryLists of Integers into one ArrayList of Integers
     * @param first The first ArrayList of Integers
     * @param second The Second ArrayList of Integers
     * @return An ArrayList that contains all of the common elements of the two ArrayLists
     */
    public static ArrayList<Integer> combineArrayLists(ArrayList<Integer> first, ArrayList<Integer> second){
        ArrayList<Integer> combinedList = new ArrayList<Integer>();
        int i = 0;
        int j = 0;
        
        while((i < first.size()) && (j < second.size())){
            if(first.get(i) < second.get(j))
                i++;
            
            else if(first.get(i) > second.get(j))
                j++;
            
            else{
                combinedList.add(first.get(i));
                i++;
                j++;
            }
        }
        
        return combinedList;
    }
    
    
    
    /**
     * Checks to see if the nextProduct's class matches one of the media types that the user is searching for
     * 
     * @param nextProduct The product whose class we wish to compare
     * @param mediaTypes The String array of all of the user's chosen media types
     * @return True if the mediaTypes is an empty string (no restrictions on the class of the object)
     *         or one of the user's entered media types matches the class of the current object.
     *         Otherwise, false.
     */
    public static boolean typesMatch(Entry nextProduct, String[] mediaTypes){
        if((mediaTypes.length == 0) || (mediaTypes[0].length() == 0))
            return true;
        else
            return keywordsMatch(nextProduct.getClass().getSimpleName(),mediaTypes); 
    }
    
    /**
     * Checks if any of the tokens that the user has input matches the comparison String
     * @param keyword The String that we want to find a match to
     * @param userInput The tokens that the user has entered as an array of strings
     * @return True if AT LEAST one of the user's tokens matches the comparison String, false otherwise
     */
    public static boolean keywordsMatch(String keyword, String[] userInput){
        for(int i=0; i<userInput.length; i++)
            if(userInput[i].equalsIgnoreCase(keyword))
                return true;
        
        return false;
    }
    
    /**
     * Checks if every keyword of user input matches a string in the comparison String
     * @param keywords The keywords that we wish to find matches for
     * @param comparison The string array that we want to find the keywords in
     * @return True if ALL of the keywords have been found in the comparison String, false otherwise
     */
    public static boolean allKeywordsMatch(String[] keywords, String[] comparison){
        int matches = 0;
        
        if((keywords != null) && ((keywords[0].length() > 0) || (keywords.length > 1))){
            for(int i=0; i<keywords.length; i++)
                for(int j=0; j<comparison.length; j++)
                    if(keywords[i].equalsIgnoreCase(comparison[j])){
                        matches++;
                        break;
                    }
            
            return (matches == keywords.length);
        }
        else
            return true;
    }
    
    /**
     * Checks if a release date is within the desired date range.
     * if any of the last two parameters are null, then there is no bound on that side of the range
     * 
     * @param releaseDate The Date object that contains the day that the product was released
     * @param bottom The lower bound for the date range. All dates after, but after top are accepted
     * @param top The upper bound for the date range. All dates before, but not before bottom are accepted
     * @return True if the release date lies within the range that was input, false otherwise
     */
    public static boolean withinDateRange(Date releaseDate, Date bottom, Date top){
        if(bottom != null){
            if(top != null)
                return ((bottom.preceeds(releaseDate)) && (releaseDate.preceeds(top)));
            return (bottom.preceeds(releaseDate));
        }
        else if(top != null)
            return (releaseDate.preceeds(top));
        
            return true;
    }


    
    /**
     * Adds up to 12 products from chapters.ca to our products ArrayList based on the restrictions that the user has entered
     * 
     * @param mediaTypes The specific type of entries that the user is searching for (eg. book, movie)
     * @param titleKeywords The words that must be in the title of the product
     * @param bottomRange The lower bound for the release date range of the product
     * @param topRange The upper bound for the release date range of the product
     * @return A string containing all of the matching entries from the Internet or an error message depending on what went wrong
     */
    public static String getInternetInformation(String[] mediaTypes, String[] titleKeywords, Date bottomRange, Date topRange){
        String matchedProducts = " "; 
        String currentProduct;
        
        try{
            URL chapters;
            Scanner page;
            
            String[] validTypes = {"Book", "Movie", "Music"};
            String searchType;
            String keywords = encodeKeywords(titleKeywords);
            String webPageBuffer;
            
            // we need to search chapters.ca as many times as valid tokens because the URL needs to change depending on the type of product we are searching for
            for(int i=0; i<mediaTypes.length; i++){
                searchType = encodeType(mediaTypes[i]);
                webPageBuffer = "";
                
                chapters = new URL("http://www.chapters.indigo.ca/" + searchType + "/search/?keywords=" + keywords + "&pageSize=12");
                page = new Scanner(chapters.openStream());

                while(page.hasNextLine()){
                    webPageBuffer = webPageBuffer + page.nextLine();
                }
                page.close();
                
                currentProduct = parseWebpage(webPageBuffer, mediaTypes[i], bottomRange, topRange);
                if(currentProduct.trim().length() > 0)
                    matchedProducts = matchedProducts + currentProduct + "\n ";
            }
        }
        catch(Exception e){
            matchedProducts = "Chapters.ca ERROR";
            matchedProducts = matchedProducts + "\n\nLooks like there is something wrong with Chapters.ca.";
            matchedProducts = matchedProducts + "\nMake sure that you are connected to the internet and try your search again later.";
        }
        
        if(matchedProducts.trim().length() == 0){
            matchedProducts = matchedProducts + "\nYour chosen title yeilded no matches from Chapters.ca, \nor no matches that are not already in your current collection";
            matchedProducts = matchedProducts + "\nTry reducing the number of (or changing) your title keywords.\n";
        }

        return matchedProducts;
    }
    
    /**
     * Searches the webSite chapters.ca for up to 12 products.
     * It will then add any product that matches the user's input criteria to the products ArrayList and indexes hashMap
     * 
     * @param webPage The entire contents of the webPage that contains our search results (the HTML source code)
     * @param mediaType The media types that the user wishes to find
     * @param bottomRange The date after which we want the release date of the product to be
     * @param topRange The date before which we want the release date of the product to be
     * @return The number of products in our search results from chapters.ca that matched all of the user's criteria
     */
    public static String parseWebpage(String webPage, String mediaType, Date bottomRange, Date topRange){
        String[] buffer;
        String title;
        String publisher = "";
        String type = "";
        String date;
        String price;
        
        String matchingProducts = "";
        String currentProduct = "";
        
        int startingPosition = 0;
        int endingPosition;
        
        while(webPage.indexOf("<h3", startingPosition) > 0){
            date = null;
            publisher = "";
            type = "";
            
            startingPosition = webPage.indexOf("<h3", startingPosition);
            startingPosition = webPage.indexOf("<a", startingPosition);
            startingPosition = webPage.indexOf(">", startingPosition);
            endingPosition = webPage.indexOf("</a>", startingPosition);
            title = webPage.substring(startingPosition + 1, endingPosition);

            // some products do NOT have a publisher, so the if statement prevents me from skipping to the next record if it doesn't contatin a publisher
            if((webPage.indexOf("<h4", startingPosition) < (webPage.indexOf("<h5", startingPosition)))){
                startingPosition = webPage.indexOf("<h4", startingPosition);
                startingPosition = webPage.indexOf("<a", startingPosition);
                startingPosition = webPage.indexOf(">", startingPosition);
                endingPosition = webPage.indexOf("</a>", startingPosition);
                publisher = webPage.substring(startingPosition + 1, endingPosition);
            }

            startingPosition = webPage.indexOf("<h5", startingPosition);
            startingPosition = webPage.indexOf(">", startingPosition);
            endingPosition = webPage.indexOf("</h5>", startingPosition);
            buffer = webPage.substring(startingPosition + 1, endingPosition).trim().split("[|]+");

            startingPosition = webPage.indexOf("<strong class='priceStyle'", startingPosition);
            startingPosition = webPage.indexOf(">", startingPosition);
            endingPosition = webPage.indexOf("</strong>", startingPosition);
            price = webPage.substring(startingPosition + 1, endingPosition);

            // the h5 tag can contain 1 to 3 tokens, and any token may be absent - so I need to account for every valid situation (eg. no date = invalid)
            if(buffer.length == 3){
                date = buffer[1];
                type = buffer[2];
            }
            else if(buffer.length == 2){
                if(Date.validateDate(buffer[0])){
                    date = buffer[0];
                    type = buffer[1];
                }
                else if(Date.validateDate(buffer[1]))
                    date = buffer[1];
            }
            else if((buffer.length == 1) && (Date.validateDate(buffer[0])))
                date = buffer[0];

            currentProduct = initalizeSpecificProduct(mediaType, bottomRange, topRange, title, publisher, type, date, price);
            if(currentProduct.length() > 0)
                matchingProducts = matchingProducts + "\n " + currentProduct;
        }
        return matchingProducts;       
    }

    /**
     * Change the mediaType into a type that chapters.ca understands
     * @param mediaType The user's media type (the type of Entry that we want to search for)
     * @return A String that can be used in the creation of the chapters.ca URL
     */
    private static String encodeType(String mediaType){
        mediaType = mediaType.trim();
        
        if(mediaType.equalsIgnoreCase("book"))
            return "books";
        else if(mediaType.equalsIgnoreCase("movie"))
            return "dvd";
        else if(mediaType.equalsIgnoreCase("music"))
            return "music";
        else 
            return "home";
            
    }
    
    /**
     * Combines all of the keywords into something that chapter.ca can understand
     * @param keywords The user's title keywords (words that should be in the results titles)
     * @return A String that represents all of the keywords in a format that can be used in the creation of a chapters.ca URL
     */
    private static String encodeKeywords(String[] keywords){
        String searchwords = keywords[0];
        
        for(int i=1; i<keywords.length; i++)
            searchwords = searchwords + "+" + keywords[i];
        
        return searchwords;
    }
    
    
    /**
     * Initializes one object of the same class as the string in the user entered mediaType with the information extracted from the parseWebpage method
     * 
     * @param mediaType The class of object that the user would like to create
     * @param bottomRange The date after which we want the release date of the product to be
     * @param topRange The date before which we want the release date of the product to be
     * @param title The title parsed from the webPage
     * @param publisher The publisher (author, actors or company depending on which Class is chosen in mediaType) parsed from the webPage
     * @param type The type parsed from the webPage
     * @param date The releaseDate parsed from the webPage
     * @param price The price parsed from the webPage
     * @return The String representation of the object that we just created
     */
    private static String initalizeSpecificProduct(String mediaType, Date bottomRange, Date topRange, String title, String publisher, String type, String date, String price){
        Entry newProduct;
        String product = "";
        Date releaseDate;
        int priceInCents;
        
        try{
            releaseDate = new Date(date);
            priceInCents = Entry.parsePrice(price);
            
            if(withinDateRange(releaseDate, bottomRange, topRange)){
                if(mediaType.trim().equalsIgnoreCase("Book"))
                    newProduct = new Book(title, publisher, type, releaseDate, priceInCents);
                else if(mediaType.trim().equalsIgnoreCase("Movie"))
                    newProduct = new Movie(title, publisher, type, releaseDate, priceInCents);
                else 
                    newProduct = new Music(title, publisher, type, releaseDate, priceInCents);
            
            
                // another example of dynamic binding
                for(Entry e : products)
                    if(e.equals(newProduct)){
                        newProduct = null;
                        break;
                    }
                
                if(newProduct != null){
                    products.add(newProduct);
                    updateIndexes(title);
                    product = mediaType.trim() + ": " + title + "; " + publisher + "; " + type + "; " + date + "; " + price;
                }
            }
        }
        catch(Exception e){
            // do nothing - this is because if a product from chapters.ca does not meet our specifications it should have
            // no effect on the user!
            // for an example of checking for incorrect input so that the user can try again please
            // see the addProducts method
        }
        return product;
    }

    /**
     * Adds all of the words in the title String to the indexes hashMap (to keep track of which words appear in which title of the products ArrayList)
     * @param title The title of the product that we will be immediately afterwards adding to the products array
     */
    private static void updateIndexes(String title){
        String[] titleTokens;
        String delimiters = "[ \\p{Punct}\\.]+";
        
        titleTokens = title.toLowerCase().split(delimiters);
        
        for(int i=0; i<titleTokens.length; i++)
            if(!titleTokens[i].trim().equals("")){
                if(!indexes.containsKey(titleTokens[i]))
                    indexes.put(titleTokens[i], new ArrayList<Integer>());
                // STOPS us from adding an index to a token multiple times if the token appears multiple times in the title (eg. the lord of the files = 2 of the)
                if(!indexes.get(titleTokens[i]).contains(products.size() - 1))
                    indexes.get(titleTokens[i]).add(products.size() - 1);
            }
    }
    
    
    
    /**
     * Writes all of the Entries in our current collection into the file "productsList.txt"
     * It then updates both text areas of the GUI with the results of the write (whether it was successful or not)
     */
    public static void writeProductsToFile(){
        try{
            PrintWriter outputStream = new PrintWriter(new FileOutputStream("productsList.txt"));
            
            for(Entry currentProduct : products)
                outputStream.println(currentProduct.toFormattedString());
            outputStream.close();
            
            userInterface.setErrorMessages("  Current Collection successfully writen to file.");
            userInterface.setSearchResults("  Current Collection successfully writen to file.");
        }
        catch(Exception e){
            userInterface.setErrorMessages("  File Write Failed.");
            userInterface.setSearchResults("  File Write Failed.");
        }
    }
    
    /**
     * Adds all of the Entries in a file to our products list (and updates our hash map)
     * NOTE - the file that you are reading the entries in from must have been created using the above method (writeProductsToFile())
     */
    public static void loadProductsFromFile(){
        String statusMessage = "  Collection successfully read from file.";
        String currentProduct;
        String delimiters = "[;]";
        String[] tokenizedProduct;
        Entry newProduct;
        
        try{
            Scanner inputStream = new Scanner(new FileInputStream("productsList.txt"));

            while(inputStream.hasNextLine()){
                currentProduct = inputStream.nextLine();
                tokenizedProduct = currentProduct.split(delimiters);
                
                if(tokenizedProduct[0].trim().equalsIgnoreCase("Book"))
                    newProduct = new Book(tokenizedProduct);
                else if(tokenizedProduct[0].trim().equalsIgnoreCase("Movie"))
                    newProduct = new Movie(tokenizedProduct);
                else
                    newProduct = new Music(tokenizedProduct);

                for(Entry e : products)
                    if(e.equals(newProduct)){
                        newProduct = null;
                        break;
                    }
                
                if(newProduct != null){
                    products.add(newProduct);
                    updateIndexes(tokenizedProduct[1]);
                }
            }
        }
        catch(Exception e){
            statusMessage = "FILE READ ERROR : " + "\n\nYou need to save information to file before you can read from it!";
        }
        
        finally{
            userInterface.setErrorMessages(statusMessage);
            userInterface.setSearchResults(statusMessage);
        }
    }
    
    /**
     * Clears all products in your products list
     */
    public static void clearProductsList(){
        products = new ArrayList<Entry>();
        indexes = new HashMap<String, ArrayList<Integer>>(); 
    }
}