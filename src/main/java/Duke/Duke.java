package Duke;

import Duke.Command.Parser;
import Duke.Exceptions.DukeException;
import Duke.storage.Storage;
import Duke.task.TaskList;
import Duke.ui.UI;

import java.io.*;

/**
 * Main Class to run duke programme.
 */
public class Duke {

    final UI ui;

    /**
   * Constructor for Duke.
   * Handles exceptions during loading process
   *
   * @param filePath receives the path of the data file
   */
    public Duke(String filePath) throws DukeException, IOException {
        File f = new File(filePath);
        Storage storage;
        TaskList tasks;
        if (!f.exists()) {
            File data = new File("data");
            data.mkdirs();
            File duke = new File("data/duke.txt");
            duke.createNewFile();
            storage = new Storage(duke.getPath());
        } else {
            storage = new Storage(filePath);
        }
        tasks = storage.loading();
        ui = new UI(new Parser(tasks, storage));
    }
}
