/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author VIANIE
 */
public class Projet extends Application {
    Connection con;
    Statement pst;
    PreparedStatement st;
    ResultSet rst;
    public void start(Stage fen){
            fen.setResizable(false);
        GridPane grid = new GridPane();
        Scene sc = new Scene(grid, 900, 500);
        Text title = new Text();
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setText("Connexion interface");
        grid.add(title, 0, 0, 2, 1);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
       grid.setHgap(10);
         grid.setVgap(10);
        fen.setScene(sc);
        fen.setTitle("WELCOME IN YOUR PHARMACY'S APPLICATION");
        fen.initStyle(StageStyle.UTILITY);
        fen.initStyle(StageStyle.UNIFIED);
        fen.initStyle(StageStyle.DECORATED);
        fen.show();
        
        Label labuser = new Label("USER NAME");
        TextField TextUser = new TextField();
        TextUser.setPromptText("Entrez le nom d'utilisateur ici");
        grid.add(labuser, 0, 1);
        grid.add(TextUser,1, 1);
       
       
        PasswordField Textpwd = new PasswordField();
        Label labpwd = new Label("PASSWORD");
        Textpwd.setPromptText("Entrez le mot de passe ici");
        grid.add(labpwd, 0, 2);
       grid.add(Textpwd,1, 2 );
       sc.getStylesheets().add(Projet.class.getResource("mise1Forme.css").toExternalForm());
fen.getIcons().setAll(new Image(getClass().getResource("icon.png").toExternalForm()));
        
  
       
//       sc.getStylesheets().add(Beignet.class.getResource("beignetForm.css").toExternalForm());
       
    Button bout = new Button("SE CONNECTER");
    bout.setDefaultButton(true);
    
    grid.add(bout,1, 4);
    bout.setLayoutX(10);
    
    Button boutA = new Button("Enregistrer");
    
    Button boutB = new Button("Achat");
    Button boutC = new Button("Ajout-Stock");
    
    
    bout.setOnAction(new EventHandler<ActionEvent>(){
        
        public void handle(ActionEvent e){
//            bout.setEffect(shadow);
             try {
    Class.forName("com.mysql.jdbc.Driver");
       System.out.println("Driver Ok");
     } catch (Exception i) {
         
     }
            try {
          con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacie","root","");
          System.out.println("Connection Ok");
         } catch (Exception o) {   
         }
            try{
        pst = con.createStatement();
        String a = TextUser.getText();
        String b = Textpwd.getText();
        rst = pst.executeQuery("select * from connexion where user='"+a+"' and pwd ='"+b+"' ");
        if(rst.next()){
             boutA.visibleProperty();
             grid.add(boutA,2, 5);
              boutA.setOnAction(new EventHandler<ActionEvent>(){
        
        public void handle(ActionEvent e){
            p1 var = new p1() ;
            Stage o = new Stage ();
            var.start(o);
            fen.close();
              }
        });
              
             boutB.setDefaultButton(true);
             grid.add(boutB,3, 5);
             boutB.setOnAction(new EventHandler<ActionEvent>(){
        public void handle(ActionEvent e){
            conecxion var = new conecxion() ;
            Stage o = new Stage ();
            var.start(o);
            fen.close();
              }
        });
             
                    boutC.visibleProperty();
             grid.add(boutC,4, 5);
             boutC.setOnAction(new EventHandler<ActionEvent>(){
        public void handle(ActionEvent e){
            incr var = new incr() ;
            Stage o = new Stage ();
            var.start(o);
            fen.close();
              }
        });

        }
        else{
           
            TextUser.setText("");
            Textpwd.setText("");
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
dialog.setTitle("Echec de connection ");
dialog.setHeaderText("");
dialog.setContentText("login ou password incorect essayer encore");
dialog.showAndWait();
        
                
        }
        
        }catch(Exception i){}
        }
        });
        

    }
        
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
