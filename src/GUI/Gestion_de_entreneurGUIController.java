/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.javafx.iio.ImageStorage.ImageType;
import entities.Categorie;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import entities.Entreneur;
import entities.Categorie;
import entities.Category;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.glxn.qrgen.QRCode;
import services.categorieService;
import services.entreneurService;

/**
 * FXML Controller class
 *
 * @author Lounga
 */
public class Gestion_de_entreneurGUIController extends AdminDashboardController {

    @FXML
    private TextField type_categorie;
    @FXML
    private TextField tag_categorie;
    @FXML
    private Button b_ajouter_categorie;

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Categorie> categorie_table;
    @FXML
    private Button b_Modifer_categorie;
    @FXML
    private Button b_supprimer_categ;
    @FXML
    private TableView<Entreneur> entreneur_table;
    @FXML
    private TextField e_nom;
    @FXML
    private TextField e_prenom;
    @FXML
    private TextField e_age;
    @FXML
    private TextField e_type;
    @FXML
    private TextField e_categnum;
    @FXML
    private TextField e_image;
    @FXML
    private Button b_ajouter_entreneur;
    @FXML
    private TableColumn<Categorie,Integer> c_affiche_id;
    @FXML
    private TableColumn<Categorie, String> c_affiche_type;
    @FXML
    private TableColumn<Categorie, String> c_affiche_tag;
    @FXML
    private TableColumn<Entreneur, Integer> id_affiche_table;
    @FXML
    private TableColumn<Entreneur, String> nom_affiche_table;
    @FXML
    private TableColumn<Entreneur, String> prenom__affiche_table;
    @FXML
    private TableColumn<Entreneur, String> age_affiche_table;
    @FXML
    private TableColumn<Entreneur, String> type_affiche_table;
    @FXML
    private TableColumn<Entreneur, String> numero_affiche_table;
    @FXML
    private TableColumn<Entreneur, String> image_affiche_table;
    @FXML
    private Pane addbutton;
    @FXML
    private Label titrepage;
    @FXML
    private Button e_b_modifer;
    @FXML
    private Button B_import;
    @FXML
    private ImageView a_picture_kol;
     private int identifiant;
      private int ref;
    @FXML
    private Button stat;
    @FXML
    private Button b_qrcode;
    @FXML
    private TextField e_recherche_qr;
    @FXML
    private Button b_tri;
    private ComboBox<?> combobox;
    @FXML
    private ComboBox<String> comboboxTYpe;
    @FXML
    private ComboBox<String> DESCASC;
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboboxTYpe.setItems(FXCollections.observableArrayList("Age","Nom","Prenom"));
        DESCASC.setItems(FXCollections.observableArrayList("decroissant","Croissante"));
        loadTablecateg();
        
        loadTableEntreneur();
    }  
       ObservableList<Categorie> oblist = FXCollections.observableArrayList();
     categorieService cs= new categorieService();    
     
     ObservableList<Entreneur> oblistE = FXCollections.observableArrayList();
     entreneurService es= new entreneurService();
     
    private void loadTablecateg(){//affiche categ 
        List <Categorie> ls2 =cs.afficher();
        ls2.forEach(e->oblist.add(e));
        System.out.println(oblist);
        c_affiche_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        c_affiche_tag.setCellValueFactory(new PropertyValueFactory<>("tag"));
        c_affiche_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        categorie_table.setItems(oblist);
    }
    private void loadTableEntreneur() {
   List <Entreneur> ls =es.afficher();
        ls.forEach(e->oblistE.add(e));
        System.out.println(oblistE);
        id_affiche_table.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_affiche_table.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom__affiche_table.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        age_affiche_table.setCellValueFactory(new PropertyValueFactory<>("age"));
        type_affiche_table.setCellValueFactory(new PropertyValueFactory<>("type"));
        numero_affiche_table.setCellValueFactory(new PropertyValueFactory<>("categ_id"));
        image_affiche_table.setCellValueFactory(new PropertyValueFactory<>("image"));
      
     entreneur_table.setItems(oblistE);
    }

   


    @FXML
    private void add_categ(ActionEvent event) {
         if(type_categorie.getText().equals("") || tag_categorie.getText().equals("")){ 
            JOptionPane.showMessageDialog(null, "veuillez remplir tout les champs ");
        }
       
       else {
        Categorie c= new Categorie(type_categorie.getText(),tag_categorie.getText());
        cs.ajouter(c);
        categorie_table.getItems().clear();
        loadTablecateg();
    }}

    
   
    @FXML
    private void c_select(MouseEvent event) {
         int index = categorie_table.getSelectionModel().getSelectedIndex();
        Categorie h = categorie_table.getSelectionModel().getSelectedItem();
        System.out.println(h);
       type_categorie.setText((String) c_affiche_type.getCellData(index));
        tag_categorie.setText((String) c_affiche_tag.getCellData(index));
        identifiant=categorie_table.getSelectionModel().getSelectedItem().getId();
       
    }
    
        @FXML
    private void supp_categ(MouseEvent event) {
        Categorie c= (Categorie) categorie_table.getSelectionModel().getSelectedItem();
      cs.supprimer(c);
      categorie_table.getItems().clear();
      loadTablecateg();
    }

    @FXML
    private void e_select(MouseEvent event) {
         int index = entreneur_table.getSelectionModel().getSelectedIndex();
        Entreneur h = (Entreneur) entreneur_table.getSelectionModel().getSelectedItem();
        e_nom.setText((String) nom_affiche_table.getCellData(index));
        e_prenom.setText((String) prenom__affiche_table.getCellData(index));
        e_image.setText((String) image_affiche_table.getCellData(index));
        e_age.setText(age_affiche_table.getCellData(index));
        e_categnum.setText(numero_affiche_table.getCellData(index));
        e_type.setText(type_affiche_table.getCellData(index));
        ref=h.getId();
        
        
    }
    
    

    @FXML
    private void add_entreneur(ActionEvent event) throws SQLException {
       
        int nbr2=Integer.parseInt(e_categnum.getText());  
        if(e_nom.getText().equals("") || e_prenom.getText().equals("")|| e_age.getText().equals("")|| e_type.getText().equals("")){ 
            JOptionPane.showMessageDialog(null, "veuillez remplir le maximum des champs ");
        }else if (!(e_age.getText().matches("^[0-9]+$"))){
        JOptionPane.showMessageDialog(null, "le age doit etre and nombre ");
        }else {
        Entreneur e= new Entreneur(e_nom.getText(),e_prenom.getText(),e_age.getText(),e_type.getText(),nbr2,e_image.getText());
        es.ajouterEntraineur(e);
        entreneur_table.getItems().clear();
        loadTableEntreneur();
    }}


    @FXML
    private void b_supprimer_entreneur(MouseEvent event) {
          Entreneur e= (Entreneur) entreneur_table.getSelectionModel().getSelectedItem();
      es.supprimer(e);
      entreneur_table.getItems().clear();
      loadTableEntreneur();
    }


    @FXML
    private void c_modifer(ActionEvent event) {
              if(type_categorie.getText().equals("") || tag_categorie.getText().equals("")){ 
            JOptionPane.showMessageDialog(null, "veuillez remplir tout les champs ");
        }
       
       else {
        Categorie c= new Categorie(type_categorie.getText(),tag_categorie.getText());
        cs.modifier(c,identifiant);
        categorie_table.getItems().clear();
        loadTablecateg();
    }
    }

    @FXML
    private void modifer_entreneur(ActionEvent event) {
            entreneurService es = new entreneurService(); 
     Entreneur a=  entreneur_table.getSelectionModel().getSelectedItem(); 
           int nbr1=Integer.parseInt(e_categnum.getText());
            a.setNom(e_nom.getText());
            a.setPrenom(e_prenom.getText());
            a.setAge(e_age.getText());
            a.setType(e_type.getText());
            a.setCateg_id(nbr1);
            a.setImage(e_image.getText());
     es.modifier(a,ref);
     categorie_table.getItems().clear();
        loadTablecateg();  
    }

    @FXML
    private void h_importpath(ActionEvent event) {
         //String url = "https://media.istockphoto.com/photos/downtown-cleveland-hotel-entrance-and-waiting-taxi-cab-picture-id472899538?b=1&k=20&m=472899538&s=170667a&w=0&h=oGDM26vWKgcKA3ARp2da-H4St2dMEhJg23TTBeJgPDE=";
        //  https://scontent.ftun2-1.fna.fbcdn.net/v/t1.6435-1/183506009_570330220601497_8193355351482540241_n.jpg?stp=dst-jpg_p240x240&_nc_cat=100&ccb=1-5&_nc_sid=7206a8&_nc_ohc=WzY1pEm1wmgAX9glMHe&_nc_ht=scontent.ftun2-1.fna&oh=00_AT_xDNWjHCOZtztRCNPXji-Vv_9lhxihMESPO4Hk6DRBSA&oe=62838EE0
        
        String url=e_image.getText();
        System.out.println(url);

            Image image = new Image(url);
            if (image.isError()) {
                System.out.println("Error loading image from "+url);
                JOptionPane.showMessageDialog(null, "verifer voter URL ");
                // image.getException().printStackTrace();
            } else {
                  
                System.out.println("Successfully loaded image from " + url);
                a_picture_kol.setImage(image);
    }

 
}

    @FXML
    private void Import(MouseEvent event) {
    }
      @FXML
    private void Statistique(ActionEvent event) throws SQLException, IOException {
      try {
            entreneurService bs = new entreneurService();
        List<Entreneur> Entreneurs = bs.afficher();
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("StatEnt.fxml"));
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
    private void e_shareQR(MouseEvent event) {
             try {
            String details =e_recherche_qr.getText(); 
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa"+details);/* toString(hs.getByReferanc(Integer.parseInt(h_recherche_referance.getText())))*/ 
            ByteArrayOutputStream out =QRCode.from(es.QRdonner(Integer.parseInt(e_recherche_qr.getText()))).to(net.glxn.qrgen.image.ImageType.PNG).stream();
            String f_name = e_recherche_qr.getText();
            String Path_name="C:\\Users\\oussa\\OneDrive\\Desktop\\aymenellouzi\\MbodyUpjava\\MbodyUpjava\\pic\\";//******************************************
            FileOutputStream fout = new FileOutputStream(new File(Path_name +(f_name +".PNG")));
                    fout.write(out.toByteArray());
                    fout.flush();
                    JOptionPane.showMessageDialog(null, "QRcode Crée");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void TRI(ActionEvent event) {
       String va1 = comboboxTYpe.getValue();
        String va2 = DESCASC.getValue();
        System.out.println(va1);
        System.out.println(va2);
        entreneur_table.getItems().clear();
      
        
        
        if((va1.equals("Age")) && (va2.equals("decroissant")))
        {
            System.out.println("in");
        
        List <Entreneur> ls =es.afficherAGEDESC();
        ls.forEach(e->oblistE.add(e));
        System.out.println(oblistE);
        id_affiche_table.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_affiche_table.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom__affiche_table.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        age_affiche_table.setCellValueFactory(new PropertyValueFactory<>("age"));
        type_affiche_table.setCellValueFactory(new PropertyValueFactory<>("type"));
        numero_affiche_table.setCellValueFactory(new PropertyValueFactory<>("categ_id"));
        image_affiche_table.setCellValueFactory(new PropertyValueFactory<>("image"));
      
     entreneur_table.setItems(oblistE);
        }
        else if (("Age".equals(va1)) && ("Croissante".equals(va2)))
        {System.out.println("***************");
       
       List <Entreneur> ls =es.afficherAGEASC();
        ls.forEach(e->oblistE.add(e));
        System.out.println(oblistE);
        id_affiche_table.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_affiche_table.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom__affiche_table.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        age_affiche_table.setCellValueFactory(new PropertyValueFactory<>("age"));
        type_affiche_table.setCellValueFactory(new PropertyValueFactory<>("type"));
        numero_affiche_table.setCellValueFactory(new PropertyValueFactory<>("categ_id"));
        image_affiche_table.setCellValueFactory(new PropertyValueFactory<>("image"));
      
     entreneur_table.setItems(oblistE);
        }
        else if (("Nom".equals(va1)) && ("Croissante".equals(va2)))
        {
        entreneur_table.getItems().clear();
        List <Entreneur> ls =es.afficherNOMASC();
        ls.forEach(e->oblistE.add(e));
        System.out.println(oblistE);
        id_affiche_table.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_affiche_table.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom__affiche_table.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        age_affiche_table.setCellValueFactory(new PropertyValueFactory<>("age"));
        type_affiche_table.setCellValueFactory(new PropertyValueFactory<>("type"));
        numero_affiche_table.setCellValueFactory(new PropertyValueFactory<>("categ_id"));
        image_affiche_table.setCellValueFactory(new PropertyValueFactory<>("image"));
      
     entreneur_table.setItems(oblistE);
        }
        else if (("Nom".equals(va1)) && ("decroissant".equals(va2)))
        {
            System.out.println("GUI.Gestion_de_entreneurGUIController.TRI()");
       List <Entreneur> ls =es.afficherNOMDESC();
        ls.forEach(e->oblistE.add(e));
        System.out.println(oblistE);
        id_affiche_table.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_affiche_table.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom__affiche_table.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        age_affiche_table.setCellValueFactory(new PropertyValueFactory<>("age"));
        type_affiche_table.setCellValueFactory(new PropertyValueFactory<>("type"));
        numero_affiche_table.setCellValueFactory(new PropertyValueFactory<>("categ_id"));
        image_affiche_table.setCellValueFactory(new PropertyValueFactory<>("image"));
      
     entreneur_table.setItems(oblistE);
        }
        else if (("Prenom".equals(va1)) && (va2=="decroissant"))
        {
        entreneur_table.getItems().clear();
        List <Entreneur> ls =es.afficherPRENOMDESC();
        ls.forEach(e->oblistE.add(e));
        System.out.println(oblistE);
        id_affiche_table.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_affiche_table.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom__affiche_table.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        age_affiche_table.setCellValueFactory(new PropertyValueFactory<>("age"));
        type_affiche_table.setCellValueFactory(new PropertyValueFactory<>("type"));
        numero_affiche_table.setCellValueFactory(new PropertyValueFactory<>("categ_id"));
        image_affiche_table.setCellValueFactory(new PropertyValueFactory<>("image"));
      
     entreneur_table.setItems(oblistE);
        }
        else if (("Prenom".equals(va1)) || ("Croissante".equals(va2)))
        {
        entreneur_table.getItems().clear();
        List <Entreneur> ls =es.afficherPRENOMASC();
        ls.forEach(e->oblistE.add(e));
        System.out.println(oblistE);
        id_affiche_table.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_affiche_table.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom__affiche_table.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        age_affiche_table.setCellValueFactory(new PropertyValueFactory<>("age"));
        type_affiche_table.setCellValueFactory(new PropertyValueFactory<>("type"));
        numero_affiche_table.setCellValueFactory(new PropertyValueFactory<>("categ_id"));
        image_affiche_table.setCellValueFactory(new PropertyValueFactory<>("image"));
      
     entreneur_table.setItems(oblistE);
    }
        else {System.out.println("error");}
        
    }
    
    
    }



    
