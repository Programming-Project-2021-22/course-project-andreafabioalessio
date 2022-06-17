import Exceptions.InvalidPasswordError;
import Exceptions.InvalidUsernameError;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

        /////////////////////////////////////////////////////////////
        ///                        MESSAGE                        ///
        ///             "storeUserListValues()" and               ///
        ///               "resetUserList() methods                ///
        ///       are used to keep the file "UsersList.txt        ///
        ///             unchanged while running tests             ///
        /////////////////////////////////////////////////////////////

public class SignupTest {

    User[] userArray;

    @BeforeAll
    static void storeUserListValues() throws IOException {

        //Copies all data besides old user data in myTempFile.txt
        File usersList = new File("src/UsersList.txt");
        File tempList = new File("src/tempUsersList.txt");

        BufferedReader reader = new BufferedReader(new FileReader(usersList));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempList));

        String currentLine = reader.readLine();

        while (currentLine != null) {
            writer.write(currentLine.trim());
            currentLine = reader.readLine();
            //if the next line is not null we go to a new line
            if (currentLine != null) {
                writer.write("\n");
            }
        }

        writer.close();
        reader.close();
    }

    //USERNAME TESTS
    @Test
    void newUsernameShouldWork(){
        User morty = new User("Morty", "Hello", 1);
        User rick = new User ("Rick", "PickleRick", 1);

        userArray = new User[1];
        userArray[0] = rick;

        assertDoesNotThrow(() -> {
            new Signup(new JFrame(), userArray).createButtonPress(new JFrame(), userArray, morty.getUsername(), morty.getPassword());
        });
    }

    @Test
    void alreadyExistingUsernameShouldThrowException(){
        User rick = new User ("Rick", "PickleRick", 1);
        User rick2 = new User ("Rick", "Another", 1);
        userArray = new User[1];
        userArray[0] = rick;

        assertThrowsExactly(InvalidUsernameError.class, () -> {
            new Signup(new JFrame(), userArray).createButtonPress(new JFrame(), userArray, rick2.getUsername(), rick2.getPassword());
        });
    }

    @Test
    void emptyUsernameShouldThrow(){
        User empty = new User ("", "PickleRick", 1);
        User rick = new User ("Rick", "PickleRick", 1);

        userArray = new User[1];
        userArray[0] = rick;

        assertThrowsExactly(InvalidUsernameError.class, () -> {
            new Signup(new JFrame(), userArray).createButtonPress(new JFrame(), userArray, empty.getUsername(), empty.getPassword());
        });
    }

    //PASSWORD TESTS
    @Test
    void RightPasswordShouldWork(){
        User MrMeeseks = new User("MrMeeseks", "Valid123", 1);
        User rick = new User ("Rick", "PickleRick", 1);

        userArray = new User[1];
        userArray[0] = rick;

        assertDoesNotThrow(() -> {
            new Signup(new JFrame(), userArray).createButtonPress(new JFrame(), userArray, MrMeeseks.getUsername(), MrMeeseks.getPassword());
        });
    }

    @Test
    void wrongPasswordFormat1() {
        User MrMeeseks = new User("MrMeeseks", "123456", 1);
        User rick = new User ("Rick", "PickleRick", 1);

        userArray = new User[1];
        userArray[0] = rick;

        assertThrowsExactly(InvalidPasswordError.class, () -> {
            new Signup(new JFrame(), userArray).createButtonPress(new JFrame(), userArray, MrMeeseks.getUsername(), MrMeeseks.getPassword());
        });
    }

    @Test
    void wrongPasswordFormat2() {
        User MrMeeseks = new User("MrMeeseks", "Password!", 1);
        User rick = new User ("Rick", "PickleRick", 1);

        userArray = new User[1];
        userArray[0] = rick;

        assertThrowsExactly(InvalidPasswordError.class, () -> {
            new Signup(new JFrame(), userArray).createButtonPress(new JFrame(), userArray, MrMeeseks.getUsername(), MrMeeseks.getPassword());
        });
    }

    @Test
    void wrongPasswordFormat3() {
        User MrMeeseks = new User("MrMeeseks", "ThisPasswordIsTooLong", 1);
        User rick = new User ("Rick", "PickleRick", 1);

        userArray = new User[1];
        userArray[0] = rick;

        assertThrowsExactly(InvalidPasswordError.class, () -> {
            new Signup(new JFrame(), userArray).createButtonPress(new JFrame(), userArray, MrMeeseks.getUsername(), MrMeeseks.getPassword());
        });
    }

    @AfterAll
    static void resetUserList() throws IOException {
        File usersList = new File("src/UsersList.txt");
        File tempList = new File("src/tempUsersList.txt");

        //Rewrites content of myTempFile to UsersList
        BufferedReader reader = new BufferedReader(new FileReader(tempList));
        BufferedWriter writer = new BufferedWriter(new FileWriter(usersList));

        String currentLine = reader.readLine();

        while(currentLine != null){
            writer.write(currentLine.trim());
            currentLine = reader.readLine();
            if(currentLine != null){
                writer.write("\n");
            }
        }

        writer.close();
        reader.close();
    }
}