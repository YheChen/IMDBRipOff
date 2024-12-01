package data_access;

import org.bson.Document;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import entity.User;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * DAO for Users in database.
 */
public class DBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface {
    private static final String COLLECTION = "users";
    private static final String ID_FIELD = "_id";
    private static final String USERNAME_FIELD = "username";
    private static final String PASSWORD_FIELD = "password";

    private String currentUsername;

    @Override
    public boolean existsByName(String username) {
        try (MongoDBClient db = new MongoDBClient()) {
            final Document document = db.getCollection(COLLECTION).find(Filters.eq(USERNAME_FIELD, username)).first();
            return document != null;
        }
    }

    @Override
    public void save(User user) {
        try (MongoDBClient db = new MongoDBClient()) {
            final Document document = new Document()
                    .append(ID_FIELD, user.getId())
                    .append(USERNAME_FIELD, user.getUsername())
                    .append(PASSWORD_FIELD, user.getPassword());
            db.getCollection(COLLECTION).insertOne(document);
        }
    }

    @Override
    public User get(String username) {
        User user = null;
        try (MongoDBClient db = new MongoDBClient()) {
            final Document document = db.getCollection(COLLECTION).find(Filters.eq(USERNAME_FIELD, username)).first();
            if (document != null) {
                final String id = document.getString(ID_FIELD);
                final String password = document.getString(PASSWORD_FIELD);
                user = new User(id, username, password);
            }
        }
        return user;
    }

    @Override
    public void changePassword(User user) {
        // Replace the old entry with the new password
        try (MongoDBClient db = new MongoDBClient()) {
            db.getCollection(COLLECTION).updateOne(
                    Filters.eq(ID_FIELD, user.getId()),
                    Updates.set(PASSWORD_FIELD, user.getPassword())
            );
        }
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }
}
