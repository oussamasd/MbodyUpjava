/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import entities.Produit;
import entities.Categories;
import entities.ReservationProduit;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mehdi AZZAZ
 */
public class ReservationProduitController implements Initializable {

    @FXML
    private TableView<Produit> tableact;
    @FXML
    private TableColumn<Produit, Integer> id;
    @FXML
    private TableColumn<Produit, String> Nom;
    @FXML
    private TableColumn<Produit, Integer> prix;
    @FXML
    private TableColumn<Produit, Integer> quantite;
    @FXML
    private TableColumn<Produit, String> photo;
    @FXML
    private TableColumn<Categories, Integer> Categories;
     ObservableList<Produit>  ActList = FXCollections.observableArrayList();
      Stage stage ;
     Scene scene ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
        loadDate(ReservationProduit.lp);
    }  
    
     private void refreshTable(List<Produit> la) {
           
            
            tableact.getItems().clear();//actualiser
            
           List<Produit> listabon = ReservationProduit.lp;
           ActList.addAll(la);
            
          
         tableact.setItems(ActList);
            
            
        
        
        
        
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
    private void goBack(ActionEvent event) 
    {
             try{
               
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/Accueil.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }
    }

    @FXML
    private void goReserve(ActionEvent event) {
        
        try{
            qrcodr();
            ReservationProduit.lp.clear();
               
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/ReservationProduit.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }
        
    }
public void qrcodr(){

QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = "http://java-buddy.blogspot.com/";
        String msg="" ;
        for(Produit p : ReservationProduit.lp){
            
        msg=msg+p.Sres()+"\n";
        
        
        }
        
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(msg, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
            System.out.println(ex);
        }
        
        ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        
        StackPane root = new StackPane();
        root.getChildren().add(qrView);
        
        Scene scene = new Scene(root, 350, 350);
        Stage se =  new Stage();
       
        se.setTitle("mes reserves");
        se.setScene(scene);
       se.show();
 

};
    
}
