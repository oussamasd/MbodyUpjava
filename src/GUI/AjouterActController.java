/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import entities.Activitie;
import entities.Category;
import entities.Exercice;
import entities.ImageAct;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ActivityService;
import services.CatService;
import services.ExerciceService;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class AjouterActController extends AdminDashboardController {
    
   
    @FXML
    private TextField Tnameact;
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
    public Boolean verif = true;
    ActivityService actS = new ActivityService();
    ExerciceService exS = new ExerciceService();
    CatService catS = new CatService();
    @FXML
    private Pane addbutton;
    @FXML
    private Label titrepage;
    @FXML
    private Button button;
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
    private Label lblError;
    @FXML
    private HBox r;
    @FXML
    private HBox r2;
    public int k =0; 
    public Collection<CheckBox> lc = new ArrayList();
    @FXML
    private Label imgnbr;
    
    List<File> listF=null;
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
       
        try {
            
            for(Exercice ec : exS.afficherexercice()){
                CheckBox cex = new CheckBox(ec.st());
                lc.add(cex);
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
    private void addActiv1(ActionEvent event) {
        Collection<Exercice> Lex = new ArrayList();
        Activitie act = new Activitie();
        
             for(CheckBox ck : lc){
                 if(ck.isSelected()){
                  
                          try {
                         Lex.add(exS.Getexercice(Integer.parseInt(ck.getText().split("-")[0])));
                              
                        
                          act.setExercices(Lex);
                     } catch (SQLException a) {
                         System.out.println(a);
                     }
                    
                 
                 }
         
                   
             }
              System.out.println(Lex);
        if(Validchamp(Tnameact)&&Validchamp(Ttime)&&Validchamp(numberplace)&&desc.getText()!=null){
            
            act.setNom_Act(Tnameact.getText());
            act.setTemp_act(Ttime.getText());
            act.setQuantity(Integer. parseInt(numberplace.getText()) );
            act.setDate_Act(datepicker.getValue().toString());
            act.setDescription_Act(desc.getText());
            Category c = new Category();
            
            c.setId(Integer.parseInt(catselect.getValue().toString().split("_")[0]));
            c.setNom_Cat(catselect.getValue().toString().split("_")[1]);
            act.setCategory(c);
        
           
        
              
             
                  try {
                      
                        if(listF != null){
                            Collection <ImageAct> listim = new ArrayList();
                                     for(File f : listF){
                                        System.out.println(f.getAbsolutePath());
                                         ImageAct ima = new ImageAct();
                                         ima.setUrl(f.getAbsolutePath());
                                         listim.add(ima);
                                         
            
                                     }
                                     
                             act.setImages(listim);
        
        
                        }
                        actS.ajouterActivity(act);
        
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Nouvelle Activite");
          alert.setHeaderText(null);
          alert.setContentText("Votre Act est ajouté");
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

  

    @FXML
    private void uploadimages(ActionEvent event) {
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("upload images");
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         listF = filechooser.showOpenMultipleDialog(stage);
        if(listF != null){
            imgnbr.setText(Integer.toString(listF.size()));
        
        }
        
        
        
    }



    
}
