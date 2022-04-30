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
        try{
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/AfficherProduit.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }     
      
     
    }
        @FXML
    private void goCAtmehdi(ActionEvent event) {
  try{
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/AfficherCategories.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }     
    }
     @FXML
    private void goReclamation(ActionEvent event) {
          try{
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/AfficherReclamation.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
      
     
    }}
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
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/Accueil.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }
     
    }
   
    
    
}
