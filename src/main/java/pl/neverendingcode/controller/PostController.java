package pl.neverendingcode.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.neverendingcode.model.Post;
import pl.neverendingcode.service.IntegrationService;

@RestController
@RequestMapping(value = "/post")
@RequiredArgsConstructor
public class PostController {

    private final IntegrationService integrationService;

    @GetMapping("/get")
    public ResponseEntity<String> getPosts() {
        return integrationService.getPosts();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getPost(@PathVariable("id") int id) {
        return integrationService.getPostById(id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removePost(@PathVariable("id") int id) {
        return integrationService.removePost(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        return integrationService.addPost(post);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePost(@RequestBody Post post, @PathVariable("id") int id) {
        return integrationService.updatePost(post, id);
    }

}
