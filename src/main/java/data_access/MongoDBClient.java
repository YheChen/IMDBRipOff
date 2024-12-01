package data_access;

import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * A client to interface with MongoDB.
 */
public class MongoDBClient implements AutoCloseable {
    private final MongoClient client;
    private final MongoDatabase database;

    MongoDBClient() {
        final String connectionString = System.getProperty("MONGODB_CONNECTION");
        this.client = MongoClients.create(connectionString);
        this.database = client.getDatabase("imdbripoff");
        System.out.println("DB connection succeeded");
    }

    /**
     * Get a MongoDB collection by name.
     * @param name the collection name
     * @return a collection of documents
     */
    public MongoCollection<Document> getCollection(String name) {
        return this.database.getCollection(name);
    }

    /* public static void main(String[] args) {
        Dotenv.configure().systemProperties().load();
        try (MongoDBClient db = new MongoDBClient()) {
            for (String name : db.database.listCollectionNames()) {
                System.out.println(name);
            }
        }
    }*/

    @Override
    public void close() {
        client.close();
    }
}