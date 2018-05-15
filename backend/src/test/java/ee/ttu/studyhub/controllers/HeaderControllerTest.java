package ee.ttu.studyhub.controllers;

import ee.ttu.studyhub.entity.Header;
import ee.ttu.studyhub.service.HeaderService;
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
public class HeaderControllerTest {

    private static final long TEST_HEADER_ID1 = 1L;
    private static final long TEST_HEADER_ID2 = 2L;

    private MockMvc mockMvc;

    @MockBean
    private HeaderService headerService;

    @MockBean
    private SubjectService subjectService;

    @InjectMocks
    private HeaderController headerController;

    private Header sampleHeader1;
    private Header sampleHeader2;

    private List<Header> sampleHeaders;

    @Before
    public void setup() {
        sampleHeader1 = new Header();
        sampleHeader1.setId(TEST_HEADER_ID1);
        sampleHeader1.setHeader("Content");
        sampleHeader1.setIsMark(true);

        sampleHeader2 = new Header();
        sampleHeader2.setId(TEST_HEADER_ID2);
        sampleHeader2.setHeader("Content2");
        sampleHeader2.setIsMark(false);

        sampleHeaders = new ArrayList<>();
        sampleHeaders.add(sampleHeader1);
        sampleHeaders.add(sampleHeader2);

        given(headerService.getAllHeaders()).willReturn(sampleHeaders);

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(headerController)
                .addFilters(new org.apache.catalina.filters.CorsFilter())
                .build();
    }

    @Test
    public void getAllHeaders() throws Exception {
        Mockito.when(headerService.getAllHeaders()).thenReturn(sampleHeaders);
        mockMvc.perform(get("/headers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].header", Matchers.is("Content")))
                .andExpect(jsonPath("$[0].isMark", Matchers.is(true)))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].header", Matchers.is("Content2")))
                .andExpect(jsonPath("$[1].isMark", Matchers.is(false)));
        verify(headerService, times(1)).getAllHeaders();
        verifyNoMoreInteractions(headerService);
        Assert.assertEquals(headerService.getAllHeaders(), sampleHeaders);
    }

    @Test
    public void removeHeader() throws Exception{
        doNothing().when(headerService).removeHeader(sampleHeader1.getId());
        mockMvc.perform(delete("/headers/{id}", sampleHeader1.getId()))
                .andExpect(status().isOk());
        verify(headerService, times(1)).removeHeader(sampleHeader1.getId());
    }
}