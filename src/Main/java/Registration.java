import Exceptions.InvalidUsernameError;
import Exceptions.WrongPasswordError;

import javax.swing.*;
import java.io.IOException;

public abstract class Registration extends JPanel {

    //Abstract method:
    /***
     * Checks if the username entered already exists or not in Signup and Login
     * @param userArray: array containing all registered Users
     * @param usernameEntered: username provided by the player in textField
     * @param passwordEntered: password provided by the player in textField
     * @return = true if the username matches one already existing in the array, false otherwise
     * @throws WrongPasswordError = During login, if the player inputs the right username but the wrong password
     * @throws InvalidUsernameError = During login, if the username entered is not registered
     */
    protected abstract boolean checkUserInArray(User[] userArray, String usernameEntered, String passwordEntered) throws WrongPasswordError, InvalidUsernameError;

    //Concrete methods:

    /***
     * Processes the press of the back button and goes back to Startup page
     * @param window: window on which the Startup page gets painted on
     */
    protected void goBackToStartup(JFrame window) {
        Startup s = new Startup(window);
        window.getContentPane().removeAll();
        window.setTitle("Menù");
        window.setContentPane(s);
        window.revalidate();
        window.repaint();
    }

    /***
     * Gets the user from the array and starts menu window
     * @param userArray: array containing all registered Users
     * @param window: window on which components will get painted on
     * @param usernameEntered: username provided by the player in textField
     * @throws IOException: thrown by the openMenu(JFrame window, User user) method
     */
    protected void getUser (User[] userArray, JFrame window, String usernameEntered) throws IOException {

        for (User u : userArray) {
            if(usernameEntered.equalsIgnoreCase(u.getUsername().trim())){
                openMenu(window, u);
            }
        }
    }

    /***
     * Opens the menu window
     * @param window: window on which the Menu page gets painted on
     * @param user: the logged-in User
     * @throws IOException: thrown by one of the methods in the Menu class, because of a ImageIO.read() call in the updateLevelGraphics(User user) method
     */
    protected void openMenu(JFrame window, User user) throws IOException {
        Menu m = new Menu(window, user);
        window.getContentPane().removeAll();
        window.setTitle("Login");
        window.setContentPane(m);
        window.revalidate();
        window.repaint();
    }
}