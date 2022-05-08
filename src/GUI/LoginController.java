/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import com.github.sarxos.webcam.Webcam;
import utils.MyDB;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import entities.SessionUser;
import java.io.File;
import services.ServiceUser;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author IMNA
 */
public class LoginController implements Initializable {

    @FXML
    private Label lbl_close;
     @FXML
    private Label lblErrors;
    @FXML
    private TextField txtUsername;
     @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnSignIn;
    @FXML
    private Button btnSignUp;
    @FXML
    private Label forgot;
    @FXML
     private MediaView mediaView;
   @FXML 
    private MediaView mv;       
            
   
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
        static User user= new User();
    @FXML
    private CheckBox show_password_login;
    @FXML
    private TextField show_password_a_login;

    /**
     * Initializes the controller class.
     */
    
  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        show_password_a_login.setVisible(false);
                   Media media =new Media ("file:///C:/Users/oussa/OneDrive/Documents/NetBeansProjects/MbodyUpjava/src/img/bg.mp4");
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.setVolume(0);
        player.play();
         Media media1 =new Media ("file:///C:/Users/oussa/OneDrive/Documents/NetBeansProjects/MbodyUpjava/src/img/bg.mp4");
        MediaPlayer player1 = new MediaPlayer(media1);
        mv.setMediaPlayer(player1);
        player1.setVolume(0);
        player1.play();
        if (con == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is up : Good to go");
        }
        
    }    
    public LoginController() {
         con = MyDB.getInstance().getConnexion();
    }
     private String logIn() throws NoSuchAlgorithmException {
        String status = "Success";
        String email = txtUsername.getText();
        String password = txtPassword.getText();
      
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("En format hexa : " + sb.toString());
        ServiceUser retUser = new ServiceUser();
        if(email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            //query
            String sql = "SELECT * FROM user Where email = ? and password = ?";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, sb+"");
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                } else {
                   user.setId(resultSet.getInt(9));
                   user.setRoles(resultSet.getString("roles"));
     //       preparedStatement.setString(3, role);
                 
                   
                        User userSession = retUser.afficherUserSession(email, sb.toString());
                        SessionUser.id=userSession.getId();
                        SessionUser.cin=userSession.getCin();
                        SessionUser.nom=userSession.getNom();
                        SessionUser.prenom=userSession.getPrenom();

                        SessionUser.email=userSession.getEmail();
                        SessionUser.password=userSession.getPassword();
                        SessionUser.numero=userSession.getNumero();
                        SessionUser.adresse=userSession.getAdresse();
                        System.out.println(userSession);
            
                    setLblError(Color.GREEN, "Login Successful..Redirecting.."+user.getId()+user.getRoles());
                }
                 
                   
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
        
        return status ;
    }

    private void setLblError(Color color, String text) {
         lblErrors.setTextFill(color);
         lblErrors.setText(text);
         System.out.println(text);
        
    }
     @FXML
    private void getSignUpView() throws IOException {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            Scene scene = new Scene(parent);
            Stage thstage = new Stage();
            thstage.setScene(scene);
            thstage.initStyle(StageStyle.UTILITY);
            thstage.show();
        } catch (IOException ex) {
            Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void handleButtonAction(javafx.scene.input.MouseEvent event) throws NoSuchAlgorithmException {
         if (event.getSource()==lbl_close)
        {
            System.exit(0);
        }
//         if (event.getSource()==btnSignIn)
//        {
//            logIn();
//        }
         
          if (event.getSource() == btnSignIn) {
            //login here
            if (logIn().equals("Success")) {
                try {
               
                    if (user.getRoles()=="")
                    {
                                Node node = (Node) event.getSource();
                    Stage stage1 = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage1.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AdminDashboard.fxml")));
                    stage1.setScene(scene);
                    stage1.show();
                    }
                    else{
                               Node node = (Node) event.getSource();
                    Stage stage1 = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage1.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Accueil.fxml")));
                    stage1.setScene(scene);
                    stage1.show();
                    //add you loading or delays - ;-)
                    }

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        }
    
    
         
    }
    @FXML
     public void getForgot(javafx.scene.input.MouseEvent event){
      try {
            Parent parent = FXMLLoader.load(getClass().getResource("ForgotPass.fxml"));
            Scene scene = new Scene(parent);
            Stage thstage = new Stage();
            thstage.setScene(scene);
            thstage.initStyle(StageStyle.UTILITY);
            thstage.show();
        } catch (IOException ex) {
            Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

 
      @FXML
    private void show_password_login(ActionEvent event) {

        if (show_password_login.isSelected()) {
            show_password_a_login.setVisible(true);
            show_password_a_login.setText(txtPassword.getText());

            txtPassword.setVisible(false);

        } else {
            txtPassword.setVisible(true);
            show_password_a_login.setVisible(false);
            txtPassword.setText(show_password_a_login.getText());

        }

    }
    private void take_picture() throws IOException {

        Webcam webcam = Webcam.getDefault();
        webcam.open();
        ImageIO.write(webcam.getImage(), "JPG", new File("C:/Users/Skander/OneDrive/Desktop/test1.jpg"));
        String current = System.getProperty("user.dir");
        System.out.println(current);

        System.setProperty("user.dir", current);

        webcam.close();

    }

}

    

