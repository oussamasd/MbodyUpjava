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
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
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
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.ActivityService;
import services.CatService;
import services.ExerciceService;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class ModifierActController extends AjouterActController {
     @FXML
    private TextField Tnameact;
     @FXML
    private Label iiact;
    @FXML
    private TextField Ttime;
    @FXML
    private TextField numberplace;
    @FXML
    private TextArea desc;
    @FXML
    private DatePicker datepicker;
    @FXML
    private ComboBox catselect ;
    @FXML
    private HBox r;
    @FXML
    private HBox r2;
     ActivityService actS = new ActivityService();
    ExerciceService exS = new ExerciceService();
    CatService catS = new CatService();
    public Collection<CheckBox> lc = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Category>list;
        
        try {
            list = FXCollections.observableArrayList(catS.affichercat());
            catselect.setItems(list);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        // TODO
         
    }  
    public void reciveAct(Activitie act){
        iiact.setText(Integer.toString(act.getId()));
        
        
        try {
            for(Exercice ec : exS.afficherexercice()){
                CheckBox cex = new CheckBox(ec.st());
                lc.add(cex);
               cex.setId(Integer.toString(ec.getId()));
                for(Exercice exact : exS.getExerciceAct(act.getId())){
                
                    if(exact.getId()==ec.getId()){
                    
                        cex.setSelected(true);
                    
                    }
                
                
                }
                 k+=1;
                if(k<7){
                    r.getChildren().add(cex);
                }else{
                
                    r2.getChildren().add(cex);
                }
                
                
                
                r.setSpacing(5);
                r2.setSpacing(5);
                
               
            }
                
                
            
            
        } catch (SQLException cc) {
            System.out.println(cc);
        }
        
        
      
        Tnameact.setText(act.getNom_Act());
        Ttime.setText(act.getTemp_act());
        numberplace.setText(Integer.toString(act.getQuantity()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int year = Integer.parseInt(act.getDate_Act().split("-")[0]);
        int month = Integer.parseInt(act.getDate_Act().split("-")[1]);
        int jour =   Integer.parseInt(act.getDate_Act().split("-")[2]);      
        datepicker.setValue(LocalDate.of(year, month, jour));
        desc.setText(act.getDescription_Act());
        catselect.setValue(act.getCategory());
        catselect.getSelectionModel().select(3);
        
        
        
        
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
    private void update(ActionEvent event) {
               Activitie act = new Activitie();
                   Collection<Exercice> Lex = new ArrayList();
        
        
             for(CheckBox ck : lc){
                 if(ck.isSelected()){
                  
                          try {
                         Lex.add(exS.Getexercice(Integer.parseInt(ck.getText().split("-")[0])));
         
                         
                     } catch (SQLException a) {
                         System.out.println(a);
 
                 }
                 }
             }
              act.setExercices(Lex);
              System.out.println(act.getExercices());
              if(Validchamp(Tnameact)&&Validchamp(Ttime)&&Validchamp(numberplace)&&desc.getText()!=null){
              act.setId(Integer. parseInt(iiact.getText()));
            act.setNom_Act(Tnameact.getText());
            act.setTemp_act(Ttime.getText());
            act.setQuantity(Integer. parseInt(numberplace.getText()) );
            act.setDate_Act(datepicker.getValue().toString());
            act.setDescription_Act(desc.getText());
            Category c = new Category();
            
            c.setId(Integer.parseInt(catselect.getValue().toString().split("_")[0]));
            c.setNom_Cat(catselect.getValue().toString().split("_")[1]);
            act.setCategory(c);
      /*  try {
            act.setExercices(exS.afficherexercice());
        } catch (SQLException ex) {
            System.out.println(ex);
        }*/
              
              if(verif){
                  try {
                      actS.modifierActivity(act);
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Activité modifie");
          alert.setHeaderText(null);
          alert.setContentText("Votre act est modifier");
          alert.showAndWait();
                        try{
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/AfficheActback.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

                    }catch(IOException e){
                        System.out.println(e);
        
                                    }
                  } catch (SQLException ex) {
                      System.out.println(ex);
                  }
      
    }
              }
    }
              
              
    @FXML
    private void delete(ActionEvent event) {
         Activitie act = new Activitie();
              act.setId(Integer. parseInt(iiact.getText()));
            act.setNom_Act(Tnameact.getText());
            act.setTemp_act(Ttime.getText());
            act.setQuantity(Integer. parseInt(numberplace.getText()) );
            act.setDate_Act(datepicker.getValue().toString());
            act.setDescription_Act(desc.getText());
          
        
       try {
                      actS.SupprimerActivity(act);
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Activité suppressme");
          alert.setHeaderText(null);
          alert.setContentText("Votre act est supprimz");
          alert.showAndWait();
                        try{
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/AfficheActback.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

                    }catch(IOException e){
                        System.out.println(e);
        
                                    }
                  } catch (SQLException ex) {
                      System.out.println(ex);
                  }
     
    }
    
}
