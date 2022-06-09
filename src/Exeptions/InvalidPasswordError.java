package Exeptions;

import java.io.IOException;

public class InvalidPasswordError extends IOException {

    public InvalidPasswordError(String m){
        super(m);
    }
}
