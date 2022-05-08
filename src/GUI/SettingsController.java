/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Mail;
import entities.SessionUser;
import java.io.IOException;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import services.SendSms;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class SettingsController extends AccueilController  {

    @FXML
    private MediaView mv;
    @FXML
    private TextField txt_Email;
    @FXML
    private CheckBox CheckSMS;
    int randomCode ; 
    @FXML
    private Label MessageVerified;
    @FXML
    private CheckBox checkverified;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Media media1 =new Media ("file:///C:/Users/Skander/Downloads/sbg.mp4");
        MediaPlayer player1 = new MediaPlayer(media1);
        mv.setMediaPlayer(player1);
        player1.setVolume(0);
        player1.play();
          verifiedMail();
    }    

    @FXML
    private void Send(MouseEvent event) {
      Random rand= new Random();
    randomCode= rand.nextInt(999999);
    SessionUser.RandomCode=randomCode;
    String subject = "Verifcication Mail Code";
    String message = "Your Verify code is "+randomCode;
    String user = "kasmi.iskander4@gmail.com";
    String pass = "Iskanderismylove2018";
    String to = txt_Email.getText();
    Mail.send(
    user,
    pass,
    to,
    subject,
    message
  );
     try {

                    Parent parent = FXMLLoader.load(getClass().getResource("verifyEmail.fxml"));
            Scene scene = new Scene(parent);
            Stage consstage = new Stage();
            consstage.setScene(scene);
            consstage.initStyle(StageStyle.UTILITY);
            consstage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
    
    
    boolean sessionDebug = false ; 
    Properties pros = System.getProperties();
//        pros.put("mail.smtp.starttls.enable", "true");
//        pros.put("mail.smtp.starttls.required", "true");
//        pros.put("mail.smtp.auth", "true");
//        pros.put("mail.smtp.host", "smtp.gmail.com");
//        pros.put("mail.smtp.port", "587");
//        pros.setProperty("mail.smtp.ssl.enable", "true");
//       pros.setProperty("mail.smtp.ssl.socketFactory.class","DummySSLSocketFactory");
//        pros.setProperty("mail.smtp.ssl.socketFactory.fallback", "false");

//pros.put("mail.smtp.user",user); 
//pros.put("mail.smtp.host",host); 
//pros.put("mail.smtp.port", "2525"); 
//
//pros.put("mail.debug", "true"); 
//pros.put("mail.smtp.auth", "true"); 
//pros.put("mail.smtp.starttls.enable","true"); 
//pros.put("mail.smtp.EnableSSL.enable","true");

//pros.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
//pros.setProperty("mail.smtp.socketFactory.fallback", "false");   
//pros.setProperty("mail.smtp.port", "587");   
//pros.setProperty("mail.smtp.socketFactory.port", "587"); 

          java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        //Session mailSession = Session.getDefaultInstance(pros,null) ;
          Session mailSession = Session.getInstance(pros,
          new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
          }); 
    //Session mailSession = Session.getDefaultInstance(pros);
    mailSession.setDebug(sessionDebug);
    try {
    Message msg= new MimeMessage(mailSession);
    msg.setFrom(new InternetAddress(user));
//        InternetAddress [] address = {new InternetAddress(to)};
//    msg.setRecipients(Message.RecipientType.TO, address);
    msg.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to));
    
    msg.setSubject(subject);
    msg.setText(message);
//        Transport transport = mailSession.getTransport("smtp");
//        transport.connect("smtp.gmail.com",user, pass);
//        transport.sendMessage(msg, msg.getAllRecipients());
     //    Transport.send(msg);
        //transport.close();
        JOptionPane.showMessageDialog(null, "code has been sent to the email");
           
   
    
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
        
 }
    
    private void verifiedMail()
    {
        if (SessionUser.verified==true)
        {
            MessageVerified.setVisible(true);
         
        checkverified.setVisible(true);
        }
        else
        {
            MessageVerified.setVisible(false);
           
            checkverified.setVisible(false);
        }
    }
    @FXML
    private void verifieSMShhhh(ActionEvent event) throws MalformedURLException, IOException {

        if (CheckSMS.isSelected()) {
                 SendSms.sendSms("Votre Compte User has been virified", "93049367");
                           
          
        } 

    }
    
   
    

    
}
