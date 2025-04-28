package com.example.ErrandService.service;

import com.example.ErrandService.model.Feedback;
import com.example.ErrandService.repo.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    public Feedback submitFeedback(Long errandId, Long userId, String feedbackText, int rating) {
        Feedback feedback = new Feedback();
        feedback.setErrandId(errandId);
        feedback.setUserId(userId);
        feedback.setFeedbackText(feedbackText);
        feedback.setRating(rating);

        return feedbackRepository.save(feedback);
    }
}
