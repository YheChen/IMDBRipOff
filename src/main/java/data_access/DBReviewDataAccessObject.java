package data_access;

import entity.Review;
import entity.User;
import org.bson.Document;
import use_case.write_review.WriteReviewDataAccessInterface;

import java.util.Date;

import static com.mongodb.client.model.Filters.eq;

public class DBReviewDataAccessObject implements WriteReviewDataAccessInterface {
    private static final String COLLECTION = "reviews";
    private static final String ID_FIELD = "_id";
    private static final String USER_ID_FIELD = "user_id";
    private static final String MEDIA_ID_FIELD = "media_id";
    private static final String CONTENT_FIELD = "content";
    private static final String RATING_FIELD = "rating";
    private static final String CREATED_FIELD = "created";
    private static final String UPDATED_FIELD = "updated";

    @Override
    public boolean existsByID(String id) {
        try (MongoDBClient db = new MongoDBClient()) {
            Document document = db.getCollection(COLLECTION).find(eq(ID_FIELD, id)).first();
            return document != null;
        }
    }

    @Override
    public void save(Review review) {
        try (MongoDBClient db = new MongoDBClient()) {
            Document document = new Document()
                    .append(ID_FIELD, review.getReviewID())
                    .append(USER_ID_FIELD, review.getUserID())
                    .append(MEDIA_ID_FIELD, review.getMediaID())
                    .append(CONTENT_FIELD, review.getContent())
                    .append(RATING_FIELD, review.getRating())
                    .append(CREATED_FIELD, review.getDateCreated())
                    .append(UPDATED_FIELD, review.getDateUpdated());
            db.getCollection(COLLECTION).insertOne(document);
        }
    }

    @Override
    public Review get(String id) {
        try (MongoDBClient db = new MongoDBClient()) {
            Document document = db.getCollection(COLLECTION).find(eq(ID_FIELD, id)).first();
            if (document != null) {
                String userId = document.getString(USER_ID_FIELD);
                String mediaId = document.getString(MEDIA_ID_FIELD);
                String content = document.getString(CONTENT_FIELD);
                int rating = document.getInteger(RATING_FIELD);
                Date dateCreated = document.getDate(CREATED_FIELD);
                return new Review(id, userId, mediaId, content, rating, dateCreated);
            }
        }
        return null;
    }
}
