
import org.example.model.Candy;
import org.example.parser.CandyDOMParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class CandyDOMParserTest {

    private CandyDOMParser parser;

    @BeforeEach
    void setUp() {
        parser = new CandyDOMParser();
    }

    @Test
    void testParseReturnsCandies() {
        CandyDOMParser candyDOMParser = new CandyDOMParser();
        List<Candy> candies = candyDOMParser.parse("/Users/polyamelnik/Desktop/ООП/CandyParserProject/src/main/resources/candies.xml"); // Вкажіть правильний шлях до вашого тестового файлу

        assertNotNull(candies);
        assertFalse(candies.isEmpty());
    }


    @Test
    void testParseReturnsNullForEmptyFile() throws Exception {
        String emptyFilePath = "/Users/polyamelnik/Desktop/ООП/CandyParserProject/src/main/resources/empty_file.xml"; // Вказати правильний шлях до порожнього файлу

        List<Candy> candies = parser.parse(emptyFilePath);

        assertNull(candies);
    }
}
