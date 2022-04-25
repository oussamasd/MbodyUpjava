/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.sun.org.apache.xerces.internal.util.URI;
import entities.Reclamation;
import entities.TypeReclamation;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

import services.TypeReclamationService;
import services.reclamationService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterTypeRecController implements Initializable {
    Connection cnx;
    Statement stm;
    PreparedStatement prepared;
    ResultSet resultat;
    
   
    
    ObservableList<TypeReclamation> observableListoffre = FXCollections.observableArrayList();
    @FXML
    private Pane addbutton;
    @FXML
    private Label titrepage;
    @FXML
    private TextField tnom;
    @FXML
    private Label titrepage1;
    @FXML
    private Label erreurref;
    @FXML
    private TableView<TypeReclamation> tableact;
    private TypeReclamation cc=null;
    @FXML
    private TableColumn<TypeReclamation, String> Nom;
    @FXML
    private TextField recherche;
    @FXML
    private Button pdf;
    @FXML
    private Button stat;
       
     Stage stage ;
     Scene scene ;
    @FXML
    private Button email;
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          affichage();
          tnom.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            tnom.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
          tnom.textProperty().addListener(new ChangeListener<String>()
            {
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   if(newValue.isEmpty())
        {
                       erreurref.setText("Champs vide!");
                 
        }
                   else if(newValue.length()>10)
        {
                       erreurref.setText("Max longueur 10");
          
        }
                   else
        {
                erreurref.setText("   ");
              
                }
                }
                
                
            });
          tableact.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                cc = (TypeReclamation)tableact.getSelectionModel().getSelectedItem();
                System.out.println(cc);
                
            tnom.setText(cc.getNom());               
            }
          });
    }    

    @FXML
    private void goAccueil(ActionEvent event) {
    }

    @FXML
    private void goUser(ActionEvent event) {
    }

    @FXML
    private void goActivite(ActionEvent event) {
    }

    @FXML
    private void goExercice(ActionEvent event) {
    }

    @FXML
    private void goAbonnement(ActionEvent event) {
    }

    @FXML
    private void goOffer(ActionEvent event) {
    }

    @FXML
    private void goProduit(ActionEvent event) {
    }

    @FXML
    private void goCAtmehdi(ActionEvent event) {
    }

    @FXML
    private void goReclamation(ActionEvent event) {
    }

    @FXML
    private void goTypeReclamation(ActionEvent event) {
    }

    @FXML
    private void goEntraineur(ActionEvent event) {
    }

    @FXML
    private void goCatAymen(ActionEvent event) {
    }

    @FXML
    private void BtnAjouterP(ActionEvent event) throws SQLException {
        try {
              if(tnom.getText().isEmpty())
        {   
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
        fail.setHeaderText("failure");
        fail.setContentText("Champs vide !");
        fail.showAndWait(); 
        }else{ 
             String ref = tnom.getText();
             
             
            TypeReclamation p1=new TypeReclamation(ref);
            TypeReclamationService ps = new TypeReclamationService();
            
            ps.ajouterType(p1);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Ajout TypeReclamation");
		alert.setContentText("TypeReclamation Ajouté avec succes!");

		alert.showAndWait();
            tnom.clear();
            
           
            affichage();}
        } catch (SQLException ex) {
            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void BtnSuppRec(ActionEvent event) {
          
        TypeReclamationService ps = new TypeReclamationService();
        String ref = tnom.getText(); 
       ps.deleteTypeReclamation(ref);
       affichage();
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Suppression TypeReclamation");
		alert.setContentText("TypeReclamation supprimé avec succes!");
		alert.showAndWait();
    }

    @FXML
    private void BtnModifier(ActionEvent event) {
         TypeReclamationService cs = new TypeReclamationService();
     System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir TypeRec");
                   
        }else{
              
                   
                
              try {
                  
                      cs.update(new TypeReclamation(tnom.getText()),cc.getId());
                  
                  affichage();
                  JOptionPane.showMessageDialog(null, "typeRec modifier");
                  tnom.clear();
                 
                  cc=null;
              } catch (SQLException ex) {
                  Logger.getLogger(AjouterTypeRecController.class.getName()).log(Level.SEVERE, null, ex);
              }
            } 
    }

    @FXML
    private void Recherche(ActionEvent event)  throws SQLException{
        String search11 = recherche.getText();
    
        String req="select * from type_reclamation where nom = '"+search11+"' ";
       List<TypeReclamation> list = new ArrayList<>();
       Connection mc;
    Statement ste;

    
        mc=MyDB.getInstance().getConnexion();
    
     ste=mc.createStatement();
            ResultSet rs = ste.executeQuery(req);
            if(rs.next()){

                TypeReclamation p2=new TypeReclamation(rs.getInt(1),rs.getString(2));

                list.add(p2);
                TypeReclamationService sp = new TypeReclamationService();

       ObservableList et=FXCollections.observableArrayList(list);
       tableact.setItems(et);
       
  
       Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
       
    }   
            else {
                affichage();
            }
       
    }

    @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException , SQLException , DocumentException {
        
        Document doc  = new Document();
        String sql = " Select * from type_reclamation";

        try {
//          prepared = cnx.prepareStatement(sql);
//        resultat = prepared.executeQuery();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mbodyup", "root", "");
            PreparedStatement pt = con.prepareStatement("select * from type_reclamation" );
            ResultSet rs = pt.executeQuery();
            //
                PdfWriter.getInstance(doc,new FileOutputStream("C:\\Users\\user\\Downloads\\MbodyUpjava\\TypeReclamation.pdf"));
                doc.open();
                doc.add(new Paragraph("Liste des TypesReclamation"));
                doc.add(new Paragraph("-------------------------"));
                PdfPTable table = new PdfPTable(1);
                table.setWidthPercentage(100);
                PdfPCell cell;
                ///
                cell = new PdfPCell(new Phrase("Nom", FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(cell);
                //
                while(rs.next())
                {
                     TypeReclamation p = new TypeReclamation();
                    cell = new PdfPCell(new Phrase(rs.getString("nom").toString(), FontFactory.getFont("Arial",11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
               }
                
                //
                doc.add(table);
                    doc.close(); 
                    Desktop .getDesktop().open(new File("C:\\Users\\user\\Downloads\\MbodyUpjava\\TypeReclamation.pdf"));
                
                
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (DocumentException ex) {
                ex.printStackTrace();
            }catch(URI.MalformedURIException e )
            {
                 e.printStackTrace();
            }catch(IOException e )
            {
                 e.printStackTrace();
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(AjouterTypeRecController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        catch(SQLException e1)
//            {
//                e1.printStackTrace();
//            }

            
            
    }
    
    
     private void affichage(){
        
         try {
            TypeReclamationService sp = new TypeReclamationService();
       List events=sp.afficherType();
       ObservableList<TypeReclamation> et=FXCollections.observableArrayList(events);
       //listview.setItems(et);
   tableact.setItems(et);
    Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
  
        } catch (SQLException ex) {
                Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    }

    @FXML
    private void btnstat(ActionEvent event) {
    
        TypeReclamationService ms = new TypeReclamationService();
        ObservableList<TypeReclamation> list = FXCollections.observableArrayList(ms.TrierParId());
        
      
       
         
    }

    @FXML
    private void BtnMail(ActionEvent event) throws MessagingException {
       Properties prop = System.getProperties();
prop.put("mail.smtp.port", "587");
prop.put("mail.smtp.auth", true);
prop.put("mail.smtp.starttls.enable", "true");
Session newSession = Session.getDefaultInstance(prop, null);

String emailsubject="MbodyUp !";//titre
String emailbody="Une nouvlle Type de Reclamations a eté ajouteé!! :";//contenu
Message message = new MimeMessage(newSession);
message.addRecipient(Message.RecipientType.TO, new InternetAddress("ilyes.nakhli@esprit.tn"));//recepteur

message.setSubject(emailsubject);




MimeBodyPart mimeBodyPart = new MimeBodyPart();
mimeBodyPart.setContent(emailbody, "text/html");
Multipart multipart = new MimeMultipart();
multipart.addBodyPart(mimeBodyPart);


message.setContent(multipart);

String fromuser ="ilyesnakhlii188@gmail.com"; //emetteir
String pass ="ilyesnakhli123";
String emailhost="smtp.gmail.com";
Transport transport =newSession.getTransport("smtp");
transport.connect(emailhost,fromuser,pass);
transport.sendMessage( message, message.getAllRecipients());
transport.close();
        
    }
}
