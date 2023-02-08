package duke.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.FilePermissionsException;

public class ArchiveCommand extends Command {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm");

    private final String fileName;

    public ArchiveCommand(String fileName) {
        if (fileName == null) {
            String date = DATE_FORMAT.format(LocalDateTime.now());
            this.fileName = String.format("archive-%s.txt", date);
        } else {
            this.fileName = fileName;
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        Path fullPath = Paths.get(System.getProperty("user.dir"), "archive", this.fileName);
        File archiveFile = fullPath.toFile();
        try {
            // create the parent directories and file if needed
            archiveFile.getParentFile().mkdir();
            archiveFile.createNewFile();
        } catch (IOException e) {
            // do nothing if we can't create the file!
            // either the file already exists, or we do not have permission to create it
            // the second case will be handled in the read and save methods
        }
        try {
            String toWrite = taskList.serializeTasks();

            FileWriter writer = new FileWriter(archiveFile);
            writer.write(toWrite);
            writer.close();

            String message = String.format("All data has successfully been archived to:\n%s",
                    fullPath.toString());
            ui.addToMessage(message);

            taskList.clearTasks();
        } catch (IOException e) {
            throw new FilePermissionsException(fullPath.toString());
        }
    }
}
