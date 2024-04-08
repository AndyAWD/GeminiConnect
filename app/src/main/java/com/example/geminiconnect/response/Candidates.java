package com.example.geminiconnect.response;

import java.util.List;

public class Candidates {
    private List<Candidate> candidates;

    public Candidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }
}
