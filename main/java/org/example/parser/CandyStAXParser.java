package org.example.parser;

import org.example.model.Candy;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class CandyStAXParser {
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
                            System.out.println("====================================");
                            System.out.println("Parsed with StAX:");
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
                                    System.out.println("Name: " + elementContent);
                                    break;
                                case "Energy":
                                    candy.setEnergy(Integer.parseInt(elementContent));
                                    System.out.println("Energy: " + elementContent);
                                    break;
                                case "Type":
                                    candy.addType(elementContent);
                                    System.out.println("Type: " + elementContent);
                                    break;
                                case "Water":
                                case "Sugar":
                                case "Fructose":
                                case "ChocolateType":
                                case "Vanilla":
                                    candy.addIngredient(reader.getLocalName() + ": " + elementContent);
                                    System.out.println("Ingredient: " + reader.getLocalName() + ": " + elementContent);
                                    break;
                                case "Proteins":
                                    candy.setProtein(Double.parseDouble(elementContent));
                                    System.out.println("Proteins: " + elementContent);
                                    break;
                                case "Fats":
                                    candy.setFat(Double.parseDouble(elementContent));
                                    System.out.println("Fats: " + elementContent);
                                    break;
                                case "Carbohydrates":
                                    candy.setCarbohydrate(Double.parseDouble(elementContent));
                                    System.out.println("Carbohydrates: " + elementContent);
                                    break;
                                case "Production":
                                    candy.setProduction(elementContent);
                                    System.out.println("Production: " + elementContent);
                                    break;
                                case "Candy":
                                    candies.add(candy);
                                    System.out.println("====================================");
                                    candy = null; // Reset candy for the next element
                                    break;
                            }
                            elementContent = null; // Reset content after processing
                        }
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candies;
    }
}
