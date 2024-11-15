package org.example.validator;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XMLTransform {

    public void transform(String inputXml, String xsltFile, String outputXml) {
        try {
            // Створення фабрики трансформаторів
            TransformerFactory factory = TransformerFactory.newInstance();
            // Створення трансформатора з XSLT файлу
            Source xslt = new StreamSource(new File(xsltFile));
            Transformer transformer = factory.newTransformer(xslt);

            // Перетворення XML за допомогою XSLT
            Source xml = new StreamSource(new File(inputXml));
            transformer.transform(xml, new StreamResult(new File(outputXml)));

            System.out.println("Transformation completed. Output saved in " + outputXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
