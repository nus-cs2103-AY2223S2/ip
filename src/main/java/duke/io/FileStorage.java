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
     * Creates a FileStorage object.
     *
     * @param path The path of the file to use for storage.
     */
    public FileStorage(Path path) {
        assert path != null;

        this.path = path;
    }

    @Override
    public boolean doesExist() throws DukeException {
        try {
            return Files.exists(path);
        } catch (SecurityException e) {
            throw new DukeException("I do not have enough permissions to check for the save file's existence!");
        }
    }

    @Override
    public void create() throws DukeException {
        try {
            ensureDirectoriesExist();
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            // Nothing to do as the file already exist
        } catch (IOException e) {
            System.out.println(e);
            throw new DukeException("I encountered an I/O error when creating the save file!");
        } catch (SecurityException e) {
            throw new DukeException("I do not have enough permissions to create the save file!");
        }
    }

    @Override
    public String read() throws DukeException {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new DukeException("I encountered an I/O error when reading the save file!");
        } catch (OutOfMemoryError e) {
            throw new DukeException("I need more memory to read the save file!");
        } catch (SecurityException e) {
            throw new DukeException("I do not have enough permissions to read the save file!");
        }
    }

    @Override
    public void write(String data) throws DukeException {
        assert data != null;

        try {
            Files.writeString(path, data);
        } catch (IOException e) {
            throw new DukeException("I encountered an I/O error when writing to the save file!");
        } catch (SecurityException e) {
            throw new DukeException("I do not have enough permissions to write to the save file!");
        }
    }

    private void ensureDirectoriesExist() throws IOException {
        try {
            Path parentDir = path.getParent();

            if (parentDir != null) {
                Files.createDirectories(path.getParent());
            }
        } catch (FileAlreadyExistsException e) {
            // Nothing to do as the directories already exist
        }
    }
}
