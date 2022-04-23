/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Category;
import entities.Exercice;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.CatService;
import services.ExerciceService;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class ModifierExController extends AjouterExController{

    @FXML
    private Pane addbutton;
    @FXML
    private Label titrepage;
    @FXML
    private Button button;
    @FXML
    private TextArea descex;
    @FXML
    private TextField Tnameex;
    @FXML
    private ComboBox<Category> catselect;
    @FXML
    private Label lblNameError;
    @FXML
    private Label lblMailError;
    @FXML
    private Label lblPassError;
    @FXML
    private Label lblCountryError;
    @FXML
    private Label lblCityError;
    @FXML
    private Label lblPassError1;
    @FXML
    private TextField dureeex;
    @FXML
    private Label lblError; 

    @FXML
    private Label idex1;
     CatService catS = new CatService();
    ExerciceService exS = new ExerciceService();
    /**
     * Initializes the controller class.
     */
    /*
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    */

     public void reciveEx(Exercice ex){
         
         idex1.setText(Integer.toString(ex.getId()));
         descex.setText(ex.getDescription_Exercice());   
         Tnameex.setText(ex.getNom_Exercice());
         dureeex.setText(ex.getDure_Exercice());
         catselect.setValue(ex.getCategory());
  
     
     
     }
       private boolean Validchamp(TextField T){
         if(T.getText().isEmpty() | T.getLength() <=1 ){ //verifier nom o deesc vide 
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur de formulaire");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir tous les champs!");
            alert.showAndWait();
      return false;
    }return true;
}

    @FXML
    private void updateEx(ActionEvent event) {
        Category c = new Category();
            
            c.setId(Integer.parseInt(catselect.getValue().toString().split("_")[0]));
            c.setNom_Cat(catselect.getValue().toString().split("_")[1]);
            
            Exercice ex = new Exercice();
            ex.setCategory(c);
            ex.setDescription_Exercice(descex.getText());
            ex.setDure_Exercice(dureeex.getText());
            ex.setNom_Exercice(Tnameex.getText());
            ex.setId(Integer.parseInt(idex1.getText()));
               if(Validchamp(dureeex)&&Validchamp(Tnameex)&&descex.getText()!=null){
                  try {
                      exS.modifierExercice(ex);
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Nouvelle ajoute");
          alert.setHeaderText(null);
          alert.setContentText("Votre exercice est modifié");
          alert.showAndWait();
                        try{
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/AfficheExercice.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

                    }catch(IOException e){
                        System.out.println(e);
        
                                    }
                  } catch (SQLException l) {
                      System.out.println(l);
                  }
        
     
    
        
        }
    }

    @FXML
    private void DeleteEx(ActionEvent event) {
        try {
            exS.DeleteExercic(Integer.parseInt(idex1.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("NSuppression");
          alert.setHeaderText(null);
          alert.setContentText("Votre exercice est supprime");
          alert.showAndWait();
                        try{
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/AfficheExercice.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

                    }catch(IOException e){
                        System.out.println(e);
        
                                    }
        } catch (SQLException xx) {
            System.out.println(xx);
        }
        
    }
    
}
