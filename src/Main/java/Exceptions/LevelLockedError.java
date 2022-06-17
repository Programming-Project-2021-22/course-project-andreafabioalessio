package Exceptions;

public class LevelLockedError extends ArithmeticException {

    /***
     * Thrown when the level selected is not unlocked
     * @param m: String to be displayed when the exception is thrown
     */
    public LevelLockedError(String m){
        super(m);
    }
}