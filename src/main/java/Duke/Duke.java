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
            boolean buildDirectory = data.mkdirs();
            assert (buildDirectory);
            File duke = new File("data/duke.txt");
            boolean buildFile = duke.createNewFile();
            assert (buildFile);
            storage = new Storage(duke.getPath());
        } else {
            storage = new Storage(filePath);
        }
        tasks = storage.loading();
        ui = new UI(new Parser(tasks, storage));
    }
}
