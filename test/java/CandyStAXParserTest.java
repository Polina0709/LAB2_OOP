import org.example.model.Candy;
import org.example.parser.CandyStAXParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CandyStAXParserTest {

    private CandyStAXParser parser;
    private String testFilePath;

    @BeforeEach
    public void setUp() throws IOException {
        parser = new CandyStAXParser();

        testFilePath = "/Users/polyamelnik/Desktop/ООП/CandyParserProject/src/main/resources/candies.xml";
        createTestXMLFile(testFilePath);
    }

    private void createTestXMLFile(String testFilePath) {
    }

    @Test
    public void testParseEmptyFile() {
        List<Candy> candies = parser.parse("/Users/polyamelnik/Desktop/ООП/CandyParserProject/src/main/resources/empty_file.xml");

        assertNotNull(candies);
        assertTrue(candies.isEmpty());
    }

    @Test
    void testNoDuplicateCandiesInExistingFile() {
        File candiesFile = new File("/Users/polyamelnik/Desktop/ООП/CandyParserProject/src/main/resources/candies.xml");

        List<Candy> candies = parser.parse(candiesFile.getAbsolutePath());

        Set<String> candyNames = new HashSet<>();
        for (Candy candy : candies) {

            assertTrue(candyNames.add(candy.getName()), "Duplicate found: " + candy.getName());
        }
    }
}
