package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A user entity.
 */
public class User {
    private static final int USERID_LENGTH = 5;

    private String userID;
    private String username;
    private String password;
    private List<String> reviewHistory;

    public User(String username, String password) {
        this.userID = generateUserID();
        this.username = username;
        this.password = password;
        this.reviewHistory = new ArrayList<String>();
    }

    public User(String userId, String username, String password) {
        this.userID = userId;
        this.username = username;
        this.password = password;
        this.reviewHistory = new ArrayList<String>();
    }

    public String getId() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    private String generateUserID() {
        final String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of alphaNumericString
        final StringBuilder sb = new StringBuilder(USERID_LENGTH);

        for (int i = 0; i < USERID_LENGTH; i++) {

            // generate a random number between
            // 0 to alphaNumericString variable length
            final int index = (int)
                    (alphaNumericString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(alphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
