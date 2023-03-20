package duke;

import duke.DukeException;

public class StorageFileFormatException extends DukeException {
    public StorageFileFormatException () {
        super("There is an error in the format of the data in the Data duke.Storage File");
    }
}
