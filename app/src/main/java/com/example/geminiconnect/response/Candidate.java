package com.example.geminiconnect.response;

import java.util.List;

public class Candidate {
    private int index;
    private Content content;
    private String finishReason;

    public Candidate(int index, Content content, String finishReason, List<SafetyRating> safetyRatings) {
        this.index = index;
        this.content = content;
        this.finishReason = finishReason;
        this.safetyRatings = safetyRatings;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setSafetyRatings(List<SafetyRating> safetyRatings) {
        this.safetyRatings = safetyRatings;
    }

    private List<SafetyRating> safetyRatings;

    public Content getContent() {
        return content;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public int getIndex() {
        return index;
    }

    public List<SafetyRating> getSafetyRatings() {
        return safetyRatings;
    }


}
