package exceptions.missing;

import java.nio.file.Path;

public class File extends exceptions.DukeException{
    /**
     * Constructs a Missing File Exception for the given TaskType.
     *
     */
    public File(Path filePath) {
        super(String.format("%s Unable to access %s", OOPS, filePath.toFile()));
    }
}
