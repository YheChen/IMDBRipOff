package data_access;

import entity.Review;
import org.bson.Document;
import org.bson.conversions.Bson;
import use_case.browse_reviews.BrowseReviewDataAccessInterface;
import use_case.write_review.WriteReviewDataAccessInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Sorts.ascending;

public class DBReviewDataAccessObject implements BrowseReviewDataAccessInterface, WriteReviewDataAccessInterface {
    private static final String COLLECTION = "reviews";
    private static final String ID_FIELD = "_id";
    private static final String USER_ID_FIELD = "user_id";
    private static final String MEDIA_ID_FIELD = "media_id";
    private static final String CONTENT_FIELD = "content";
    private static final String RATING_FIELD = "rating";
    private static final String CREATED_FIELD = "created";
    private static final String UPDATED_FIELD = "updated";

    private Review documentToReview(Document document) {
        if (document != null) {
            String id = document.getString(ID_FIELD);
            String userId = document.getString(USER_ID_FIELD);
            String mediaId = document.getString(MEDIA_ID_FIELD);
            String content = document.getString(CONTENT_FIELD);
            int rating = document.getInteger(RATING_FIELD);
            Date dateCreated = document.getDate(CREATED_FIELD);
            return new Review(id, userId, mediaId, content, rating, dateCreated);
        }
        return null;
    }

    @Override
    public boolean existsByID(String id) {
        try (MongoDBClient db = new MongoDBClient()) {
            Document document = db.getCollection(COLLECTION).find(eq(ID_FIELD, id)).first();
            return document != null;
        }
    }

    @Override
    public Review get(String id) {
        try (MongoDBClient db = new MongoDBClient()) {
            Document document = db.getCollection(COLLECTION).find(eq(ID_FIELD, id)).first();
            return documentToReview(document);
        }
    }

    @Override
    public Collection<Review> getAll() {
        return getAllSorted("recent", null);
    }

    @Override
    public Collection<Review> getAllSorted(String orderBy, String searchText) {
        Bson sortBy;
        switch (orderBy) {
            case "recent":
                sortBy = descending(UPDATED_FIELD);
                break;
            case "highScore":
                sortBy = descending(RATING_FIELD);
                break;
            case "lowScore":
                sortBy = ascending(RATING_FIELD);
                break;
            default:
                sortBy = ascending(ID_FIELD);
                break;
        }

        try (MongoDBClient db = new MongoDBClient()) {
            ArrayList<Review> reviews = new ArrayList<>();
            db.getCollection(COLLECTION).find().sort(sortBy).limit(100).forEach(document -> {
                Review review = documentToReview(document);
                if (review != null) {
                    reviews.add(review);
                }
            });
            return reviews;
        }
    }

    @Override
    public long count() {
        try (MongoDBClient db = new MongoDBClient()) {
            return db.getCollection(COLLECTION).countDocuments();
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
}
