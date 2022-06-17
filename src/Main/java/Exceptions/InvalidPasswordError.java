package Exceptions;

import java.io.IOException;

public class InvalidPasswordError extends IOException {

    /***
     * Thrown when the password does not match the requirements
     * @param m: String to be displayed when the exception is thrown
     */
    public InvalidPasswordError(String m){
        super(m);
    }
}
