package org.example.parser;

import org.example.model.Candy;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CandySAXParser {
    private static final Logger logger = Logger.getLogger(CandySAXParser.class.getName());
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
                                logger.warning("Invalid energy value: " + value);
                            }
                            break;
                        case "type":
                            currentCandy.getType().add(value);
                            break;
                        case "ingredient":
                            currentCandy.getIngredients().add(value);
                            break;
                        case "proteins":
                            try {
                                currentCandy.setProtein(Double.parseDouble(value));
                            } catch (NumberFormatException e) {
                                logger.warning("Invalid protein value: " + value);
                            }
                            break;
                        case "fats":
                            try {
                                currentCandy.setFat(Double.parseDouble(value));
                            } catch (NumberFormatException e) {
                                logger.warning("Invalid fat value: " + value);
                            }
                            break;
                        case "carbohydrates":
                            try {
                                currentCandy.setCarbohydrate(Double.parseDouble(value));
                            } catch (NumberFormatException e) {
                                logger.warning("Invalid carbohydrate value: " + value);
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
            logger.log(Level.SEVERE, "Error during parsing: ", e);
        }
        return candies.isEmpty() ? null : candies;
    }

    public void printCandyDetails(List<Candy> saxCandies) {
        for (Candy candy : candies) {
            logger.info("Name: " + candy.getName());
            logger.info("Energy: " + candy.getEnergy());
            logger.info("Type: " + String.join(", ", candy.getType()));

            if (candy.getIngredients().isEmpty()) {
                logger.info("Ingredient: N/A");
            } else {
                for (String ingredient : candy.getIngredients()) {
                    logger.info("Ingredient: " + ingredient);
                }
            }

            logger.info("Proteins: " + candy.getProtein());
            logger.info("Fats: " + candy.getFat());
            logger.info("Carbohydrates: " + candy.getCarbohydrate());
            logger.info("Production: " + (candy.getProduction() != null ? candy.getProduction() : "N/A"));
            logger.info("====================================");
        }
    }
}
