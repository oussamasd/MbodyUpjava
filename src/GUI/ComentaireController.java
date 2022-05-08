/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Comment;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class ComentaireController {

    @FXML
    private Label username;
    @FXML
    private Label cmnt;
    @FXML
    private Label dt;
//nzido user 
    public void safecmt(Comment c){
    
        username.setText(c.getId_user()+"");
        cmnt.setText(c.getText());
        dt.setText(c.getDt());
    
    
    }
      
    
}
