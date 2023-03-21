package Duke;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Manages all initial actions to be taken before the application starts.
 */
public class Duke extends Application {


    /**
     * includes all the processes to be executed in the beginning before taking input
     */

    public Duke() {
        Storage.loadData();
    }
    public static void run() {
        Ui.greet();
        boolean stillOn = true;
        Storage.loadData();
        while(stillOn) {
            Parser.makeSense(Ui.getInput());
            stillOn = Parser.parserStatus;
        }
    }

    @Override
    public void start(Stage stage) {
    }

    public String getResponse(String input) {
        String result = Parser.makeSense(input.strip());
        return result;
    }

}