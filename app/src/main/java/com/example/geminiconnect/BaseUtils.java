package com.example.geminiconnect;

import androidx.annotation.NonNull;

import com.example.geminiconnect.request.Content;
import com.example.geminiconnect.request.Part;
import com.example.geminiconnect.request.Request;

import java.util.ArrayList;

public class BaseUtils {
    public static BaseUtils getInstance() {
        return BaseUtilsHolder.BASE_UTILS_HOLDER;
    }

    private static class BaseUtilsHolder {
        private static final BaseUtils BASE_UTILS_HOLDER = new BaseUtils();
    }

    public String getUrl(String model, String key){
        StringBuilder url;
        url = new StringBuilder();
        url.append(BaseContent.URL);
        url.append(model);
        url.append(BaseContent.generate_content_key);
        url.append(key);
        return url.toString();
    }

    @NonNull
    public Request getRequestObject(String message) {
        ArrayList<Part> parts = new ArrayList<>();
        parts.add(new Part(message));
        ArrayList<Content> contents = new ArrayList<>();
        contents.add(new Content(parts));
        Request request;
        request = new Request(contents);
        return request;
    }
}
