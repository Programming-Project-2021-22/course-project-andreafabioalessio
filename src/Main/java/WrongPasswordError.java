import java.io.IOException;

public class WrongPasswordError extends IOException {

    public WrongPasswordError (String m){
        super(m);
    }
}