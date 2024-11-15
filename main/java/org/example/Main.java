package org.example;

import org.example.model.Candy;
import org.example.parser.CandyDOMParser;
import org.example.parser.CandySAXParser;
import org.example.parser.CandyStAXParser;
import org.example.validator.XMLValidator;

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

        logger.info("Starting Candy Parser Application.");

        try {
            // Використання DOM парсера
            CandyDOMParser domParser = new CandyDOMParser();
            List<Candy> domCandies = domParser.parse(inputXml);
            logger.info("Parsed using DOM:");
            domParser.printCandies(domCandies);

            // Використання SAX парсера
            CandySAXParser saxParser = new CandySAXParser();
            List<Candy> saxCandies = saxParser.parse(inputXml);
            logger.info("Parsed using SAX:");
            saxParser.printCandyDetails(saxCandies);

            // Використання StAX парсера
            CandyStAXParser staxParser = new CandyStAXParser();
            List<Candy> staxCandies = staxParser.parse(inputXml);
            logger.info("Parsed using StAX:");
            staxCandies.forEach(candy -> logger.info(candy.toString()));

            // Виконання XSL трансформації
            XMLValidator transformer = new XMLValidator();
            transformer.validate(inputXml, xsltFile, outputXml);
            logger.info("Transformation completed successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while processing:", e);
        }
    }

    private static String getRelativePath(String relativePath) {
        return Paths.get(relativePath).toAbsolutePath().toString();
    }
}
