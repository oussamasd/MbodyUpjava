/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Activitie;
import entities.Category;
import entities.Comment;
import entities.Exercice;
import entities.ImageAct;
import entities.Mail;
import entities.Rate;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.Rating;
import services.ActivityService;
import services.CommentService;

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
    private VBox grd;
    @FXML
    private GridPane grid;
    public int iduser=6;
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
        try{
        tnombreplace.setText(actS.countparticipation(a.getId())+"/"+a.getQuantity());
        }catch(SQLException ll){
        
            System.out.println(ll);
        
        }
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
         ra.setRating(actS.rated(a.getId(), iduser));
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
                    rt.setId_use(iduser);
                    rt.setRating((int)ra.getRating());
                    
                    actS.participeRate(rt);
                    
                    ra.setDisable(true);
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        });
    
    }else{
    
    ra.setDisable(true);
    }
    
    
    //**********************************Comment**********************************
    
  /*   new Timer().scheduleAtFixedRate(new TimerTask(){
    @Override
    public void run(){
                
         System.out.println("hh");
        
        
    }
},0,5000);*/
      comt(a);  
        
       
    //*************************notif
    
    
    
  
    
        try {
            //******************************************************

            btnp();
            
        } catch (SQLException kk) {
            System.out.println(kk);
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
         CommentService cmtS = new CommentService();
         Comment co = new Comment();
         co.setActivity_id(ac.getId());
         co.setText(txtcomment.getText());
         co.setDt("2022-04-26");
         co.setId_user(iduser);
         
             
        try {
            if(!txtcomment.getText().isEmpty()){
            cmtS.ajouterCom(co);
            txtcomment.setText("");
           
             try {
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/DetailAct.fxml"));
                 Parent root = loader.load();
                 DetailActController mdc = loader.getController();
                 mdc.safeAct(ac);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 Scene scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
             } catch (IOException ml) {
                 System.out.println(ml);
             }
             }
               
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    private void btnp() throws SQLException{
    
        if(actS.countparticipation(ac.getId())>= ac.getQuantity()){
        
            participe.setText("No Place");
            
            participe.setDisable(true);
            
        
        }else if(actS.isParticipated(ac.getId(), iduser)){
            
            participe.setText("Participated");
            participe.setDisable(true);
        
        }
    
    
    }

    @FXML
    private void partic(ActionEvent event) {
        
        try {
            actS.participeAct(ac.getId(), iduser);
           participe.setText("Participated");
            participe.setDisable(true);
             
        tnombreplace.setText(actS.countparticipation(ac.getId())+"/"+ac.getQuantity());
        String us = "Mr "+iduser+"  , you participate in Activity  "+ac.getNom_Act()+"  in date : "+ac.getDate_Act();
        String emailTo="oussama.saddi2@gmail.com";
         Mail.send(
    "oussama.saddi3@gmail.com",
    "oussama123456789",
    emailTo,
    "participe in actitvity",
    us
  );
       //**************Notiv
       
           SystemTray tray = SystemTray.getSystemTray();
     //If the icon is a file
             java.awt.Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        try {
            tray.add(trayIcon);
        } catch (AWTException vb) {
            System.out.println(vb);
        }

        trayIcon.displayMessage("MbodyUp", "you partipated in this activty , check your email", MessageType.INFO);
       
       ///////***************
            
        } catch (SQLException mm) {
            System.out.println(mm);
        }
        
    }
    
    public void comt( Activitie a){
    
     
     
     int row = 1;
        CommentService cmtS = new CommentService();
        try {
          
               try {
                   List<Comment>  la = cmtS.getCmtAct(a.getId());
                     System.out.println(la);
                   //for (Activitie actt : actS.afficheractivities()) 
                   for (int i = 0; i < la.size(); i++) 
                   {
                      // System.out.println(la.get(i));
                       FXMLLoader fxmlLoader = new FXMLLoader();
                       fxmlLoader.setLocation(getClass().getResource("/GUI/comentaire.fxml"));
                       AnchorPane anchorPane = fxmlLoader.load();
                      
                       ComentaireController itemController = fxmlLoader.getController();
                       itemController.safecmt(la.get(i));
                       
                       
                      row++;
                       
                       grid.add(anchorPane, 0, row); //(child,column,row)
                       //set grid width
                       grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                       grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                       grid.setMaxWidth(Region.USE_PREF_SIZE);
                       
                       //set grid height
                       grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                       grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                       grid.setMaxHeight(Region.USE_PREF_SIZE);
                       
                       GridPane.setMargin(anchorPane, new Insets(30));
                   } 
               
               } catch (SQLException cv) {
                   System.out.println(cv);;
               }
        } catch (IOException e) {
            System.out.println(e);;
        }
    
    
    }
    
}
