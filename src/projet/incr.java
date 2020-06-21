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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static projet.p1.pst;

/**
 *
 * @author VIANIE
 */
public class incr extends Application {

       Connection con;
    static PreparedStatement pst;
    
    @Override
    public void start(Stage fen){      
        
        
            fen.setResizable(false);
            fen.initStyle(StageStyle.UTILITY);
        fen.initStyle(StageStyle.UNIFIED);
        fen.initStyle(StageStyle.DECORATED);
        fen.setTitle("PHARMACIE");
        
        Text title = new Text();
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setText("enregistrement-suppresion des medicaments en stock");
        
        
        Label labnom = new Label("Nom du medicament");
        TextField Textnom = new TextField();
        Textnom.setPromptText("Entrez le nom ici");
        
        
        Label labCod = new Label("code du medicament");
        TextField textCod = new TextField();
        textCod.setPromptText("Entrez le code ici");
        
        
        Label labPU = new Label("Prix unitaire");
        TextField textPU = new TextField();
        textPU.setPromptText("Entrez le Prix unitaire ici");
        
       
        Label labQte = new Label("Quantite ajoutée");
        TextField textQte = new TextField();
        textQte.setPromptText("Entrez la Quantite ajoutée ici");
        
        Button bout = new Button("Enregistrer");
        bout.setDefaultButton(true);
        
        
        Button boutA = new Button("Supprimer");
        boutA.setDefaultButton(true);
        
               Button boutB = new Button("RETURN");
       
        
        boutB.setVisible(true);
        
        
        Text texEnr = new Text("");
        texEnr.setFont(Font.font("Arial",FontWeight.BOLD,30));
         
        // ajout du label, textfield et bouton
            VBox vbox = new VBox(labnom, Textnom, labCod, textCod, labPU, textPU, labQte, textQte,  bout, boutA, texEnr, boutB ); 
  
            // espacement
            vbox.setSpacing(10); 
  
            //  alignement pour le HBox 
            vbox.setAlignment(Pos.CENTER); 
  
            // creation de la scene 
            Scene scene = new Scene(vbox, 700, 500); 
  
            // creation de l'input stream 
            FileInputStream input = null; 
        try {
            input = new FileInputStream("C:\\Users\\VIANIE\\Documents\\NetBeansProjects\\projet\\src\\projet\\medoc.PNG");
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
            scene .getStylesheets().add(Projet.class.getResource("mise1Forme.css").toExternalForm());
  fen.getIcons().setAll(new Image(getClass().getResource("med.jpg").toExternalForm()));
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
         pst = con.prepareStatement("insert into medicament ( code_med, nom_med, prix_uni, quantite) values (?, ?,  ?, ?)");
        
         pst.setString(1, textCod.getText());
         pst.setString(2, Textnom.getText());
         pst.setInt(3, Integer.parseInt(textPU.getText()));
         pst.setInt(4, Integer.parseInt(textQte.getText()));
         
         pst.execute();
         System.out.println("Insertion ok");
         int num = Integer.parseInt(textCod.getText());
         ResultSet res = pst.executeQuery("SELECT * FROM medicament ");
         texEnr.setText("le medicament " +Textnom.getText()+" a ete enregistré  ");
         
         Textnom.setText("");
         textCod.setText("");
         textPU.setText("");
         textQte.setText("");
 } catch (Exception u){
 }          
        }
        });
        
        boutA.setOnAction(new EventHandler<ActionEvent>(){
        
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
         pst = con.prepareStatement("DELETE  FROM medicament WHERE code_med  = '\"+ Textcod.getText()+ \"'" );

         
         pst.execute();
         System.out.println("Suppression Ok");
         texEnr.setText("le medicament " +Textnom.getText()+" a ete supprimé  ");
         
         Textnom.setText("");
         textCod.setText("");
         textPU.setText("");
         textQte.setText("");
 } catch (Exception u){
 }          
        }
        });
        
          boutB.setOnAction(new EventHandler<ActionEvent>(){
        public void handle(ActionEvent e){
            Projet var = new Projet() ;
            Stage o = new Stage ();
            var.start(o);
            fen.close();
              }
        });
        
    }
    
    
     public static void main(String[] args) {
        launch(args);
    }
}
