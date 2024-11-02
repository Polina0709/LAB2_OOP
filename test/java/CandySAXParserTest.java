import org.example.model.Candy;
import org.example.parser.CandySAXParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class CandySAXParserTest {

    private CandySAXParser parser;

    @BeforeEach
    void setUp() {
        parser = new CandySAXParser();
    }

    @Test
    void testParseValidFile() {
        String filePath = "/Users/polyamelnik/Desktop/ООП/CandyParserProject/src/main/resources/candies.xml"; // Adjust path as needed
        List<Candy> candies = parser.parse(filePath);

        assertNotNull(candies);
        assertEquals(7, candies.size());
        assertEquals("Chocolate Bar", candies.get(0).getName());
        assertEquals(250, candies.get(0).getEnergy());
        assertTrue(candies.get(0).getType().contains("CHOCOLATE_WITH_FILLING"));
    }

    @Test
    void testParseEmptyFile() {
        String filePath = "/Users/polyamelnik/Desktop/ООП/CandyParserProject/src/main/resources/empty_file.xml";
        List<Candy> candies = parser.parse(filePath);

        assertNull(candies);
    }

}
