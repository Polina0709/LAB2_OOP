package org.example.parser;

import generated.Candy;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

public class CandyDOMParser {

    public List<Candy> parse(String filePath) throws Exception {
        List<Candy> candies = new ArrayList<>();

        // Створюємо об'єкт DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Парсимо XML файл
        File file = new File(filePath);
        Document document = builder.parse(file);
        document.getDocumentElement().normalize();

        // Отримуємо всі елементи <Candy>
        NodeList nodeList = document.getElementsByTagName("Candy");

        // Проходимо по кожному елементу <Candy>
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                // Створюємо новий об'єкт Candy
                Candy candy = new Candy();

                // Парсимо Name
                String name = getTagValue("Name", element);
                candy.setName(name);

                // Парсимо Energy
                int energy = Integer.parseInt(getTagValue("Energy", element));
                candy.setEnergy(energy);

                // Парсимо Type
                NodeList typeList = element.getElementsByTagName("Type");
                for (int j = 0; j < typeList.getLength(); j++) {
                    String type = typeList.item(j).getTextContent();
                    candy.getType().add(type); // Додаємо тип до списку
                }

                // Парсимо Ingredients
                Element ingredientsElement = (Element) element.getElementsByTagName("Ingredients").item(0);
                Candy.Ingredients ingredients = new Candy.Ingredients();
                ingredients.setWater(Integer.parseInt(getTagValue("Water", ingredientsElement)));
                ingredients.setSugar(Integer.parseInt(getTagValue("Sugar", ingredientsElement)));
                ingredients.setFructose(Integer.parseInt(getTagValue("Fructose", ingredientsElement)));
                ingredients.setChocolateType(getTagValue("ChocolateType", ingredientsElement));
                ingredients.setVanilla(Integer.parseInt(getTagValue("Vanilla", ingredientsElement)));
                candy.setIngredients(ingredients);

                // Парсимо Value
                Element valueElement = (Element) element.getElementsByTagName("Value").item(0);
                Candy.Value value = new Candy.Value();
                value.setProteins(Double.parseDouble(getTagValue("Proteins", valueElement)));
                value.setFats(Double.parseDouble(getTagValue("Fats", valueElement)));
                value.setCarbohydrates(Double.parseDouble(getTagValue("Carbohydrates", valueElement)));
                candy.setValue(value);

                // Парсимо Production
                String production = getTagValue("Production", element);
                candy.setProduction(production);

                // Додаємо цукерку до списку
                candies.add(candy);
            }
        }

        return candies;
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        if (nodeList != null && nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            return node.getTextContent();
        }
        return null;
    }
}
