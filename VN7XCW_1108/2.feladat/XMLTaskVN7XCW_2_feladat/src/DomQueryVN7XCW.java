import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.File;
import java.util.Scanner;
import org.xml.sax.InputSource;
import javax.xml.xpath.*;

public class DomQueryVN7XCW {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("src/XML_VN7XCW.xml");

            if (!xmlFile.exists()) {
                System.out.println("The file not found.");
                return;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();


            //LEKÉRDEZNI KÍVÁNT ELEMENT LEKÉRDEZÉSE

            Scanner sc = new Scanner(System.in);

            System.out.println("Add the name of the element: ");

            String elementName = sc.nextLine();

            System.out.println("Add ID of the element: ");

            String elementID = sc.nextLine();

            sc.close();

            selectElementByID(doc, elementName, elementID);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void selectElementByID(Document doc, String elementName, String elementID) {
        NodeList nodeList = doc.getElementsByTagName(elementName);
        String xpathQuery = "//" + elementName + "[ID='" + elementID + "']";

        // Evaluate the XPath expression and retrieve the matching node
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = null;
        try {
            expr = xpath.compile(xpathQuery);
        } catch (XPathExpressionException e) {
            System.out.println("Element not found.");
            throw new RuntimeException(e);
        }
        Node result = null;
        try {
            result = (Node) expr.evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            System.out.println("Element not found.");
            throw new RuntimeException(e);
        }

        // Process the result
        if (result != null) {
            // Access the matched node and print its data
            Element element = (Element) result;
            NodeList children = element.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(child.getNodeName() + ": " + child.getTextContent().trim());
                }
            }
        } else {
            System.out.println("Element not found.");
        }
    }
}
