package Default_Package;


import Model_Package.Lines;
import Model_Package.Users;
import Model_Package.Employees;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.FileSystems;
import java.util.ArrayList;

public class XmlDatabase {
    public static ArrayList<Lines> getLinesFromXml() {
        ArrayList<Lines> linesDatabase= new ArrayList<Lines>();
        try {
            File fXmlFile = new File(System.getProperty("user.dir") +
                    FileSystems.getDefault().getSeparator() + "lines.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("line");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    linesDatabase.add(lineFromXml(eElement));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return linesDatabase;
    }

    private static Lines lineFromXml(Element eElement) {
        return new Lines(
                Integer.parseInt(eElement.getAttribute("id")),
                eElement.getElementsByTagName("number").item(0).getTextContent(),
                eElement.getElementsByTagName("finalStation").item(0).getTextContent(),
                eElement.getElementsByTagName("startStation").item(0).getTextContent(),
                eElement.getElementsByTagName("stations").item(0).getTextContent());
    }

    public static ArrayList<Employees> getEmployeesFromXml() {
        ArrayList<Employees> employeesDatabase= new ArrayList<Employees>();
        try {
            File fXmlFile = new File(System.getProperty("user.dir") +
                    FileSystems.getDefault().getSeparator() + "employees.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("employee");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    employeesDatabase.add(employeeFromXml(eElement));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeesDatabase;
    }

    private static Employees employeeFromXml(Element eElement) {
        return new Employees(
                Integer.parseInt(eElement.getAttribute("id")),
                eElement.getElementsByTagName("name").item(0).getTextContent(),
                eElement.getElementsByTagName("email").item(0).getTextContent(),
                eElement.getElementsByTagName("birthDate").item(0).getTextContent());
    }

    public static ArrayList<Users> getUsersFromXml() {
        ArrayList<Users> userDatabase= new ArrayList<Users>();
        try {
            File fXmlFile = new File(System.getProperty("user.dir") +
                    FileSystems.getDefault().getSeparator() + "users.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("user");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    userDatabase.add(userFromXml(eElement));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDatabase;
    }

    private static Users userFromXml(Element eElement) {
        return new Users(
                Integer.parseInt(eElement.getAttribute("id")),
                eElement.getElementsByTagName("password").item(0).getTextContent(),
                eElement.getElementsByTagName("role").item(0).getTextContent(),
                eElement.getElementsByTagName("username").item(0).getTextContent());
    }
}
