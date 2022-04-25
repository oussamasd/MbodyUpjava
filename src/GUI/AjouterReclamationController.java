/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import services.reclamationService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private Pane addbutton;
    @FXML
    private Label titrepage;
    @FXML
    private Label titrepage1;
    @FXML
    private Label titrepage11;
    @FXML
    private TextField tnom;
    @FXML
    private TextField tdescription;
    @FXML
    private TableView<Reclamation> tableact;
    @FXML
    private TableColumn<Reclamation, Integer> id;
    private Reclamation cc=null;
    @FXML
    private TableColumn<Reclamation, String> Nom;
    @FXML
    private TableColumn<Reclamation, String> Description;
    @FXML
    private Label erreurref;
    @FXML
    private Label nomerreur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        affichage();
     

 tnom.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            tnom.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
 tdescription.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            tdescription.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
 
tnom.textProperty().addListener(new ChangeListener<String>()
            {
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   if(newValue.isEmpty())
        {
                       erreurref.setText("Champs vide!");
                 
        }
                   else if(newValue.length()>5)
        {
                       erreurref.setText("Max longueur 5");
          
        }
                   else
        {
                erreurref.setText("   ");
              
                }
                }
                
                
            });
tdescription.textProperty().addListener(new ChangeListener<String>()
            {
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   if(newValue.isEmpty())
        {
                       nomerreur.setText("Champs vide!");
                 
        }
                   else if(newValue.length()>20)
        {
                       nomerreur.setText("Max longueur 20");
          
        }
                   else
        {
                nomerreur.setText("   ");
              
                }
                }
                
                
            });



        tableact.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                cc = (Reclamation)tableact.getSelectionModel().getSelectedItem();
                System.out.println(cc);
                
            tnom.setText(cc.getNom());
                tdescription.setText(cc.getDescription());
                
               
               
            }
          });
        
        
        
        
        
    }    

    @FXML
    private void goAccueil(ActionEvent event) {
    }

    @FXML
    private void goUser(ActionEvent event) {
    }

    @FXML
    private void goActivite(ActionEvent event) {
    }

    @FXML
    private void goExercice(ActionEvent event) {
    }

    @FXML
    private void goAbonnement(ActionEvent event) {
    }

    @FXML
    private void goOffer(ActionEvent event) {
    }

    @FXML
    private void goProduit(ActionEvent event) {
    }

    @FXML
    private void goCAtmehdi(ActionEvent event) {
    }

    @FXML
    private void goReclamation(ActionEvent event) {
    }

    @FXML
    private void goTypeReclamation(ActionEvent event) {
    }

    @FXML
    private void goEntraineur(ActionEvent event) {
    }

    @FXML
    private void goCatAymen(ActionEvent event) {
    }

    @FXML
    private void BtnAjouterP(ActionEvent event) throws SQLException {
//          Reclamation p = new Reclamation(tnom.getText(), tdescription.getText());
//        reclamationService ps = new reclamationService();
//        try {
//            ps.ajouterp(p);
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Succes");
//            alert.setContentText("Reclamation ajoutée");
//            alert.show();
//            tnom.setText("");
//            tdescription.setText("");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }

        String ref = tnom.getText();
        String nom = tdescription.getText();
       
        
      //  LocalDate datedebut = datedebpicker.getValue();
      
       if(tnom.getText().trim().isEmpty()){
       Alert fail= new Alert(Alert.AlertType.INFORMATION);
        fail.setHeaderText("failure");
        fail.setContentText("Champs vide !");
        fail.showAndWait();    
         
     }  
    
       // Date datedebut1 = LocalDate.pa
       // Date datefin = datefinpicker.
      // Hotel h = new Hotel();
       Reclamation p1=new Reclamation(ref,nom);
       reclamationService ps = new reclamationService();
       //ps.chercher(ref1);
       
           
       if (ps.chercher(ref)==false)
       {
       ps.ajouterp(p1);
       FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReclamation.fxml"));
       
       try{  
        
       Parent root = loader.load();
        affichage();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Ajout Reclamation");

		// alert.setHeaderText("Results:");
		alert.setContentText("Reclamation Ajouté avec succes!");

		alert.showAndWait();
      }catch(IOException ex){
        System.out.println(ex.getMessage());
      }
       }
       else
       {
            Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Ajout Reclamation");

		// alert.setHeaderText("Results:");
		//alert.setContentText("ID Existant!");

		alert.showAndWait();
       }
    }

    @FXML
    private void BtnSuppRec(ActionEvent event) {
        
        reclamationService ps = new reclamationService();
        String ref = tnom.getText(); 
       ps.deleteReclamation(ref);
       affichage();
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Suppression Reclamation");
		alert.setContentText("Reclamation supprimé avec succes!");
		alert.showAndWait();
                
    }
    private void affichage(){
        try {
       reclamationService sp = new reclamationService();
       List events=sp.afficherpersonne();
       ObservableList<Reclamation> et=FXCollections.observableArrayList(events);
       //listview.setItems(et);
       
      tableact.setItems(et);
       
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
       Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       Description.setCellValueFactory(new PropertyValueFactory<>("description"));
      
       
       
        } catch (SQLException ex) {
                Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    }

    @FXML
    private void BtnModifier(ActionEvent event) {
        String nom = tnom.getText();
        String description = tdescription.getText();
      
       
       Reclamation p1=new Reclamation(nom,description);
       reclamationService ps = new reclamationService();
       ps.modifierRec(p1);
       affichage();
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Modification Reclamation");

		// alert.setHeaderText("Results:");
		alert.setContentText("Reclamation modifié avec succes");

		alert.showAndWait();
    }

    
}
