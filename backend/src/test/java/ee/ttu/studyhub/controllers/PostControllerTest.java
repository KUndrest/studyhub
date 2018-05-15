package ee.ttu.studyhub.controllers;

import ee.ttu.studyhub.entity.Post;
import ee.ttu.studyhub.service.PostService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PostControllerTest {

    private static final long TEST_POST_ID1 = 1L;
    private static final long TEST_POST_ID2 = 2L;

    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @InjectMocks
    private PostController postController;

    private Post samplePost1;
    private Post samplePost2;

    private List<Post> samplePosts;

    @Before
    public void setup() {
        samplePost1 = new Post();
        samplePost1.setId(TEST_POST_ID1);
        samplePost1.setTitle("Title");
        samplePost1.setContent("Content");
        samplePost1.setDate("Date");

        samplePost2 = new Post();
        samplePost2.setId(TEST_POST_ID2);
        samplePost2.setTitle("Title2");
        samplePost2.setContent("Content2");
        samplePost2.setDate("Date2");

        samplePosts = new ArrayList<>();
        samplePosts.add(samplePost1);
        samplePosts.add(samplePost2);

        given(postService.getAllPosts()).willReturn(samplePosts);

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(postController)
                .addFilters(new org.apache.catalina.filters.CorsFilter())
                .build();
    }

    @Test
    public void getAllPosts() throws Exception {
        Mockito.when(postService.getAllPosts()).thenReturn(samplePosts);
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].title", Matchers.is("Title")))
                .andExpect(jsonPath("$[0].content", Matchers.is("Content")))
                .andExpect(jsonPath("$[0].date", Matchers.is("Date")))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].title", Matchers.is("Title2")))
                .andExpect(jsonPath("$[1].content", Matchers.is("Content2")))
                .andExpect(jsonPath("$[1].date", Matchers.is("Date2")));
        verify(postService, times(1)).getAllPosts();
        verifyNoMoreInteractions(postService);
        Assert.assertEquals(postService.getAllPosts(), samplePosts);
    }

    @Test
    public void removePost() throws Exception{
        doNothing().when(postService).removePost(samplePost1.getId());
        mockMvc.perform(delete("/posts/{id}", samplePost1.getId()))
                .andExpect(status().isOk());
        verify(postService, times(1)).removePost(samplePost1.getId());
    }
}
