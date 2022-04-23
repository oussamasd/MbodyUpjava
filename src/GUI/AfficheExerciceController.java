/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Activitie;
import entities.Category;
import entities.Exercice;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.CatService;
import services.ExerciceService;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class AfficheExerciceController extends AdminDashboardController{

    @FXML
    private GridPane ll;
    @FXML
    private TextField search;
    @FXML
    private Pane addbutton;
    @FXML
    private Label titrepage;
    @FXML
    private TableView<Exercice> tableexercice;
    @FXML
    private TableColumn<Exercice, Integer> idex;
    @FXML
    private TableColumn<Exercice, String> nomex;
    @FXML
    private TableColumn<Exercice, String> dureex;
    @FXML
    private TableColumn<Exercice, String> descex;
    @FXML
    private TableColumn<Exercice, String> cat;
     ExerciceService exS = new ExerciceService();
    CatService catS = new CatService();
     ObservableList<Exercice>  ActList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        searchact();
    }    
    private void refreshTable(List<Exercice> la) {
        
           
            tableexercice.getItems().clear();
           ActList.addAll(la);
            
            
         tableexercice.setItems(ActList);
            
            
       
        
        
    }
     private void loadDate(List<Exercice> la) {
        
        
        
        
        idex.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomex.setCellValueFactory(new PropertyValueFactory<>("nom_Exercice"));
        dureex.setCellValueFactory(new PropertyValueFactory<>("dure_Exercice"));
        descex.setCellValueFactory(new PropertyValueFactory<>("description_Exercice"));
        cat.setCellValueFactory(new PropertyValueFactory<>("category"));
         if(la!=null){
        
        refreshTable(la);
        }
   
     }

   
    @FXML
    private void addEx(ActionEvent event) {
             try{
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/AjouterEx.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }
    }

    

    @FXML
    private void modifex(ActionEvent event) {
             Exercice a = tableexercice.getSelectionModel().getSelectedItem();
       
        System.out.println(a);
           try{
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/modifierEx.fxml"));
                 Parent root = loader.load();
                 ModifierExController mdc = loader.getController();
                 mdc.reciveEx(a);
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }
    }
        public  void searchact(){
         try {
             List<Exercice> allact = exS.afficherexercice();
         
    
     search.textProperty().addListener((observable, oldValue, newValue) -> {
   // System.out.println(newValue.toString());
   if( newValue.isEmpty()){
       //loadDate();
        
             loadDate(allact);
        
       
   }else{
   
       System.out.println(newValue);
       List<Exercice> a = new ArrayList();
       a = allact.stream().filter(item-> {
           
           
            int index = item.getNom_Exercice().toUpperCase ().indexOf(newValue.toUpperCase ());
            int index2 = item.getDescription_Exercice().toUpperCase ().indexOf(newValue.toUpperCase ());
            int index3 = Integer.toString(item.getId()).toUpperCase ().indexOf(newValue.toUpperCase ());
               
              if((index ==-1)&&(index2==-1)&&(index3==-1) ) 
              {
              return false;
              }else{
              
                  return true ;
              }
               
               
               
                       }
       
       ).collect(Collectors.toList());
       
      
       loadDate(a);
   
   }
   
});
         
             loadDate(allact);
        
      } catch (SQLException ex) {
             System.out.println(ex);
         }  
    
    }
    
}
