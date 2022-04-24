/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Activitie;
import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.time.DayOfWeek;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ActivityService;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class AdminDashboardController implements Initializable {
    private TextField search;
     @FXML
    private Label titrepage;
      
      private Button pp ;
      
     Stage stage ;
     Scene scene ;
    @FXML
    private Pane addbutton;
    //*********new********
    @FXML
    private LineChart<?, ?> lineChart;
    @FXML
    private PieChart pieChart;
    
    ActivityService actS = new ActivityService();
   
    
    
    
    //*************
    
    
    
     
  
 

    /**
     * Initializes the controller class.
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("lool");
        iniLineChart();
        piec();
    }
    private void addAct(ActionEvent event) { 
       // FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjouterPFXML.fxml"));
       /* try {
            Parent root = loader.load();
            AjouterPFXMLController controller = loader.getController();
            controller.SetUsername(tusername.getText());
            tusername.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }*/
       System.out.println(search.getText());
    }
      @FXML
    private void goActivite(ActionEvent event) {
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AfficheActback.fxml"));
        try{
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/AfficheActback.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }
         
         
        
      
     
    }
     @FXML
    private void goExercice(ActionEvent event) {
          try{
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/AfficheExercice.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }
      
     
    }
        @FXML
    private void goUser(ActionEvent event) {
        titrepage.setText("goUser");
     
    }
     @FXML
    private void goAbonnement(ActionEvent event) {
        titrepage.setText("goAbonnement");
      
     
    }
        @FXML
    private void goOffer(ActionEvent event) {
        titrepage.setText("goOffer");
     
    }
     @FXML
    private void goProduit(ActionEvent event) {
        titrepage.setText("goProduit");
      
     
    }
        @FXML
    private void goCAtmehdi(ActionEvent event) {
        titrepage.setText("goCAtmehdi");
     
    }
     @FXML
    private void goReclamation(ActionEvent event) {
        titrepage.setText("goReclamation");
      
     
    }
        @FXML
    private void goTypeReclamation(ActionEvent event) {
        titrepage.setText("goTypeReclamation");
     
    }
     @FXML
    private void goEntraineur(ActionEvent event) {
        titrepage.setText("goEntraineur");
      
     
    }
        @FXML
    private void goCatAymen(ActionEvent event) {
        titrepage.setText("goCatAymen");
     
    }
        @FXML
    private void goAccueil(ActionEvent event) {
         try{
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/AdminDashboard.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }
     
    }
    
    private void iniLineChart() {
        
    
        XYChart.Series series = new XYChart.Series();
        int mon=0;
        int tu=0;
        int we=0;
        int th=0;
        int fr=0;
        int sa=0;
        int su=0;
        try {
            List<Activitie> actList = actS.afficheractivities();
            for(Activitie a : actList){
               
                String year = a.getDate_Act().split("-")[0];
                String mot =a.getDate_Act().split("-")[1];
                String jour =a.getDate_Act().split("-")[2];
                String dt = jour+"/"+mot+"/"+year;
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                LocalDate date = LocalDate.parse(dt, formatter); // LocalDate = 2010-02-23
                DayOfWeek dow = date.getDayOfWeek();  // Extracts a `DayOfWeek` enum object.
                 String output = dow.getDisplayName(TextStyle.SHORT, Locale.US);
                 if(output.equals("Mon")){mon+=actS.countparticipation(a.getId());}
                 if(output.equals("Tue")){tu+=actS.countparticipation(a.getId());}
                 if(output.equals("Wed")){we+=actS.countparticipation(a.getId());}
                 if(output.equals("Thu")){th+=actS.countparticipation(a.getId());}
                 if(output.equals("Fri")){fr+=actS.countparticipation(a.getId());}
                 if(output.equals("Sat")){sa+=actS.countparticipation(a.getId());}
                 if(output.equals("Sun")){su+=actS.countparticipation(a.getId());}
                
            }
            
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
     
    
        
        
        
        series.getData().add(new XYChart.Data("Monday",mon));
        series.getData().add(new XYChart.Data("tuesday",tu));
        series.getData().add(new XYChart.Data("wednesday",we));
        series.getData().add(new XYChart.Data("thursday",th));
        series.getData().add(new XYChart.Data("friday",fr));
        series.getData().add(new XYChart.Data("saturday",sa));
        series.getData().add(new XYChart.Data("sunday",su));
        lineChart.getData().addAll(series);
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color:transparent");
        series.getNode().setStyle("-fx-stroke:#FFD6DC");
    
    
    
    }
    private void piec(){
    
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
        
                new PieChart.Data("and",15),
                new PieChart.Data("agga",5),
                new PieChart.Data("hhh",20),
                new PieChart.Data("bb",22),
                new PieChart.Data("bbvv",71),
                new PieChart.Data("ada",101)
                
        
        
        );
        pieChart.setData(pieChartData);
        
    
    }
   
    
    
}
