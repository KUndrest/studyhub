package ee.ttu.studyhub.controllers;

import ee.ttu.studyhub.entity.Subject;
import ee.ttu.studyhub.service.SubjectService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SubjectControllerTest {
    private static final long TEST_SUBJECT_ID1 = 1L;
    private static final long TEST_SUBJECT_ID2 = 2L;

    private MockMvc mockMvc;

    @MockBean
    private SubjectService subjectService;

    @InjectMocks
    private SubjectController subjectController;

    private Subject sampleSubject1;
    private Subject sampleSubject2;

    private List<Subject> sampleSubjects;

    @Before
    public void setup() {
        sampleSubject1 = new Subject();
        sampleSubject1.setId(TEST_SUBJECT_ID1);
        sampleSubject1.setSubject("Content");
        sampleSubject1.setCode("IDK111");

        sampleSubject2 = new Subject();
        sampleSubject2.setId(TEST_SUBJECT_ID2);
        sampleSubject2.setSubject("Content2");
        sampleSubject2.setCode("IDK222");

        sampleSubjects = new ArrayList<>();
        sampleSubjects.add(sampleSubject1);
        sampleSubjects.add(sampleSubject2);

        given(subjectService.getAllSubjects()).willReturn(sampleSubjects);

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(subjectController)
                .addFilters(new org.apache.catalina.filters.CorsFilter())
                .build();
    }

    @Test
    public void getAllSubjects() throws Exception {
        Mockito.when(subjectService.getAllSubjects()).thenReturn(sampleSubjects);
        mockMvc.perform(get("/subjects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].subject", Matchers.is("Content")))
                .andExpect(jsonPath("$[0].code", Matchers.is("IDK111")))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].subject", Matchers.is("Content2")))
                .andExpect(jsonPath("$[1].code", Matchers.is("IDK222")));
        verify(subjectService, times(1)).getAllSubjects();
        verifyNoMoreInteractions(subjectService);
        Assert.assertEquals(subjectService.getAllSubjects(), sampleSubjects);
    }

    @Test
    public void removeScore() throws Exception{
        doNothing().when(subjectService).removeSubject(sampleSubject1.getId());
        mockMvc.perform(delete("/subjects/{id}", sampleSubject1.getId()))
                .andExpect(status().isOk());
        verify(subjectService, times(1)).removeSubject(sampleSubject1.getId());
    }
}
