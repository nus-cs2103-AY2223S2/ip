package duke.util;

import duke.Duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Storage {
    private final File file;
    private final String filePath;
    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

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

    public TaskList load() throws DukeException {
        TaskList tl = new TaskList();
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) {
                String[] split = sc.nextLine().split("\\|");
                ArrayList<String> encodeSplit = new ArrayList<>();
                for (String s : split) {
                    encodeSplit.add(s);
                }
                String type = encodeSplit.get(1);
                boolean isDone;
                if (encodeSplit.get(2).equals("X")) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                String description;
                switch (type) {
                    case "T":
                        if (encodeSplit.size() != 4) {
                            throw new DukeException("Error with Todo load");
                        }
                        description = encodeSplit.get(3);
                        tl.add(new Todo(description, isDone));
                        break;
                    case "D":
                        if (encodeSplit.size() != 5) {
                            throw new DukeException("Error with Deadline load");
                        }
                        description = encodeSplit.get(3);
                        String by = encodeSplit.get(4);
                        LocalDateTime byTime = LocalDateTime.parse(by, DATETIME_FORMAT);
                        tl.add(new Deadline(description, byTime, isDone));
                        break;
                    case "E":
                        if (encodeSplit.size() != 6) {
                            throw new DukeException("Error with Deadline load");
                        }
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
