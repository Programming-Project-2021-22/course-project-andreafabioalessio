package Exeptions;

public class LevelTooLowError extends ArithmeticException {

    public LevelTooLowError(String m){
        super(m);
    }
}
