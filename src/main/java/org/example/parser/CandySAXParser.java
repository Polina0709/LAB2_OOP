package org.example.parser;

import generated.Candy;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

public class CandySAXParser {

    public List<Candy> parse(String filePath) throws Exception {
        List<Candy> candies = new ArrayList<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        // Обробник SAX
        DefaultHandler handler = new DefaultHandler() {

            private Candy currentCandy = null;
            private Candy.Ingredients currentIngredients = null;
            private Candy.Value currentValue = null;
            private String currentTag = "";

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                currentTag = qName;

                if ("Candy".equals(currentTag)) {
                    currentCandy = new Candy();
                } else if ("Ingredients".equals(currentTag)) {
                    currentIngredients = new Candy.Ingredients();
                } else if ("Value".equals(currentTag)) {
                    currentValue = new Candy.Value();
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                if ("Candy".equals(qName)) {
                    candies.add(currentCandy); // Додаємо цукерку до списку
                } else if ("Ingredients".equals(qName)) {
                    currentCandy.setIngredients(currentIngredients);
                } else if ("Value".equals(qName)) {
                    currentCandy.setValue(currentValue);
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                String value = new String(ch, start, length).trim();

                if ("Name".equals(currentTag)) {
                    currentCandy.setName(value);
                } else if ("Energy".equals(currentTag)) {
                    if (!value.isEmpty()) {
                        currentCandy.setEnergy(Integer.parseInt(value)); // Перевірка на порожнечу
                    } else {
                        currentCandy.setEnergy(0); // або якесь значення за замовчуванням
                    }
                } else if ("Type".equals(currentTag)) {
                    currentCandy.getType().add(value); // Додаємо тип до списку
                } else if ("Water".equals(currentTag)) {
                    if (!value.isEmpty()) {
                        currentIngredients.setWater(Integer.parseInt(value));
                    } else {
                        currentIngredients.setWater(0); // значення за замовчуванням
                    }
                } else if ("Sugar".equals(currentTag)) {
                    if (!value.isEmpty()) {
                        currentIngredients.setSugar(Integer.parseInt(value));
                    } else {
                        currentIngredients.setSugar(0); // значення за замовчуванням
                    }
                } else if ("Fructose".equals(currentTag)) {
                    if (!value.isEmpty()) {
                        currentIngredients.setFructose(Integer.parseInt(value));
                    } else {
                        currentIngredients.setFructose(0); // значення за замовчуванням
                    }
                } else if ("ChocolateType".equals(currentTag)) {
                    currentIngredients.setChocolateType(value);
                } else if ("Vanilla".equals(currentTag)) {
                    if (!value.isEmpty()) {
                        currentIngredients.setVanilla(Integer.parseInt(value));
                    } else {
                        currentIngredients.setVanilla(0); // значення за замовчуванням
                    }
                } else if ("Proteins".equals(currentTag)) {
                    if (!value.isEmpty()) {
                        currentValue.setProteins(Double.parseDouble(value));
                    } else {
                        currentValue.setProteins(0.0); // значення за замовчуванням
                    }
                } else if ("Fats".equals(currentTag)) {
                    if (!value.isEmpty()) {
                        currentValue.setFats(Double.parseDouble(value));
                    } else {
                        currentValue.setFats(0.0); // значення за замовчуванням
                    }
                } else if ("Carbohydrates".equals(currentTag)) {
                    if (!value.isEmpty()) {
                        currentValue.setCarbohydrates(Double.parseDouble(value));
                    } else {
                        currentValue.setCarbohydrates(0.0); // значення за замовчуванням
                    }
                } else if ("Production".equals(currentTag)) {
                    currentCandy.setProduction(value);
                }
            }
        };

        // Парсимо XML файл
        File file = new File(filePath);
        parser.parse(file, handler);

        return candies;
    }
}
