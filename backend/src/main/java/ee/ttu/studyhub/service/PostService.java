package ee.ttu.studyhub.service;

import ee.ttu.studyhub.entity.Person;
import ee.ttu.studyhub.entity.Post;
import ee.ttu.studyhub.entity.Subject;
import ee.ttu.studyhub.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post addPost(Post post) {

        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(long postId) {

        return postRepository.findOne(postId);
    }

    public void removePost(long postId) {
        postRepository.delete(postId);
    }

    public List<Post> findBySubject(Subject subject) {
        return postRepository.findAllBySubject(subject);
    }

    public List<Post>  findAllBySubjectPerson (Person person) {
        return postRepository.findBySubjectPerson(person);
    }
}
