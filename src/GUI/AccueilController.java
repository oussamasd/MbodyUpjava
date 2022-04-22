/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class AccueilController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
     private MediaView mediaView;
    @FXML
    private Tab PffresPane;
    @FXML
    private Tab AboonementsPane;
    @FXML
    private Tab AcitivitéPane1;
    @FXML
    private AnchorPane ActivitéPanel;
    @FXML
    private Tab AcitivitéPane11;
    @FXML
    private AnchorPane CoachPanel;
    @FXML
    private Tab AcitivitéPane111;
    @FXML
    private AnchorPane ReclamtionPanel1;
    @FXML
    private Tab ProcductsPane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    /*  Media media =new Media ("file:///C:/Users/oussa/OneDrive/Documents/NetBeansProjects/MbodyUpjava/src/img/bg.mp4");  //badel path mta3 video
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.setVolume(0);
        player.play();*/
    }    
    
}
