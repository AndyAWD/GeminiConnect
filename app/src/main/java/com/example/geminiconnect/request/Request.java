package com.example.geminiconnect.request;

import java.util.List;

public class Request {
    private List<Content> contents;

    public Request(List<Content> contents) {
        this.contents = contents;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }
}
