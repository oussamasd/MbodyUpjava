/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Abonnement;
import static entities.Abonnement.filename;
import entities.Activitie;
import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import services.AbonnementService;
import services.ActivityService;
import javafx.scene.chart.PieChart;
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * FXML Controller class
 *
 * @author User
 */
public class AfficherAbonneController extends AdminDashboardController {
     
    public String imagecomp; 
               Integer idd;
     Connection connexion;
    Statement stm;
          Abonnement a=new Abonnement();

    @FXML
    private TableView<Abonnement> tababon;
    @FXML
    private TableColumn<Abonnement, Integer> id;
    @FXML
    private TableColumn<Abonnement, String> nom;
    @FXML
    private TableColumn<Abonnement, Float> prix;
    @FXML
    private TableColumn<Abonnement, String> categories;
    @FXML
    private TableColumn<Abonnement, String> description;
    @FXML
     private TableColumn<Abonnement, String> photo;

    
    @FXML
    private TextField tnom;
    @FXML
    private TextField Acat;
     @FXML
      private TextField Aprix;
     @FXML
    private Button But_AjouAbon;

    
    AbonnementService servact = new AbonnementService();
    
    ObservableList<Abonnement>  ActList = FXCollections.observableArrayList();
        ObservableList myList ;

    @FXML
    private GridPane ll;
    @FXML
    private Button But_ModifAbon;
    @FXML
    private Button But_SuppAbon;
    @FXML
    private TextField Adesc;
    @FXML
    private Pane addbutton;
    @FXML
    private Label titrepage;
    @FXML
    private ImageView imagefield;
    @FXML
    private Button upload;
    @FXML
    private Button print;
    @FXML
    private Button pdf;
    @FXML
    private Button tri;
    private PieChart piechart;
    private ObservableList<Data> Data;
    @FXML
    private Button staat;
    @FXML
    private Button front;
  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    
    {
        
    loadDate();
    }    
    private void refreshTable() {
        try {   
            
            tababon.getItems().clear();//actualiser
            
           List<Abonnement> listabon = servact.afficherAbonnement();
           ActList.addAll(listabon);
            
          
         tababon.setItems(ActList);
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
        
    }
     private void loadDate() {
         
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        categories.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
     

           
       // photo.setCellValueFactory(new PropertyValueFactory<Abonnement, ImageView>("imgv"));
        refreshTable();
     }
     
    @FXML
    private void AjouterAbonn(ActionEvent event) throws AWTException {
     //    if( Validchamp(tnom) &&  Validchamp(Acat) &&  Validchamp(Adesc))
     if(tnom.getText().equals("") || Acat.getText().equals("")||Aprix.getText().equals("")||Adesc.getText().equals("")){
            JOptionPane.showMessageDialog(null, "veuillez remplir les champs vides");}
         else if(!(Acat.getText().matches("^[a-zA-Z]+$"))) {

            JOptionPane.showMessageDialog(null, "verifier la catégorie");
             }
            else if (!(Aprix.getText().matches("^[0-9]+$"))){
                     JOptionPane.showMessageDialog(null, "Le prix est non Valide");}
        
           else{

      
           

    Abonnement a = new Abonnement();

        a.setNom(tnom.getText());
        a.setCategorie(Acat.getText());
        a.setDescription(Adesc.getText());
        a.setPrix(Float.parseFloat(Aprix.getText()));
        a.setPhoto(imagecomp);
        AbonnementService sp = new AbonnementService();
        try {          sp.ajouterAbonnement(a);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Nouvelle abonnemet");
          alert.setHeaderText(null);
          alert.setContentText("Votre abonnement est ajouté");
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

        trayIcon.displayMessage("MbodyUp", "Abonnement ajouté", MessageType.INFO);
         }
         loadDate();
    }
          
    @FXML
    private void SupprimerAbonnement(ActionEvent event) throws SQLException, AWTException {
       
    if (tababon.getSelectionModel().isEmpty() ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        JOptionPane.showMessageDialog(null,"Aucune abonnement est selectionné ,veuillez choisir une abonnement");
     }else{
   int responce=JOptionPane.showConfirmDialog(null, "Attention vous allez supprimer l'abonnement sélectionné etes-vous sur ?","Suppression",JOptionPane.YES_NO_OPTION);
            if (responce==JOptionPane.YES_OPTION){
           AbonnementService so = new AbonnementService();
                    Abonnement a = (Abonnement) tababon.getSelectionModel().getSelectedItem();
                    so.SupprimerAbonnement(a);
             //refresh(true);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);

         alert.setContentText("Votre abonnement a été bien supprimé");
                  JOptionPane.showMessageDialog(null,"abonnement supprimé");

            } else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Votre abonnement a été bien supprimé");
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

        trayIcon.displayMessage("MbodyUp", "Abonnement supprimé", MessageType.INFO);
    }
             loadDate();

    }
       /* private boolean Validchamp(TextField T){
         if(T.getText().isEmpty() | T.getLength() <4 ){ //verifier nom o deesc vide 
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur de formulaire");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir tous les champs!");
            alert.showAndWait();
      return false;
    }return true;
}*/
        @FXML
    private void But_ModifAbon(ActionEvent event) {
        
       // if( Validchamp(tnom) &&  Validchamp(Acat) &&  Validchamp(Adesc)){  
        Abonnement a = tababon.getSelectionModel().getSelectedItem();

        a.setNom(tnom.getText());
        a.setCategorie(Acat.getText());
        a.setDescription(Adesc.getText());
        a.setPrix(Float.parseFloat(Aprix.getText()));
        a.setPhoto(imagecomp);
        
        AbonnementService sp = new AbonnementService();
        try {

            sp.ModifierAbonnementAb(a);
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
       loadDate();
        } 
   
    @FXML
    private void selectB(MouseEvent event) {
       Abonnement a = tababon.getSelectionModel().getSelectedItem();
      tnom.setText(a.getNom());
      Acat.setText(a.getCategorie());
       Adesc.setText(a.getDescription()); 
      Aprix.setText(String.valueOf(a.getPrix()));
      imagecomp=a.getPhoto();
    }


    @FXML
    private void uploadimg(ActionEvent event) {
        FileChooser f = new FileChooser();
        String img;

        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png"));
        File fc = f.showOpenDialog(null);
        if (f != null) {
            //System.out.println(fc.getName());
            img = fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(img);
            imagefield.setImage(i);
            imagecomp = fc.toString();
            System.out.println(imagecomp);
            int index = imagecomp.lastIndexOf('\\');
            if (index > 0) {
                filename = imagecomp.substring(index + 1);
            }

            //source = new File(pathimage);
            //dest = new File(System.getProperty("user.dir") + "\\src\\com\\esprit\\img\\" + filename);
            Abonnement.filename = "C:\\Users\\zcart\\Desktop\\kiftrip-website\\public\\uploads" + filename;
            //se.sendphp(fc.getAbsolutePath());
        }
        imagefield.setFitHeight(100);
        imagefield.setFitWidth(160);
        //..\img\google.png

        //C:\Users\splin\Documents\NetBeansProjects\FanArt\\com\esprit\img
        Abonnement.pathfile = fc.getAbsolutePath();
    } 

   @FXML
    private void exportpdfrepasbtn(ActionEvent event) {
        
      PrinterJob job = PrinterJob.createPrinterJob();
       
        Node root= this.tababon;
        
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
      @FXML
    private void pdfabonn(ActionEvent event) throws SQLException, IOException {
        
        AbonnementService ps = new AbonnementService();
         ObservableList<Abonnement> list = ps.getRepasList();

       // ObservableList<Abonnement>  ActList = FXCollections.observableArrayList();
        try {
           OutputStream file = new FileOutputStream(new File("D:\\Liste_des_abonnements.pdf"));
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
           PdfWriter.getInstance(document, file);
            document.open();

            Font font = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
            Paragraph pdfTitle = new Paragraph("Abonnement list", font);
            pdfTitle.setAlignment(Element.ALIGN_CENTER);

            document.add(pdfTitle);
            document.add(new Chunk("\n"));
            PdfPTable table = new PdfPTable(4);
            table.setHeaderRows(4);

            table.addCell("Nom");
            table.addCell("categories");
            table.addCell("Description");
            table.addCell("photo");


            ActList.forEach((_item) -> {
                table.addCell(_item.getNom());
                table.addCell(_item.getDescription());
                table.addCell(_item.getCategorie());
                table.addCell(_item.getPhoto());
               
            });

            document.add(table);

            document.close();

           file.close();

        } catch (DocumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Cannot export data!");
            alert.show();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setContentText("PDF Créé");
                JOptionPane.showMessageDialog(null, "PDF Créé");

    }
    private void generateqr(ActionEvent event) throws SQLException {
        AbonnementService ps = new AbonnementService();

                if (tababon.getSelectionModel().getSelectedItem() != null) {
            Abonnement e = new Abonnement();
            e.setDescription(ps.liste2().get(tababon.getSelectionModel().getSelectedIndex()).getDescription());
            e.setNom(ps.liste2().get(tababon.getSelectionModel().getSelectedIndex()).getNom());
            e.setCategorie(ps.liste2().get(tababon.getSelectionModel().getSelectedIndex()).getCategorie());
            
            Map hints = new HashMap();
            hints.put(com.google.zxing.EncodeHintType.ERROR_CORRECTION, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.H);
            com.google.zxing.qrcode.QRCodeWriter writer = new com.google.zxing.qrcode.QRCodeWriter();
            com.google.zxing.common.BitMatrix bitMatrix = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                // Create a qr code with the url as content and a size of 250x250 px
                bitMatrix = writer.encode("The plate's name= "+e.getNom()+"  "+"The plate's description= "+e.getDescription()+"  "+"The plate's adress=  "+e.getCategorie(), BarcodeFormat.QR_CODE, 250, 250, hints);
                MatrixToImageConfig config = new MatrixToImageConfig(MatrixToImageConfig.BLACK, MatrixToImageConfig.WHITE);
                // Load QR image
                BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, config);
                // Load logo image
                File file = new File("D:\\abbb.PNG");
                BufferedImage logoImage = ImageIO.read(file);
                // Calculate the delta height and width between QR code and logo
                int deltaHeight = qrImage.getHeight() - logoImage.getHeight();
                int deltaWidth = qrImage.getWidth() - logoImage.getWidth();
                // Initialize combined image
                BufferedImage combined = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = (Graphics2D) combined.getGraphics();
                // Write QR code to new image at position 0/0
                g.drawImage(qrImage, 0, 0, null);
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                // Write logo into combine image at position (deltaWidth / 2) and
                // (deltaHeight / 2). Background: Left/Right and Top/Bottom must be
                // the same space for the logo to be centered
                g.drawImage(logoImage, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);
                // Write combined image as PNG to OutputStream
                ImageIO.write(combined, "png", new File("D:\\afo.png"));
                //System.out.println("done");
            } catch (Exception ea) {
                System.out.println(ea);
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Choose a row !");
            alert.show();
        }
                
    }
   @FXML
    private void triabonnombtn(ActionEvent event) throws SQLException {
        
        
        AbonnementService ps = new AbonnementService();
        ps.tri();
        ObservableList<Abonnement> observableArrayList = 
           FXCollections.observableArrayList(ps.tri());
           tababon.setItems(observableArrayList);
        
       
        //tababon.setItems((ObservableList<Abonnement>) ps.tri());
    }
  /* @FXML
    private void abonfront(ActionEvent event) throws IOException {
        
          Abonnement r =  tababon.getSelectionModel().getSelectedItem();
       FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));

  Stage stage = new Stage(StageStyle.DECORATED.DECORATED);
  stage.setScene(
    new Scene(loader.load()));

   AccueilController controller = loader.getController();
  controller.abonfront(r);
   stage.show();
    }
   
 */ 
    /*private void stat(ActionEvent event) {
               
      try {
           
            String query = "SELECT COUNT(*),categories FROM abonnement GROUP BY categories" ;

             PreparedStatement PreparedStatement = connexion.prepareStatement(query);
                     PreparedStatement pst=connexion.prepareStatement(query);

             ResultSet rst= PreparedStatement.executeQuery();
           
            while (rst.next()){               
               a.add(new PieChart.Data(rst.getString("categories"),rst.getInt("COUNT(*)")));
            }     
        } catch (SQLException ex) {
            Logger.getLogger(AfficherAbonneController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        piechart.setTitle("**Statistiques nombres des catégories**");
        piechart.setLegendSide(Side.LEFT);
        piechart.setData(Data);
        
    }*/
    @FXML
    private void StatistiqueAbo(ActionEvent event) throws SQLException, IOException {
      try {
            AbonnementService bs = new AbonnementService();
        List<Abonnement> Abonnements = bs.afficherAbonnement();
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("StaticsAbonnement.fxml"));
           AnchorPane rootLayout = (AnchorPane) loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
        } 
    }

 @FXML
    private void Front(ActionEvent event) throws SQLException, IOException {
      try {
           
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Accueil.fxml"));
           AnchorPane rootLayout = (AnchorPane) loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
        } 
    }

}
