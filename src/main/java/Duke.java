import java.io.IOException;
import java.util.ArrayList;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Task;
import duke.Ui;
import javafx.application.Platform;

/**
 * The main class where the programs start
 */
@SuppressWarnings("checkstyle:Regexp")
public class Duke {
    private Storage storage;
    private ArrayList<Task> list;

    /**
     * @param filePath indicates the place where the txt file is located at
     * @throws IOException throws an exception when the file cannot be found or created
     */
    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            Ui userInterface = new Ui();
            userInterface.welcomeMessage();
            list = new ArrayList<>();
            storage.loadData(list);
        } catch (IOException e) {
            System.out.println("Error occurs when try to load.");
        }
    }

    public String getResponse(String input) throws DukeException {
        boolean saveData = true;
        if (input.contains("bye")) {
            Platform.exit();
        } else if (input.contains("find")) {
            saveData = false;
        } else if (input.contains("delete all")) {
            saveData = false;
        }

        try {
            String output = "";
            Parser parser = new Parser();
            output = parser.parserInput(list, input);
            if (saveData) {
                storage.saveData(list);
            }
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Error when trying to load/save your file";
        }
    }
}





