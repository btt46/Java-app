// scene, collections libraries
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.geometry.*;
import javafx.collections.*;
import javafx.beans.property.SimpleStringProperty;
import java.util.*;

// Stage, File libraries
import javafx.stage.Stage;
import javafx.stage.FileChooser;  
import java.io.File;  

// XML libraries
import javax.xml.parsers.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.*;
import javax.xml.*;
import org.w3c.dom.*;

// chart library
import javafx.scene.chart.*;

public class App extends AppLayout {

    @Override
    public void start(Stage primaryStage){
        super.start(primaryStage);
        ObservableList<MenuItem> items = menu.getItems();
        MenuItem item1 = items.get(0);
        item1.setOnAction(new ImportFile());
        MenuItem item2 = items.get(1);
        item2.setOnAction(new ExportFile());
        MenuItem item3 = items.get(2);
        item3.setOnAction(new ExitProgram());

        drawButton.setOnAction(new BarGraph("Draw"));
        bmiButton.setOnAction(new BarGraph("BMI"));
        heightButton.setOnAction(new BarGraph("Height"));
        weightButton.setOnAction(new BarGraph("Weight"));
    }
    
    class ImportFile implements EventHandler<ActionEvent> {
        FileChooser file;
        File file1;
        public void handle(ActionEvent e){
            Stage stage = new Stage();
            file = new FileChooser();  
            file.setTitle("Open File");  
            file1 = file.showOpenDialog(stage); 
            inputFileName = file1.getPath();
            try{ 
                BodyCategory bodycategory = new BodyCategory(inputFileName);
                Body[] bodyData = bodycategory.body;
                for(int i= 0; i< bodyData.length;i++){
                    String name = bodyData[i].getName();
                    String height = String.valueOf(bodyData[i].getHeight());
                    String weight = String.valueOf(bodyData[i].getWeight());
                    Person personData = new Person(name,height,weight);
                    data.add(personData);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class ExportFile implements EventHandler<ActionEvent> {
        private FileChooser file;
        
        public void handle(ActionEvent e) {
            Stage primaryStage = new Stage();
            file = new FileChooser();  
            file.setTitle("Export File");  
            exportFileName = file.showSaveDialog(primaryStage).getPath();
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.newDocument();

                Element rootElement = doc.createElement("bodyData");
                doc.appendChild(rootElement);
                for(int i=0;i < data.size();i++){
                    Element body = doc.createElement("body");
                    rootElement.appendChild(body);

                    Attr attrBody = doc.createAttribute("name");
                    attrBody.setValue(data.get(i).getBodyName());
                    body.setAttributeNode(attrBody);

                    Attr attrHeight = doc.createAttribute("height");
                    attrHeight.setValue(data.get(i).getBodyHeight());
                    body.setAttributeNode(attrHeight);

                    Attr attrWeight = doc.createAttribute("weight");
                    attrWeight.setValue(data.get(i).getBodyWeight());
                    body.setAttributeNode(attrWeight);
                }
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(exportFileName));
                transformer.transform(source, result);       
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class ExitProgram implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e) {
            Stage stage = new Stage();
            Button exitBtn = new Button("Exit");
            exitBtn.setOnAction((ActionEvent t) -> {
                System.exit(0);
             });
            Label exitLabel =  new Label("Do you want to exit the application?");
            
            BorderPane pane = new BorderPane(); 
            pane.setCenter(exitLabel);
            pane.setBottom(exitBtn);
            BorderPane.setAlignment(exitBtn, Pos.BOTTOM_CENTER);

            Scene scene = new Scene(pane, 300, 50);
            stage.setScene(scene);
            stage.setTitle("Exit application");
            stage.show();
        }
    }

    class BarGraph implements EventHandler<ActionEvent> {
        ObservableList<String> nameList = FXCollections.observableArrayList();
        Float BMI ;
        Float height ;
        Float weight ;
        String graphSeries;
        String name;

        public BarGraph(String type){
            graphSeries = type;
        }

        public void handle(ActionEvent e) {
            bc.getData().clear();
            nameList.clear();
            for(int i=0;i < data.size();i++){
                nameList.add(data.get(i).getBodyName());
            }
    
            xAxis.setCategories(nameList);
            xAxis.setLabel("Name");       
            yAxis.setLabel("value");
            XYChart.Series series = new XYChart.Series();
            series.setName("BMI");
            XYChart.Series series2 = new XYChart.Series();
            series2.setName("Height");
            XYChart.Series series3 = new XYChart.Series();
            series3.setName("Weight");

            for(int i=0;i < data.size();i++){
                name = nameList.get(i);
                BMI = Float.valueOf(data.get(i).getBodyBMI());
                series.getData().add(new XYChart.Data(name, BMI));

                height = Float.valueOf(data.get(i).getBodyHeight());
                series2.getData().add(new XYChart.Data(name, height));                

                weight = Float.valueOf(data.get(i).getBodyWeight());
                series3.getData().add(new XYChart.Data(name, weight));
                
            }
            if(graphSeries.equals("BMI")){
                bc.getData().add(series);
            } else if (graphSeries.equals("Height")) {
                bc.getData().add(series2);
            } else if (graphSeries.equals("Weight")) {
                bc.getData().add(series3);
            } else {
                bc.getData().addAll(series,series2,series3);
            }  
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}