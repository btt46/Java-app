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

public class AppLayout extends Application{
    protected Button button;
    protected MenuBar menubar;
    protected Menu menu;

    protected String inputFileName;
    protected String exportFileName;

    protected TableView<Person> table = new TableView<Person>();

    protected CategoryAxis xAxis = new CategoryAxis();
    protected NumberAxis yAxis = new NumberAxis();
    protected BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);
    protected final ObservableList<Person> data =FXCollections.observableArrayList();

    final VBox vbox = new VBox();
    final VBox vbox2 =  new VBox();
    final HBox hb = new HBox();
    final HBox hb2 = new HBox();
    final HBox hb3 = new HBox();
    final BorderPane pane = new BorderPane(); 

    final Button drawButton = new Button("Draw");
    final Button bmiButton = new Button("BMI");
    final Button heightButton = new Button("Height");
    final Button weightButton = new Button("Weight");
    
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("BMI Application");
        primaryStage.setTitle("BMI Application");
        String[] menuTitle = {"Open File","Save as","Quit"};
        menubar = new MenuBar();
        menu = new Menu("Menu");
        
        for(int n = 0; n <menuTitle.length;n++){
            menu.getItems().add(new MenuItem(menuTitle[n]));  
        }
        menubar.getMenus().add(menu);   

        /* table */
        TableList tableList = new TableList("Student List");
    
        /* Graph */
        DrawGraph graph = new DrawGraph("Graph");

        /* Main Pane */
        pane.setTop(menubar); 
        pane.setRight(vbox);
        pane.setCenter(vbox2);
        primaryStage.setScene(new Scene(pane, 1400, 700)); 
        primaryStage.show(); 
    }

    class TableList {
        public TableList(String name) {
            final Label label = new Label(name);
            label.setFont(new Font("Arial", 20));
            table.setEditable(true);
     
            TableColumn bodyNameCol = new TableColumn("Name");
            bodyNameCol.setMinWidth(100);
            bodyNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("bodyName"));
    
            TableColumn bodyHeightCol = new TableColumn("Height");
            bodyHeightCol.setMinWidth(100);
            bodyHeightCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("bodyHeight"));
    
            TableColumn bodyWeightCol = new TableColumn("Weight");
            bodyWeightCol.setMinWidth(100);
            bodyWeightCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("bodyWeight"));
    
            TableColumn bodyBMICol = new TableColumn("BMI");
            bodyBMICol.setMinWidth(100);
            bodyBMICol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("bodyBMI"));
    
            table.setItems(data);    
            table.getColumns().addAll(bodyNameCol, bodyHeightCol, bodyWeightCol,bodyBMICol);
     
            final TextField addBodyName = new TextField();
            addBodyName.setPromptText("Name");
            addBodyName.setMaxWidth(bodyNameCol.getPrefWidth());
    
            final TextField addBodyHeight = new TextField();
            addBodyHeight.setPromptText("Height");
            addBodyHeight.setMaxWidth(bodyHeightCol.getPrefWidth());
    
            final TextField addBodyWeight = new TextField();
            addBodyWeight.setPromptText("Weight");
            addBodyWeight.setMaxWidth(bodyWeightCol.getPrefWidth());
    
            final Button addButton = new Button("Add");
            addButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    Person personData = new Person(addBodyName.getText(),addBodyHeight.getText(),
                                                    addBodyWeight.getText());
                    data.add(personData);
                    addBodyName.clear();
                    addBodyHeight.clear();
                    addBodyWeight.clear();
                }
            });
    
            final TextField delBody = new TextField();
            delBody.setPromptText("Order");
            delBody.setMaxWidth(bodyWeightCol.getPrefWidth());
    
            final Button delButton = new Button("Delete");
            delButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    int order = Integer.valueOf(delBody.getText())-1;
                    data.remove(order);
                    delBody.clear();
                }
            });
    
            hb.getChildren().addAll(addBodyName, addBodyHeight, addBodyWeight, addButton);
            hb.setSpacing(3);

            hb2.getChildren().addAll(delBody, delButton);
            hb2.setSpacing(2); 
                   
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(50, 10, 100, 10));
            vbox.getChildren().addAll(label, table, hb,hb2);
        }
    }

    class DrawGraph {
        public DrawGraph(String name){
            final Label label2 = new Label(name);
            label2.setFont(new Font("Arial", 20));
            hb3.getChildren().addAll(drawButton, bmiButton, heightButton, weightButton);
            hb3.setSpacing(40);
            vbox2.setSpacing(5);
            vbox2.setPadding(new Insets(50, 50, 50, 400));
            vbox2.getChildren().addAll(label2, bc, hb3);    
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}