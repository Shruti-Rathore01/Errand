package com.example.ErrandService.controller;

import com.example.ErrandService.model.Feedback;
import com.example.ErrandService.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // Endpoint to submit feedback for a completed errand
    @PostMapping("/submit/{errandId}/{userId}")
    public ResponseEntity<Feedback> submitFeedback(@PathVariable Long errandId, @PathVariable Long userId,
                                                   @RequestBody Feedback feedb) {
        Feedback feedback = feedbackService.submitFeedback(errandId, userId, feedb.getFeedbackText(), feedb.getRating());
        return ResponseEntity.status(HttpStatus.CREATED).body(feedback);
    }
}
