package duke.command;

import duke.Storage;
import duke.gui.Ui;
import duke.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Users enter the archive command
 */
public class ArchiveCommand extends Command {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");

    /**
     * Constructor
     */
    public ArchiveCommand() {

    }

    /**
     * Naming the folder the current datetime, so that names will be distinct
     * Create the new file, and write the taskList to the new file
     * Lastly, empty the Tasklist and the Storage
     * @return Success message
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        LocalDateTime dt = LocalDateTime.now();
        String newFilePath = "data/" + dt.format(formatter) + ".txt";
        storage.createNewFile(newFilePath);
        storage.writeFromList(tasks,newFilePath);

        tasks.clear();
        storage.empty();
        return "Success !";
    }
}
