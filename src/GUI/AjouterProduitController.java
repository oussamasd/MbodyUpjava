/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Produit;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import services.ProduitService;



/**
 * FXML Controller class
 *
 * @author Mehdi AZZAZ
 */
public class AjouterProduitController implements Initializable {

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
    private TextField tdescription;
    @FXML
    private TableView<Produit> tableact;
    @FXML
    private TableColumn<Produit, Integer> id;
        private Produit cc=null;

    @FXML
    private TableColumn<Produit, String> Nom;
  
   
    private Label erreurref;
    private Label nomerreur;
    @FXML
    private Label titrepage12;
    private TextField tnom1;
    private Label erreurref1;
    @FXML
    private TextField tquantite;
    @FXML
    private TableColumn<Produit, Integer> prix;
    @FXML
    private TableColumn<Produit, Integer> quantite;
    @FXML
    private TextField tprix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loadDate();
       
     

 tnom.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            tnom.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
 tnom1.textProperty().addListener((observable, oldValue, newValue) -> {
         if (!newValue.matches("\\sa-zA-Z*")) {
            tnom1.setText(newValue.replaceAll("[^\\s0-9]", ""));
        }
    });
 tdescription.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            tdescription.setText(newValue.replaceAll("[^\\s0-9]", ""));
        }
    });
 
tnom.textProperty().addListener(new ChangeListener<String>()
            {
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   if(newValue.isEmpty())
        {
                       erreurref.setText("Champs vide!");
                 
        }
                   else if(newValue.length()>10)
        {
                       erreurref.setText("Max longueur 10");
          
        }
                   else
        {
                erreurref.setText("   ");
              
                }
                }
                
                
            });
tprix.textProperty().addListener(new ChangeListener<String>()
            {
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   if(newValue.isEmpty())
        {
                       erreurref1.setText("Champs vide!");
                 
        }
                   else if(newValue.length()>10)
        {
                       erreurref1.setText("Max longueur 10");
          
        }
                   else
        {
                erreurref.setText("   ");
              
                }
                }
                
                
            });
tquantite.textProperty().addListener(new ChangeListener<String>()
            {
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   if(newValue.isEmpty())
        {
                       nomerreur.setText("Champs vide!");
                 
        }
                   else if(newValue.length()>200)
        {
                       nomerreur.setText("Max longueur 200");
          
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
                cc = (Produit)tableact.getSelectionModel().getSelectedItem();
                System.out.println(cc);
                
            tnom.setText(cc.getNom());
             tprix.setText(String.valueOf(cc.getPrix()));
                 tquantite.setText(String.valueOf(cc.getQuantite()));
                
               
               
            }
          });
    }   
    ProduitService servact = new ProduitService();
    ObservableList<Produit>  ActList = FXCollections.observableArrayList();
        ObservableList myList ;
     private void refreshTable() {
        try {   
            
            tableact.getItems().clear();//actualiser
            
           List<Produit> listabon = servact.afficherProduit();
           ActList.addAll(listabon);
            
          
         tableact.setItems(ActList);
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
        
    }
    private void loadDate() {
         
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
       
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
     

           
       // photo.setCellValueFactory(new PropertyValueFactory<Abonnement, ImageView>("imgv"));
        refreshTable();
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
         if( Validchamp(tnom) &&  Validchamp(tnom1) &&  ValidchampD(tdescription)){  

    Produit a = new Produit();

        a.setNom(tnom.getText());
       a.setPrix(Integer.parseInt(tprix.getText()));
        a.setPrix(Integer.parseInt(tquantite.getText()));
      
      
        ProduitService sp = new ProduitService();
        try {
          sp.ajouterCategoriesCa(a);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Nouvelle Categories");
          alert.setHeaderText(null);
          alert.setContentText("Votre categoires est ajouté");
          alert.showAndWait();
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
          
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getToolkit().createImage(getClass().getResource("icon.png"));

       

        
         }
         loadDate();
    }
    

    @FXML
    private void BtnSuppRec(ActionEvent event) throws SQLException {
        
     if (tableact.getSelectionModel().isEmpty() ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        JOptionPane.showMessageDialog(null,"Aucune Categories est selectionné ,veuillez choisir une Categories");
     }else{
   int responce=JOptionPane.showConfirmDialog(null, "Attention vous allez supprimer l'Categories sélectionné etes-vous sur ?","Suppression",JOptionPane.YES_NO_OPTION);
            if (responce==JOptionPane.YES_OPTION){
           ProduitService so = new ProduitService();
                    Produit a = (Produit) tableact.getSelectionModel().getSelectedItem();
                     so.SupprimerCategories(a);
             //refresh(true);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);

         alert.setContentText("Votre Categories a été bien supprimé");
                  JOptionPane.showMessageDialog(null,"Categories supprimé");

            } else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Votre Categories a été bien supprimé");
                 JOptionPane.showMessageDialog(null,"Suppression annulé");
            }
           SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
             java.awt.Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getToolkit().createImage(getClass().getResource("icon.png"));

       
        
    }
             loadDate();

    }
        private boolean Validchamp(TextField T){
         if(T.getText().isEmpty() | T.getLength() <4 ){ //verifier nom o deesc vide 
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur de formulaire");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir tous les champs!");
            alert.showAndWait();
      return false;
    }return true;
   
    
    }
        private boolean ValidchampD(TextField T){
         if(T.getText().isEmpty() | T.getLength() <1 ){ //verifier nom o deesc vide 
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur de formulaire");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir tous les champs!");
            alert.showAndWait();
      return false;
    }return true;


        }
    

    @FXML
    private void BtnModifier(ActionEvent event) {
           Produit a = tableact.getSelectionModel().getSelectedItem();

        a.setNom(tnom.getText());
        a.setPrix(Integer.parseInt(tprix.getText()));
        a.setPrix(Integer.parseInt(tquantite.getText()));
      
        ProduitService sp = new ProduitService();
        try {

            sp.ModifierCategories(a);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.show();
            alert.setTitle("Modifier !");
            alert.setContentText("Modifier avec succés");
          
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
            alert.setTitle("fail !");
           alert.setContentText("failed ");
        }
       loadDate();
    }
    }
    

