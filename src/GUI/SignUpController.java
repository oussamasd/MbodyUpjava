/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.AddUserController.adresse;
import static GUI.AddUserController.isTel;
import static GUI.AddUserController.isUsername;
import static GUI.AddUserController.iscin;
import static GUI.AddUserController.validPasswor;
import static GUI.AddUserController.validmail;
import com.github.sarxos.webcam.Webcam;
import entities.User;
import utils.MyDB;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
//import JSONObject. ;

import org.json.JSONArray;
import org.json.JSONObject;
//import org.json;

/**
 * FXML Controller class
 *
 * @author IMNA
 */
public class SignUpController extends AdminDashboardController {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField cin;

    @FXML
    private TextField mail;

    @FXML
    private TextField adresse;

    @FXML
    private TextField numero;

    @FXML
    private TextField password;
     @FXML
     private MediaView mediaView;
   @FXML 
    private MediaView mv;
   @FXML
       private Button Signin;
   
    private String link_picture = "";
    Connection cnx=null;
    String query = null;
    PreparedStatement preparedStatement;
    User user= new User();
    @FXML
    private Label picture_label;
    
      private static Matcher matcher;

    public static boolean isString(String text) {

        if (text.matches("^[a-zA-Z]+$")) {

            return true;
        } 
            return false;

    }

     public static boolean isNull(String text){
         if(text == ""){
             return true; //null
         }

         return false ;//n'est pas vide
     }

          public static boolean isUsername(String text) {

        if (text.matches("^[A-Za-z0-9]+$+") ) {

            return true;
        } 
            return false;
    }

        

      public static boolean adresse(String text) {

        if (text.matches("^[A-Z a-z 0-9]+$")) {
            return true;
        }
            return false;
    }

          public static boolean iscin(String text) {

        if (text.matches("^[0-9]+$")&& text.length()== 8) {

            return true;

        } else {

            return false;

        }

    }

                  public static boolean isTel(String text) {

        if (text.matches("^[0-9]+$")&& text.length()==8) {

            return true;

        } else {

            return false;

        }

    }



     private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
     private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);
     private static final String pwd=  "^[A-Za-z0-9]+$";
     private static Pattern pattern1 = Pattern.compile(pwd);

     public static boolean validmail(final String hex) {

        matcher = pattern.matcher(hex);

        return matcher.matches();

    }

      public static boolean validPasswor(final String hex) {

        matcher = pattern1.matcher(hex);

        return matcher.matches();

    }
    @FXML
    private Label LblError;
    @FXML
    private Label LblError1;
    @FXML
    private Label LblError1111;
    @FXML
    private Label LblError111;
    @FXML
    private Label LblError1112;
    @FXML
    private Label LblError11121;
    @FXML
    private Label LblError111211;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        // TODO
    }    
   
    public void SignUp () throws SQLException, NoSuchAlgorithmException{
         cnx = MyDB.getInstance().getConnexion();
         query = "INSERT INTO `user` (`cin`,`email`,`roles`,`password`,`nom`, `prenom`,`adresse`,`numero`) VALUES (?,?,?,?,?,?,?,?)";
         preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, cin.getText());
            preparedStatement.setString(2, mail.getText() );
          String role = user.getRoles();
            String[]rolee = new String[]{role};
     //       preparedStatement.setString(3, role);
            JSONObject roles = new JSONObject(rolee);
            preparedStatement.setString(3,  roles.toString());
            String Password=password.getText();
             MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Password.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
          StringBuffer hexString = new StringBuffer();
     for (int i=0;i<byteData.length;i++) {
      String hex=Integer.toHexString(0xff & byteData[i]);
          if(hex.length()==1) hexString.append('0');
          hexString.append(hex);
     }
     System.out.println("En format hexa : " + hexString.toString());
    

            preparedStatement.setString(4, hexString+"");
            preparedStatement.setString(5, nom.getText());
            preparedStatement.setString(6, prenom.getText());
            preparedStatement.setString(7, adresse.getText());
            preparedStatement.setString(8, numero.getText());
            preparedStatement.execute();
          
            
            
          
    }
    private void clean() {
        cin.setText(null);
        mail.setText(null);
        password.setText(null);
        nom.setText(null);
        prenom.setText(null);
        adresse.setText(null);
        numero.setText(null);
        
    }
    @FXML
    private void save(javafx.scene.input.MouseEvent event) throws SQLException, NoSuchAlgorithmException {
        
        cnx = MyDB.getInstance().getConnexion();
        String Cin = cin.getText();
        String Email = mail.getText();
        String Password = password.getText();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Password.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
          StringBuffer hexString = new StringBuffer();
     for (int i=0;i<byteData.length;i++) {
      String hex=Integer.toHexString(0xff & byteData[i]);
          if(hex.length()==1) hexString.append('0');
          hexString.append(hex);
     }
     System.out.println("En format hexa : " + hexString.toString());
    

        

        String Nom = nom.getText();
        String Prenom = prenom.getText();
        String Adresse =adresse.getText();
        String Numero =numero.getText();
        
       if(Cin.isEmpty() || (iscin(Cin)==false)) {
            setLblError(Color.TOMATO, "Please Enter valid Cin" , LblError);
            }
            if( (validmail(Email)==false) || (Email.isEmpty())) {
            setLblError(Color.TOMATO, "Please Enter valid Email" , LblError1);
            }
             if((validPasswor(Password)==false) || Password.isEmpty()) {
            setLblError(Color.TOMATO, "Please Enter valid Password" , LblError1111);
             }
             if( (isUsername(Nom)==false) ||Nom.isEmpty()  ) {
            setLblError(Color.TOMATO, "Please Enter valid Name" ,LblError111);
             }
             if( (isUsername(Prenom)==false) ||Prenom.isEmpty()  ) {
            setLblError(Color.TOMATO, "Please Enter valid LastName" , LblError1112);
             }
              if( (adresse(Adresse)==false) ||Adresse.isEmpty()  ) {
            setLblError(Color.TOMATO, "Please Enter valid Address" , LblError11121);
              }
             if ((isTel(Numero)==false)|| Numero.isEmpty()){
             setLblError(Color.TOMATO, "Please Enter valid Numero" , LblError111211);
        } else {
            SignUp();
            clean();
        }
    }
  

    private String code_random() {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(4);

        for (int i = 0; i < 4; i++) {

            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb 
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();

    }

     @FXML
    private void take_picture() throws IOException {
        String code_random = code_random();
        
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        String filename = "";
        filename = code_random + ".jpg";
        ImageIO.write(webcam.getImage(), "JPG", new File("C:/Users/Skander/OneDrive/Desktop/" + filename));
        link_picture = filename;
        webcam.close();
        

    }
  private void setLblError(Color color, String text ,Label label ) {
         
         label.setTextFill(color);
         label.setText(text);
         System.out.println(text);
        
    }


   

}
