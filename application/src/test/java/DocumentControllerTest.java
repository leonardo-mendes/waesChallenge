import com.google.gson.Gson;
import configTests.ConfigTests;
import controllers.DocumentController;
import domains.DocumentRequest;
import domains.DocumentResponse;
import domains.enums.Side;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import services.DocumentService;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ConfigTests.class)
public class DocumentControllerTest {

    private final String DATA_ONE = "Y2hhbGxlbmdld2Flcw==";
    private static final String API_URL = "/v1/diff/1";

    private Gson gson;

    private MockMvc mockMvc;

    private DocumentRequest buildRequest(String data) {
        return new DocumentRequest(data);
    }

    private DocumentResponse buildResponse(String message) {
        return new DocumentResponse(message);
    }

    @InjectMocks
    private DocumentController documentController;

    @Mock
    private DocumentService documentService;

    @Before
    public void setUp() {
        gson = new Gson();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(documentController).build();
    }

    @Test
    public void ShouldInsertADocumentInLeftSide() throws Exception {
        String content= "Document " + Side.LEFT + "-side saved successfully.";
        when(documentService.insert(anyInt(), anyString(), any())).thenReturn(content);
        mockMvc.perform(post(API_URL + "/left").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(buildRequest(DATA_ONE))))
                .andExpect(status().isOk())
                .andExpect(content().string(gson.toJson(buildResponse(content))));
        verify(documentService, times(1)).insert(anyInt(), anyString(), any());
    }

    @Test
    public void ShouldInsertADocumentInRightSide() throws Exception {
        String content= "Document " + Side.RIGHT + "-side saved successfully.";
        when(documentService.insert(anyInt(), anyString(), any())).thenReturn(content);
        mockMvc.perform(post(API_URL + "/right").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(buildRequest(DATA_ONE))))
                .andExpect(status().isOk())
                .andExpect(content().string(gson.toJson(buildResponse(content))));
        verify(documentService, times(1)).insert(anyInt(), anyString(), any());
    }

    @Test
    public void ShouldNotInsertADocument() throws Exception {
        String content= "DocumentRequest is blank or null.";
        when(documentService.insert(anyInt(), anyString(), any())).thenReturn(content);
        mockMvc.perform(post(API_URL + "/right").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(buildRequest(""))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void ShouldNotFoundDocument() throws Exception {
        String content= "Document not found.";
        when(documentService.documentAnalysis(anyInt())).thenReturn(content);
        mockMvc.perform(get(API_URL + "/result"))
                .andExpect(status().isOk())
                .andExpect(content().string(gson.toJson(buildResponse(content))));
        verify(documentService, times(1)).documentAnalysis(anyInt());
    }

    @Test
    public void ShouldShowMessageToSameDocuments() throws Exception {
        String content= "Document Base64 data are equal.";
        when(documentService.documentAnalysis(anyInt())).thenReturn(content);
        mockMvc.perform(get(API_URL + "/result"))
                .andExpect(status().isOk())
                .andExpect(content().string(gson.toJson(buildResponse(content))));
        verify(documentService, times(1)).documentAnalysis(anyInt());
    }

    @Test
    public void ShouldShowMessageToDifferentDocumentsSizes() throws Exception {
        String content= "Document Base64 data have not same size.";
        when(documentService.documentAnalysis(anyInt())).thenReturn(content);
        mockMvc.perform(get(API_URL + "/result"))
                .andExpect(status().isOk())
                .andExpect(content().string(gson.toJson(buildResponse(content))));
        verify(documentService, times(1)).documentAnalysis(anyInt());
    }

    @Test
    public void ShouldShowMessageToDifferentDocuments() throws Exception {
        String content= "Document Base64 data got the same size, but their offsets are different: 0 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17.";
        when(documentService.documentAnalysis(anyInt())).thenReturn(content);
        mockMvc.perform(get(API_URL + "/result"))
                .andExpect(status().isOk())
                .andExpect(content().string(gson.toJson(buildResponse(content))));
        verify(documentService, times(1)).documentAnalysis(anyInt());
    }

}
