
import Exceptions.InvalidUsernameError;
import Exceptions.WrongPasswordError;
import org.junit.jupiter.api.Test;
import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    User[] userArray;

    @Test
    void loginWithExistingUser(){
        User morty = new User("Morty", "Hello", 1);

        userArray = new User[1];
        userArray[0] = morty;

        assertDoesNotThrow(() -> {
            new Login(new JFrame(), userArray).checkUserInArray(userArray, morty.getUsername(), morty.getPassword());
        });
    }

    @Test
    void loginWithWrongPasswordShouldThrow(){
        User morty = new User("Morty", "Hello", 1);

        userArray = new User[1];
        userArray[0] = morty;

        assertThrowsExactly(WrongPasswordError.class, () -> {
            new Login(new JFrame(), userArray).checkUserInArray(userArray, morty.getUsername(), "Hello2");
        });
    }

    @Test
    void loginWithNonExistingUserShouldThrow(){
        User morty = new User("Morty", "Hello", 1);
        User rick = new User("Rick", "PickleRick", 1);

        userArray = new User[1];
        userArray[0] = morty;

        assertThrowsExactly(InvalidUsernameError.class, () -> {
            new Login(new JFrame(), userArray).checkUserInArray(userArray, rick.getUsername(), rick.getPassword());
        });
    }
}
