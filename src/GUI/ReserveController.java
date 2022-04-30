/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Produit;
import entities.Reserve;
import java.awt.SystemTray;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import services.ProduitService;
import services.ReserveService;

/**
 * FXML Controller class
 *
 * @author Mehdi AZZAZ
 */
public class ReserveController implements Initializable {

    @FXML
    private TableView<Reserve> tableres;
    @FXML
    private TableColumn<Reserve, Integer> idr;
    @FXML
    private TableColumn<Reserve, String> Nomr;
    @FXML
    private TableColumn<Reserve, Integer> prixr;
    @FXML
    private TableView<Produit> tableact;
    private Produit cc=null;
    @FXML
    private TableColumn<Produit, Integer> id;
     @FXML
    private TextField recherche;
     
    @FXML
    private TableColumn<Produit, String> Nom1;
    @FXML
    private TableColumn<Produit, Integer> prix;
    @FXML
    private TableColumn<Produit, Integer> quantite;
    @FXML
    private TableColumn<Produit, String> photo;
   
    @FXML
    private TextField tnom;
    private Label erreurref1;
      private Label erreurref;
       private Label nomerreur;
    @FXML
    private TextField tprix;
    @FXML
    ProduitService servact = new ProduitService();
    ReserveService servrec =new ReserveService();
    @FXML

  
    ObservableList<Produit>  ActList = FXCollections.observableArrayList();
    ObservableList<Reserve>  ActList1 = FXCollections.observableArrayList();
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             
    searchact();
    
   
        
        
        
        
        
    
        tnom.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            tnom.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
 tprix.textProperty().addListener((observable, oldValue, newValue) -> {
         if (!newValue.matches("\\s0-9")) {
            tprix.setText(newValue.replaceAll("[^\\s0-9]", ""));
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




        tableact.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                cc = (Produit)tableact.getSelectionModel().getSelectedItem();
                System.out.println(cc);
                
            tnom.setText(cc.getNom());
             tprix.setText(String.valueOf(cc.getPrix()));
                 
                
               
               
            }
          });
    }   
    
   
        ObservableList myList ;
        
     private void refreshTable(List<Produit> la) {
        try {   
            
            tableact.getItems().clear();//actualiser
            
           List<Produit> listabon = servact.afficherProduit();
           ActList.addAll(la);
            
          
         tableact.setItems(ActList);
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
        
    }
      private void refreshTableres(List<Reserve> l) {
        try {   
            
            tableres.getItems().clear();//actualiser
            
           List<Reserve> listabon = servrec.afficherReserve();
           ActList1.addAll(l);
            
          
         tableres.setItems(ActList1);
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
        
    }
      private void loadDateres(List<Reserve> l) {
        
        
        
        
        idr.setCellValueFactory(new PropertyValueFactory<>("id"));
        Nomr.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prixr.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
     
        

        if(l!=null){
        refreshTableres(l);
     }
   
    
      }
     private void loadDate(List<Produit> la) {
        
        
        
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
        

        if(la!=null){
        refreshTable(la);
     }

    }    

    @FXML
    private void AjouterRES(ActionEvent event) throws SQLException {
         if( Validchamp(tnom) &&  ValidchampD(tprix) ){  

    Reserve a = new Reserve();

        a.setNom(tnom.getText());
       a.setPrix(Integer.parseInt(tprix.getText()));
      
       
      
        ReserveService sp = new ReserveService();
        sp.afficherReserve(a);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nouvelle Categories");
        alert.setHeaderText(null);
        alert.setContentText("Votre categoires est ajouté");
        alert.showAndWait();
         SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
          
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getToolkit().createImage(getClass().getResource("icon.png"));

       

        
         }
    loadDateres(servrec.afficherReserve());
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
    private void supprimerRES(ActionEvent event) {
    }
    public  void searchact(){
         try {
             
             List<Reserve> allact = servrec.afficherReserve();
     recherche.textProperty().addListener((observable, oldValue, newValue) -> {
   // System.out.println(newValue.toString());
   if( newValue.isEmpty()){
       //loadDate();
       
           
       
       
       
       
       loadDateres(allact);
       
       
   }else{
   
       List<Reserve> a = new ArrayList();
       a = allact.stream().filter(item-> {
           
           
            int index = item.getNom().toUpperCase ().indexOf(newValue.toUpperCase ());
            
             int index2 = Integer.toString(item.getId()).toUpperCase ().indexOf(newValue.toUpperCase ());
              int index3 = Integer.toString(item.getPrix()).toUpperCase ().indexOf(newValue.toUpperCase ());
            

               
              if((index ==-1)&&(index2==-1)&&(index3==-1) )
              {
              return false;
              }else{
             
                  return true ;
              }
                       }
       
       ).collect(Collectors.toList());
       
     
       loadDateres(a);
   
   }
   
});
         
             loadDateres(allact);
       
      } catch (SQLException ex) {
             System.out.println(ex);
         }  
   
}
}
