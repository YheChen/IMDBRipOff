package entity;
import java.util.ArrayList;
import java.util.List;

public class User
{
    String userID;
    String username;
    String password;
    List<String> reviewHistory; // this might change

    public User(String username, String password){
        this.userID = generateUserID();
        this.username = username;
        this.password = password;
        this.reviewHistory = new ArrayList<String>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    private String generateUserID(){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        final int USERID_LENGTH = 5;
        StringBuilder sb = new StringBuilder(USERID_LENGTH);

        for (int i = 0; i < USERID_LENGTH; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
