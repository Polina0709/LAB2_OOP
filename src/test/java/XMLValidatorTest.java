import org.example.validator.XMLValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XMLValidatorTest {

    @Test
    void testValidateValidXML() {
        XMLValidator validator = new XMLValidator();
        String validXml = "src/main/resources/valid_candies.xml";
        String xsdFile = "src/main/resources/candies.xsd";

        // Очікується, що валідація пройде без помилок
        assertDoesNotThrow(() -> validator.validate(validXml, xsdFile));
    }
}

