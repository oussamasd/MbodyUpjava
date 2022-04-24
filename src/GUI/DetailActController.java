/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Activitie;
import entities.Exercice;
import entities.ImageAct;
import entities.Rate;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
public class DetailActController implements Initializable {

    @FXML
    private TextArea txtcomment;
    @FXML
    private Label tnomact;
    @FXML
    private Label tcat;
    @FXML
    private Label tdate;
    @FXML
    private Label ttemps;
    @FXML
    private Label tdescription;
    @FXML
    private Label tnombreplace;
    @FXML
    private VBox vboxEx;
    @FXML
    private ImageView grandpict;
    
    ImageAct iact = new ImageAct();
    ImageAct initialimg = new ImageAct();
    Activitie ac = new Activitie();
    int idImgInitial ;
    @FXML
    private Button participe;
    
    Rating ra = new Rating(5,0);
    @FXML
    private HBox hbrate;
    ActivityService actS = new ActivityService();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void safeAct(Activitie a){
        ac=a;
        tnomact.setText(a.getNom_Act());
        tcat.setText(a.getCategory().getNom_Cat());
        tdate.setText(a.getDate_Act());
        ttemps.setText(a.getTemp_act());
        tdescription.setText(a.getDescription_Act());
        tnombreplace.setText("/"+a.getQuantity());
        if(!a.getExercices().isEmpty()){
            for(Exercice ee : a.getExercices()){
                Label nom = new Label();
                Label dure = new Label();
                Label desc = new Label();
                Label cat = new Label();
                nom.setText("Nom Exercice  :"+ee.getNom_Exercice());
                dure.setText("Dure Exercice :"+ee.getDure_Exercice());
                //cat.setText("Category       :"+ee.getCategory().getNom_Cat());
                desc.setText("Description    :"+ee.getDescription_Exercice());
                vboxEx.getChildren().addAll(nom , dure , desc , cat);
                
            
            
            }

        }
        if(!a.getImages().isEmpty()){
            for(ImageAct ia : a.getImages()){
                idImgInitial=ia.getId();
                initialimg=ia;
                File file = new File(ia.getUrl());
                 try {
                     iact=ia;
               String localUrl = file.toURI().toURL().toString();
               grandpict.setFitHeight(200);
               grandpict.setFitWidth(200);
               Image image = new Image(localUrl);
               grandpict.setImage(image);
               grandpict.setFitHeight(352);
               grandpict.setFitWidth(473);
               
               
               
           
           } catch (MalformedURLException ex) {
               System.out.println(ex);;
           }
           
          break;
            
            }
        
        
        }
      
    hbrate.getChildren().add(ra);
   ra.updateOnHoverProperty();
   
        try {
            //*********************User id
         ra.setRating(actS.rated(a.getId(), 5));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    if(ra.getRating()==0){
         ra.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                try {
                    ra.setRating((Double)newValue);
                    Rate rt = new Rate();
                    rt.setId_act(a.getId());
                    rt.setId_use(5);
                    rt.setRating((int)ra.getRating());
                    
                    actS.participe(rt);
                    
                    ra.setDisable(true);
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        });
    
    }else{
    
    ra.setDisable(true);
    }
   
    
    }

    @FXML
    private void preciousimg(ActionEvent event) {
        
         int LsizeImg = ac.getImages().size();
       for(ImageAct ia : ac.getImages()){
       
           if(ia.getId()>idImgInitial){
           
                if(ia.getId()==iact.getId()-1){
               
                        File file = new File(ia.getUrl());
                 try {
                     iact=ia;
               String localUrl = file.toURI().toURL().toString();
               grandpict.setFitHeight(200);
               grandpict.setFitWidth(200);
               Image image = new Image(localUrl);
               grandpict.setImage(image);
               grandpict.setFitHeight(352);
               grandpict.setFitWidth(473);
               
               
               
           
                     } catch (MalformedURLException ex) {
                System.out.println(ex);;
                    }
           
                    }
                System.out.println("h11");
               
           }else{
            System.out.println("h22");
               
                  File file = new File(initialimg.getUrl());
                 try {
                     iact=initialimg;
               String localUrl = file.toURI().toURL().toString();
               grandpict.setFitHeight(200);
               grandpict.setFitWidth(200);
               Image image = new Image(localUrl);
               grandpict.setImage(image);
               grandpict.setFitHeight(352);
               grandpict.setFitWidth(473);
               
               
               
           
                     } catch (MalformedURLException ex) {
                System.out.println(ex);;
                    }
           
           
           }
           
           
       
       
       }
        
    }

    @FXML
    private void nextimg(ActionEvent event) {
       int LsizeImg = ac.getImages().size();
       for(ImageAct ia : ac.getImages()){
       
           if(iact.getId()+1<idImgInitial+LsizeImg){
           
                if(ia.getId()==iact.getId()+1){
               
                        File file = new File(ia.getUrl());
                 try {
                     iact=ia;
               String localUrl = file.toURI().toURL().toString();
               grandpict.setFitHeight(200);
               grandpict.setFitWidth(200);
               Image image = new Image(localUrl);
               grandpict.setImage(image);
               grandpict.setFitHeight(352);
               grandpict.setFitWidth(473);
               
               
               
           
                     } catch (MalformedURLException ex) {
                System.out.println(ex);;
                    }
           
                    }
               
           }else{
           
               
                  File file = new File(initialimg.getUrl());
                 try {
                     iact=initialimg;
               String localUrl = file.toURI().toURL().toString();
               grandpict.setFitHeight(200);
               grandpict.setFitWidth(200);
               Image image = new Image(localUrl);
               grandpict.setImage(image);
               grandpict.setFitHeight(352);
               grandpict.setFitWidth(473);
               
               
               
           
                     } catch (MalformedURLException ex) {
                System.out.println(ex);;
                    }
           
           
           }
           
           
       
       
       }
       
        
    }

    @FXML
    private void backToaccueil(MouseEvent event) {
        try{
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/Accueil.fxml"));
                 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 Scene scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }
    }

    @FXML
    private void sendComment(ActionEvent event) {
    }

    @FXML
    private void partic(ActionEvent event) {
    }
    
}
