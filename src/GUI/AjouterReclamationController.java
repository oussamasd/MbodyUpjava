/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.TypeReclamationService;
import services.reclamationService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterReclamationController extends AdminDashboardController {
ObservableList<Reclamation> observableListoffre = FXCollections.observableArrayList();
    @FXML
    private Pane addbutton;
    @FXML
    private Label titrepage;
    @FXML
    private Label titrepage1;
    @FXML
    private Label titrepage11;
    @FXML
    private TextField tnom;
    @FXML
    private TextField tdescription;
    @FXML
    private TableView<Reclamation> tableact;
    private Reclamation cc=null;
    @FXML
    private TableColumn<Reclamation, String> Nom;
    @FXML
    private TableColumn<Reclamation, String> Description;
    @FXML
    private Label erreurref;
    @FXML
    private Label nomerreur;
    private TextField rechercheReclamation;
    private Connection con;
    @FXML
    private ComboBox classeres;
//     String fc="Produit";
//    String bc="entraineur";
//    String ec="Salle Musculation";
    @FXML
    private TextField recherche;
    @FXML
    private Button pdf;
    @FXML
    private TableColumn<Reclamation, Integer> TypeRec;
    
    TypeReclamationService typeS = new TypeReclamationService();
   Stage stage ;
     Scene scene ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        affichage();
//       classeres.getItems().addAll(fc,bc,ec);
      ObservableList<TypeReclamation>list;
        try {
            list = FXCollections.observableArrayList(typeS.afficherType());
            classeres.setItems(list);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       

 tnom.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            tnom.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
 tdescription.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            tdescription.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
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
tdescription.textProperty().addListener(new ChangeListener<String>()
            {
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   if(newValue.isEmpty())
        {
                       nomerreur.setText("Champs vide!");
                 
        }
                   else if(newValue.length()>20)
        {
                       nomerreur.setText("Max longueur 20");
          
        }
                   else
        {
                nomerreur.setText("   ");
              
                }
                }
                
                
            });



        tableact.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                cc = (Reclamation)tableact.getSelectionModel().getSelectedItem();
                System.out.println(cc);
                
            tnom.setText(cc.getNom());
                tdescription.setText(cc.getDescription());
                //
                 classeres.valueProperty().addListener(new ChangeListener<String>(){
            
                          
                
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
              
            }

        }  ); 
                 
               
                
               
               
            }
          });
        
        
        
        
    }    

   

  

   

    @FXML
    private void BtnAjouterP(ActionEvent event) throws SQLException {


 try {
              if(tnom.getText().isEmpty() ||(tdescription.getText().isEmpty()) )
        {   
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
        fail.setHeaderText("failure");
        fail.setContentText("Champs vide !");
        fail.showAndWait(); 
        }else{ 
             String ref = tnom.getText();
             String nom = tdescription.getText();
             TypeReclamation tp = new TypeReclamation();
             tp.setId(Integer.parseInt(classeres.getValue().toString().split("_")[0]));
            
            Reclamation p1=new Reclamation(ref,nom,tp);
            reclamationService ps = new reclamationService();
            
            ps.ajouterp(p1);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Ajout Reclamation");
		alert.setContentText("Reclamation Ajouté avec succes!");

		alert.showAndWait();
            tnom.clear();
            tdescription.clear();
           
            affichage();}
        } catch (SQLException ex) {
            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void BtnSuppRec(ActionEvent event) {
        
        reclamationService ps = new reclamationService();
        String ref = tnom.getText(); 
       ps.deleteReclamation(ref);
       affichage();
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Suppression Reclamation");
		alert.setContentText("Reclamation supprimé avec succes!");
		alert.showAndWait();
                
    }
    private void affichage(){
        try {
       reclamationService sp = new reclamationService();
       List events=sp.afficherpersonne();
       ObservableList<Reclamation> et=FXCollections.observableArrayList(events);
       //listview.setItems(et);
       
      tableact.setItems(et);
       
//        id.setCellValueFactory(new PropertyValueFactory<>("id"));
       Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       Description.setCellValueFactory(new PropertyValueFactory<>("description"));
       TypeRec.setCellValueFactory(new PropertyValueFactory<>("typeReclamation"));
      
      
       
       
        } catch (SQLException ex) {
                Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    }

    @FXML
    private void BtnModifier(ActionEvent event) {
//        String nom = tnom.getText();
//        String description = tdescription.getText();
//       String classe = classeres.getValue();
//        TypeReclamation tp = new TypeReclamation(36,classe);
//       
//       Reclamation p1=new Reclamation(nom,description,tp);
//       reclamationService ps = new reclamationService();
//       ps.modifierRec(p1);
//       affichage();
//       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//		alert.setTitle("Modification Reclamation");
//
//		// alert.setHeaderText("Results:");
//		alert.setContentText("Reclamation modifié avec succes");
//
//		alert.showAndWait();
reclamationService cs = new reclamationService();
     System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir Reclamation");
                   
        }else{
              
                   
                
              try {
                  
                      cs.update(new Reclamation(tnom.getText(),tdescription.getText()),cc.getId());
                  
                  affichage();
                  JOptionPane.showMessageDialog(null, "Rec modifier");
                  tnom.clear();
                 
                  cc=null;
              } catch (SQLException ex) {
                  Logger.getLogger(AjouterTypeRecController.class.getName()).log(Level.SEVERE, null, ex);
              }
            }
    }

    
    @FXML
    private void Recherche(ActionEvent event) throws SQLException{
      String search11 = recherche.getText();
    
        String req="select * from reclamation where nom = '"+search11+"' or description = '"+search11+"'";
       List<Reclamation> list = new ArrayList<>();
       Connection mc;
    Statement ste;

    
        mc=MyDB.getInstance().getConnexion();
    
     ste=mc.createStatement();
            ResultSet rs = ste.executeQuery(req);
            if(rs.next()){
     //ystem.out.println(v.getImage().toString());
                Reclamation p2=new Reclamation(rs.getString(2),rs.getString(3),null);
                //E131DHHJMNE code wifi whatelse?
               
                list.add(p2);
                reclamationService sp = new reclamationService();
      // List events=sp.readAll();
       ObservableList et=FXCollections.observableArrayList(list);
       tableact.setItems(et);
       
       Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       Description.setCellValueFactory(new PropertyValueFactory<>("description"));
    }   
            else {
                affichage();
            }
     
    }
       
    

    @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException , SQLException , DocumentException{
         Document doc  = new Document();
        String sql = " Select * from reclamation";

        try {
//          prepared = cnx.prepareStatement(sql);
//        resultat = prepared.executeQuery();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mbodyup", "root", "");
            PreparedStatement pt = con.prepareStatement("select * from reclamation" );
            ResultSet rs = pt.executeQuery();
            //
                PdfWriter.getInstance(doc,new FileOutputStream("C:\\Users\\user\\Downloads\\MbodyUpjava\\Reclamation.pdf"));
                doc.open();
                doc.add(new Paragraph("Liste des TypesReclamation"));
                doc.add(new Paragraph("-------------------------"));
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                PdfPCell cell;
                ///
                cell = new PdfPCell(new Phrase("Nom", FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Description", FontFactory.getFont("Comic Sans MS",12)));
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
                
                 cell = new PdfPCell(new Phrase(rs.getString("description").toString(), FontFactory.getFont("Arial",11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
               
               }
                
                //
                doc.add(table);
                    doc.close(); 
                    Desktop .getDesktop().open(new File("C:\\Users\\user\\Downloads\\MbodyUpjava\\Reclamation.pdf"));
                
                
            
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
         
      
    }

    

