import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.WelcomeUI;

public class Duke extends Application {

    /**
     * Main driver method which initialises the chatbot with
     * a Scanner, Welcome UI and runs it.
     *
     * @param args
     */
    public static void main(String[] args) {
        WelcomeUI welcome = new WelcomeUI();
        Scanner input = new Scanner(System.in);
        ChatBot chat = new ChatBot(input, welcome);

        chat.run();
        input.close();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
