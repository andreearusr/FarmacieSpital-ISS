package controllers;

import domain.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import repository.LogException;
import service.Service;

import java.io.IOException;
import java.util.List;

public class FarmacistController {

    private Service service;
    private Stage primaryStage;
    private Farmacist farmacist;

    private final ObservableList<Comanda> modelComenziInAsteptare = FXCollections.observableArrayList();
    private final ObservableList<Comanda> modelComenziOnorate = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Comanda, Medicament> tableColumnMedicament;

    @FXML
    private TableColumn<Comanda, Integer> tableColumnCantitate;

    @FXML
    private TableView<Comanda> tableComenziInAsteptare;

    @FXML
    private ListView<Comanda> tableComenziOnorate;


    public void setService(Service service) {
        this.service = service;
        initModel();
    }


    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setFarmacist(Farmacist farmacist) {
        this.farmacist = farmacist;
    }

    @FXML
    private void initialize() {
        tableColumnMedicament.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getMedicament()));
        tableColumnCantitate.setCellValueFactory(new PropertyValueFactory<>("cantitate"));

        tableComenziInAsteptare.setItems(modelComenziInAsteptare);
        tableComenziOnorate.setItems(modelComenziOnorate);
    }

    private void initModel() {
        setItems();
    }

    private void setItems() {
        tableComenziInAsteptare.getItems().clear();
        List<Comanda> comenziInAsteptare = service.getComenziInAsteptare();
        modelComenziInAsteptare.setAll(comenziInAsteptare);

        tableComenziOnorate.getItems().clear();
        List<Comanda> comenziOnorate = service.getComenziOnorate();
        modelComenziOnorate.setAll(comenziOnorate);
    }

    @FXML
    private void handleOnorareComanda() {
        if (tableComenziInAsteptare.getSelectionModel().getSelectedItem() != null) {
            Comanda comanda = tableComenziInAsteptare.getSelectionModel().getSelectedItem();

            comanda.setStatus("ONORATA");
            service.modificaComanda(comanda.getId(), comanda);

            Medicament medicament = comanda.getMedicament();
            medicament.setStoc(medicament.getStoc() - comanda.getCantitate());
            service.modificaMedicament(medicament.getId(), medicament);

            setItems();
        } else
            MessageAlert.showErrorMessage(null, "Nu este selectata nicio comanda!");
    }

    @FXML
    private void handleDeconectare() throws IOException {
        try {
            service.logOutFarmacist(farmacist);
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
