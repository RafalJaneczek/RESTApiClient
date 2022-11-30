package pl.neverendingcode.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.neverendingcode.model.Post;

@Slf4j
@Component
public class PostConverter {

    private final Gson gson = new GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    public Post convertToPost(String json) {
        log.info("\nConverting post from json: \n" + json + " \nto object");
        return gson.fromJson(json, Post.class);
    }

}
