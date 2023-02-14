package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a storage object that handles interactions with the save file.
 */
public class Storage {
    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private final File file;
    private final String filePath;

    /**
     * Represents a constructor for a Storage object.
     *
     * @param filePath Path of the save file.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        this.filePath = filePath;

        try {
            if (this.file.createNewFile()) {
                new Ui().showToUser("New save file made");
            } else {
                new Ui().showToUser("File already exists meow");
            }
        } catch (IOException e) {
            new Ui().showToUser("Error when construction of Storage meow");
            System.out.println(new Ui().formatMessage(e.getMessage()));
        }
    }

    /**
     * Returns a TaskList after loading the save file into a TaskList object.
     *
     * @return TaskList object containing the tasks in the save file.
     * @throws DukeException If there is an error in loading the save file.
     */
    public TaskList load() throws DukeException {
        TaskList tl = new TaskList();
        try {
            checkLoadFileValidity();
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) {
                String[] split = sc.nextLine().split("\\|");
                ArrayList<String> encodeSplit = new ArrayList<>(Arrays.asList(split));
                String type = encodeSplit.get(1);
                boolean isDone = encodeSplit.get(2).equals("X");
                assert type != null : "Loading the files, the type should not be null";
                String description;
                switch (type) {
                case "T":
                    description = encodeSplit.get(3);
                    tl.add(new Todo(description, isDone));
                    break;
                case "D":
                    description = encodeSplit.get(3);
                    String by = encodeSplit.get(4);
                    LocalDateTime byTime = LocalDateTime.parse(by, DATETIME_FORMAT);
                    tl.add(new Deadline(description, byTime, isDone));
                    break;
                case "E":
                    description = encodeSplit.get(3);
                    String from = encodeSplit.get(4);
                    String to = encodeSplit.get(5);
                    LocalDateTime fromTime = LocalDateTime.parse(from, DATETIME_FORMAT);
                    LocalDateTime toTime = LocalDateTime.parse(to, DATETIME_FORMAT);
                    tl.add(new Event(description, fromTime, toTime, isDone));
                    break;
                default:
                    throw new DukeException("Loading got problem");
                }

            }
        } catch (FileNotFoundException e) {
            new Ui().showToUser("Cannot find save file while loading!! MEOWWW");
            new Ui().showToUser(e.getMessage());
        }
        return tl;
    }

    private void checkLoadFileValidity() throws DukeException {
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) {
                String[] split = sc.nextLine().split("\\|");
                ArrayList<String> encodeSplit = new ArrayList<>(Arrays.asList(split));
                String type = encodeSplit.get(1);
                boolean isDone;
                switch (type) {
                case "T":
                    if (encodeSplit.size() != 4) {
                        throw new DukeException("Error with Todo load");
                    }
                    break;
                case "D":
                    if (encodeSplit.size() != 5) {
                        throw new DukeException("Error with Deadline load");
                    }
                    break;
                case "E":
                    if (encodeSplit.size() != 6) {
                        throw new DukeException("Error with Deadline load");
                    }
                    break;
                default:
                }
            }
        } catch (FileNotFoundException e) {
            new Ui().showToUser("Cannot find save file while loading!! MEOWWW");
            new Ui().showToUser(e.getMessage());
        }
    }

    /**
     * Updates the save file with the current TaskList.
     *
     * @param tl TaskList object containing the current tasks.
     * @throws IOException If there is an error in writing to the save file.
     */
    public void update(TaskList tl) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        String saveTask = "";
        int maxSize = tl.size();
        for (int i = 0; i < maxSize; i++) {
            Task t = tl.get(i);
            int stringNumber = i + 1;
            saveTask += stringNumber + "|" + String.join("|", t.encode());
            if (i < maxSize - 1) {
                saveTask += System.lineSeparator();
            }
        }
        fw.write(saveTask);
        fw.close();
    }


}
