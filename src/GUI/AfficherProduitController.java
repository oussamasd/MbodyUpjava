/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Categories;
import entities.MehdiMail;
import entities.Produit;
import static entities.Produit.filename;
import javafx.scene.image.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import services.CategoriesService;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Mehdi AZZAZ
 */
public class AfficherProduitController extends AdminDashboardController {
    
    public String imagecomp;
           Integer idd;

    @FXML
    private Pane addbutton;
    @FXML
    private Label titrepage;
    @FXML
    private TableView<Produit> tableact;
    @FXML
    private TableColumn<Produit, Integer> id;
    private Produit cc=null;
    @FXML
    private TextField recherche;
    @FXML
    private TableColumn<?, ?> photo;
    @FXML
    private Button upload;
    @FXML
    private ImageView imagefield;
    @FXML
    private TableColumn<Categories, Integer> Categories;
    @FXML
    private ComboBox nom_cat_id;
    @FXML
    private TextField Email;
    @FXML
    private Button exportPDF;


    public AfficherProduitController() {
    }
    @FXML
    private TableColumn<Produit, String> Nom;
   
    
    
    ProduitService servact = new ProduitService();
    CategoriesService CatS =new CategoriesService();
    
    ObservableList<Produit>  ActList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Produit,Integer > prix;
    @FXML
    private TableColumn<Produit, Integer> quantite;
    @FXML
    private Label titrepage1;
    @FXML
    private Label titrepage11;
    @FXML
    private TextField tnom;
    @FXML
    private TextField tquantite;
     private Label erreurref1;
      private Label erreurref;
       private Label nomerreur;
    @FXML
    private Label titrepage12;
    @FXML
    private TextField tprix;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//         loadDate();

                nom_cat_id.setItems(ActList);
    searchact();
    ObservableList<Categories>list;
        try {
            list = FXCollections.observableArrayList(CatS.afficherCategories());
            nom_cat_id.setItems(list);
        } catch (SQLException ex) {
            System.out.println(ex);
            
            
        }
        
        
        
        
        
    
         tnom.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            tnom.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
 tprix.textProperty().addListener((observable, oldValue, newValue) -> {
         if (!newValue.matches("\\s0-9")) {
            tprix.setText(newValue.replaceAll("[^\\s0-9]", ""));
        }
    });
 tquantite.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\s0-9*")) {
            tquantite.setText(newValue.replaceAll("[^\\s0-9]", ""));
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
tprix.textProperty().addListener(new ChangeListener<String>()
            {
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   if(newValue.isEmpty())
        {
                       erreurref1.setText("Champs vide!");
                 
        }
                   else if(newValue.length()>10)
        {
                       erreurref1.setText("Max longueur 10");
          
        }
                   else
        {
                erreurref.setText("   ");
              
                }
                }
                
                
            });
tquantite.textProperty().addListener(new ChangeListener<String>()
            {
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   if(newValue.isEmpty())
        {
                       nomerreur.setText("Champs vide!");
                 
        }
                   else if(newValue.length()>200)
        {
                       nomerreur.setText("Max longueur 200");
          
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
                cc = (Produit)tableact.getSelectionModel().getSelectedItem();
                System.out.println(cc);
                
            tnom.setText(cc.getNom());
             tprix.setText(String.valueOf(cc.getPrix()));
                 tquantite.setText(String.valueOf(cc.getQuantite()));
                
               
               
            }
          });
    }   
    
   
        ObservableList myList ;
        
     private void refreshTable(List<Produit> la) {
        try {   
            
            tableact.getItems().clear();//actualiser
            
           List<Produit> listabon = servact.afficherProduit();
           ActList.addAll(la);
            
          
         tableact.setItems(ActList);
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
        
    }
   
    
   
     private void loadDate(List<Produit> la) {
        
        
        
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
        Categories.setCellValueFactory(new PropertyValueFactory<>("cat_id"));

        if(la!=null){
        refreshTable(la);
     }
   
     }

    

    @FXML
    private void BtnSuppRec(ActionEvent event) throws SQLException {
         if (tableact.getSelectionModel().isEmpty() ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        JOptionPane.showMessageDialog(null,"Aucune Categories est selectionné ,veuillez choisir une Produit");
     }else{
   int responce=JOptionPane.showConfirmDialog(null, "Attention vous allez supprimer l'Produit sélectionné etes-vous sur ?","Suppression",JOptionPane.YES_NO_OPTION);
            if (responce==JOptionPane.YES_OPTION){
           ProduitService so = new ProduitService();
                    Produit a = (Produit) tableact.getSelectionModel().getSelectedItem();
                     so.SupprimerProduit(a);
             //refresh(true);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);

         alert.setContentText("Votre Produit a été bien supprimé");
                  JOptionPane.showMessageDialog(null,"Categories supprimé");

            } else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Votre Produit a été bien supprimé");
                 JOptionPane.showMessageDialog(null,"Suppression annulé");
            }
           SystemTray tray = SystemTray.getSystemTray();

        
       
        
    }
         
             loadDate(servact.afficherProduit());
    }

    @FXML
    private void BtnModifier(ActionEvent event) throws SQLException {
          Produit a = tableact.getSelectionModel().getSelectedItem();

        a.setNom(tnom.getText());
        a.setPrix(Integer.parseInt(tprix.getText()));
        a.setQuantite(Integer.parseInt(tquantite.getText()));
      
        ProduitService sp = new ProduitService();
        try {

            sp.ModifierProduit(a);
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
        loadDate(servact.afficherProduit());
    
    }

    @FXML
    private void BtnAjouterP(ActionEvent event) throws SQLException {
         if( Validchamp(tnom) &&  ValidchampD(tprix) &&  ValidchampD(tquantite)){  

    Produit a = new Produit();

        a.setNom(tnom.getText());
       a.setPrix(Integer.parseInt(tprix.getText()));
        a.setQuantite(Integer.parseInt(tquantite.getText()));
        a.setCat_id(Integer.parseInt(nom_cat_id.getValue().toString().split("_")[0]));
        a.setPhoto(imagecomp);
      
      
        ProduitService sp = new ProduitService();
        try {
            sendMail();
          sp.ajouterProduitP(a);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Nouvelle Categories");
          alert.setHeaderText(null);
          alert.setContentText("Votre categoires est ajouté");
          alert.showAndWait();
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
          
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getToolkit().createImage(getClass().getResource("icon.png"));

     

        
         }
    loadDate(servact.afficherProduit());
    }
     private boolean Validchamp(TextField T){
         if(T.getText().isEmpty() | T.getLength() <4 ){ //verifier nom o deesc vide 
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur de formulaire");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir tous les champs!");
            alert.showAndWait();
      return false;
    }return true;
   
    
    }
        private boolean ValidchampD(TextField T){
         if(T.getText().isEmpty() | T.getLength() <1 ){ //verifier nom o deesc vide 
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur de formulaire");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir tous les champs!");
            alert.showAndWait();
      return false;
    }return true;
        }
    
    public  void searchact(){
         try {
             
             List<Produit> allact = servact.afficherProduit();
     recherche.textProperty().addListener((observable, oldValue, newValue) -> {
   // System.out.println(newValue.toString());
   if( newValue.isEmpty()){
       //loadDate();
       
           
       
       
       
       
       loadDate(allact);
       
       
   }else{
   
       List<Produit> a = new ArrayList();
       a = allact.stream().filter(item-> {
           
           
            int index = item.getNom().toUpperCase ().indexOf(newValue.toUpperCase ());
            
             int index2 = Integer.toString(item.getId()).toUpperCase ().indexOf(newValue.toUpperCase ());
              int index3 = Integer.toString(item.getPrix()).toUpperCase ().indexOf(newValue.toUpperCase ());
               int index4 = Integer.toString(item.getQuantite()).toUpperCase ().indexOf(newValue.toUpperCase ());

               
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
            Produit.filename = "C:\\Users\\zcart\\Desktop\\kiftrip-website\\public\\uploads" + filename;
            //se.sendphp(fc.getAbsolutePath());
        }
        imagefield.setFitHeight(100);
        imagefield.setFitWidth(160);
        //..\img\google.png

        //C:\Users\splin\Documents\NetBeansProjects\FanArt\\com\esprit\img
        Produit.pathfile = fc.getAbsolutePath();
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
    private void exportPDF(ActionEvent event) {
        
      PrinterJob job = PrinterJob.createPrinterJob();
       
        Node root= this.tableact;
        
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
 public void sendMail(){
        MehdiMail.send(
    "mehdi.azzaz@esprit.tn",
    "nosforever24552201",
    "mehdi.azzaz20@gmail.com",
    "Bienvenu sur yaa",
    "mail de test!"
       );
    }

    public TextField getEmail() {
        return Email;
    }

    public void setEmail(TextField Email) {
        this.Email = Email;
    }
        public boolean FindTextLine(){
        String[] words = {"mauvaise", "mauvais","insatisfait","décu"};
        String experience_des = tnom.getText();
        CharSequence c = words.toString();
        if(experience_des.contains(c)){
            //System.out.println("");    
       
        return false;
    }return true;
       
}



}

