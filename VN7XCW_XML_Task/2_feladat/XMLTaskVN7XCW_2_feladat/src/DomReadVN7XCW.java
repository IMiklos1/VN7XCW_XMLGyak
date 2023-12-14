package src;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class DomReadVN7XCW {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("VN7XCW_XML_Task/2_feladat/XMLTaskVN7XCW_2_feladat/src/XML_VN7XCW.xml");

            if (!xmlFile.exists()) {
                System.out.println("The file not found.");
                return;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            Element rootElement = doc.getDocumentElement();
            System.out.println("Root element: " + rootElement.getNodeName());

            WriteOutContent(rootElement, "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void WriteOutContent(Node node, String indent) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.print(indent + "<" + node.getNodeName());


            if (node.hasAttributes()) {
                NamedNodeMap attrib = node.getAttributes();
                for (int i = 0; i < attrib.getLength(); i++) {
                    Node attribute = attrib.item(i);
                    System.out.print(" " + attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
                }
            }

            if (!node.hasChildNodes() || node.getFirstChild().getNodeType() == Node.TEXT_NODE) {

                System.out.print(">");

            } else {
                if(node.toString().startsWith("which_product"))
                {
                System.out.println(">");

                }
            }

            if (node.hasChildNodes()) {
                NodeList childList = node.getChildNodes();
                boolean hasTextChild = false;
                for (int i = 0; i < childList.getLength(); i++) {
                    Node child = childList.item(i);
                    if (child.getNodeType() == Node.TEXT_NODE && !child.getNodeValue().trim().isEmpty()) {
                        System.out.print(indent + "  " + child.getNodeValue().trim());
                        hasTextChild = true;
                    } else if (child.getNodeType() == Node.ELEMENT_NODE) {
                        WriteOutContent(child, indent + "  ");
                    }
                }
                if (!hasTextChild) {
                    System.out.println(indent + "</" + node.getNodeName() + ">");
                } else {
                    System.out.println(indent + "</" + node.getNodeName() + ">");
                }
            }
        } else if (node.getNodeType() == Node.TEXT_NODE) {
            String data = node.getNodeValue().trim();
            if (!data.isEmpty()) {
                System.out.print(data);
            }
        }
    }

}

