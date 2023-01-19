package duke.io;

import duke.exception.DukeException;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Represents a file storage.
 */
public class FileStorage implements Storage {
    private final Path path;

    /**
     * Creates a file storage.
     *
     * @param path The path of the file to use for storage.
     */
    public FileStorage(Path path) {
        this.path = path;
    }

    @Override
    public boolean doesExist() throws DukeException {
        try {
            return Files.exists(path);
        } catch (SecurityException e) {
            throw new DukeException("Unable to check if save file exist due to missing permissions.");
        }
    }

    @Override
    public void create() throws DukeException {
        try {
            ensureDirectoriesExist();
            Files.createFile(path);
        } catch (FileAlreadyExistsException ignored) {
        } catch (IOException e) {
            throw new DukeException("Unable to create save file due to I/O error.");
        } catch (SecurityException e) {
            throw new DukeException("Unable to create save file due to missing permissions.");
        }
    }

    @Override
    public String read() throws DukeException {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new DukeException("Unable to read save file due to I/O error.");
        } catch (OutOfMemoryError e) {
            throw new DukeException("Unable to read save file due to insufficient memory.");
        } catch (SecurityException e) {
            throw new DukeException("Unable to read save file due to missing permissions.");
        }
    }

    @Override
    public void write(String data) throws DukeException {
        try {
            Files.writeString(path, data);
        } catch (IOException e) {
            throw new DukeException("Unable to write to save file due to I/O error.");
        } catch (SecurityException e) {
            throw new DukeException("Unable to write to save file due to missing permissions.");
        }
    }

    private void ensureDirectoriesExist() throws IOException {
        try {
            Files.createDirectories(path.getParent());
        } catch (FileAlreadyExistsException ignored) {
        }
    }
}
