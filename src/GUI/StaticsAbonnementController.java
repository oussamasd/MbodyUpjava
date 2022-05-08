/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.stage.Stage;
import services.AbonnementService;

/**
 * FXML Controller class
 *
 * @author User
 */
public class StaticsAbonnementController implements Initializable {
    
 AbonnementService rs = new AbonnementService();
   Connection connexion;
    Statement stm;

    @FXML
    private PieChart stat_abon;
    private ObservableList<Data> Data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList<PieChart.Data> pieChartData;
        try {
           

            pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("simple", rs.pendingType()),
                    new PieChart.Data("couple", rs.confirmedType()),
                    new PieChart.Data("enfant", rs.cancelledType())
            );
           stat_abon.setData(pieChartData);
        

        } catch (SQLException ex) {
            Logger.getLogger(StaticsAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       /* {
      try {
           
            String query = "SELECT COUNT(*),categories FROM abonnement GROUP BY categories" ;

             PreparedStatement PreparedStatement = connexion.prepareStatement(query);
             PreparedStatement pst=connexion.prepareStatement(query);

             ResultSet rst= PreparedStatement.executeQuery();
           
            while (rst.next()){               
               rs.add(new PieChart.Data(rst.getString("categories"),rst.getInt("COUNT(*)")));
            }     
        } catch (SQLException ex) {
            Logger.getLogger(AfficherAbonneController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        stat_abon.setTitle("**Statistiques nombres des catégories**");
        stat_abon.setLegendSide(Side.LEFT);
        stat_abon.setData(Data);
        
       /* {
      try {
           
            String query = "SELECT COUNT(*),categories FROM abonnement GROUP BY categories" ;

             PreparedStatement PreparedStatement = connexion.prepareStatement(query);
             PreparedStatement pst=connexion.prepareStatement(query);

             ResultSet rst= PreparedStatement.executeQuery();
           
            while (rst.next()){               
               rs.add(new PieChart.Data(rst.getString("categories"),rst.getInt("COUNT(*)")));
            }     
        } catch (SQLException ex) {
            Logger.getLogger(AfficherAbonneController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        stat_abon.setTitle("**Statistiques nombres des catégories**");
        stat_abon.setLegendSide(Side.LEFT);
        stat_abon.setData(Data);
        }*/
    }    
      
    }    
    

