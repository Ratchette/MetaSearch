/**
 * Description: Assignment #4
 * Created: November 27, 2011
 * Last Modified: December 3, 2011
 */
package assignment4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A class that models the graphical user interface for assignment 4
 * @author Jennifer Winer
 */
public class GUI extends JFrame{
    public static final Dimension windowDimensions = new Dimension(500,500);    // the exact dimensions of my window
    
    private JPanel startPanel;      // the panel that appears as soon as you run my program
    private JPanel addPanel;        // the panel that contains all of the components needed to add an Entry
    private JPanel searchPanel;     // the panel that contains all of the componenets needed to search for an Entry
    
    private JCheckBox[] searchLocation;         // the checkboxes that dictate where you are searching for entries (local collection or internet)
    private JComboBox entryTypesComboBox;       // the comboBox that determines what type of entry you are trying to add
    private JCheckBox[] entryTypesCheckBoxes;   // the checkBoxes that determine what type of entry you are trying to search for
    private JButton[] buttons;                  // the add, search and reset buttons
    private JTextField[] dataEntryFields;       // all of the textfields in my GUI (for adding + searching)
    private JLabel[] EntryTypeDependentLabels;  // the labels for author, actors and company (that need to change depending on the entry type chosen from the entryTypesComboBox)
    private JTextArea[] messagesTextArea;       // the two text areas that are in my GUI
    
    private WindowListener safeguardWindowListener;
    
    /**
     * A no-argument constructor for my GUI
     */
    public GUI(){
        super("Meta Search");
        setSize(windowDimensions);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        
        createWindowComponents();
        addWindowListener(safeguardWindowListener);
        
        setJMenuBar(createMainMenuBar());
        
        startPanel = createStartPage();
        add(startPanel);
        
        createAddPanel();
        createSearchPanel();
        
        add(addPanel);
        add(searchPanel);
    }
    
    /**
     * Creates the two menus for my program, and adds them to a JMenuBar
     * @return The menu bar for my GUI with the main and file I/O menus
     */
    private JMenuBar createMainMenuBar(){
        JMenuBar mainMenuBar = new JMenuBar();
        
        // create main menu and all of its commands
        JMenu mainMenu = new JMenu("Commands");
        JMenuItem addChoice = new JMenuItem("Add");
        addChoice.addActionListener(new menuListener());
        mainMenu.add(addChoice);
        
        JMenuItem searchChoice = new JMenuItem("Search");
        searchChoice.addActionListener(new menuListener());
        mainMenu.add(searchChoice);
        
        mainMenu.addSeparator();
        
        JMenuItem quitChoice = new JMenuItem("Quit");
        quitChoice.addActionListener(new menuListener());
        mainMenu.add(quitChoice);
        
        // create the file I/O menu and all of its commands
        JMenu fileIOMenu = new JMenu("Save / Load");
        JMenuItem save = new JMenuItem("Save current collection to file");
        save.addActionListener(new menuListener());
        fileIOMenu.add(save);
        
        JMenuItem load = new JMenuItem("Load prducts from file");
        load.addActionListener(new menuListener());
        fileIOMenu.add(load);
        
        fileIOMenu.addSeparator();
        
        JMenuItem clear = new JMenuItem("Clear current collection");
        clear.addActionListener(new menuListener());
        fileIOMenu.add(clear);
        
        // add both menus to the JMenuBar
        mainMenuBar.add(mainMenu);
        mainMenuBar.add(fileIOMenu);
        
        return mainMenuBar;
    }
    
    /**
     * Initializes all of the previously defined private instance variables required for a GUI class object
     */
    private void createWindowComponents(){
        String[] addingEntryTypes = {"   Book   ", "   Movie   ", "   Music   "};
        entryTypesComboBox = new JComboBox(addingEntryTypes);
        entryTypesComboBox.setSelectedIndex(0);
        entryTypesComboBox.addActionListener(new EntryTypeListener());
        
        entryTypesCheckBoxes = new JCheckBox[3];
        String[] searchingEntryTypes = {"Book      ", "Movie     ", "Music"};
        for(int i=0; i<3; i++){
            entryTypesCheckBoxes[i] = new JCheckBox(searchingEntryTypes[i]);
            entryTypesCheckBoxes[i].setSelected(true);
        }
        
        buttons = new JButton[4];
        String[] buttonNames = {"Reset", "  Add  ", "Reset", "Search"};
        for(int i=0; i<4; i++){
            buttons[i] = new JButton(buttonNames[i]);
            buttons[i].addActionListener(new ButtonListener());
            buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        
        dataEntryFields = new JTextField[8];
        String[] textFieldNames = {"title", "creator", "kind", "date", "price", "title", "startDate", "endDate"};
        for(int i=0; i<8; i++){
            dataEntryFields[i] = new JTextField(20);
            dataEntryFields[i].setName(textFieldNames[i]);
        }
            
        EntryTypeDependentLabels = new JLabel[3];
        EntryTypeDependentLabels[0] = new JLabel("Author");
        EntryTypeDependentLabels[1] = new JLabel("Actors");
        EntryTypeDependentLabels[1].setVisible(false);
        EntryTypeDependentLabels[2] = new JLabel("Company");
        EntryTypeDependentLabels[2].setVisible(false);
        
        
        messagesTextArea = new JTextArea[2];
        for(int i=0; i<2; i++){
            if(i == 0)
                messagesTextArea[i] = new JTextArea(9,41);
            else
                messagesTextArea[i] = new JTextArea(11,41);
            messagesTextArea[i].setEditable(false);
        }
 
        
        searchLocation = new JCheckBox[2];
        searchLocation[0] = new JCheckBox("Current Collection    ");
        searchLocation[0].setSelected(true);
        searchLocation[1] = new JCheckBox("Internet");
        
        safeguardWindowListener = new ConfirmQuit();
    }
    
    /**
     * Creates a JPanel that contains all of the components needed for the start page (including appropriate listeners attached to their corresponding items)
     * @return the start page panel
     */
    private JPanel createStartPage(){
        JPanel startPage = new JPanel();
        
        JTextArea startMessage = new JTextArea(20, 26);
        startMessage.setLineWrap(true);
        startMessage.append("\n\n\n\n      Welcome to the Meta Search System.");
        startMessage.append("\n\n\n\n   Choose a command from the \"Commands\"      menu above for adding an entry, searching     entries, or quitting the system.");
        
        startMessage.setFont(new Font("Arial", Font.BOLD, 20));
        startMessage.setEditable(false);
        startMessage.setOpaque(false);
        
        startPage.add(startMessage, JTextArea.CENTER_ALIGNMENT);
        return startPage;
    }
    
    /**
     * Creates a JPanel that contains all of the components needed for the add page (including appropriate listeners attached to their corresponding items)
     * @return the add page panel
     */
    private void createAddPanel(){
        addPanel = new JPanel();
        addPanel.setLayout(new BorderLayout());

        addPanel.add(createTitle("Adding an Entry"), BorderLayout.NORTH);
        addPanel.add(createUserInputPanel(), BorderLayout.WEST);
        addPanel.add(createButtonPanel(0), BorderLayout.CENTER);
        addPanel.add(createMessagesPanel("  Messages", 0), BorderLayout.SOUTH);
        
        addPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        addPanel.setVisible(false);
    }
    
    /**
     * Creates a JPanel that contains all of the components needed for the search page (including appropriate listeners attached to their corresponding items)
     * @return the search page panel
     */
    private void createSearchPanel(){
        searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());

        searchPanel.add(createTitle("Searching for Entries"), BorderLayout.NORTH);
        searchPanel.add(createSearchingParametersPanel(), BorderLayout.WEST);
        searchPanel.add(createButtonPanel(2), BorderLayout.CENTER);
        searchPanel.add(createMessagesPanel("  Search Results", 1), BorderLayout.SOUTH);
        
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        searchPanel.setVisible(false);
    }
    
    /**
     * Creates a title panel (A Large title with a separator bellow it)
     * @param title The title that you want displayed in the panel
     * @return a properly formatted title panel
     */
    private JPanel createTitle(String title){
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(titleLabel);
        titlePanel.add(new JSeparator(JSeparator.HORIZONTAL));
        
        return titlePanel;
    }
    
    /**
     * Creates a panel with the buttons required for the add or search panel
     * @param buttonIndices Dictates whether whether you want the buttons for an add or a search page. 0 = add page, 2 = search page.
     * @return A panel containing the 2 buttons specified for in the button indices
     */
    private JPanel createButtonPanel(int buttonIndices){
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        
        // add vs search pannels have different dimensions and therefore need different spacing
        if(buttonIndices == 0)
            buttonsPanel.add(Box.createRigidArea(new Dimension(0, 35)));
        else 
            buttonsPanel.add(Box.createRigidArea(new Dimension(0, 45)));
        // then add appropriate button
        buttonsPanel.add(buttons[buttonIndices]);
        
        // add vs search pannels have different dimensions and therefore need different spacing
        if(buttonIndices == 0)
            buttonsPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        else
            buttonsPanel.add(Box.createRigidArea(new Dimension(0, 35)));
        // then add appropriate button
        buttonsPanel.add(buttons[buttonIndices + 1]);

        return buttonsPanel;
    }
    
    /**
     * Creates the bottom panels for the add and search pages
     * @param title The title that you want the messages panel to have
     * @param textAreaIndex Dictates whether whether you want to create a text area for an add or a search page. 0 = add page, 1 = search page.
     * @return A panel containing a separator, title and JTextArea
     */
    private JPanel createMessagesPanel(String title, int textAreaIndex){
        JPanel messagesPanel = new JPanel();
        messagesPanel.setLayout(new BorderLayout());
        messagesPanel.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.NORTH);
        
        // create a large title with an empty border arround it
        JLabel messagesLabel = new JLabel(title);
        messagesLabel.setFont(new Font("Arial", Font.BOLD, 15));
            messagesLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 7, 0));
        messagesPanel.add(messagesLabel, BorderLayout.CENTER);
        
        // add the appropriate JTextArea
        JScrollPane scrollableMessagesTextArea;
        scrollableMessagesTextArea = new JScrollPane(messagesTextArea[textAreaIndex]);
        scrollableMessagesTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableMessagesTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        messagesPanel.add(scrollableMessagesTextArea, BorderLayout.SOUTH);
        messagesPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return messagesPanel;
    }
    
    /**
     * Creates the panel on the add page that the user can interact with and place information into 
     * @return A panel containing labels, the entryTypesComboBox and 5 JtextFields
     */
    private JPanel createUserInputPanel(){
        JPanel userInputPanel = new JPanel();
        userInputPanel.setLayout(new FlowLayout());
        JPanel labelsPanel = new JPanel();
        JPanel inputBoxesPanel = new JPanel();
        
        // set the layout for the panel with the labels
        GridLayout addPanelLayout = new GridLayout(6,1);
        addPanelLayout.setVgap(5);
        labelsPanel.setLayout(addPanelLayout);
        
        // add the labeks to the panel (and make them look pretty)
        JPanel label;
        String[] addPageLabels = {"Entry Type", "Title", "", "Kind", "Date", "Price"};
        for(int i=0; i<6; i++){
            label = new JPanel();
            label.setLayout(new FlowLayout(FlowLayout.LEFT));
            
            if(i != 2)
                label.add(new JLabel(addPageLabels[i]));
            else
                for(int j=0; j<3; j++)
                    label.add(EntryTypeDependentLabels[j]);
            
            labelsPanel.add(label);
        }
        userInputPanel.add(labelsPanel);
        
        // add the combo box to the panel for user input
        inputBoxesPanel.setLayout(addPanelLayout);
        inputBoxesPanel.add(entryTypesComboBox);
      
        // add all of the required text fields for the adding panel user input area
        for(int i=0; i<5; i++)
            inputBoxesPanel.add(dataEntryFields[i]);
        
        userInputPanel.add(inputBoxesPanel);
        return userInputPanel;
    }
    
    /**
     * Creates the panel on the search page that the user can interact with and place information into 
     * @return A panel containing labels, all of the JCheckBoxes created in createWindowComponents and 3 JtextFields
     */
    private JPanel createSearchingParametersPanel(){
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new BorderLayout());
        
        JPanel userInputPanel = new JPanel();
        userInputPanel.setLayout(new FlowLayout());
        JPanel labelsPanel = new JPanel();
        JPanel inputBoxesPanel = new JPanel();
        
        outerPanel.add(createCheckBoxesPanel(), BorderLayout.NORTH);
        
        labelsPanel.setLayout(new GridLayout(3,1));
        
        JPanel label;
        String[] addPageLabels = {"Title", "Start Date", "End Date"};
        for(int i=0; i<3; i++){
            label = new JPanel();
            label.setLayout(new FlowLayout(FlowLayout.LEFT));
            label.add(new JLabel(addPageLabels[i]));
            labelsPanel.add(label);
        }
        userInputPanel.add(labelsPanel);
        
        GridLayout searchPanelLayout = new GridLayout(3,1);
        searchPanelLayout.setVgap(6);
        inputBoxesPanel.setLayout(searchPanelLayout);
        
        for(int i=5; i<8; i++)
            inputBoxesPanel.add(dataEntryFields[i]);
        userInputPanel.add(inputBoxesPanel);
        outerPanel.add(userInputPanel, BorderLayout.SOUTH);
        
        return outerPanel;
    }
    
    /**
     * Creates a panel that holds the JCheckBoxes and their labels
     * This function only exists to make the formatting of the JCheckBoxes portion of my search page prettier
     * 
     * @return A properly formatted panel with the format: label   Associated CheckBoxes
     */
    private JPanel createCheckBoxesPanel(){
        JPanel checkBoxesPanel = new JPanel();
        checkBoxesPanel.setLayout(new BorderLayout());
        JPanel locationChoices = new JPanel();
        locationChoices.setLayout(new FlowLayout());
        
        // add label, then the associated checkboxes to the right of it
        locationChoices.add(new JLabel("Location       "));
        for(int i=0; i<2; i++)
            locationChoices.add(searchLocation[i]);
        checkBoxesPanel.add(locationChoices, BorderLayout.NORTH);
        
        JPanel entryTypeChoices = new JPanel();
        entryTypeChoices.setLayout(new FlowLayout());
        
        // add label, then the associated checkboxes to the right of it
        entryTypeChoices.add(new JLabel("Entry Types  "));
        for(int i=0; i<3; i++)
            entryTypeChoices.add(entryTypesCheckBoxes[i]);
        checkBoxesPanel.add(entryTypeChoices, BorderLayout.SOUTH);
        
        return checkBoxesPanel;
    }
    
    
    /**
     * A private inner class that listens to the entryTypesComboBox created in createWindowComponents
     * @author Jennifer Winer
     */
    private class EntryTypeListener implements ActionListener{
        /**
         * changes the visible label to the one appropriate for the chosen entry type
         * @param e The ActionEvent that was triggered
         */
        public void actionPerformed(ActionEvent e){
            /**
             * Java was complaining about an unchecked cast, (which is not true - considering that I only have ONE listener object attached
             * to ONE combo box - the only thing that would ever be returned is a JComboBox)
             * So I added this annotation to stop it from fussing over nothing.
             */
            @SuppressWarnings("unchecked")
            JComboBox firedBox = (JComboBox)e.getSource();
            if(firedBox.getSelectedIndex() == 0){
                EntryTypeDependentLabels[0].setVisible(true);
                EntryTypeDependentLabels[1].setVisible(false);
                EntryTypeDependentLabels[2].setVisible(false);
            }
            else if(firedBox.getSelectedIndex() == 1){
                EntryTypeDependentLabels[0].setVisible(false);
                EntryTypeDependentLabels[1].setVisible(true);
                EntryTypeDependentLabels[2].setVisible(false);
            }
            else if(firedBox.getSelectedIndex() == 2){
                EntryTypeDependentLabels[0].setVisible(false);
                EntryTypeDependentLabels[1].setVisible(false);
                EntryTypeDependentLabels[2].setVisible(true);
            }
            addPanel.validate();
        }
    }
    
    /**
     * A private inner class that listens to all of the buttons placed in my GUI
     * @author Jennifer Winer
     */
    private class ButtonListener implements ActionListener{
        /**
         * Depending on which button was pressed, performs the action listed on the button 
         * @param e The ActionEvent that was triggered
         */
        public void actionPerformed(ActionEvent e){
            JButton firedButton = (JButton)e.getSource();
            
            if(firedButton.getText().trim().equals("Reset"))
                resetFields();
            if(firedButton.getText().trim().equals("Add"))
                MetaSearch.addProducts(getAddingUserInput());
            if(firedButton.getText().trim().equals("Search")){
                if(!searchLocation[0].isSelected() && !searchLocation[1].isSelected())
                    setSearchResults("SEARCHING ERROR:\n\nYou must select at least one location to search");
                else
                    MetaSearch.searchForProducts(getSearchParameters());
            }
                
        }
    }
    
    
    /**
     * A private inner class that listens to all of the menu options placed in my JMenuBar
     * @author Jennifer Winer
     */
    private class menuListener implements ActionListener{
        /**
         * Performs the action chosen from the menu
         * @param e The ActionEvent that was triggered
         */
        public void actionPerformed(ActionEvent e){
            String chosenAction = e.getActionCommand();
            if(chosenAction.equals("Add")){
                startPanel.setVisible(false);
                searchPanel.setVisible(false);
                addPanel.setVisible(true);
                validate();
            }
            else if(chosenAction.equals("Search")){
                startPanel.setVisible(false);
                addPanel.setVisible(false);
                searchPanel.setVisible(true);
                validate();
            }
            else if(chosenAction.equals("Quit")){
                safeguardWindowListener.windowClosing(null);
            }
            
            else if(chosenAction.equals("Save current collection to file"))
                MetaSearch.writeProductsToFile();
            else if(chosenAction.equals("Load prducts from file"))
                MetaSearch.loadProductsFromFile();
            else{
                MetaSearch.clearProductsList();
                resetFields();
                setErrorMessages("   Current collection is now empty");
                setSearchResults("   Current collection is now empty");
            }
        }
    }
    
    /**
     * Clears all text areas, text fields, and sets all of the check boxes and combo boxes to their original state
     */
    private void resetFields(){
        entryTypesComboBox.setSelectedIndex(0);
        
        for(int i=0; i<3; i++)
            entryTypesCheckBoxes[i].setSelected(true);

        for(int i=0; i<8; i++)
            dataEntryFields[i].setText("");

        searchLocation[0].setSelected(true);
        searchLocation[1].setSelected(false);

        for(int i=0; i<2; i++)
            messagesTextArea[i].setText("");

        validate();
    }
    
    
    /**
     * Gather all the user input and then concatenate it into an array of strings
     * @return An array of strings that can be sent to a constructor of the Book, Movie or Music classes
     */
    public String[] getAddingUserInput(){
        String[] entry = new String[6];
        
        entry[0] = (String)entryTypesComboBox.getSelectedItem();
        
        for(int i=0; i<5; i++)
            entry[i+1] = dataEntryFields[i].getText();
        
        for(int i=0; i<6; i++)
            entry[i] = entry[i].trim();
        
        return entry;
    } 
    
    /**
     * Gather all of the user input and concatenate it into an array of strings
     * @return 
     */
    public String[] getSearchParameters(){
        String[] parameters = new String[5];
        String searchTypes = "";
        
        if(searchLocation[0].isSelected() && searchLocation[1].isSelected())
            parameters[0] = "both";
        else if(searchLocation[0].isSelected())
            parameters[0] = "array";
        else
            parameters[0] = "internet";
        
        if(entryTypesCheckBoxes[0].isSelected())
            searchTypes = searchTypes + "Book ";
        if(entryTypesCheckBoxes[1].isSelected())
            searchTypes = searchTypes + "Movie ";
        if(entryTypesCheckBoxes[2].isSelected())
            searchTypes = searchTypes + "Music ";
        parameters[1] = searchTypes;
        
        for(int i=0; i<3; i++)
            parameters[i+2] = dataEntryFields[i+5].getText();
        
        for(int i=0; i<5; i++)
            parameters[i] = parameters[i].trim();
        
        return parameters;
    }
    
    /**
     * Send the String input to the JtextArea displayed in the add panel
     * @param messages The pre-formatted message that you want printed to the add JTextArea
     */
    public void setErrorMessages(String messages){
        if(messages.equals("  Product Successfully Added!"))
            resetFields();
        
        messagesTextArea[0].setText(messages);
        messagesTextArea[0].setCaretPosition(0);
    }
    
    /**
     * Send the String input to the JtextArea displayed in the search panel
     * @param searchResults The pre-formatted message that you want printed to the search JTextArea
     */
    public void setSearchResults(String searchResults){
        if(searchResults.equals(""))
            searchResults = "Your search yeilded no results.\nChange some of the search parameters and try again.";
        
        messagesTextArea[1].setText(searchResults);
        messagesTextArea[1].setCaretPosition(0);
    }
}