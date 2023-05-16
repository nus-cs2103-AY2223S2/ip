package duke.duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Deals with loading and saving tasks to and from the local file.
 * Saves data into file in the format of
 * [T][B][Description], where
 * - T is type of task
 * - B is int representing bool isMarked
 * - Description is details of the task.
 */
public class Storage {
    private static final String FILE_NAME = "saved_data.txt";

    private static int createFile() {
        int code = -1;
        try {
            File saveData = new File(FILE_NAME);
            code = saveData.createNewFile() ? 1 : 0;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return code;
    }

    private static ArrayList<String> readFile() {
        ArrayList<String> data = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(FILE_NAME);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return data;
    }

    /**
     * Writes a list of tasks into the local file.
     * @param data List of tasks
     */
    public static void writeFile(TaskList data) {
        ArrayList<String> savedTask = new ArrayList<>();
        for (int i = 0; i < data.getSize(); i++) {
            Task task = data.getEntry(i);
            String isMarked = task.isMarked() ? "1" : "0";
            String entry = task.getTag() + isMarked + task.getDescription();
            savedTask.add(entry);
        }

        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (String entry : savedTask) {
                writer.write(entry);
                writer.newLine();
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static TaskList addEntry(ArrayList<String> savedData) {
        TaskList data = new TaskList();
        for (String entry : savedData) { //entry: [T][B][Description]
            char type = entry.charAt(0);
            int marked = Character.getNumericValue(entry.charAt(1));
            String description = entry.substring(2);

            if (type == 'T') {
                ToDo todo = new ToDo(description);
                if (marked == 1) {
                    todo.markDone();
                }
                data.addFileEntry(todo);
            } else if (type == 'D') {
                Deadline deadline = new Deadline(description);
                if (marked == 1) {
                    deadline.markDone();
                }
                data.addFileEntry(deadline);
            } else if (type == 'E'){
                Event event = new Event(description);
                if (marked == 1) {
                    event.markDone();
                }
                data.addFileEntry(event);
            } else {
                assert type == 'T' || type == 'D' || type == 'E' : "Error saving: Invalid task";
            }
        }
        return data;
    }

    /**
     * Populates an empty TaskList with data read from an existing local file.
     * Used to initialise data when program starts.
     * @return The initialised list of tasks
     */
    public static TaskList populateList() {
        createFile();
        ArrayList<String> savedData = readFile();
        TaskList data = addEntry(savedData);
        return data;
    }


}
