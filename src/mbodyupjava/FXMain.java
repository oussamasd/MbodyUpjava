/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbodyupjava;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import entities.MehdiMail;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;

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
            parent = FXMLLoader.load(getClass().getResource("/GUI/AdminDashboard.fxml"));
            Scene scene = new Scene(parent);
            stage.setTitle("MbodyUP");
            stage.setScene(scene);

 
           stage.show();
            
            
           
//           MehdiMail.send(
//    "mehdi.azzaz@esprit.tn",
//    "nosforever24552201",
//    "mehdi.azzaz20@gmail.com",
//    "Bienvenu sur WayToLearnX",
//    "mail de test!"
//  );
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
