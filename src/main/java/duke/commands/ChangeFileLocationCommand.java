package duke.commands;


import duke.dukeexceptions.InvalidArgumentException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class ChangeFileLocationCommand extends Command{
    private String fileLocation;
    private Storage storage;

    public ChangeFileLocationCommand(String fileLocation, Storage storage){
        super("CHANGE_FILE_LOCATION");
        this.fileLocation = fileLocation;
        this.storage = storage;
    }

    @Override
    public String execute(TaskList tasks) {
        if (this.fileLocation == "") {
            throw new InvalidArgumentException("file location cannot be empty");
        }
        return this.storage.changeFileLocation(fileLocation, tasks);
    }

}
