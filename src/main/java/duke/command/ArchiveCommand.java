package duke.command;

import duke.exception.DukeException;
import duke.io.FileStorage;
import duke.io.Storage;
import duke.task.TaskList;

import java.nio.file.Path;

/**
 * Represents an archive command for archiving the task list into a file and deleting all tasks tracked by the app.
 */
public class ArchiveCommand implements Command {
    /**
     * Archives the task list to a file at the path specified in input, then clears the task list.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return An acknowledgement message.
     * @throws DukeException Indicates failure to write to file or no path was specified in input.
     */
    @Override
    public String run(String input, TaskList tasks) throws DukeException {
        Storage storage = createStorage(input);

        tasks.save(storage);
        tasks.clear();

        return "And poof! All gone! All your tasks are gone.";
    }

    private Storage createStorage(String input) throws DukeException {
        String path = extractSavePath(input);

        Storage storage = new FileStorage(Path.of(path));
        storage.create();

        return storage;
    }

    private String extractSavePath(String input) throws DukeException {
        assert input != null;

        String path = input.replaceFirst("archive", "").trim();

        validateNonEmptyPath(path);

        return path;
    }

    private void validateNonEmptyPath(String path) throws DukeException {
        assert path != null;

        if (path.isEmpty()) {
            throw new DukeException("You didn't specify a path to save the archive to!");
        }
    }
}
