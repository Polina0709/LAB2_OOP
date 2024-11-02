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

                candy.setName(element.getElementsByTagName("Name").item(0).getTextContent().trim());
                candy.setEnergy(Integer.parseInt(element.getElementsByTagName("Energy").item(0).getTextContent().trim()));

                NodeList typeList = element.getElementsByTagName("Type");
                for (int j = 0; j < typeList.getLength(); j++) {
                    candy.getType().add(typeList.item(j).getTextContent().trim());
                }

                NodeList ingredientList = element.getElementsByTagName("Ingredients");
                for (int j = 0; j < ingredientList.getLength(); j++) {
                    candy.getIngredients().add(ingredientList.item(j).getTextContent().trim());
                }

                // Парсинг значень білків, жирів і вуглеводів
                int protein = Integer.parseInt(element.getElementsByTagName("Proteins").item(0).getTextContent().trim());
                int fat = Integer.parseInt(element.getElementsByTagName("Fats").item(0).getTextContent().trim());
                int carbohydrate = Integer.parseInt(element.getElementsByTagName("Carbohydrates").item(0).getTextContent().trim());

                candy.setProtein(protein);
                candy.setFat(fat);
                candy.setCarbohydrate(carbohydrate);

                candy.setProduction(element.getElementsByTagName("Production").item(0).getTextContent().trim());

                candies.add(candy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candies.isEmpty() ? null : candies;
    }
}
