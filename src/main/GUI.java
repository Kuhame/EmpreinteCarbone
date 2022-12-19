package main;

import consoCarbone.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utilisateur.Utilisateur;

public class GUI extends Application {
    @Override
    public void start(Stage stage) {
        // ***** Alimentation *****

        // Taux boeuf
        Label labelTxBoeuf = new Label("Proportion de repas à base de boeuf ?");
        TextField tfTxBoeuf = new TextField();

        // Taux vege
        Label labelTxVege = new Label("Proportion de repas végétariens ?");
        TextField tfTxVege = new TextField();

        // ***** Habillement *****

        // Chemises
        Label labelNbChemises = new Label("Nombre de chemises achetées par an ?");
        TextField tfNbChemises = new TextField();

        // Jeans
        Label labelNbJeans = new Label("Nombre de jeans achetés par an ?");
        TextField tfNbJeans = new TextField();

        // T-Shirts
        Label labelNbTShirts = new Label("Nombre de T-shirts achetés par an ?");
        TextField tfNbTShirts = new TextField();

        // Pulls
        Label labelNbPulls = new Label("Nombre de pulls achetés par an ?");
        TextField tfNbPulls = new TextField();

        // Manteaux
        Label labelNbManteaux = new Label("Nombre de manteaux achetés par an ?");
        TextField tfNbManteaux = new TextField();

        // Robes
        Label labelNbRobes = new Label("Nombre de robes achetés par an ?");
        TextField tfNbRobes = new TextField();

        // Chaussures
        Label labelNbChaussures = new Label("Nombre de chaussures achetées par an ?");
        TextField tfNbChaussures = new TextField();

        // ***** Logement *****

        // Superficie
        Label labelSuperficie = new Label("Superficie de votre logement ?");
        TextField tfSuperficie = new TextField();

        // Classe énergétique
        Label labelCE = new Label("Classe énergétique de votre logement ?");
        ComboBox<CE> cbxCE = new ComboBox<>();
        cbxCE.setItems(FXCollections.observableArrayList(CE.values()));
        cbxCE.setValue(CE.A);

        // ***** Transport *****

        // Possède
        Label labelPossede = new Label("Possédez-vous une voiture ?");
        CheckBox chkPossede = new CheckBox();
        chkPossede.setSelected(false);

        // Taille
        Label labelTaille = new Label("Taille de votre voiture ?");
        labelTaille.setDisable(true);
        ComboBox<Taille> cbxTaille = new ComboBox<>();
        cbxTaille.setItems(FXCollections.observableArrayList(Taille.values()));
        cbxTaille.setValue(Taille.G);
        cbxTaille.setDisable(true);

        // Nb km année
        Label labelNbKmAn = new Label("Nombre de kilomètres parcourus par an ?");
        labelNbKmAn.setDisable(true);
        TextField tfNbKmAn = new TextField();
        tfNbKmAn.setDisable(true);

        // Amortissement
        Label labelAmortissement = new Label("Durée d'amortissement ?");
        labelAmortissement.setDisable(true);
        TextField tfAmortissement = new TextField();
        tfAmortissement.setDisable(true);

        // Popup pour afficher les recommandations
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Résultats");
        ButtonType btnOk = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(btnOk);

        // ***** Bouton de calcul *****
        Button btnCalcul = new Button("Calculer");
        btnCalcul.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double txBoeuf = Double.parseDouble(tfTxBoeuf.getText());
                double txVege = Double.parseDouble(tfTxVege.getText());
                int nbChemises = Integer.parseInt(tfNbChemises.getText());
                int nbJeans = Integer.parseInt(tfNbJeans.getText());
                int nbTshirts = Integer.parseInt(tfNbTShirts.getText());
                int nbPulls = Integer.parseInt(tfNbPulls.getText());
                int nbManteaux = Integer.parseInt(tfNbManteaux.getText());
                int nbRobes = Integer.parseInt(tfNbRobes.getText());
                int nbChaussures = Integer.parseInt(tfNbChaussures.getText());
                int superficie = Integer.parseInt(tfSuperficie.getText());
                CE classeEnergetique = cbxCE.getValue();
                Transport transport = new Transport();
                if(chkPossede.isSelected()){
                    Taille taille = cbxTaille.getValue();
                    int kilomAnnee = Integer.parseInt(tfNbKmAn.getText());
                    int amortissement = Integer.parseInt(tfAmortissement.getText());
                    transport = new Transport(chkPossede.isSelected(), taille, kilomAnnee, amortissement);
                }
                Alimentation alimentation = new Alimentation(txBoeuf, txVege);
                BienConso habillement = new Habillement(nbChemises, nbJeans, nbTshirts, nbPulls, nbManteaux, nbRobes, nbChaussures);
                Logement logement = new Logement(superficie, classeEnergetique);
                ServicesPublics servicesPublics = ServicesPublics.getInstance();
                Utilisateur utilisateur = new Utilisateur(alimentation, habillement, logement, transport, servicesPublics);

                dialog.setContentText(utilisateur.recommandations());
                dialog.showAndWait();
            }
        });


                stage.setTitle("Calculateur d'empreinte carbone");

        // Partie gauche du formulaire
        VBox gauche = new VBox();
        gauche.setPadding(new Insets(20));
        gauche.setSpacing(10);
        gauche.getChildren().addAll(
                labelTxBoeuf,
                tfTxBoeuf,
                labelTxVege,
                tfTxVege,
                labelNbChemises,
                tfNbChemises,
                labelNbJeans,
                tfNbJeans,
                labelNbTShirts,
                tfNbTShirts,
                labelNbPulls,
                tfNbPulls,
                labelNbManteaux,
                tfNbManteaux,
                labelNbRobes,
                tfNbRobes,
                labelNbChaussures,
                tfNbChaussures
        );

        // Partie droite du formulaire
        VBox droite = new VBox();
        droite.setPadding(new Insets(20));
        droite.setSpacing(10);
        droite.getChildren().addAll(
                labelSuperficie,
                tfSuperficie,
                labelCE,
                cbxCE,
                labelPossede,
                chkPossede,
                labelTaille,
                cbxTaille,
                labelNbKmAn,
                tfNbKmAn,
                labelAmortissement,
                tfAmortissement,
                btnCalcul
        );

        HBox root = new HBox(50);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(gauche, droite);

        stage.setScene(new Scene(root, 600, 600));

        stage.show();

        // Checkbox event handler
        chkPossede.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            labelTaille.setDisable(oldValue);
            cbxTaille.setDisable(oldValue);
            labelNbKmAn.setDisable(oldValue);
            tfNbKmAn.setDisable(oldValue);
            labelAmortissement.setDisable(oldValue);
            tfAmortissement.setDisable(oldValue);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}