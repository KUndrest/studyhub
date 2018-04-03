package ee.ttu.studyhub.controllers;

import ee.ttu.studyhub.entity.Post;
import ee.ttu.studyhub.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value="/posts/add", method= RequestMethod.POST,
            consumes = "application/json")
    public Post addPost(@RequestBody Post post) {
        return postService.addPost(post);
    }

    @RequestMapping(value="/posts", method=RequestMethod.GET)
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @RequestMapping(value = "/posts/{id}", method=RequestMethod.GET)
    public Post getPost(@PathVariable("id") long postId) {
        return postService.getPostById(postId);
    }

    @DeleteMapping(value = "/posts/{id}")
    public void removePost(@PathVariable("id") long postId) { postService.removePost(postId); }
}
