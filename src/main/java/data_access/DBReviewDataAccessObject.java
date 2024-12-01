package data_access;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import entity.Review;
import use_case.browse_reviews.BrowseReviewDataAccessInterface;
import use_case.write_review.WriteReviewDataAccessInterface;

/**
 * DAO for Reviews in the database.
 */
public class DBReviewDataAccessObject implements BrowseReviewDataAccessInterface, WriteReviewDataAccessInterface {
    private static final String COLLECTION = "reviews";
    private static final String ID_FIELD = "_id";
    private static final String USER_ID_FIELD = "user_id";
    private static final String MEDIA_ID_FIELD = "media_id";
    private static final String CONTENT_FIELD = "content";
    private static final String RATING_FIELD = "rating";
    private static final String CREATED_FIELD = "created";
    private static final String UPDATED_FIELD = "updated";

    private static final int QUERY_LIMIT = 100;

    /**
     * Converts a bson Document into a review object.
     * @param document the document to convert
     * @return a review
     */
    private Review documentToReview(Document document) {
        Review review = null;
        if (document != null) {
            final String id = document.getString(ID_FIELD);
            final String userId = document.getString(USER_ID_FIELD);
            final String mediaId = document.getString(MEDIA_ID_FIELD);
            final String content = document.getString(CONTENT_FIELD);
            final int rating = document.getInteger(RATING_FIELD);
            final Date dateCreated = document.getDate(CREATED_FIELD);
            review = new Review(id, userId, mediaId, content, rating, dateCreated);
        }
        return review;
    }

    @Override
    public boolean existsByID(String id) {
        try (MongoDBClient db = new MongoDBClient()) {
            final Document document = db.getCollection(COLLECTION).find(Filters.eq(ID_FIELD, id)).first();
            return document != null;
        }
    }

    @Override
    public Review get(String id) {
        try (MongoDBClient db = new MongoDBClient()) {
            final Document document = db.getCollection(COLLECTION).find(Filters.eq(ID_FIELD, id)).first();
            return documentToReview(document);
        }
    }

    @Override
    public Collection<Review> getAll() {
        return getAllSorted("recent", null);
    }

    @Override
    public Collection<Review> getAllSorted(String orderBy, String searchText) {
        try (MongoDBClient db = new MongoDBClient()) {
            final ArrayList<Review> reviews = new ArrayList<>();
            FindIterable<Document> query = db.getCollection(COLLECTION).find();
            if (orderBy != null) {
                final Bson sortBy;
                switch (orderBy) {
                    case "recent":
                        sortBy = Sorts.descending(UPDATED_FIELD);
                        break;
                    case "highScore":
                        sortBy = Sorts.descending(RATING_FIELD);
                        break;
                    case "lowScore":
                        sortBy = Sorts.ascending(RATING_FIELD);
                        break;
                    default:
                        sortBy = Sorts.ascending(ID_FIELD);
                        break;
                }
                query = query.sort(sortBy);
            }
            if (searchText != null) {
                query = query.filter(Filters.text(searchText));
            }
            query.limit(QUERY_LIMIT).forEach(document -> {
                final Review review = documentToReview(document);
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
            final Document document = new Document()
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
