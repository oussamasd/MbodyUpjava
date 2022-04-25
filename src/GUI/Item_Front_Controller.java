/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Abonnement;
import entities.Activitie;
import entities.ImageAct;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class Item_Front_Controller  {

   

    
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private ImageView fruitImg;
    @FXML
    private Label nomAct;
    @FXML
    private Label dtact;
    @FXML
    private Label catact;
    @FXML
    private Label descact;
    
    Activitie ac ;

    
    
   public void safe(Abonnement act){
       
       nomAct.setText(act.getNom());
     //  dtact.setText(act.getDate_Act);
      // catact.setText(act.getCategory().getNom_Cat());
       descact.setText(act.getDescription());
       dtact.setText(act.getCategorie());

       
       if(act.getPhoto()!=null){
          
          File file = new File(act.getPhoto());
           try {
               String localUrl = file.toURI().toURL().toString();
               fruitImg.setFitHeight(200);
               fruitImg.setFitWidth(200);
               Image image = new Image(localUrl);
               fruitImg.setImage(image);
               
               
           
           } catch (MalformedURLException ex) {
               System.out.println(ex);;
           }
           
         
       
     }else{
           
           Image empty = new Image("/img/empty.jpg");
           fruitImg.setFitHeight(200);
            fruitImg.setFitWidth(200);
           fruitImg.setImage(empty);
           
           
       
       }
      
   
   }
    

   

    @FXML
    private void showMore(ActionEvent event) {
        
      /*  try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/DetailAct.fxml"));
                 Parent root = loader.load();
                 DetailActController mdc = loader.getController();
                 mdc.safeAct(ac);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 Scene scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
        }catch(IOException ex){
            System.out.println(ex);
        
        }*/
    }
    
}