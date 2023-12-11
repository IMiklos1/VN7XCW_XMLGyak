import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

public class XPathVN7XCW {

    public static void main(String[] args) throws Exception {
        // Reading an XML document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("studentVN7XCW.xml");

        // Creating XPath object
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        // 1) Select all student elements that are children of the class element!
        NodeList studentsOfClass = (NodeList) xPath.evaluate("/class/student", document, XPathConstants.NODESET);
        System.out.println("1) " + studentsOfClass.getLength() + " student elements found under the class element.");

        // 2) Select the student element that has an "id" attribute with a value of "2"!
        Node studentWithId2 = (Node) xPath.evaluate("/class/student[@id='2']", document, XPathConstants.NODE);
        System.out.println("2) The student element with id='2': " + studentWithId2.getTextContent());

        // 3) Select all student elements regardless of their location in the document!
        NodeList allStudents = (NodeList) xPath.evaluate("//student", document, XPathConstants.NODESET);
        System.out.println("3) Total " + allStudents.getLength() + " student elements.");

        // 4) Select the second student element that is a child of the class root element!
        Node secondStudentInClass = (Node) xPath.evaluate("/class/student[2]", document, XPathConstants.NODE);
        System.out.println("4) The second student under the class element: " + secondStudentInClass.getTextContent());

        // 5) Select the last student element that is a child of the class root element!
        Node lastStudentInClass = (Node) xPath.evaluate("/class/student[last()]", document, XPathConstants.NODE);
        System.out.println("5) The last student under the class element: " + lastStudentInClass.getTextContent());

        // 6) Select the second-to-last student element that is a child of the class root element!
        Node secondToLastStudentInClass = (Node) xPath.evaluate("/class/student[last()-1]", document, XPathConstants.NODE);
        System.out.println("6) The second-to-last student under the class element: " + secondToLastStudentInClass.getTextContent());

        // 7) Select the first two student elements that are children of the root element!
        NodeList firstTwoStudents = (NodeList) xPath.evaluate("/class/student[position() <= 2]", document, XPathConstants.NODESET);
        System.out.println("7) The first two student elements: ");
        for (int i = 0; i < firstTwoStudents.getLength(); i++) {
            System.out.println("   " + firstTwoStudents.item(i).getTextContent());
        }

        // 8) Select all child elements of the class root element!
        NodeList allClassChildren = (NodeList) xPath.evaluate("/class/*", document, XPathConstants.NODESET);
        System.out.println("8) All child elements of the class element: ");
        for (int i = 0; i < allClassChildren.getLength(); i++) {
            System.out.println("   " + allClassChildren.item(i).getNodeName());
        }

        // 9) Select all student elements that have at least one attribute!
        NodeList studentsWithAttributes = (NodeList) xPath.evaluate("//student[@*]", document, XPathConstants.NODESET);
        System.out.println("9) Total " + studentsWithAttributes.getLength() + " student elements with attributes.");

        // 10) Select all elements in the document!
        NodeList allElements = (NodeList) xPath.evaluate("//*", document, XPathConstants.NODESET);
        System.out.println("10) Total " + allElements.getLength() + " elements in the document.");

        // 11) Select all student elements under the class root element where age > 20!
        NodeList studentsOver20 = (NodeList) xPath.evaluate("/class/student[kor>20]", document, XPathConstants.NODESET);
        System.out.println("11) Students whose age is over 20: ");
        for (int i = 0; i < studentsOver20.getLength(); i++) {
            System.out.println("   " + studentsOver20.item(i).getTextContent());
        }

        // 12) Select all nodes containing the first name or last name of all student elements!
        NodeList names = (NodeList) xPath.evaluate("//student/keresztnev | //student/vezeteknev", document, XPathConstants.NODESET);
        System.out.println("12) Nodes containing the first name or last name of all students: ");
        for (int i = 0; i < names.getLength(); i++) {
            System.out.println("   " + names.item(i).getTextContent());
        }
    }

}