package org.example;

import generated.Candy;
import org.example.validator.XMLValidator;
import org.example.validator.XMLTransform;
import org.example.parser.CandyDOMParser;
import org.example.parser.CandySAXParser;
import org.example.parser.CandyStAXParser;

import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        // Відносні шляхи до файлів
        String inputXml = getRelativePath("src/main/resources/candies.xml");
        String xsltFile = getRelativePath("src/main/resources/transform.xsl");
        String outputXml = getRelativePath("src/main/resources/TransformedCandy.xml");
        String xsdFile = getRelativePath("src/main/resources/schema.xsd");

        logger.info("Starting Candy Parser Application.");

        try {
            // Перевірка на валідність XML
            XMLValidator xmlValidator = new XMLValidator();
            xmlValidator.validate(inputXml, xsdFile);

            // Використання DOM парсера
            CandyDOMParser domParser = new CandyDOMParser();
            List<Candy> domCandies = domParser.parse(inputXml);

            // Використання SAX парсера
            CandySAXParser saxParser = new CandySAXParser();
            List<Candy> saxCandies = saxParser.parse(inputXml);
            logger.info("Parsed using SAX:");

            // Використання StAX парсера
            CandyStAXParser staxParser = new CandyStAXParser();
            List<Candy> staxCandies = staxParser.parse(inputXml);
            logger.info("Parsed using StAX:");
            staxCandies.forEach(candy -> logger.info(candy.toString()));

            // Виконання XSL трансформації
            XMLTransform transformer = new XMLTransform();
            transformer.transform(inputXml, xsltFile, outputXml);
            logger.info("Transformation completed successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while processing:", e);
        }
    }

    private static String getRelativePath(String relativePath) {
        return Paths.get(relativePath).toAbsolutePath().toString();
    }
}
