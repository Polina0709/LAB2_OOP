package org.example.parser;

import org.example.model.Candy;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CandyStAXParser {
    private static final Logger logger = Logger.getLogger(CandyStAXParser.class.getName());

    public List<Candy> parse(String filePath) {
        List<Candy> candies = new ArrayList<>();
        Candy candy = null;
        String elementContent = null;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(filePath));

            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        String currentElement = reader.getLocalName();
                        if ("Candy".equalsIgnoreCase(currentElement)) {
                            candy = new Candy();
                            logger.info("====================================");
                            logger.info("Parsed with StAX:");
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        if (candy != null) {
                            elementContent = reader.getText().trim();
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        if (candy != null && elementContent != null && !elementContent.isEmpty()) {
                            switch (reader.getLocalName()) {
                                case "Name":
                                    candy.setName(elementContent);
                                    logger.info("Name: " + elementContent);
                                    break;
                                case "Energy":
                                    candy.setEnergy(Integer.parseInt(elementContent));
                                    logger.info("Energy: " + elementContent);
                                    break;
                                case "Type":
                                    candy.addType(elementContent);
                                    logger.info("Type: " + elementContent);
                                    break;
                                case "Water":
                                case "Sugar":
                                case "Fructose":
                                case "ChocolateType":
                                case "Vanilla":
                                    candy.addIngredient(reader.getLocalName() + ": " + elementContent);
                                    logger.info("Ingredient: " + reader.getLocalName() + ": " + elementContent);
                                    break;
                                case "Proteins":
                                    candy.setProtein(Double.parseDouble(elementContent));
                                    logger.info("Proteins: " + elementContent);
                                    break;
                                case "Fats":
                                    candy.setFat(Double.parseDouble(elementContent));
                                    logger.info("Fats: " + elementContent);
                                    break;
                                case "Carbohydrates":
                                    candy.setCarbohydrate(Double.parseDouble(elementContent));
                                    logger.info("Carbohydrates: " + elementContent);
                                    break;
                                case "Production":
                                    candy.setProduction(elementContent);
                                    logger.info("Production: " + elementContent);
                                    break;
                                case "Candy":
                                    candies.add(candy);
                                    logger.info("====================================");
                                    candy = null;
                                    break;
                            }
                            elementContent = null;
                        }
                        break;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred during parsing", e);
        }
        return candies;
    }
}
