import java.util.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;

public class Person {
    private final SimpleStringProperty bodyName;
    private final SimpleStringProperty bodyHeight;
    private final SimpleStringProperty bodyWeight;
    private final SimpleStringProperty bodyBMI;

    public Person(String name, String height, String weight) {
        this.bodyName = new SimpleStringProperty(name);
        this.bodyHeight = new SimpleStringProperty(height);
        this.bodyWeight = new SimpleStringProperty(weight);
        float heightFloat = Float.valueOf(height);
        float weightFloat = Float.valueOf(weight);
        float BMI = weightFloat/(heightFloat * heightFloat);
        this.bodyBMI = new SimpleStringProperty(String.valueOf(BMI));
    }

    public String getBodyName(){
        return bodyName.get(); 
    }

    public void setBodyName(String name){
        bodyName.set(name);
    }

    public String getBodyHeight(){
        return bodyHeight.get();
    }

    public void setBodyHeight(String height){
        bodyHeight.set(height);
    }

    public String getBodyWeight(){
        return bodyWeight.get();
    }

    public void setBodyWeight(String weight){
        bodyWeight.set(weight);
    }

    public String getBodyBMI(){
        return bodyBMI.get();
    }

    public void setBodyBMI(String bmi){
        bodyBMI.set(bmi);
    }
}
