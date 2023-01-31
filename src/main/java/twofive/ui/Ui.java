package twofive.ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import twofive.TwoFive;
import twofive.exception.TwoFiveException;

/**
 * Handles interaction with the user by reading inputs from the user
 * and displaying the corresponding outputs.
 */
public class Ui extends Application {
    private TwoFive twoFive;

    private static FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) throws NullPointerException {
        try {
            twoFive = new TwoFive("data/twofive.txt");
            fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setRoot(stage);
            stage = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().setTwoFive(twoFive);
            stage.show();
        } catch (IOException | TwoFiveException e) {
            showError(e.getMessage(), stage);
        } catch (DateTimeParseException e) {
            showError("Deadline/start time/end time must be in the"
                    + "format yyyy-MM-dd HH:mm, e.g. 2023-01-23 16:31", stage);
        }
    }

    private void showError(String error, Stage stage) {
        Label errorLabel = new Label(error);
        errorLabel.setPadding(new Insets(10));
        Scene scene = new Scene(errorLabel);

        stage.setScene(scene);
        stage.show();
    }
}
