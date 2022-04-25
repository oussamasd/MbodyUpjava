/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Categories;
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
import services.CategoriesService;


/**
 * FXML Controller class
 *
 * @author Mehdi AZZAZ
 */
public class AjouterCategoriesController implements Initializable {

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
    private TableView<Categories> tableact;
    @FXML
    private TableColumn<Categories, Integer> id;
        private Categories cc=null;

    @FXML
    private TableColumn<Categories, String> Nom;
    @FXML
    private TableColumn<Categories, String> type;
    @FXML
    private TableColumn<Categories, String> description;
    @FXML
    private Label erreurref;
    @FXML
    private Label nomerreur;
    @FXML
    private Label titrepage12;
    @FXML
    private TextField tnom1;
    @FXML
    private Label erreurref1;

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
            tnom1.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
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
tnom1.textProperty().addListener(new ChangeListener<String>()
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
tdescription.textProperty().addListener(new ChangeListener<String>()
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
                cc = (Categories)tableact.getSelectionModel().getSelectedItem();
                System.out.println(cc);
                
            tnom.setText(cc.getNom());
            tnom1.setText(cc.getType());
                tdescription.setText(cc.getDescription());
                
               
               
            }
          });
    }   
    CategoriesService servact = new CategoriesService();
    ObservableList<Categories>  ActList = FXCollections.observableArrayList();
        ObservableList myList ;
     private void refreshTable() {
        try {   
            
            tableact.getItems().clear();//actualiser
            
           List<Categories> listabon = servact.afficherCategories();
           ActList.addAll(listabon);
            
          
         tableact.setItems(ActList);
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
        
    }
    private void loadDate() {
         
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
       
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
     

           
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

    Categories a = new Categories();

        a.setNom(tnom.getText());
        a.setType(tnom1.getText());
        a.setDescription(tdescription.getText());
      
      
        CategoriesService sp = new CategoriesService();
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
           CategoriesService so = new CategoriesService();
                    Categories a = (Categories) tableact.getSelectionModel().getSelectedItem();
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
           Categories a = tableact.getSelectionModel().getSelectedItem();

        a.setNom(tnom.getText());
        a.setType(tnom1.getText());
        a.setDescription(tdescription.getText());
      
        CategoriesService sp = new CategoriesService();
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
    

