package pl.neverendingcode.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.neverendingcode.converter.PostConverter;
import pl.neverendingcode.model.Post;

import java.net.URI;
import java.util.List;

@Service
@Slf4j
public class IntegrationService {

    @Value("${posts.api.url}")
    private String postsApiUrl;
    private final RestTemplate restTemplate;
    private final HttpHeaders headers;
    private final PostConverter postConverter;

    public IntegrationService(RestTemplate restTemplate, PostConverter postConverter) {
        this.restTemplate = restTemplate;
        this.postConverter = postConverter;

        headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    }

    public ResponseEntity<String> getPosts() {
        log.info("Get posts");
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        String response = restTemplate.exchange(postsApiUrl + "/posts", HttpMethod.GET, httpEntity, String.class).getBody();
        log.info(response);

        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<String> getPostById(int id) {
        log.info("Get post by id: " + id);
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        String response = restTemplate.exchange(postsApiUrl + "/posts/" + id, HttpMethod.GET, httpEntity, String.class).getBody();
        log.info(response);

        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<?> removePost(int id) {
        log.info("Remove post with id: " + id);
        HttpEntity<Post> httpEntity = new HttpEntity<Post>(headers);
        String response = restTemplate.exchange(postsApiUrl + "/posts/" + id, HttpMethod.DELETE, httpEntity, String.class).getBody();

        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<Post> addPost(Post post) {
        HttpEntity<Post> httpEntity = new HttpEntity<Post>(post, headers);
        String response = restTemplate.exchange(postsApiUrl + "/posts", HttpMethod.POST, httpEntity, String.class).getBody();
        Post createdPost = postConverter.convertToPost(response);

        return ResponseEntity.created(URI.create("/posts/get/" + createdPost.getId())).body(createdPost);
    }

    public ResponseEntity<String> updatePost(Post post, int id) {
        HttpEntity<Post> httpEntity = new HttpEntity<Post>(post, headers);
        String response = restTemplate.exchange(postsApiUrl + "/posts/" + id, HttpMethod.PUT, httpEntity, String.class).getBody();

        return ResponseEntity.ok().body(response);
    }

}
