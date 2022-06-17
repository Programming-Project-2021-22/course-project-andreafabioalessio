package Exceptions;

import java.io.IOException;

public class WrongPasswordError extends IOException {

    /***
     * Thrown when the username entered matches with one registered, but the password does not
     * @param m: String to be displayed when the exception is thrown
     */
    public WrongPasswordError (String m){
        super(m);
    }
}