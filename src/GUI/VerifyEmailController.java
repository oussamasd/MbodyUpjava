/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.SessionUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class VerifyEmailController extends SettingsController {

    @FXML
    private MediaView mv;
    @FXML
    private TextField txtCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           Media media1 =new Media ("file:///C:/Users/oussa/OneDrive/Documents/NetBeansProjects/MbodyUpjava/src/img/bg.mp4");
        MediaPlayer player1 = new MediaPlayer(media1);
        mv.setMediaPlayer(player1);
        player1.setVolume(0);
        player1.play();
    }    

    @FXML
    private void Verify(MouseEvent event) {
          if (Integer.valueOf(txtCode.getText())==SessionUser.RandomCode)
    {
        SessionUser.verified=true;
         //ResetController resC = new ResetController(txt_Email.getText());
                try {

                    Parent parent = FXMLLoader.load(getClass().getResource("Settings.fxml"));
            Scene scene = new Scene(parent);
            Stage consstage = new Stage();
            consstage.setScene(scene);
            consstage.initStyle(StageStyle.UTILITY);
            consstage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
    }else {
    JOptionPane.showMessageDialog(null, "code do not match");
    }
    }
    
    
}
