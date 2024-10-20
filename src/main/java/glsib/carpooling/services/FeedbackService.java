package glsib.carpooling.services;

import glsib.carpooling.entities.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback createFeedback(Feedback feedback);
    List<Feedback> getAllFeedbacks();
    List<Feedback> getFeedbacksForRide(Long rideId);
}
