package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import duke.task.*;

/**
 * {@code Storage} class encapsulates storage of data
 * from file at specified filePath
 */
public class Storage {

    /**
     * filePath of file to access
     */
    private Path filePath;

    /**
     * List of lines read from file
     */
    private List<String> lines;

    /**
     * Constructor method for Storage class
     * @param filePath
     * @throws DukeException
     */
    public Storage(Path filePath) throws DukeException {
        this.filePath = filePath;
        try {
            this.lines = Files.lines(this.filePath).collect(Collectors.toList());
        } catch (IOException err) {
            this.lines = new ArrayList<>();
            throw new DukeException("Unable to access content of files!");
        }
    }

    /**
     * Loads lines read from file
     * @return list of string lines
     */
    public List<String> loadLines() {
        return this.lines;
    }

    /**
     * Edits file with new content or replaces existing lines with updated lines
     * @param taskList task list that keeps track of tasks
     * @throws DukeException when file fails to be written to
     */
    public void editFile(ArrayList<Task> taskList) throws DukeException {
        try {
            appendToFile(taskList);

            updateFile(taskList);
        } catch (IOException err) {
            throw new DukeException("Unable to edit File!");
        }
    }

    /**
     * appends data of new tasks into file
     * @param taskArrayList task list that keeps track of tasks
     * @throws IOException when writing to file is unsuccessful
     */
    private void appendToFile(ArrayList<Task> taskArrayList) throws IOException {
        if (taskArrayList.size() > lines.size()) {
            for (int i = lines.size(); i < taskArrayList.size(); i++) {
                Files.write(this.filePath,
                        (i + " | " + taskArrayList.get(i).toString() + "\n").getBytes(),
                        StandardOpenOption.APPEND);
            }
        }
    }

    /**
     * updates existing data of tasks into file
     * @param taskArrayList task list that keeps track of tasks
     * @throws IOException when writing to file is unsuccessful
     */
    private void updateFile(ArrayList<Task> taskArrayList) throws IOException {
        if (taskArrayList.size() <= lines.size()) {
            for (int i = 0; i < Math.min(taskArrayList.size(), lines.size()); i++) {
                String lineInQuestion = i + " | " + taskArrayList.get(i).toString() + "\n";
                if (!lines.get(i).contains(lineInQuestion)) {
                    lines.remove(i);
                } else if (!lines.get(i).equalsIgnoreCase(lineInQuestion)) {
                    lines.add(i, lineInQuestion);
                }
            }
            Files.write(this.filePath, lines);
        }
    }
}
