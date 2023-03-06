package Duke;

import javafx.application.Application;
import javafx.stage.Stage;

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

//    public static void main(String[] args) {
//        run();
//    }

    public String getResponse(String input) {
        String result = Parser.makeSense(input.strip());
        return result;
    }

}