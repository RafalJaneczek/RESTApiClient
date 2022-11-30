package pl.neverendingcode.converter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.neverendingcode.model.Post;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class PostConverterTest {

    @Autowired
    private PostConverter postConverter;

    @Test
    void convert_to_post() {
        // given
        Post post = Post.builder()
                .userId(1)
                .id(101)
                .title("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")
                .body("quia et suscipit nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam nnostrum rerum est autem sunt rem eveniet architecto")
                .build();
        String json = "{\"userId\":1,\"id\":101,\"title\":\"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\"body\":\"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"}";
        // when
        Post result = postConverter.convertToPost(json);

        // then
        assertThat(post.getId(), equalTo(result.getId()));
    }

}