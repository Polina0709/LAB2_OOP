package org.example.validator;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;


public class XMLValidator {
    public void validate(String inputXml, String xsltFile, String outputXml) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(new File(xsltFile));
            Transformer transformer = factory.newTransformer(xslt);

            Source xml = new StreamSource(new File(inputXml));
            transformer.transform(xml, new StreamResult(new File(outputXml)));

            System.out.println("Transformation completed. Output saved in " + outputXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


