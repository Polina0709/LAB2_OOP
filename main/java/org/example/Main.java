package org.example;

import org.example.model.Candy;
import org.example.parser.CandyDOMParser;
import org.example.parser.CandySAXParser;
import org.example.parser.CandyStAXParser;
import org.example.validator.XMLValidator;

import java.util.List;
import java.io.File;
import java.util.Comparator;


public class Main {
    public static void main(String[] args) {
        // Шлях до файлів
        String inputXml = "/Users/polyamelnik/Desktop/ООП/CandyParserProject/src/main/resources/candies.xml";
        String xsltFile = "/Users/polyamelnik/Desktop/ООП/CandyParserProject/src/main/resources/transform.xsl";
        String outputXml = "/Users/polyamelnik/Desktop/ООП/CandyParserProject/src/main/resources/TransformedCandy.xml";

        // Використання DOM парсера
        CandyDOMParser domParser = new CandyDOMParser();
        List<Candy> domCandies = domParser.parse(inputXml);
        System.out.println("Parsed using DOM: " + domCandies);

        // Використання SAX парсера
        CandySAXParser saxParser = new CandySAXParser();
        List<Candy> saxCandies = saxParser.parse(inputXml);
        System.out.println("Parsed using SAX: " + saxCandies);

        // Використання StAX парсера
        CandyStAXParser staxParser = new CandyStAXParser();
        List<Candy> staxCandies = staxParser.parse(inputXml);
        System.out.println("Parsed using StAX: " + staxCandies);

        // Виконання XSL трансформації
        XMLValidator transformer = new XMLValidator();
        transformer.validate(inputXml, xsltFile, outputXml);
    }
}

