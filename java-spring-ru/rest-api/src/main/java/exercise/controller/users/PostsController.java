package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api/users")
public class PostsController {
    private final List<Post> posts = Data.getPosts();

    @GetMapping("/{id}/posts")
    public List<Post> show(@PathVariable Integer id) {
        return posts.stream().filter(p -> p.getUserId() == id).toList();
    }

    @PostMapping("/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable Integer id, @RequestBody Post newPost) {
        Post post = new Post();
        post.setTitle(newPost.getTitle());
        post.setBody(newPost.getBody());
        post.setSlug(newPost.getSlug());
        post.setUserId(id);
        posts.add(post);
    }
}
// END
