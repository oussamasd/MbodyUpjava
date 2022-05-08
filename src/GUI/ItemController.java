/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Activitie;
import entities.ImageAct;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import services.ActivityService;

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
    
    Activitie ac ;
    @FXML
    private HBox rateHbox;
    
    Rating r = new Rating();
    
    ActivityService actS = new ActivityService();
    @FXML
    private HBox hbb;
    Label l = new Label();

    
    
   public void safe(Activitie act) throws SQLException{
       ac=act;
       
       int a = Integer.parseInt(actS.countrate(act.getId()).split("_")[0]);
       int b = Integer.parseInt(actS.countrate(act.getId()).split("_")[1]);
       float c=0;
       if(b!=0){
           c=a/b;
       }
       l.setText((double)c +"/5");
       //float c = a/b;
       r.setRating((double)c);
       r.setDisable(true);
       hbb.getChildren().add(r);
        hbb.getChildren().add(l);
       
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
        
        try{
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
        
        }
    }
    
}
