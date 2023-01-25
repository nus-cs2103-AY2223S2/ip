package duke;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent the file to store the task list.
 */
public class Storage {
    private final String filePath;
    private final Path path;

    /**
     * Constructor to create a storage.
     *
     * @param filepath string representation of the path.
     * @throws DukeFileNotFoundException
     */
    public Storage(String filepath) throws DukeFileNotFoundException {
        try {
            this.filePath = filepath;
            this.path = Path.of(filepath);
        } catch (InvalidPathException e) {
            throw new DukeFileNotFoundException("File is not found!");
        }
    }

    /**
     * Set a default storage if the file or folder is not found.
     */
    protected static void setDefaultStorage() {
        File folder = new File("data/");
        if (!folder.exists()) {
            folder.mkdir();
        }

        File file = new File("data/duke.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("IO Exception occur");
            }
        }
    }

    /**
     * Insert new task into the file.
     *
     * @param t task to be inserted.
     * @throws DukeIOException
     */
    protected void updateData(Task t) throws DukeIOException {
        try {
            List<String> allLine =  Files.readAllLines(path);
            String s = t.toString().charAt(1) + " | " + t.getStatusIcon() + " | " + t.description;
            if (t instanceof Deadlines) {
                Deadlines d =  (Deadlines) t;
                s += " | " + d.getBy();
            } else if (t instanceof Events) {
                Events e = (Events) t;
                s += " | " + e.getStart() + " | " + e.getEnd();
            }
            allLine.add(s);
            Files.write(path, allLine);
        } catch (IOException e) {
            throw new DukeIOException("Cannot read from " + filePath + " data file");
        }
    }

    /**
     * Update the task in the file with its new state.
     *
     * @param lineNumber line number in the file to be toggled.
     * @param status status of file that indicate whether the task is done or not.
     * @throws DukeIOException
     */
    protected void updateData(int lineNumber, int status) throws DukeIOException {
        try {
            List<String> allLine = Files.readAllLines(path);

            // overwrite the duke.txt file
            String line = allLine.get(lineNumber);
            String s = line.substring(0, 4) + status + line.substring(5);
            allLine.set(lineNumber, s);

            Files.write(path, allLine);
        } catch (IOException e) {
            throw new DukeIOException("Cannot read from " + filePath + " data file");
        }
    }

    /**
     * Remove specific task from the file.
     *
     * @param lineNumber line number in the file to be deleted.
     * @throws DukeIOException
     */
    protected void removeData(int lineNumber) throws DukeIOException {
        try {
            List<String> allLine = Files.readAllLines(path);

            // remove the line from duke.txt
            allLine.remove(lineNumber);

            Files.write(path, allLine);
        } catch (IOException e) {
            throw new DukeIOException("Cannot read from " + filePath + " data file");
        }
    }

    /**
     * Load the task from the file at the beginning of the program execution.
     *
     * @return list of task that stored in the file before.
     * @throws DukeIOException
     * @throws DukeInvalidArgumentException
     */
    protected ArrayList<Task> load() throws DukeIOException, DukeInvalidArgumentException {
        ArrayList<Task> taskList= new ArrayList<>();
        try {
            List<String> allLine = Files.readAllLines(path);

            if (allLine.isEmpty()) {
                return taskList;
            }

            for (int i = 0; i < allLine.size(); i++) {
                String taskDescription = allLine.get(i);
                String[] s = taskDescription.split(" \\| ");

                Task t = null;
                boolean isDone = s[1].equals("1");
                if (s[0].equals("T")) {
                    t = new ToDos(s[2]);
                } else if (s[0].equals("D")) {
                    t = new Deadlines(s[2], s[3]);
                } else if (s[0].equals("E")) {
                    t = new Events(s[2], s[3], s[4]);
                }
                t.setDone(isDone);
                taskList.add(t);
            }

            return taskList;

        } catch (IOException e) {
            throw new DukeIOException("Cannot read from " + filePath + " data file");
        }
    }

    /**
     * Empty all the task that stored in the file.
     *
     * @throws DukeIOException
     */
    protected void emptyStorage() throws DukeIOException {
        try {
            List<String> emptyLine = new ArrayList<>();

            Files.write(path, emptyLine);
        } catch (IOException e) {
            throw new DukeIOException("Cannot read from " + filePath + " data file");
        }
    }
}
