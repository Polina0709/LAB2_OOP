package org.example.parser;

import org.example.model.Candy;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CandyDOMParser {
    private static final Logger logger = Logger.getLogger(CandyDOMParser.class.getName());

    public List<Candy> parse(String filePath) {
        List<Candy> candies = new ArrayList<>();
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Candy");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Element element = (Element) nList.item(temp);
                Candy candy = new Candy();

                // Парсимо основні дані
                candy.setName(element.getElementsByTagName("Name").item(0).getTextContent().trim());
                candy.setEnergy(Integer.parseInt(element.getElementsByTagName("Energy").item(0).getTextContent().trim()));

                // Парсимо типи
                NodeList typeList = element.getElementsByTagName("Type");
                for (int j = 0; j < typeList.getLength(); j++) {
                    candy.getType().add(typeList.item(j).getTextContent().trim());
                }

                // Парсимо інгредієнти
                String[] ingredientNames = {"Water", "Sugar", "Fructose", "ChocolateType", "Vanilla"};
                for (String ingredientName : ingredientNames) {
                    NodeList ingredientList = element.getElementsByTagName(ingredientName);
                    if (ingredientList.getLength() > 0) {
                        String ingredientValue = ingredientList.item(0).getTextContent().trim();
                        candy.getIngredients().add(ingredientValue);
                    } else {
                        candy.getIngredients().add("N/A"); // Якщо інгредієнт не знайдено
                    }
                }

                // Парсимо харчові компоненти
                int protein = Integer.parseInt(element.getElementsByTagName("Proteins").item(0).getTextContent().trim());
                int fat = Integer.parseInt(element.getElementsByTagName("Fats").item(0).getTextContent().trim());
                int carbohydrate = Integer.parseInt(element.getElementsByTagName("Carbohydrates").item(0).getTextContent().trim());

                candy.setProtein(protein);
                candy.setFat(fat);
                candy.setCarbohydrate(carbohydrate);

                // Парсимо виробника
                candy.setProduction(element.getElementsByTagName("Production").item(0).getTextContent().trim());

                candies.add(candy);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error parsing the file", e);
        }
        return candies.isEmpty() ? null : candies;
    }

    public void printCandies(List<Candy> candies) {
        Logger logger = Logger.getLogger(CandyDOMParser.class.getName());
        if (candies != null) {
            for (Candy candy : candies) {
                logger.info("Name: " + candy.getName());
                logger.info("Energy: " + candy.getEnergy());
                logger.info("Type: " + (candy.getType().isEmpty() ? "N/A" : String.join(", ", candy.getType())));

                // Виведення інгредієнтів
                logger.info("Ingredient: " + (candy.getIngredients().isEmpty() ? "N/A" : String.join(", ", candy.getIngredients())));

                // Виведення харчових компонентів
                logger.info("Proteins: " + candy.getProtein());
                logger.info("Fats: " + candy.getFat());
                logger.info("Carbohydrates: " + candy.getCarbohydrate());

                // Виробник
                logger.info("Production: " + candy.getProduction());
                logger.info("--------------------------------------");
            }
        } else {
            logger.warning("No candies found.");
        }
    }
}
