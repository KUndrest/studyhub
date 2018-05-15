package ee.ttu.studyhub.controllers;

import ee.ttu.studyhub.entity.Score;
import ee.ttu.studyhub.service.ScoreService;
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
public class ScoreControllerTest {
    private static final long TEST_SCORE_ID1 = 1L;
    private static final long TEST_SCORE_ID2 = 2L;

    private MockMvc mockMvc;

    @MockBean
    private ScoreService scoreService;

    @InjectMocks
    private ScoreController scoreController;

    private Score sampleScore1;
    private Score sampleScore2;

    private List<Score> sampleScores;

    @Before
    public void setup() {
        sampleScore1 = new Score();
        sampleScore1.setId(TEST_SCORE_ID1);
        sampleScore1.setScore(5);

        sampleScore2 = new Score();
        sampleScore2.setId(TEST_SCORE_ID2);
        sampleScore2.setScore(4);

        sampleScores = new ArrayList<>();
        sampleScores.add(sampleScore1);
        sampleScores.add(sampleScore2);

        given(scoreService.getAllScores()).willReturn(sampleScores);

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(scoreController)
                .addFilters(new org.apache.catalina.filters.CorsFilter())
                .build();
    }

    @Test
    public void getAllScores() throws Exception {
        Mockito.when(scoreService.getAllScores()).thenReturn(sampleScores);
        mockMvc.perform(get("/scores"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].score", Matchers.is(5)))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].score", Matchers.is(4)));
        verify(scoreService, times(1)).getAllScores();
        verifyNoMoreInteractions(scoreService);
        Assert.assertEquals(scoreService.getAllScores(), sampleScores);
    }

    @Test
    public void removeScore() throws Exception{
        doNothing().when(scoreService).removeScore(sampleScore1.getId());
        mockMvc.perform(delete("/scores/{id}", sampleScore1.getId()))
                .andExpect(status().isOk());
        verify(scoreService, times(1)).removeScore(sampleScore1.getId());
    }
}
