package panav.storage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import panav.exception.ToDoDescriptionException;
import panav.task.Deadline;
import panav.task.Event;
import panav.task.Task;
import panav.task.TaskList;
import panav.task.ToDo;


/**
 * Class to represent the storage facility of the program. It loads up the existing task list
 * when chatbot starts, and writes any changes if they are made.
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the existing list of tasks from text file.
     *
     * @return an arrayList of tasks.
     * @throws FileNotFoundException if text file doesn't exist.
     * @throws ToDoDescriptionException if todo is missing description.
     */
    public ArrayList<Task> load() throws FileNotFoundException, ToDoDescriptionException {
        ArrayList<Task> list = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String command = s.nextLine();
            String[] arr = command.split(" ~ ");
            Task curr;
            if (arr[0].compareTo("T") == 0) {
                curr = new ToDo(arr[2]);
                if (arr[1].compareTo("1") == 0) {
                    curr.markAsDone();
                }
            } else if (arr[0].compareTo("D") == 0) {
                curr = new Deadline(arr[2], arr[3]);
                if (arr[1].compareTo("1") == 0) {
                    curr.markAsDone();
                }
            } else {
                curr = new Event(arr[2], arr[3], arr[4]);
                if (arr[1].compareTo("1") == 0) {
                    curr.markAsDone();
                }
            }
            list.add(curr);

        }
        return list;
    }

    /**
     * Writes the changes to the list to the file.
     *
     * @param tasks the TaskList containing the tasks.
     * @throws IOException in case if folder is not found or some other exception.
     */
    public void write(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        String textToAdd = "";
        for (int i = 0; i < tasks.getLength(); i++) {
            Task task = tasks.getTask(i);
            String str = task.toString();
            int indexBy = str.indexOf("by:");
            assert (indexBy >= 0);
            int indexFrom = str.indexOf("from:");
            assert (indexFrom >= 0);
            int indexTo = str.indexOf("to:");
            assert (indexTo >= 0);
            int check = task.isDone() ? 1 : 0;
            int length = str.length();
            if (indexBy != -1) {
                textToAdd += String.format("%c ~ %d ~ %s ~ %s %n", str.charAt(1), check,
                        str.substring(7, indexBy - 2), str.substring(indexBy + 4, length - 1));
            } else if (indexFrom != -1) {
                textToAdd += String.format("%c ~ %d ~ %s ~ %s ~ %s %n", str.charAt(1), check,
                        str.substring(7, indexFrom - 1), str.substring(indexFrom + 6, indexTo - 1),
                        str.substring(indexTo + 4, length - 1));
            } else {
                textToAdd += String.format("%c ~ %d ~ %s %n", str.charAt(1), check, str.substring(7));
            }

        }
        fw.write(textToAdd);
        fw.close();
    }
}
