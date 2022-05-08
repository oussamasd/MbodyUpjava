/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
/**
 * FXML Controller class
 *
 * @author user
 */
public class StatistiqueController implements Initializable {
private Connection con;
    @FXML
    private PieChart piechart;
    @FXML
    private Label caption;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try {
        Statement stmt1 = con.createStatement();
          ObservableList<PieChart.Data>pieData = FXCollections.observableArrayList();
           String SQL1 = "SELECT id,nom FROM type_reclamation";
            ResultSet rs1 = stmt1.executeQuery(SQL1);
              while(rs1.next())
                                {
                                 pieData.add(new PieChart.Data("nom type_reclamation : "+rs1.getString(1)+"\n"+"id like type_reclamation : "+rs1.getString(2),rs1.getDouble(2)));        
                                 MenuItem item = new MenuItem(rs1.getString(1));
                               // offreid.getItems().add(item);
                                }
                   
       piechart.setData(pieData);
                        
caption.setTextFill(Color.DARKORANGE);
caption.setStyle("-fx-font: 24 arial;");

for (final PieChart.Data data : piechart.getData()) {
    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
        new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                int i = (int) data.getPieValue();
                caption.setText(String.valueOf("Nb_like : "+i));
             }
        });
}
              } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
