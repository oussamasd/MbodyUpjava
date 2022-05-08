/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import entities.SessionUser;
import entities.User;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.ServiceUser;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class ProfileController extends AccueilController {


    @FXML
    private TextField emailFild;
    @FXML
    private TextField numeroFild;
    @FXML
    private TextField adresseFild;
    @FXML
    private TextField passwordFild;
    @FXML
    private TextField prenomFild;
    @FXML
    private TextField NomFild;
    @FXML
    private TextField cinFild;
    @FXML
    private Button save;
    /**
     * Initializes the controller class.
     */
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    //User user = null;
    private boolean update;
    int userId;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           // TODO
//     (ModifertextNom.setText(SessionClient.),ModifertextNom.setText(SessionClient.cin),ModifertextNom.setText(SessionClient.cin),ModifertextNom.setText(SessionClient.cin),ModifertextNom.setText(SessionClient.cin),ModifertextNom.setText(SessionClient.cin),ModifertextNom.setText(SessionClient.cin),ModifertextNom.setText(SessionClient.cin));
    
        
        cinFild.setText(SessionUser.cin+"");
        NomFild.setText(SessionUser.nom+"");
        prenomFild.setText(SessionUser.prenom+"");
        emailFild.setText(SessionUser.email+"");
        numeroFild.setText(SessionUser.numero+"");
        passwordFild.setText(SessionUser.password+"");
        adresseFild.setText(SessionUser.adresse+"");
        
    }    

    @FXML
    private void ModiferClient(ActionEvent event) {
       ServiceUser us = new ServiceUser();
        User u = new User();
        u.setId(SessionUser.id);
        u.setCin(Integer.parseInt(cinFild.getText()));
        u.setNom(NomFild.getText());
        u.setPrenom(prenomFild.getText());
        u.setEmail( emailFild.getText());
        u.setPassword(passwordFild.getText());
        u.setAdresse(adresseFild.getText());
        u.setNumero(Integer.parseInt(numeroFild.getText()));
        us.modifer(u, SessionUser.id);
        
    }

   



    
     
   
      
    
     
   
}

