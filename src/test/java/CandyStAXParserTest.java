import generated.Candy;
import org.example.parser.CandyStAXParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CandyStAXParserTest {

    // Тест на обробку порожнього XML файлу
    @Test
    void testParseEmptyXML() throws Exception {
        CandyStAXParser parser = new CandyStAXParser();
        String filePath = "src/main/resources/empty_file.xml";

        List<Candy> candies = parser.parse(filePath);

        // Перевірка, що список порожній
        assertNotNull(candies);
        assertTrue(candies.isEmpty());
    }

    // Тест на обробку некоректного XML файлу
    @Test
    void testParseInvalidXML() {
        CandyStAXParser parser = new CandyStAXParser();
        String filePath = "src/main/resources/invalid_candies.xml";

        // Перевірка, що виникає виключення при спробі парсити некоректний файл
        assertThrows(Exception.class, () -> parser.parse(filePath));
    }
}
