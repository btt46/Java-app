import java.util.*;
import org.w3c.dom.*;

public class Body {
    private HashMap <String, String> bodyMap ;

    public Body(Element element) {
        bodyMap = new HashMap<String, String>();
        this.bodyMap.put("name",element.getAttribute("name"));
        this.bodyMap.put("height",element.getAttribute("height"));
        this.bodyMap.put("weight",element.getAttribute("weight"));
    }

    public String getName(){
        return this.bodyMap.get("name");    
    }

    public float getHeight(){
        return Float.valueOf(this.bodyMap.get("height"));
    }

    public float getWeight(){
        return Float.valueOf(this.bodyMap.get("weight"));
    }
    
    public float getBMI(){
        float height = this.getHeight();
        float weight = this.getWeight();
        return weight / (height * height);
    }
}