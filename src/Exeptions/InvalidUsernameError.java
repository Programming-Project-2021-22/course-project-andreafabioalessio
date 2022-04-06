package Exeptions;

import java.io.IOException;

public class InvalidUsernameError extends IOException {

    public InvalidUsernameError (String m){
        super(m);
    }
}
