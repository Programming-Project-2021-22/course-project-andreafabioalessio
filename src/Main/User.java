package Main;

public class User {

    private String username;
    private String password;
    int level = 1;

    public User (String username, String password, int level){
        this.username = username;
        this.password = password;
        this.level = level;
    }

    public String getPassword() {
        return password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

}