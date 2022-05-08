/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbodyupjava;




import java.io.*;
import entities.Mail;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import services.ActivityService;


/**
 *
 * @author oussa
 */
public class FXMain extends Application {
    
   Stage stage;
    Parent parent;
    
    @Override
    public void start(Stage primaryStage) {
       this.stage = primaryStage;
        try {
            parent = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));//AdminDashboard
            Scene scene = new Scene(parent);
            stage.setTitle("MbodyUP");
            stage.setScene(scene);
            stage.show();
        
            /* Mail.send(
    "oussama.saddi3@gmail.com",
    "oussama123456789",
    "kasmi.iskander4@gmail.com",
    "Bienvenu sur WayToLearnX",
    "mail de test!"
  );*/
           // System.out.println(new ActivityService().countparticipation(47));
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
      
    }/*
    byte[] a = new byte[20] ;
    a[0]=1;
            //ByteArrayInputStream inStreambj = new ByteArrayInputStream(a);
            
       try {
           //BufferedImage image = ImageIO.read(new File("C:\\Users\\oussa\\OneDrive\\Desktop\\MbodyUpjava\\src\\img\\cherry.png"));
           //ByteArrayOutputStream outStreamObj = new ByteArrayOutputStream();
           // ImageIO.write(image, "jpg", outStreamObj);
           byte [] byteArray = outStreamObj.toByteArray();
            ByteArrayInputStream inStreambj = new ByteArrayInputStream(byteArray);
          // System.out.println(byteArray.toString());
           BufferedImage newImage = ImageIO.read(inStreambj);
           File New = new File("outputImage.jpg");
           ImageIO.write(newImage, "jpg", New );
           Desktop desktop = Desktop.getDesktop();
           desktop.open(New);
           System.out.println("Image generated from the byte array.");
       } catch (IOException ex) {
           System.out.println("ha");;
       }
        */
        
        //*************************************
       
        
        //**********************************
    
    
    
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
      
        
    }
    
}
