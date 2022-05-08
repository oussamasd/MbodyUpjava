/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Mail;
import entities.User;
import services.ServiceUser;
import utils.MyDB;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.mail.MessagingException;
import services.SendSms;

/**
 * FXML Controller class
 *
 * @author IMNA
 */
public class backUserController extends AdminDashboardController {

    @FXML
    private TableView<User> UsersTable;
    @FXML
    private TableColumn<User, Integer> CinCol;
    @FXML
    private TableColumn<User, Integer> EmailCol;
    @FXML
    private TableColumn<User, Integer> RolesCol;
    @FXML
    private TableColumn<User, Integer> PasswordCol;
    @FXML
    private TableColumn<User, String> NomCol;
    @FXML
    private TableColumn<User, String> PrenomCol;
    @FXML
    private TableColumn<User, String> AdresseCol;
    @FXML
    private TableColumn<User, Integer> NumeroCol;
    @FXML
    private TableColumn<User, String> editCol;
    @FXML
    private TextField txt_rech;

    ServiceUser ser = new ServiceUser();

    /**
     * Initializes the controller class.
     */
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    User user = null;
    ServiceUser scU = new ServiceUser();

    ObservableList<User> UserList = FXCollections.observableArrayList();
    List<User> catA = new ArrayList<>();
    @FXML
    private GridPane ll;
    @FXML
    private Pane addbutton;
    @FXML
    private Label titrepage;
    ServiceUser userService = new ServiceUser();

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        try {
            LoadDate();
        } catch (SQLException ex) {
            Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txt_rech.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                recherche();
            } catch (SQLException ex) {
                Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void getAddView() throws IOException {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("addUser.fxml"));
            Scene scene = new Scene(parent);
            Stage secstage = new Stage();
            secstage.setScene(scene);
            secstage.initStyle(StageStyle.UTILITY);
            secstage.show();
        } catch (IOException ex) {
            Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void refreshTable() throws SQLException {

        UserList.clear();
        // UserList=(ObservableList<User>) scU.getUsers();
        query = "SELECT * FROM `user`";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User p2 = new User();
            p2.setId(resultSet.getInt("id"));
            p2.setCin(resultSet.getInt(2));
            p2.setEmail(resultSet.getString(3));
            p2.setRoles(resultSet.getString(4));
            p2.setPassword(resultSet.getString(5));
            p2.setNom(resultSet.getString(6));
            p2.setPrenom(resultSet.getString(7));
            p2.setAdresse(resultSet.getString(8));
            p2.setNumero(resultSet.getInt(9));
            UserList.add(p2);

            UsersTable.setItems(UserList);

        }
        // System.out.println("iciiiiiiiii");

    }

    private void LoadDate() throws SQLException {

        connection = MyDB.getInstance().getConnexion();
        refreshTable();
        CinCol.setCellValueFactory(new PropertyValueFactory<>("cin"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        RolesCol.setCellValueFactory(new PropertyValueFactory<>("roles"));
        PasswordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        NomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        PrenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        AdresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        NumeroCol.setCellValueFactory(new PropertyValueFactory<>("numero"));

        //add cell of button edit 
        Callback<TableColumn<User, String>, TableCell<User, String>> cellFoctory = (TableColumn<User, String> param) -> {
            // make cell containing buttons
            final TableCell<User, String> cell = new TableCell<User, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button deleteIcon = new Button("delete");
                        Button editIcon = new Button("edit");

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked(m -> {
                            try {
                                user = UsersTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `user` WHERE id  =" + user.getId();
                                connection = MyDB.getInstance().getConnexion();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();

                            } catch (SQLException ex) {
                                Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        editIcon.setOnMouseClicked(l -> {

                            user = UsersTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("addUser.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            AddUserController addUsersController = loader.getController();

                            addUsersController.setUpdate(true);
                            addUsersController.setTextField(user.getId(), user.getCin() , user.getEmail() , user.getPassword() ,user.getNom(),user.getPrenom(),user.getAdresse(),user.getNumero());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            
                            try {
                                SendSms.sendSms("Votre utilistateur a éte modifié par l'admin", "93049367");
                            } catch (MalformedURLException ex) {
                                Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           
                            stage.show();

                        });
                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        //            HBox.setMargin(deleteIcon,new Insets(0, 0, 0, 50));
                        //          HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }
            };
            return cell;
        };
        editCol.setCellFactory(cellFoctory);
        UsersTable.setItems(UserList);

    }

    @FXML
    private void rechercher() throws SQLException {
        UserList.clear();
        //  CatDermList.clear();
        String Nom = txt_rech.getText();
        catA = scU.getUsers();
        for (int i = 0; i < catA.size(); i++) {
            if (ser.recherche(catA.get(i), Nom) == true) {
              
               
                    User p2 = new User();

                    p2.setId(resultSet.getInt("id"));
                    p2.setCin(resultSet.getInt(2));
                    p2.setEmail(resultSet.getString(3));
                    p2.setRoles(resultSet.getString(4));
                    p2.setPassword(resultSet.getString(5));
                    p2.setNom(resultSet.getString(6));
                    p2.setPrenom(resultSet.getString(7));
                    p2.setAdresse(resultSet.getString(8));
                    p2.setNumero(resultSet.getInt(9));
                    UserList.add(p2);

                    UsersTable.setItems(UserList);
                
            }
        }

        CinCol.setCellValueFactory(new PropertyValueFactory<>("cin"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        RolesCol.setCellValueFactory(new PropertyValueFactory<>("roles"));
        PasswordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        NomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        PrenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        AdresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        NumeroCol.setCellValueFactory(new PropertyValueFactory<>("numero"));

    }

    public boolean recherche() throws SQLException {
        ObservableList<User> user_rech = FXCollections.observableArrayList();
        userService.getUsers().forEach((c) -> {
            user_rech.add(c);
        });
        long count_nom = user_rech.stream().filter(t -> t.getNom().toLowerCase().startsWith(this.txt_rech.getText().toLowerCase())).count();
        long count_email = user_rech.stream().filter(t -> t.getEmail().toLowerCase().startsWith(this.txt_rech.getText().toLowerCase())).count();
        System.out.println("count nom " + count_nom);
        System.out.println("count nom " + count_nom);

        if (count_nom != 0 && count_nom != this.catA.size()) {
            List<User> list = user_rech.stream().filter(t -> t.getNom().toLowerCase().startsWith(this.txt_rech.getText().toLowerCase())).collect(Collectors.toList());
            //UsersTable.setItems(UserList);
            UsersTable.getItems().clear();
            list.forEach(e -> {
                UsersTable.getItems().add(e);
            });
            
            //            list.forEach((c) -> {
            //                listViewCours.getItems().add("Coach : " + personneService.getById(c.getId_coach()).getNom_personne() + "\nJeu : " + c.getJeu() + "\nPrix : " + c.getPrix());
            //                listIds.add(c.getId());
            // }
            //  )
            ;
            return true;
        } else if (count_email != 0 && count_email != this.UserList.size()) {
            List<User> list = user_rech.stream().filter(t -> t.getEmail().toLowerCase().startsWith(this.txt_rech.getText().toLowerCase())).collect(Collectors.toList());
            UsersTable.getItems().clear();
            list.forEach(e -> {
                UsersTable.getItems().add(e);
            });
            //            list.forEach((c) -> {
            //                UsersTable.getItems().add("Coach : " + ServiceUser.getById(c.getId()).getNom() + "\nJeu : " + c.getEmail()+ "\nPrix : " + c.getPrix);
            //                Userlist.add(c.getId());
            //    }
            // )
            ;
            return true;
        }/* else if (txt_rech.getText().equals("")) {
            UsersTable.getItems().clear();
            cours.forEach((c) -> {
                listViewCours.getItems().add("Coach : " + personneService.getById(c.getId_coach()).getNom_personne() + "\nJeu : " + c.getJeu() + "\nPrix : " + c.getPrix());
                listIds.add(c.getId());
            });
            return true;
        }
        coursService.getAll().forEach((c) -> {
            this.cours.add(c);
        });

        listViewCours.getItems().clear();
        return false;*/
        return false;
    }


}
