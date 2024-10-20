package glsib.carpooling.services.Impl;

import glsib.carpooling.entities.Feedback;
import glsib.carpooling.repositories.FeedbackRepository;
import glsib.carpooling.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public List<Feedback> getFeedbacksForRide(Long rideId) {
        return feedbackRepository.findAll().stream()
                .filter(feedback -> feedback.getRide().getId() == rideId)
                .toList();
    }
}
