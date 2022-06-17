package Exceptions;

import java.io.IOException;

public class InvalidUsernameError extends IOException {

    /***
     * Thrown when the username does not match the requirements
     * @param m: String to be displayed when the exception is thrown
     */
    public InvalidUsernameError (String m){
        super(m);
    }
}