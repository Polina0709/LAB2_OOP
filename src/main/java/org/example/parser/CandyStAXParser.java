package org.example.parser;

import generated.Candy;

import javax.xml.stream.*;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.util.*;

public class CandyStAXParser {

    public List<Candy> parse(String filePath) throws Exception {
        List<Candy> candies = new ArrayList<>();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(filePath));

        Candy currentCandy = null;
        Candy.Ingredients currentIngredients = null;
        Candy.Value currentValue = null;
        String currentTag = "";

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    currentTag = startElement.getName().toString();

                    // Створюємо нові об'єкти в залежності від тегів
                    if ("Candy".equals(currentTag)) {
                        currentCandy = new Candy();
                    } else if ("Ingredients".equals(currentTag)) {
                        currentIngredients = new Candy.Ingredients();
                    } else if ("Value".equals(currentTag)) {
                        currentValue = new Candy.Value();
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    String endTag = endElement.getName().toString();

                    if ("Candy".equals(endTag)) {
                        candies.add(currentCandy);
                    } else if ("Ingredients".equals(endTag)) {
                        currentCandy.setIngredients(currentIngredients);
                    } else if ("Value".equals(endTag)) {
                        currentCandy.setValue(currentValue);
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    String data = event.asCharacters().getData().trim();

                    if ("Name".equals(currentTag)) {
                        currentCandy.setName(data);
                    } else if ("Energy".equals(currentTag)) {
                        if (!data.isEmpty()) {
                            currentCandy.setEnergy(Integer.parseInt(data)); // Перевірка на порожнечу
                        } else {
                            currentCandy.setEnergy(0); // або значення за замовчуванням
                        }
                    } else if ("Type".equals(currentTag)) {
                        currentCandy.getType().add(data);
                    } else if ("Water".equals(currentTag)) {
                        if (!data.isEmpty()) {
                            currentIngredients.setWater(Integer.parseInt(data));
                        } else {
                            currentIngredients.setWater(0); // значення за замовчуванням
                        }
                    } else if ("Sugar".equals(currentTag)) {
                        if (!data.isEmpty()) {
                            currentIngredients.setSugar(Integer.parseInt(data));
                        } else {
                            currentIngredients.setSugar(0); // значення за замовчуванням
                        }
                    } else if ("Fructose".equals(currentTag)) {
                        if (!data.isEmpty()) {
                            currentIngredients.setFructose(Integer.parseInt(data));
                        } else {
                            currentIngredients.setFructose(0); // значення за замовчуванням
                        }
                    } else if ("ChocolateType".equals(currentTag)) {
                        currentIngredients.setChocolateType(data);
                    } else if ("Vanilla".equals(currentTag)) {
                        if (!data.isEmpty()) {
                            currentIngredients.setVanilla(Integer.parseInt(data));
                        } else {
                            currentIngredients.setVanilla(0); // значення за замовчуванням
                        }
                    } else if ("Proteins".equals(currentTag)) {
                        if (!data.isEmpty()) {
                            currentValue.setProteins(Double.parseDouble(data));
                        } else {
                            currentValue.setProteins(0.0); // значення за замовчуванням
                        }
                    } else if ("Fats".equals(currentTag)) {
                        if (!data.isEmpty()) {
                            currentValue.setFats(Double.parseDouble(data));
                        } else {
                            currentValue.setFats(0.0); // значення за замовчуванням
                        }
                    } else if ("Carbohydrates".equals(currentTag)) {
                        if (!data.isEmpty()) {
                            currentValue.setCarbohydrates(Double.parseDouble(data));
                        } else {
                            currentValue.setCarbohydrates(0.0); // значення за замовчуванням
                        }
                    } else if ("Production".equals(currentTag)) {
                        currentCandy.setProduction(data);
                    }
                    break;
            }
        }

        reader.close();
        return candies;
    }
}

