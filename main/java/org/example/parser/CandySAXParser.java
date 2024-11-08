package org.example.parser;

import org.example.model.Candy;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

public class CandySAXParser {
    private List<Candy> candies;

    public List<Candy> parse(String filePath) {
        candies = new ArrayList<>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                private Candy currentCandy;
                private StringBuilder currentValue;
                private String currentElement;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    currentValue = new StringBuilder();
                    currentElement = qName.toLowerCase();

                    if (qName.equalsIgnoreCase("Candy")) {
                        currentCandy = new Candy();
                    }

                    // Ініціалізація списку інгредієнтів
                    if (qName.equalsIgnoreCase("Ingredients")) {
                        currentCandy.setIngredients(new ArrayList<>());
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    currentValue.append(ch, start, length);
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    String value = currentValue.toString().trim();

                    switch (qName.toLowerCase()) {
                        case "candy":
                            candies.add(currentCandy);
                            break;
                        case "name":
                            currentCandy.setName(value);
                            break;
                        case "energy":
                            try {
                                currentCandy.setEnergy(Integer.parseInt(value));
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid energy value: " + value);
                            }
                            break;
                        case "type":
                            currentCandy.getType().add(value);
                            break;
                        case "ingredient":
                            // Додавання інгредієнтів з значенням
                            currentCandy.getIngredients().add(value);
                            break;
                        case "proteins":
                            try {
                                currentCandy.setProtein(Double.parseDouble(value));
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid protein value: " + value);
                            }
                            break;
                        case "fats":
                            try {
                                currentCandy.setFat(Double.parseDouble(value));
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid fat value: " + value);
                            }
                            break;
                        case "carbohydrates":
                            try {
                                currentCandy.setCarbohydrate(Double.parseDouble(value));
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid carbohydrate value: " + value);
                            }
                            break;
                        case "production":
                            currentCandy.setProduction(value);
                            break;
                    }
                }
            };

            saxParser.parse(filePath, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candies.isEmpty() ? null : candies;
    }

    // Метод для виведення результату у бажаному форматі
    public void printCandyDetails(List<Candy> saxCandies) {
        for (Candy candy : candies) {
            System.out.println("Name: " + candy.getName());
            System.out.println("Energy: " + candy.getEnergy());
            System.out.println("Type: " + String.join(", ", candy.getType()));

            // Виведення інгредієнтів
            if (candy.getIngredients().isEmpty()) {
                System.out.println("Ingredient: N/A");
            } else {
                for (String ingredient : candy.getIngredients()) {
                    System.out.println("Ingredient: " + ingredient);
                }
            }

            // Виведення макроелементів (якщо значення не null)
            System.out.println("Proteins: " + candy.getProtein());
            System.out.println("Fats: " + candy.getFat());
            System.out.println("Carbohydrates: " + candy.getCarbohydrate());

            System.out.println("Production: " + (candy.getProduction() != null ? candy.getProduction() : "N/A"));
            System.out.println("====================================");
        }
    }
}
