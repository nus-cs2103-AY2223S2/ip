package duke;

import duke.task.*;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Boolean.parseBoolean;

/**
 * Read data from the file in the hard disk and store data to the same file if there are any updates to the
 * current task list.
 */
public class Storage {
    /** The file path to the file stored with data from previous Duke session. **/
    java.nio.file.Path filePath;

    /**
     * Main constructor (for invocation by classes that require updating data to the storage and taking data
     * from the storage.
     *
     * @param filePath The file path to the file stored with data from previous Duke session.
     */
    public Storage(java.nio.file.Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data that is stored in the file.
     *
     * @return ArrayList of Tasks from the file.
     * @throws DukeException If file not found.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            Scanner scanner = new Scanner(filePath.toFile());
            ArrayList<Task> tasks = new ArrayList<Task>();
            while (scanner.hasNextLine()) {
                String cur = scanner.nextLine();
//                System.out.println(cur);
                String[] temp = cur.split(" \\| ");
//                for(int i = 0; i < temp.length; i++) {
//                    System.out.println(temp[i]);
//                }
                if (temp[0].equals("T")) {
                    tasks.add(new Todo(temp[2], parseBoolean(temp[1])));
                } else if (temp[0].equals("D")) {
                    tasks.add(new Deadline(temp[2], temp[3], parseBoolean(temp[1])));
                } else if (temp[0].equals("E")) {
                    tasks.add(new Event(temp[2], temp[4], temp[3], parseBoolean(temp[1])));
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("Loading Error\n");
        }
    }

    /**
     * Updates the data in the file to match with the current TaskList.
     * @param tasks Current task list.
     */
    public void refresh(TaskList tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()));
            String res = "";
            for (int i = 0; i < tasks.size(); i++) {
                Task cur = tasks.get(i);
                res += cur.getSymbol() + " | ";
                res += ((cur.getStatusIcon() == "X") ? "true" : "false") + " | ";
                res += cur.getDetailedDescription();
                res += "\n";
            }
            writer.write(res);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error in writing to file.\n");
        }
    }
}
