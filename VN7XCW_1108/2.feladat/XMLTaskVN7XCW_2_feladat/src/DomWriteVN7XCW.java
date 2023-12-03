import java.io.File;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DomWriteVN7XCW {
    public static void main(String[] args) {
        try {

            File xmlFile = new File("src/XML_VN7XCW.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            Element rootElement = doc.getDocumentElement();
            System.out.println("Root element: " + rootElement.getNodeName());
            writeOutContent(rootElement, "");

            writeToFile(doc, "New_XML_VN7XCW.xml"); // Create new xml file

            System.out.println("Updated version of XML saved into New_XML_VN7XCW.xml file");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeOutContent(Node node, String indent) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.println(indent + node.getNodeName());

            if (node.hasAttributes()) {
                NamedNodeMap attrib = node.getAttributes();
                for (int i = 0; i < attrib.getLength(); i++) {
                    Node attribute = attrib.item(i);
                    System.out.println(indent + attribute.getNodeName() + " = " + attribute.getNodeValue());
                }
            }

            if (node.hasChildNodes()) {
                NodeList childList = node.getChildNodes();
                for (int i = 0; i < childList.getLength(); i++) {
                    Node child = childList.item(i);
                    writeOutContent(child, indent + "  ");
                }
            }
        } else if (node.getNodeType() == Node.TEXT_NODE) {
            String datas = node.getNodeValue().trim();
            if (!datas.isEmpty()) {
                System.out.println(indent + datas);
            }
        }
    }

    public static void writeToFile(Document doc, String filename) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
