/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.itextpdf.text.Font;
//import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import entities.Abonnement;
import entities.Offre;
import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import org.apache.poi.ss.usermodel.Workbook;
import services.OffreService;
import services.AbonnementService;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AfficherOffreController extends AdminDashboardController {
    
      Offre o = new Offre();
    @FXML
    private GridPane ll;
    @FXML
    private TableView<Offre> taboff;
    @FXML
    private TableColumn<Offre, Integer> id_off;
    @FXML
    private TableColumn<Offre, String> delai;
    @FXML
    private TableColumn<Offre, String> nomoff;
    @FXML
    private TableColumn<Offre, String> descoff;
    @FXML
    private Button but_modif_off;
    @FXML
    private Button but_Supp_off;
    @FXML
    private TextField onom;
    @FXML
    private Button but_Ajou_off;
    @FXML
    private TextField odesc;
    @FXML
    private DatePicker odelai;
    @FXML
    private Pane addbutton;
    @FXML
    private Label titrepage;
    
 


OffreService servoff = new OffreService();

        AbonnementService servact = new AbonnementService();

    ObservableList<Offre>  OffList = FXCollections.observableArrayList(); 

    @FXML
    private ComboBox nom_abon_offre;
    @FXML
    private TextField recherche;
    @FXML
    private Button excel;
    @FXML
    private Button imp;
    @FXML
    private Button qr;
   
    
@Override
    public void initialize(URL url, ResourceBundle rb)
    
 {
     
     
     nom_abon_offre.setItems(OffList);

    //loadDate();
    searchact();
     ObservableList<Abonnement>list;
        try {
            list = FXCollections.observableArrayList(servact.afficherAbonnement());
            nom_abon_offre.setItems(list);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }    
    private void refreshTable(List<Offre> la) {
         
          taboff.getItems().clear();//actualiser

           OffList.addAll(la);
            
            
         taboff.setItems(OffList);
        
        
        
        
    }
     private void loadDate(List<Offre> la) {
        id_off.setCellValueFactory(new PropertyValueFactory<>("id"));
        delai.setCellValueFactory(new PropertyValueFactory<>("delai"));
        nomoff.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descoff.setCellValueFactory(new PropertyValueFactory<>("description"));
       //   nom_abon_offre.setCellValueFactory(new PropertyValueFactory<>("nom_abonnement_id"));
     
      //  refreshTable();
         if(la!=null){
        
        refreshTable(la);
        }
    
     
     
     }
      private boolean Validchamp(TextField T){
         if(T.getText().isEmpty() | T.getLength() <4 ){ //verifier nom o deesc vide 
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur de formulaire");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir tous les champs");
            alert.showAndWait();
      return false;
    }return true;
}
     @FXML
    private void AjouterOff(ActionEvent event) throws AWTException {
  if( Validchamp(onom) &&  Validchamp(odesc)){  
    Offre o = new Offre();
    
        //o.getAbonnement(Integer.parseInt(nomabon.getText()));
        // o.getAbonnement();
        o.setDescription(odesc.getText());
        o.setNom(onom.getText());
        o.setDelai(odelai.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
       // o.setAbonnement(nomabon.getText());
        OffreService sp1 = new OffreService();
        try {
          sp1.ajouterOffreOF(o);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Nouvelle offre");
          alert.setHeaderText(null);
          alert.setContentText("Votre offre est ajouté");
          alert.showAndWait();
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
             java.awt.Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        trayIcon.displayMessage("MbodyUp", "Offre ajouté", TrayIcon.MessageType.INFO);
  }
          try {
              loadDate(servoff.afficherOffre());
          } catch (SQLException ex) {
              Logger.getLogger(AfficherOffreController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    @FXML
    private void but_modif_off(ActionEvent event) {
        
         // if( Validchamp(onom) &&  Validchamp(odesc)){  

        Offre o = taboff.getSelectionModel().getSelectedItem();

        o.setDescription(odesc.getText());
        o.setNom(onom.getText());
 //        o.setDelai(odelai.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
       // o.setDelai(odelai.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));

        // M.setImage(imagecomp);
        OffreService sp1 = new OffreService();

        try {

            sp1.ModifierOffre(o);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.show();
            alert.setTitle("Modifier !");
            alert.setContentText("Modifier avec succés");
          

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
            alert.setTitle("fail !");
            alert.setContentText("failed ");

        }
     
   
          try {
              loadDate(servoff.afficherOffre());
          } catch (SQLException ex) {
              Logger.getLogger(AfficherOffreController.class.getName()).log(Level.SEVERE, null, ex);
          }

        } 
    
   // }
   
    @FXML
    private void selectA(MouseEvent event) {
       Offre o = taboff.getSelectionModel().getSelectedItem();

     
      onom.setText(o.getNom());
      
       odesc.setText(o.getDescription());
             //odelai.setText(o.getDelai());

        // odelai.setDelai(Date.valueOf(o.getText()));

      //odelai.setText(Date.valueOf(o.getDelai()));
    }
     @FXML
    private void SupprimerOffre(ActionEvent event) throws SQLException, AWTException {
       
    if (taboff.getSelectionModel().isEmpty() ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        JOptionPane.showMessageDialog(null,"Aucune offre est selectionné ,veuillez choisir une offre");
     }else{
   int responce=JOptionPane.showConfirmDialog(null, "Attention vous allez supprimer l'offre sélectionné etes-vous sur ?","Suppression",JOptionPane.YES_NO_OPTION);
            if (responce==JOptionPane.YES_OPTION){
        OffreService sp1 = new OffreService();
                    Offre o = (Offre) taboff.getSelectionModel().getSelectedItem();
                     sp1.SupprimerOffre(o);
             //refresh(true);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);

         alert.setContentText("Votre offre a été bien supprimé");
                  JOptionPane.showMessageDialog(null,"offre supprimé");

            } else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Votre offre a été bien supprimé");
                 JOptionPane.showMessageDialog(null,"Suppression annulé");
            }
           SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
             java.awt.Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("MbodyUp", "Offre supprimé", TrayIcon.MessageType.INFO);
    }
    
    loadDate(servoff.afficherOffre());

    }
 
          
   @FXML
    private void goOffer(ActionEvent event) {
        try{
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/AfficherOffre.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }
    }
 
    
     @FXML
    private void exportexcel(ActionEvent event) throws SQLException, IOException {
        Workbook workbook = new HSSFWorkbook();

        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet("sample");

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);
     
        
        for (int j = 0; j < taboff.getColumns().size(); j++) {
            row.createCell(j).setCellValue(taboff.getColumns().get(j).getText());
        }

        for (int i = 0; i < taboff.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < taboff.getColumns().size(); j++) {
                if(taboff.getColumns().get(j).getCellData(i) != null) { 
                    row.createCell(j).setCellValue(taboff.getColumns().get(j).getCellData(i).toString()); 
                }
                else {
                    row.createCell(j).setCellValue("");
                }   
            }
        }
      // FileOutputStream fileOut = new FileOutputStream("Classeur1.xlsx");
            FileOutputStream fileOut = new FileOutputStream(new File ("D:\\test.xls"));

        workbook.write(fileOut);
        fileOut.close();

    } 
 @FXML
    private void exportpdfoffbtn(ActionEvent event) {
        
      PrinterJob job = PrinterJob.createPrinterJob();
       
        Node root= this.taboff;
        
     if(job != null){
     job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
     Printer printer = job.getPrinter();
     PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
     boolean success = job.printPage(pageLayout, root);
     if(success){
        job.endJob();
     }
   }
    }

public  void searchact(){
         try {
             
             List<Offre> allact = servoff.afficherOffre();
     recherche.textProperty().addListener((observable, oldValue, newValue) -> {
   // System.out.println(newValue.toString());
   if( newValue.isEmpty()){
       //loadDate();
       
             loadDate(allact);
       
       
   }else{
   
       List<Offre> a = new ArrayList();
       a = allact.stream().filter(item-> {
           
           
            int index = item.getDelai().toUpperCase ().indexOf(newValue.toUpperCase ());
            int index2 = item.getDescription().toUpperCase ().indexOf(newValue.toUpperCase ());
            int index3 = item.getNom().toUpperCase ().indexOf(newValue.toUpperCase ());
             int index4 = Integer.toString(item.getId()).toUpperCase ().indexOf(newValue.toUpperCase ());

               
              if((index ==-1)&&(index2==-1)&&(index3==-1)&&(index4==-1) )
              {
              return false;
              }else{
             
                  return true ;
              }
                       }
       
       ).collect(Collectors.toList());
       
     
       loadDate(a);
   
   }
   
});
         
             loadDate(allact);
       
      } catch (SQLException ex) {
             System.out.println(ex);
         }  
   
    }

 }
    

