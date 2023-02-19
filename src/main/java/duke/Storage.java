package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.*;
import java.util.ArrayList;

/*
* Saves data into file in the format of [T][B][Description], where T is type of task,
* B is int representing bool isMarked, description is details of the task.
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

    public static void writeFile(TaskList data) {
        ArrayList<String> save = new ArrayList<>();
        for (int i = 0; i < data.getSize(); i++) {
            Task task = data.getEntry(i);
            String isMarked = task.isMarked() ? "1" : "0";
            String entry = task.getTag() + isMarked + task.getDescription();
            save.add(entry);
        }

        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (String entry : save) {
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

    public static TaskList populateList() {
        createFile();
        ArrayList<String> savedData = readFile();
        TaskList data = addEntry(savedData);
        return data;
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
                    todo.mark();
                }
                data.addFileEntry(todo);
            } else if (type == 'D') {
                Deadline deadline = new Deadline(description);
                if (marked == 1) {
                    deadline.mark();
                }
                data.addFileEntry(deadline);
            } else {
                Event event = new Event(description);
                if (marked == 1) {
                    event.mark();
                }
                data.addFileEntry(event);
            }
        }
        return data;
    }
}
