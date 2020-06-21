/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;




/**
 *
 * @author VIANIE
 */
public class p1 extends Application {
    Connection con;
    static PreparedStatement pst;
    
    @Override
    public void start(Stage fen){      
        
        
            fen.setResizable(false);
            fen.initStyle(StageStyle.DECORATED);
            fen.initStyle(StageStyle.UTILITY);
        fen.initStyle(StageStyle.UNIFIED);
        fen.setTitle("PHARMACIE");
        
        Text title = new Text();
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setText("Enregistrement du client");
        
        
        Label labnom = new Label("Nom du client");
        TextField Textnom = new TextField();
        Textnom.setPromptText("Entrez le nom ici");
        
        
        Label labPren = new Label("Penom");
        TextField textPren = new TextField();
        textPren.setPromptText("Entrez le prenom ici");
        
        
        Label labTel = new Label("Numero de telephone");
        TextField textTel = new TextField();
        textTel.setPromptText("Entrez le numero de telephone ici");
        
       
        Text texEnr = new Text("");
        texEnr.setFont(Font.font("Arial",FontWeight.BOLD,30));
        
       
       Button bout = new Button("Enregistrer");
       Button boutA = new Button("RETURN");
       
        
        boutA.setVisible(true);
        bout.setDefaultButton(true);
        // ajout du label, textfield et boutons
            VBox vbox = new VBox(labnom, Textnom, labPren,textPren, labTel, textTel, bout, texEnr, boutA ); 
  
            // espacement
            vbox.setSpacing(10); 
  
            //  alignement pour le HBox 
            vbox.setAlignment(Pos.CENTER); 
  
            // creation de la scene 
            Scene scene = new Scene(vbox, 500, 500); 
  
            // creation de l'input stream 
            FileInputStream input = null; 
        try {
            input = new FileInputStream("C:\\Users\\VIANIE\\Documents\\NetBeansProjects\\projet\\src\\projet\\Capture.PNG");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(p1.class.getName()).log(Level.SEVERE, null, ex);
        }
  
            // creation l'image 
            Image image = new Image(input); 
  
            // creation du background image 
            BackgroundImage backgroundimage = new BackgroundImage(image,  
                                             BackgroundRepeat.NO_REPEAT,  
                                             BackgroundRepeat.NO_REPEAT,  
                                             BackgroundPosition.DEFAULT,  
                                                BackgroundSize.DEFAULT); 
  
            // creation Background 
            Background background = new Background(backgroundimage); 
  
            // utilisation du background 
            vbox.setBackground(background); 
  
            // utilisation de la scene 
            fen.setScene(scene); 
            fen.getIcons().setAll(new Image(getClass().getResource("Enr.PNG").toExternalForm()));
            fen.show(); 
            
        
        bout.setOnAction(new EventHandler<ActionEvent>(){
        
        public void handle(ActionEvent e){
            
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
         pst = con.prepareStatement("insert into client ( nom, prenom, num_tel) values ( ?,  ?, ?)");
        
         pst.setString(1, Textnom.getText());
         pst.setString(2, textPren.getText());
         pst.setInt(3, Integer.parseInt(textTel.getText()));
         
         pst.execute();
         System.out.println("Insertion ok");
         int num = Integer.parseInt(textTel.getText());
         ResultSet res = pst.executeQuery("SELECT * FROM client ");
         while (res.next()){
             num = res.getInt(1);
         }
         texEnr.setText(Textnom.getText()+" a ete enregistré code: "+ num);
         
         Textnom.setText("");
         textPren.setText("");
         textTel.setText("");
 } catch (Exception u){
 }          
        }
        });
        
         boutA.setOnAction(new EventHandler<ActionEvent>(){
        public void handle(ActionEvent e){
            Projet var = new Projet() ;
            Stage o = new Stage ();
            var.start(o);
            fen.close();
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
