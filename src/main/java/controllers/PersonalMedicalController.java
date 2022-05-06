package controllers;

import domain.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import repository.LogException;
import service.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class PersonalMedicalController {
    private Service service;
    private Stage primaryStage;
    private PersonalMedical personalMedical;
    private Sectie sectie;

    private final ObservableList<Comanda> modelComenzi = FXCollections.observableArrayList();
    private final ObservableList<Medicament> modelMedicamente = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Comanda, String> tableColumnDenumireMed;

    @FXML
    private TableColumn<Comanda, Integer> tableColumnCantitate;

    @FXML
    private TableColumn<Comanda, String> tableColumnStatus;

    @FXML
    private TableView<Comanda> tableComenzi;

    @FXML
    private ListView<Medicament> tableMedicamente;

    @FXML
    private TextField textCutii;

    public void setService(Service service) {
        this.service = service;
        initModel();
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setPersonal(PersonalMedical personalMedical) {
        this.personalMedical = personalMedical;
    }

    public void setSectie(Sectie sectie) {
        this.sectie = sectie;
    }

    @FXML
    private void initialize() {
        tableColumnDenumireMed.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getMedicament().getDenumire()));
        tableColumnCantitate.setCellValueFactory(new PropertyValueFactory<>("cantitate"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableComenzi.setItems(modelComenzi);
        tableMedicamente.setItems(modelMedicamente);
    }

    private void initModel() {
        setItems();

        List<Medicament> medicamente = service.getMedicamente();
        modelMedicamente.setAll(medicamente);
    }

    private void setItems() {
        tableComenzi.getItems().clear();
        List<Comanda> comenziSectie = service.getComenziSectie(sectie.getId());
        modelComenzi.setAll(comenziSectie);
    }


    @FXML
    private void handleInregistrareComanda() {
        if (tableMedicamente.getSelectionModel().getSelectedItem() != null && !Objects.equals(textCutii.getText(), "")) {
            try {
                Medicament medicament = tableMedicamente.getSelectionModel().getSelectedItem();
                int cantitate = Integer.parseInt(textCutii.getText());
                if (cantitate > medicament.getStoc()) {
                    MessageAlert.showErrorMessage(null, "Nu exista suficient stoc!");
                } else {
                    Comanda comanda = new Comanda(cantitate, medicament, sectie);
                    service.inregistrareComanda(comanda);
                    setItems();
                    MessageAlert.showInfoMessage(null, "Comanda inregistrata cu succes");
                }
            } catch (Exception e) {
                MessageAlert.showErrorMessage(null, e.getMessage());
            }
        } else
            MessageAlert.showErrorMessage(null, "Nu este selectat niciun Medicament sau nu este introdusa nicio cantitate!");
    }

    @FXML
    private void handleModificaComanda() {
        if (tableComenzi.getSelectionModel().getSelectedItem() != null && !Objects.equals(textCutii.getText(), "")) {
            Comanda comanda = tableComenzi.getSelectionModel().getSelectedItem();
            if (Objects.equals(comanda.getStatus(), "ONORATA")) {
                MessageAlert.showErrorMessage(null, "Comanda este deja onorate, nu poate fi modificata!");
            } else if (Objects.equals(comanda.getStatus(), "IN_ASTEPTARE")) {
                try {
                    int cantitateNoua = Integer.parseInt(textCutii.getText());
                    if (cantitateNoua > comanda.getMedicament().getStoc()) {
                        MessageAlert.showErrorMessage(null, "Nu exista suficient stoc!");
                    } else {
                        comanda.setCantitate(cantitateNoua);
                        service.modificaComanda(comanda.getId(), comanda);
                        setItems();
                        MessageAlert.showInfoMessage(null, "Comanda a fost actualizata cu succes!");
                    }
                } catch (Exception e) {
                    MessageAlert.showErrorMessage(null, e.getMessage());
                }
            }
        } else
            MessageAlert.showErrorMessage(null, "Nu este selectata nicio comanda sau nu este introdusa nicio cantitate!");
    }

    @FXML
    private void handleStergeComanda() {
        if (tableComenzi.getSelectionModel().getSelectedItem() != null) {
            Comanda comanda = tableComenzi.getSelectionModel().getSelectedItem();
            if (Objects.equals(comanda.getStatus(), "ONORATA")) {
                MessageAlert.showErrorMessage(null, "Comanda este deja onorate, nu poate fi stearsa!");
            } else if (Objects.equals(comanda.getStatus(), "IN_ASTEPTARE")) {
                service.stergeComanda(comanda);
                setItems();
                MessageAlert.showInfoMessage(null, "Comanda stearsa cu succes");
            }
        } else
            MessageAlert.showErrorMessage(null, "Nu este selectata nicio comanda!");
    }

    @FXML
    private void handleDeconectare() throws IOException {
        try {
            service.logOutPersonalMedical(personalMedical);
        } catch (LogException e) {
            e.printStackTrace();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Farmacie Spital");
        primaryStage.setScene(scene);

        LoginController loginController = fxmlLoader.getController();
        loginController.setService(service);
        loginController.setStage(primaryStage);
    }

}
