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

public class CandyDOMParser {
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
            e.printStackTrace();
        }
        return candies.isEmpty() ? null : candies;
    }

    public void printCandies(List<Candy> candies) {
        if (candies != null) {
            for (Candy candy : candies) {
                System.out.println("Name: " + candy.getName());
                System.out.println("Energy: " + candy.getEnergy());

                // Виведення типів
                for (String type : candy.getType()) {
                    System.out.println("Type: " + type);
                }

                // Виведення інгредієнтів
                String[] ingredientNames = {"Water", "Sugar", "Fructose", "ChocolateType", "Vanilla"};
                int i = 0;

                // Виведення кожного інгредієнта
                for (String ingredient : candy.getIngredients()) {
                    if (i < ingredientNames.length) {
                        System.out.println("Ingredient: " + ingredientNames[i] + ": " + ingredient);
                    } else {
                        // Якщо інгредієнтів більше, вивести за замовчуванням
                        System.out.println("Ingredient: " + ingredientNames[ingredientNames.length - 1] + ": " + ingredient);
                    }
                    i++;
                }

                // Виведення харчових компонентів
                System.out.println("Proteins: " + candy.getProtein());
                System.out.println("Fats: " + candy.getFat());
                System.out.println("Carbohydrates: " + candy.getCarbohydrate());

                // Виведення виробника
                System.out.println("Production: " + candy.getProduction());

                // Роздільник для кращого вигляду
                System.out.println("--------------------------------------");
            }
        } else {
            System.out.println("No candies found.");
        }
    }
}
