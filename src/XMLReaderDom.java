import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esephak on 6/6/2017.
 */
public class XMLReaderDom {

    public static void main(String[] args) {
        String filePath = "employee.xml";
        File myfile = new File(filePath);

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;

        List<Student> students = new ArrayList<>();

        try{
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(myfile);
            document.getDocumentElement().normalize();

            System.out.println("root is: " + document.getDocumentElement().getNodeName());

            System.out.println("--------------------------------");
            NodeList nodeList = document.getElementsByTagName("student");

            for (int temp = 0; temp < nodeList.getLength(); temp++){
                if (nodeList.item(temp).getNodeType() == Node.ELEMENT_NODE &&
                        nodeList.item(temp).getNodeName().equalsIgnoreCase("Student")){
                    Element element = (Element)nodeList.item(temp);
                    students.add(parseStudent(element));
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        for (Student student : students){
            System.out.println(student);
        }

    }

    private static Student parseStudent(Element element){
        Student student = new Student();

        try {
            student.setRoll(Integer.parseInt(element.getAttribute("rollno")));
            student.setFirstName(element.getElementsByTagName("firstname").item(0).getTextContent());
            student.setLastName(element.getElementsByTagName("lastname").item(0).getTextContent());
            student.setNickName(element.getElementsByTagName("nickname").item(0).getTextContent());
            student.setMark(Integer.parseInt(element.getElementsByTagName("marks").item(0).getTextContent()));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return student;
    }
}
