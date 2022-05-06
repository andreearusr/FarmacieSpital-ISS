import controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.*;
import repository.orm.*;
import service.Service;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        MedicamentRepository medicamentRepository = new MedicamentORMRepository();
        ComandaRepository comandaRepository = new ComandaORMRepository();
        FarmacistRepository farmacistRepository = new FarmacistORMRepository();
        PersonalMedicalRepository personalMedicalRepository = new PersonalMedicalORMRepository();

        Service service = new Service(medicamentRepository, comandaRepository, farmacistRepository, personalMedicalRepository);

        initView(primaryStage, service);
    }

    public static void main(String[] args) {
        launch();
    }

    private void initView(Stage primaryStage, Service service) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Farmacie Spital");
        primaryStage.setScene(scene);

        LoginController loginController = fxmlLoader.getController();
        loginController.setService(service);
        loginController.setStage(primaryStage);

        primaryStage.setResizable(false);
        primaryStage.show();
    }
}