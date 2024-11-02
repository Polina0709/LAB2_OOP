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
                    if (qName.equalsIgnoreCase("Candy")) {
                        currentCandy = new Candy();
                    }
                    currentValue = new StringBuilder();
                    currentElement = qName.toLowerCase();
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    currentValue.append(ch, start, length);
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    switch (qName.toLowerCase()) {
                        case "candy":
                            candies.add(currentCandy);
                            break;
                        case "name":
                            currentCandy.setName(currentValue.toString().trim());
                            break;
                        case "energy":
                            String energyValue = currentValue.toString().trim();
                            if (!energyValue.isEmpty()) {
                                try {
                                    currentCandy.setEnergy(Integer.parseInt(energyValue));
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid energy value: " + energyValue);
                                }
                            }
                            break;
                        case "type":
                            currentCandy.getType().add(currentValue.toString().trim());
                            break;
                        case "ingredients":
                            currentCandy.setIngredients(new ArrayList<>());
                            break;
                        case "ingredient":
                            currentCandy.getIngredients().add(currentValue.toString().trim());
                            break;
                        case "protein":
                            String proteinValue = currentValue.toString().trim();
                            if (!proteinValue.isEmpty()) {
                                currentCandy.setProtein(Integer.parseInt(proteinValue));
                            }
                            break;
                        case "fat":
                            String fatValue = currentValue.toString().trim();
                            if (!fatValue.isEmpty()) {
                                currentCandy.setFat(Integer.parseInt(fatValue));
                            }
                            break;
                        case "carbohydrate":
                            String carbohydrateValue = currentValue.toString().trim();
                            if (!carbohydrateValue.isEmpty()) {
                                currentCandy.setCarbohydrate(Integer.parseInt(carbohydrateValue));
                            }
                            break;
                        case "production":
                            currentCandy.setProduction(currentValue.toString().trim());
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
}
