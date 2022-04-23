/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class AdminDashboardController implements Initializable {
    @FXML
    private TextField search;
     @FXML
    private Label titrepage;
      @FXML
    private GridPane ll;
      
      private Button pp ;
      
     Stage stage ;
     Scene scene ;
     
  
 

    /**
     * Initializes the controller class.
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("lool");
    }
      @FXML
    private void addAct(ActionEvent event) {
      
    }
      @FXML
    private void goActivite(ActionEvent event) {
      
         
         
        
      
     
    }
     @FXML
    private void goExercice(ActionEvent event) {
         
      
     
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
   
    
    
}
