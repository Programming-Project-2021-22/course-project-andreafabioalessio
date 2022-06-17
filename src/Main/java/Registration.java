import javax.swing.*;
import java.io.IOException;

public abstract class Registration extends JPanel {

    //Abstract method:
    // Checks if the username entered already exists or not in Signup and Login
    protected abstract boolean checkUserInArray(User[] userArray, String usernameEntered, String passwordEntered) throws WrongPasswordError, InvalidUsernameError;

    //Concrete methods:
    //Processes the press of the back button and goes back to Startup page
    protected void goBackToStartup(JFrame window) {
        Startup s = new Startup(window);
        window.getContentPane().removeAll();
        window.setTitle("Menù");
        window.setContentPane(s);
        window.revalidate();
        window.repaint();
    }

    //Gets the user from the array and starts menu window
    protected void getUser (User[] userArray, JFrame window, String usernameEntered) throws IOException {

        for (User u : userArray) {
            if(usernameEntered.equalsIgnoreCase(u.getUsername().trim())){
                openMenu(window, u);
            }
        }
    }

    //Opens the menu window
    protected void openMenu(JFrame window, User user) throws IOException {
        Menu m = new Menu(window, user);
        window.getContentPane().removeAll();
        window.setTitle("Login");
        window.setContentPane(m);
        window.revalidate();
        window.repaint();
    }
}