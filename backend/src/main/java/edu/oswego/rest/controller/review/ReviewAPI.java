package edu.oswego.rest.controller.review;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import edu.oswego.rest.objects.Answer;
import edu.oswego.rest.objects.Review;
import edu.oswego.rest.service.IAnswerService;
import edu.oswego.rest.service.IReviewService;
import edu.oswego.rest.service.impl.AnswerService;
import edu.oswego.rest.service.impl.ReviewService;

import javax.ws.rs.*;
import java.util.List;

@Path("/review")
public class ReviewAPI {
    private static final long serialVersionUID = 1L;
    private IReviewService reviewService;
    private Gson gson = new Gson();

    public ReviewAPI() {
        reviewService = new ReviewService();
    }

    @GET
    public String getAllReviews(){
        //TODO This method needs to ensure authentication
        try {
            List<Review> listOfReviews = reviewService.findAll();
            String res = gson.toJson(listOfReviews);
            if(listOfReviews != null) return res;
            else return "There are not any reviews in the database.";
        } catch (NumberFormatException ne){
            System.out.println("There are not any reviews in the database.");
        }
        return null;
    }

    @GET
    @Path("/{reviewId}")
    public String getSpecificAnswer(@PathParam("reviewId") String _reviewId){
        //TODO This method needs to ensure authentication
        try {

            int reviewId = Integer.parseInt(_reviewId);
            Review review = reviewService.findOne(reviewId);
            String res = gson.toJson(review);
            if(review != null) return res;
            else return "Review ID provided was not formatted properly.";
        } catch (NumberFormatException ne){
            System.out.println("Review ID provided was not formatted properly.");
        }
        return null;
    }
    @POST
    public String postReview(String payload) throws JsonProcessingException {
        Review review = gson.fromJson(payload, Review.class);
        review = reviewService.save(review);
        String res = gson.toJson(review);
        return res;
    }

    @PUT
    public String updateReview(String payload) throws JsonProcessingException {
        Review review = gson.fromJson(payload, Review.class);
        review = reviewService.update(review);
        String res = gson.toJson(review);
        return res;
    }

    @DELETE
    @Path("/{reviewId}")
    public String deleteSpecificReview(@PathParam("reviewId") String _reviewId){
        //TODO This method needs to ensure authentication
        try {

            int reviewId = Integer.parseInt(_reviewId);
            Review review = reviewService.findOne(reviewId);
            review = reviewService.delete(review);
            String res = gson.toJson(review);
            if(review != null) return res;
            else return "Review ID provided was not formatted properly.";
        } catch (NumberFormatException ne){
            System.out.println("Review ID provided was not formatted properly.");
        }
        return null;
    }
}
