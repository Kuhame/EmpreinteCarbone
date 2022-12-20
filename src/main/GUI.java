package main;

import consoCarbone.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utilisateur.Utilisateur;

public class GUI extends Application {
    @Override
    public void start(Stage stage) {
        // ***** Alimentation *****

        // Taux boeuf
        Label labelTxBoeuf = new Label("Pourcentage de repas à base de bœuf");
        Slider slTxBoeuf = new Slider(0, 100, 10);
        slTxBoeuf.setShowTickLabels(true);
        slTxBoeuf.setShowTickMarks(true);
        slTxBoeuf.setValue(0); // default

        // Taux vege
        Label labelTxVege = new Label("Pourcentage de repas végétariens");
        Slider slTxVege = new Slider(0, 100, 10);
        slTxVege.setShowTickLabels(true);
        slTxVege.setShowTickMarks(true);
        slTxVege.setValue(0);

        // Taux volaille
        Label labelTxVolaille = new Label("Pourcentage de repas à base de volaille");
        labelTxVolaille.setDisable(true);
        Slider slTxVolaille = new Slider(0, 100, 10);
        slTxVolaille.setShowTickLabels(true);
        slTxVolaille.setShowTickMarks(true);
        slTxVolaille.setValue(0);
        slTxVolaille.setDisable(true);

        // Équilibrage des trois sliders pour un total de 100%

        ajusterSlider(slTxBoeuf, slTxVege, slTxVolaille);
        ajusterSlider(slTxVege, slTxBoeuf, slTxVolaille);

        // ***** Habillement *****

        // Chemises
        Label labelNbChemises = new Label("Nombre de chemises achetées par an");
        TextField tfNbChemises = new TextField();
        tfNbChemises.setPromptText("3");

        // Jeans
        Label labelNbJeans = new Label("Nombre de jeans achetés par an");
        TextField tfNbJeans = new TextField();
        tfNbJeans.setPromptText("2");

        // T-Shirts
        Label labelNbTShirts = new Label("Nombre de T-shirts achetés par an");
        TextField tfNbTShirts = new TextField();
        tfNbTShirts.setPromptText("4");

        // Pulls
        Label labelNbPulls = new Label("Nombre de pulls achetés par an");
        TextField tfNbPulls = new TextField();
        tfNbPulls.setPromptText("2");

        // Manteaux
        Label labelNbManteaux = new Label("Nombre de manteaux achetés par an");
        TextField tfNbManteaux = new TextField();
        tfNbManteaux.setPromptText("1");

        // Robes
        Label labelNbRobes = new Label("Nombre de robes achetées par an");
        TextField tfNbRobes = new TextField();
        tfNbRobes.setPromptText("1");

        // Chaussures
        Label labelNbChaussures = new Label("Nombre de chaussures achetées par an");
        TextField tfNbChaussures = new TextField();
        tfNbChaussures.setPromptText("2");

        // ***** Logement *****

        // Superficie
        Label labelSuperficie = new Label("Superficie de votre logement");
        TextField tfSuperficie = new TextField();
        tfSuperficie.setPromptText("30");

        // Classe énergétique
        Label labelCE = new Label("Classe énergétique de votre logement");
        ComboBox<CE> cbxCE = new ComboBox<>();
        cbxCE.setItems(FXCollections.observableArrayList(CE.values()));
        cbxCE.setValue(CE.A);

        // ***** Transport *****

        // Possède
        Label labelPossede = new Label("Possédez-vous une voiture ?");
        CheckBox chkPossede = new CheckBox();
        chkPossede.setSelected(false);

        // Taille
        Label labelTaille = new Label("Taille de votre voiture");
        labelTaille.setDisable(true);
        ComboBox<Taille> cbxTaille = new ComboBox<>();
        cbxTaille.setItems(FXCollections.observableArrayList(Taille.values()));
        cbxTaille.setValue(Taille.G);
        cbxTaille.setDisable(true);

        // Nb km année
        Label labelNbKmAn = new Label("Nombre de kilomètres parcourus par an");
        labelNbKmAn.setDisable(true);
        TextField tfNbKmAn = new TextField();
        tfNbKmAn.setDisable(true);
        tfNbKmAn.setPromptText("1102");

        // Amortissement
        Label labelAmortissement = new Label("Durée d'amortissement");
        labelAmortissement.setDisable(true);
        TextField tfAmortissement = new TextField();
        tfAmortissement.setDisable(true);
        tfAmortissement.setPromptText("7");

        // Checkbox event handler
        chkPossede.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            labelTaille.setDisable(oldValue);
            cbxTaille.setDisable(oldValue);
            labelNbKmAn.setDisable(oldValue);
            tfNbKmAn.setDisable(oldValue);
            labelAmortissement.setDisable(oldValue);
            tfAmortissement.setDisable(oldValue);
        });

        // Popup pour afficher les recommandations
        Dialog<String> resultats = new Dialog<>();
        resultats.setTitle("Résultats");
        ButtonType btnOk = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        resultats.getDialogPane().getButtonTypes().add(btnOk);
        resultats.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        // Label erreur saisie
        Label labelErreur = new Label("Erreur de saisie.");
        labelErreur.setTextFill(Color.RED);
        labelErreur.setVisible(false);

        // ***** Bouton de calcul *****
        Button btnCalcul = new Button("Calculer");
        btnCalcul.setOnAction(event -> {
            try {
                double txBoeuf = slTxBoeuf.getValue() / 100;
                double txVege = slTxVege.getValue() / 100;

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
                if (chkPossede.isSelected()) {
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

                resultats.setContentText(utilisateur.recommandations());
                resultats.showAndWait();

                labelErreur.setVisible(false);

            } catch (NumberFormatException e) {
                labelErreur.setVisible(true);
            }
        });


        stage.setTitle("Calculateur d'empreinte carbone");

        // Partie gauche du formulaire
        VBox gauche = new VBox();
        gauche.setPadding(new Insets(20));
        gauche.setSpacing(10);
        gauche.getChildren().addAll(
                labelTxBoeuf,
                slTxBoeuf,
                labelTxVege,
                slTxVege,
                labelTxVolaille,
                slTxVolaille,
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
                tfNbRobes
        );

        // Partie droite du formulaire
        VBox droite = new VBox();
        droite.setPadding(new Insets(20));
        droite.setSpacing(10);
        droite.getChildren().addAll(
                labelNbChaussures,
                tfNbChaussures,
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
                btnCalcul,
                labelErreur
        );

        HBox root = new HBox(50);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(gauche, droite);

        stage.setScene(new Scene(root, 600, 625));

        stage.show();
    }

    private void ajusterSlider(Slider slider1, Slider slider2, Slider slTxVolaille) {
        slider1.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            slTxVolaille.setValue(100.0 - newValue.doubleValue() - slider2.getValue());

            double total = newValue.doubleValue() + slider2.getValue() + slTxVolaille.getValue();
            if (total > 100.0) {
                double overflow = total - 100.0;
                slider2.setValue(slider2.getValue() - overflow / 2);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}