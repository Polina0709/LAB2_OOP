import org.example.validator.XMLTransform;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XMLTransformTest {

    @Test
    void testTransform() {
        XMLTransform transformer = new XMLTransform();
        String inputXml = "src/main/resources/candies.xml";
        String xsltFile = "src/main/resources/transform.xsl";
        String outputXml = "src/main/resources/TransformedCandy.xml";

        // Перевіряємо, що трансформація не викликає помилок
        assertDoesNotThrow(() -> transformer.transform(inputXml, xsltFile, outputXml));
    }
}

