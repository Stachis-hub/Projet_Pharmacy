/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
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




/**
 *
 * @author VIANIE
 */
 public class conecxion extends Application {
    Connection con;
    static PreparedStatement pst;
    int TotSom = 0;
    int nbAch = 0;
    @Override
    public void start(Stage fen){      
        
        
            fen.setResizable(false);
            fen.initStyle(StageStyle.UTILITY);
        fen.initStyle(StageStyle.UNIFIED);
        fen.initStyle(StageStyle.DECORATED);
        fen.setTitle("Achats");
        
        Text title = new Text();
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setText("liste d'achats");
        
        
        Label labcodC = new Label("Code du client");
        TextField Textcod = new TextField();
        Textcod.setPromptText("Entrez le code du client ici");
        
        
        Label labAmnt = new Label("Nom du medicament");
        TextField textAmnt = new TextField();
        textAmnt.setPromptText("Entrez le nom du medicament ici");
        
       
        Label labPu = new Label("Prix unitaire");
        TextField TextPu = new TextField();
        TextPu.setPromptText("Entrez le prix unitaire ici");
        
        
        Label labQte = new Label();
        labQte.setText("Quantite");
        TextField TextQte = new TextField();
        TextQte.setPromptText("Entrez la quantite ici");
        
        Label labmed = new Label("TOTAL");
        TextField textmed = new TextField();
        textmed.setPromptText("Le montant total est de");
        
        
        Text texPa = new Text();
        texPa.setFont(Font.font("Arial",FontWeight.BOLD,30));
       
        
       Button bout = new Button("Calculer");
        bout.setDefaultButton(true);
        
        
        Button boutA = new Button("Calculer le total");
        
        Button boutB = new Button("RETURN");
       boutB.setDefaultButton(true);
        
        boutA.setVisible(true);
        
        // ajout du label, textfield et bouton
            VBox vbox = new VBox(labcodC, Textcod, labAmnt, textAmnt, labPu, TextPu, labQte, TextQte, labmed, textmed,  bout, boutA, texPa, boutB ); 
  
            // espacement
            vbox.setSpacing(10); 
  
            //  alignement pour le HBox 
            vbox.setAlignment(Pos.CENTER); 
  
            // creation de la scene 
            Scene scene = new Scene(vbox, 500, 600); 
  
            // creation de l'input stream 
            FileInputStream input = null; 
        try {
            input = new FileInputStream("C:\\Users\\VIANIE\\Documents\\NetBeansProjects\\projet\\src\\projet\\index.jpg");
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
  fen.getIcons().setAll(new Image(getClass().getResource("imgAchat.PNG").toExternalForm()));
            fen.show(); 
            
       
                
        
        bout.setOnAction(new EventHandler<ActionEvent>(){
        
        public void handle(ActionEvent e){
            int qte = Integer.parseInt(TextQte.getText());
            int Pu = Integer.parseInt(TextPu.getText());
            int Total = qte*Pu;
            
            String TotS = String.valueOf(Total);
            textmed.setText(TotS); 
            TotSom = TotSom + Total;
            
            TextQte.setText("");
            TextPu.setText("");
            
        }
        });
        
        boutA.setOnAction(new EventHandler<ActionEvent>(){
        
        public void handle(ActionEvent e){
            String TotS = String.valueOf(TotSom);
            texPa.setText("Total a payer: "+TotS); 
            
            TextQte.setText("");
            TextPu.setText("");
            textmed.setText("");  
              nbAch = nbAch + 1;
              System.out.println(nbAch);
            
              
        
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    
    }
    
}


