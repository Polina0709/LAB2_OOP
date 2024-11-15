package org.example.validator;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import javax.xml.validation.Validator;

public class XMLValidator {

    public void validate(String inputXml, String xsdFile) {
        try {
            // Створення фабрики схем
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            // Створення схеми з XSD файлу
            Schema schema = factory.newSchema(new File(xsdFile));
            // Створення валідатора
            Validator validator = schema.newValidator();
            // Перевірка XML на відповідність XSD
            validator.validate(new StreamSource(new File(inputXml)));
            System.out.println("XML is valid.");
        } catch (Exception e) {
            System.out.println("XML is not valid: " + e.getMessage());
        }
    }
}
