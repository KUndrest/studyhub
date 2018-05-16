package ee.ttu.studyhub.controllers;

import ee.ttu.studyhub.entity.Person;
import ee.ttu.studyhub.entity.Post;
import ee.ttu.studyhub.entity.PostDTO;
import ee.ttu.studyhub.entity.Subject;
import ee.ttu.studyhub.service.PersonService;
import ee.ttu.studyhub.service.PostService;
import ee.ttu.studyhub.service.SubjectPersonService;
import ee.ttu.studyhub.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@RestController
public class PostController {

    @PersistenceContext
    private EntityManager em;

    private PostService postService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectPersonService subjectPersonService;

    @Autowired
    private PersonService personService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/posts/add", method = RequestMethod.POST,
            consumes = "application/json")
    public Post addPost(@RequestBody PostDTO postDTO) {
        Post post = new Post();
        post.setContent(postDTO.getContent());
        post.setCreated(postDTO.getCreated());
        post.setDate(postDTO.getDate());
        post.setSubject(postDTO.getSubject());
        post.setTitle(postDTO.getTitle());
        post.setId(postDTO.getId());

        return postService.addPost(post);
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @RequestMapping(value = "/posts/{id}", method=RequestMethod.GET)
    public List<Post> getHeaders(@PathVariable("id") long subjectId) {
        Subject subject = subjectService.getSubjectById(subjectId);
        return postService.findBySubject(subject);
    }

    @GetMapping(value = "/person-posts/{id}")
    public List<Post> getPersonPosts(@PathVariable("id") long personId) {
        Person person = personService.getPersonById(personId);
        return postService.findAllBySubjectPerson(person);
    }

    @DeleteMapping(value = "/posts/{id}")
    public List<Post> removePost(@PathVariable("id") long postId) {
        postService.removePost(postId);
        return getAllPosts();
    }

    @Transactional
    @PostMapping(value = "/post")
    public Post updatePost(@RequestBody PostDTO postDTO) {
        Post post = postService.getPostById(postDTO.getId());

        post.setContent(postDTO.getContent());
        post.setCreated(postDTO.getCreated());
        post.setDate(postDTO.getDate());
        post.setSubject(postDTO.getSubject());
        post.setTitle(postDTO.getTitle());
        post.setId(postDTO.getId());
        return em.merge(post);
    }
}
