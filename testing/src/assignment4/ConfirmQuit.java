/**
 * Description: Assignment #4
 * @author Jennfier Winer
 * @since 2011-9-23
 * Created: September 23, 2011
 * Last Modified: December 4, 2011
 */
package assignment4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class listens to my main window and makes sure that the user really meant to quit before terminating my program
 * @author Jennifer
 */
public class ConfirmQuit implements WindowListener{
    /**
     * What to do when the window has been opened
     * @param e The event that was triggered by the opening of the window
     */
    public void windowOpened(WindowEvent e){}
    
    /**
     * What to do when the window has been asked to close
     * @param e The event that was triggered by trying to close the window
     */
    public void windowClosing(WindowEvent e){
        SafeguardWindow doubleCheck = new SafeguardWindow();
        doubleCheck.setVisible(true);
    }

    /**
     * What to do when the window has been closed
     * @param e The event that was triggered by the closing of the window
     */
    public void windowClosed(WindowEvent e){}
    /**
     * What to do when the window has been iconified (minimized)
     * @param e The event that was triggered by the iconification of the window
     */
    public void windowIconified(WindowEvent e){}
    /**
     * What to do when the window has been deiconified (maximized)
     * @param e The event that was triggered by the deiconification of the window
     */
    public void windowDeiconified(WindowEvent e){}
    /**
     * What to do when the window has been activated
     * @param e The event that was triggered by the activation of the window
     */
    public void windowActivated(WindowEvent e){}
    /**
     * What to do when the window has been deactivated
     * @param e The event that was triggered by the deactivation of the window
     */
    public void windowDeactivated(WindowEvent e){}
    
    /**
     * A private inner class that contains the pop-up double check window
     */
    private class SafeguardWindow extends JFrame implements ActionListener {
        /**
         * a no-argument constructor for the pop-up window
         */
        public SafeguardWindow(){
            super("Double Checking");
            setSize(325,150);
            setResizable(false);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new FlowLayout());
            setLocationRelativeTo(null);
            
            JPanel checkPage = new JPanel();
            checkPage.setLayout(new BoxLayout(checkPage, BoxLayout.Y_AXIS));
            
            JPanel messagesPanel = new JPanel();
            messagesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            messagesPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            
            JLabel message = new JLabel("Are you sure that you want to quit?");
            message.setFont(new Font("Arial", Font.BOLD, 15));
            messagesPanel.add(message);
            checkPage.add(messagesPanel);
            checkPage.add(Box.createRigidArea(new Dimension(0, 20)));
            
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new FlowLayout());
            
            JButton yesButton = new JButton("Yes");
            JButton noButton = new JButton("No");
            
            yesButton.addActionListener(this);
            noButton.addActionListener(this);
            
            buttonsPanel.add(yesButton);
            buttonsPanel.add(Box.createRigidArea(new Dimension(40, 0)));
            buttonsPanel.add(noButton);
            checkPage.add(buttonsPanel);
            
            add(checkPage, BorderLayout.WEST);
        }
        
        /**
         * What to do if the user presses the yes or no buttons
         * @param e The ActionEvent that was triggered by the user
         */
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Yes"))
                System.exit(0);
            else if(e.getActionCommand().equals("No"))
                dispose();
        }
    }
}
