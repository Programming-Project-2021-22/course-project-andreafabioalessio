public class User {

    private final String username;
    private final String password;
    private int level = 1;

    public User (String username, String password, int level){
        this.username = username;
        this.password = password;
        this.level = level;
    }

    /***
     * Gets the username of this user
     * @return the User's username
     */
    public String getUsername() {
        return username;
    }

    /***
     * Gets the password of this user
     * @return the User's password
     */
    public String getPassword() {
        return password;
    }

    /***
     * Gets the level of this user
     * @return the User's level value
     */
    public int getLevel() {
        return level;
    }

    /***
     * Sets the user's level to a new value
     * @param level the new User's level value
     */
    public void setLevel(int level) {
        this.level = level;
    }
}