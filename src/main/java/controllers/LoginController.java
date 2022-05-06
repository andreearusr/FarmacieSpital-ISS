package controllers;

import domain.Farmacist;
import domain.PersonalMedical;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.LogException;
import service.Service;

import java.io.IOException;

public class LoginController {

    private Service service;
    private Stage primaryStage;

    @FXML
    private TextField usernameText;

    @FXML
    private TextField codText;

    @FXML
    private CheckBox checkPersonal;

    @FXML
    private CheckBox checkFarmacist;

    public void setService(Service service) {
        this.service = service;
    }


    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    @FXML
    public void handleFarmacist() {
        if (checkFarmacist.isSelected())
            checkPersonal.setDisable(true);
        if (!checkPersonal.isSelected() && !checkFarmacist.isSelected()) {
            checkFarmacist.setDisable(false);
            checkPersonal.setDisable(false);
        }
    }

    @FXML
    public void handlePersonal() {
        if (checkPersonal.isSelected())
            checkFarmacist.setDisable(true);
        if (!checkPersonal.isSelected() && !checkFarmacist.isSelected()) {
            checkFarmacist.setDisable(false);
            checkPersonal.setDisable(false);
        }
    }


    @FXML
    public void handleLogare() {
        if (checkFarmacist.isSelected()) {
            if (!codText.getText().equals("") && !usernameText.getText().equals(""))
                try {
                    tryLoginFarmacist(usernameText.getText(), Long.parseLong(codText.getText()));
                } catch (NumberFormatException e) {
                    MessageAlert.showErrorMessage(null, "username sau cod invalide!");
                }
            else
                MessageAlert.showErrorMessage(null, "username sau cod invalide!");
        } else if (checkPersonal.isSelected())
            if (!codText.getText().equals("") && !usernameText.getText().equals(""))
                try {
                    tryLoginPersonal(usernameText.getText(), Long.parseLong(codText.getText()));
                } catch (NumberFormatException e) {
                    MessageAlert.showErrorMessage(null, "username sau cod invalide!");
                }
            else
                MessageAlert.showErrorMessage(null, "username sau cod invalide!");
    }


    private void tryLoginPersonal(String username, Long cod) {
        try {
            PersonalMedical pm = service.getPersonal(cod);
            if (pm != null && pm.getUsername().equals(username)) {
                service.logInPersonalMedical(pm);
                changeScenePersonal(pm);
            } else
                MessageAlert.showErrorMessage(null, "username sau cod invalide!");

        } catch (LogException | IOException e) {
            MessageAlert.showErrorMessage(null, e.getMessage());
        }
    }

    private void tryLoginFarmacist(String username, Long cod) {
        try {
            Farmacist f = service.getFarmacist(cod);
            if (f != null && f.getUsername().equals(username)) {
                service.logInFarmacist(f);
                changeSceneFarmacist(f);
            } else
                MessageAlert.showErrorMessage(null, "username sau cod invalide!");
        } catch (LogException | IOException e) {
            MessageAlert.showErrorMessage(null, e.getMessage());
        }
    }

    private void changeScenePersonal(PersonalMedical personalMedical) throws IOException, LogException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("personalMedical.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle(personalMedical.getNume() + "|" + personalMedical.getUsername());
        primaryStage.setScene(scene);

        PersonalMedicalController personalMedicalController = fxmlLoader.getController();
        personalMedicalController.setStage(primaryStage);
        personalMedicalController.setPersonal(personalMedical);
        personalMedicalController.setSectie(personalMedical.getSectie());
        personalMedicalController.setService(service);
    }

    private void changeSceneFarmacist(Farmacist farmacist) throws IOException, LogException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("farmacist.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle(farmacist.getNume() + "|" + farmacist.getUsername());
        primaryStage.setScene(scene);

        FarmacistController farmacistController = fxmlLoader.getController();
        farmacistController.setStage(primaryStage);
        farmacistController.setFarmacist(farmacist);
        farmacistController.setService(service);

    }

}
