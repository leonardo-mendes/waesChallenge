import configTests.ConfigTests;
import domains.Document;
import domains.enums.Side;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import repositories.DocumentRepository;
import services.DocumentService;
import services.exceptions.DocumentIncompleteException;
import services.exceptions.DocumentNotFoundException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ConfigTests.class)
public class DocumentServiceTest {

    private final String DATA_ONE = "Y2hhbGxlbmdld2Flcw==";
    private final String DATA_TWO = "d2Flc2NoYWxsZW5nZQ==";

    private Optional<Document> buildDocument(String rightSide, String leftSide) {
        return Optional.of(new Document(1, leftSide, rightSide));
    }

    @InjectMocks
    private DocumentService documentService;

    @Mock
    private DocumentRepository documentRepository;

    @Test
    public void ShouldInsertANewDocument() {
        when(documentRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(documentRepository.save(any())).thenReturn(any());
        String message = this.documentService.insert(1, DATA_ONE, Side.LEFT);
        verify(documentRepository, times(1)).findById(anyInt());
        verify(documentRepository, times(1)).save(any());
        assertEquals("Document left-side saved successfully.", message);
    }

    @Test
    public void ShouldUpdateASideDocument() {
        when(documentRepository.findById(anyInt())).thenReturn(buildDocument(DATA_ONE, DATA_TWO));
        when(documentRepository.save(any())).thenReturn(any());
        this.documentService.insert(1, DATA_TWO, Side.RIGHT);
        verify(documentRepository, times(1)).findById(anyInt());
        verify(documentRepository, times(1)).save(any());
    }

    @Test(expected = DocumentIncompleteException.class)
    public void ShouldShowMessageDocumentRequestisInvalid() {
        String message = this.documentService.insert(1, "", Side.LEFT);
    }

    @Test(expected = DocumentNotFoundException.class)
    public void ShouldShowMessageDataNotFound() {
        when(documentRepository.findById(anyInt())).thenReturn(Optional.empty());
        String message = this.documentService.documentAnalysis(1);
        verify(documentRepository, times(1)).findById(anyInt());;
    }

    @Test(expected = DocumentIncompleteException.class)
    public void ShouldShowMessageToDocumentMissing() {
        when(documentRepository.findById(anyInt())).thenReturn(buildDocument("", ""));
        String message = this.documentService.documentAnalysis(1);
        verify(documentRepository, times(1)).findById(anyInt());
    }

    @Test
    public void ShouldShowMessageToSameDocuments() {
        when(documentRepository.findById(anyInt())).thenReturn(buildDocument(DATA_ONE, DATA_ONE));
        String message = this.documentService.documentAnalysis(1);
        verify(documentRepository, times(1)).findById(anyInt());
        assertEquals("Document Base64 data are equal.", message);
    }

    @Test
    public void ShouldShowMessageToDifferentDocumentsSizes() {
        when(documentRepository.findById(anyInt())).thenReturn(buildDocument(DATA_ONE, DATA_ONE + "1"));
        String message = this.documentService.documentAnalysis(1);
        verify(documentRepository, times(1)).findById(anyInt());
        assertEquals("Document Base64 data have not same size.", message);
    }

    @Test
    public void ShouldShowMessageToDifferentDocuments() {
        when(documentRepository.findById(anyInt())).thenReturn(buildDocument(DATA_ONE, DATA_TWO));
        String message = this.documentService.documentAnalysis(1);
        verify(documentRepository, times(1)).findById(anyInt());
        assertEquals("Document Base64 data got the same size, but their offsets are different: 0 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17.", message);
    }
}
