import generated.Candy;
import org.example.parser.CandyDOMParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CandyDOMParserTest {

    // Тест на успішне парсинг та отримання списку цукерок
    @Test
    void testParseValidXML() throws Exception {
        CandyDOMParser parser = new CandyDOMParser();
        String filePath = "src/main/resources/candies.xml";

        List<Candy> candies = parser.parse(filePath);

        // Перевірка, що список не порожній
        assertNotNull(candies);
        assertFalse(candies.isEmpty());

        // Перевірка правильності першої цукерки
        assertEquals("Chocolate Bar", candies.get(0).getName());
        assertEquals(250, candies.get(0).getEnergy());
    }

    // Тест на обробку порожнього XML файлу
    @Test
    void testParseEmptyXML() throws Exception {
        CandyDOMParser parser = new CandyDOMParser();
        String filePath = "src/main/resources/empty_file.xml";

        List<Candy> candies = parser.parse(filePath);

        // Перевірка, що список порожній
        assertNotNull(candies);
        assertTrue(candies.isEmpty());
    }

    // Тест на обробку некоректного XML файлу
    @Test
    void testParseInvalidXML() {
        CandyDOMParser parser = new CandyDOMParser();
        String filePath = "src/main/resources/invalid_candies.xml";

        // Перевірка, що виникає виключення при спробі парсити некоректний файл
        assertThrows(Exception.class, () -> parser.parse(filePath));
    }
}
