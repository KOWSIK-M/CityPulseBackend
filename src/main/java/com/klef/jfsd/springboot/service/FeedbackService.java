package com.klef.jfsd.springboot.service;

import java.util.List;

import com.klef.jfsd.springboot.model.FeedbackDTO;
import com.klef.jfsd.springboot.model.Feedbacks;

public interface FeedbackService {
	public void submitFeedback(FeedbackDTO feedbackData);
	public Long countFeedbacks();
	public List<Feedbacks> getAllFeedbacks();
	public List<Feedbacks> getApprovedFeedbacks();
	public List<Feedbacks> getUnapprovedFeedbacks();
	public List<Feedbacks> getFeedbacksByApprovalStatus(boolean approved);
	public boolean approveFeedback(int feedbackId);
}
