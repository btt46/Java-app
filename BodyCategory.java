import java.io.*;
import java.util.*;
import org.json.*;
import javax.xml.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;

public class BodyCategory {
    public Body body[];
    private NodeList categories;

    public BodyCategory(String filename) throws Exception{
        Document doc = createDocument(filename);
        getDocumentData(doc);
    }

    public Document createDocument(String filename) throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new FileInputStream(filename));
        return doc;
    }

    public void getDocumentData(Document doc){
        getArrayOfBody(doc);
        getCategories(doc);
    }

    public void getArrayOfBody(Document doc){

        NodeList bodyNodes =  doc.getElementsByTagName("body");
        this.body = new Body[bodyNodes.getLength()];

        for(int index = 0; index < bodyNodes.getLength();index++){
            Element bodyElement = (Element) bodyNodes.item(index);
            if(bodyElement != null)this.body[index] = new Body(bodyElement);
        }

    }

    public void getCategories(Document doc){
        this.categories = doc.getElementsByTagName("category");
    }    
   
}
