/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Activitie;
import entities.ImageAct;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class ItemController  {

   

    
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

    
    
   public void safe(Activitie act){
       nomAct.setText(act.getNom_Act());
       dtact.setText(act.getDate_Act());
       catact.setText(act.getCategory().getNom_Cat());
       descact.setText(act.getDescription_Act());
       
       if(!act.getImages().isEmpty()){
          for(ImageAct aa : act.getImages()){
          File file = new File(aa.getUrl());
           try {
               String localUrl = file.toURI().toURL().toString();
               fruitImg.setFitHeight(200);
               fruitImg.setFitWidth(200);
               Image image = new Image(localUrl);
               fruitImg.setImage(image);
               
               
           
           } catch (MalformedURLException ex) {
               System.out.println(ex);;
           }
           
          break;
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
        System.out.println("hihi");
    }
    
}
