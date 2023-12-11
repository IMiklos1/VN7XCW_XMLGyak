import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class XPathModifyVN7XCW {
    public static void main(String[] args) {
        try {
            // Parse XML document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("orarend_VN7XCW.xml");

            // Perform modifications
            modifyDocument(document);

            // Print the result to the console
            printDocument(document);

            // Save the modified document to a file
            saveDocument(document, "orarend_THDWDR1.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void modifyDocument(Document document) {
        try {
            // Modification 1: Changing student's name
            Node studentNode = document.getElementsByTagName("student").item(0);
            Node nameNode = findNode(studentNode, "name");
            nameNode.setTextContent("John Doe");

            // Modification 2: Adding a new course
            Node coursesNode = document.getElementsByTagName("courses").item(0);
            Node newCourseNode = document.createElement("course");
            newCourseNode.setTextContent("\n");
            Attr idAttribute = document.createAttribute("id");
            idAttribute.setValue("THDWDR");
            ((Element) newCourseNode).setAttributeNode(idAttribute);  // Casting to Attr type

            Node courseNameNode = document.createElement("courseName");
            courseNameNode.setTextContent("New Course");
            newCourseNode.appendChild(courseNameNode);

            Node creditNode = document.createElement("credit");
            creditNode.setTextContent("3");
            newCourseNode.appendChild(creditNode);

            Node locationNode = document.createElement("location");
            locationNode.setTextContent("Room 105");
            newCourseNode.appendChild(locationNode);

            Node scheduleNode = document.createElement("schedule");
            scheduleNode.setTextContent("Thursday 10 AM - 12 PM");
            newCourseNode.appendChild(scheduleNode);

            Node instructorNode = document.createElement("instructor");
            instructorNode.setTextContent("Dr. Smith");
            newCourseNode.appendChild(instructorNode);

            coursesNode.appendChild(newCourseNode);

            // Modification 3: Deleting a course
            Node courseToDelete = findCourse(document, "Web Technologies 1");
            if (courseToDelete != null) {
                courseToDelete.getParentNode().removeChild(courseToDelete);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Node findNode(Node parent, String nodeName) {
        NodeList nodes = parent.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeName().equals(nodeName)) {
                return node;
            }
        }
        return null;
    }

    private static Node findCourse(Document document, String courseName) {
        NodeList courses = document.getElementsByTagName("course");
        for (int i = 0; i < courses.getLength(); i++) {
            Node course = courses.item(i);
            Node courseNameNode = findNode(course, "courseName");
            if (courseNameNode != null && courseNameNode.getTextContent().equals(courseName)) {
                return course;
            }
        }
        return null;
    }

    private static void printDocument(Document document) {
        try {
            // Writing to the console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(System.out);

            transformer.transform(domSource, streamResult);
            System.out.println("\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveDocument(Document document, String fileName) {
        try {
            // Writing to a file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(fileName);

            transformer.transform(domSource, streamResult);
            System.out.println("The modified document has been saved to the file: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
