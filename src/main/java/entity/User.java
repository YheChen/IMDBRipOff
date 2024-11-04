package main.java.entity;
import java.util.List;

public class User
{
    String userID;
    String username;
    String password;
    List<String> reviewHistory; // this might change

    public User(String userID, String username, String password, List<String> reviewHistory){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.reviewHistory = reviewHistory;
    }
}
