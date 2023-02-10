package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Storage class encapsulates
 * loading and saving operations.
 */
public class Storage {
    public static final String SAVE_LOCATION = "duke.txt";

    /**
     * Loads the save file from a fixed location
     *
     * @return Tasklist loaded from the save file
     */
    public static Tasklist load() throws FileNotFoundException {
        Tasklist listOfThings = new Tasklist();
        File saveFile = new File(SAVE_LOCATION);
        Scanner fileReader = new Scanner(saveFile);
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            char taskType = data.charAt(4);
            boolean taskDone = (data.charAt(7) == 'X');
            String taskName = "";


            switch (taskType) {
            case 'T':
                taskName = data.substring(10, data.length());
                Task.Todo newTodo = new Task.Todo(taskName);
                if (taskDone) {
                    newTodo.setDone();
                }
                listOfThings.add(newTodo);
                break;
            case 'D':
                taskName = data.substring(10, data.indexOf("(by:") - 1);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
                String byWhen = data.substring(data.indexOf("by:") + 4, data.length() - 7);
                LocalDate date = LocalDate.parse(byWhen, formatter);
                String byTime = data.substring(data.length() - 6, data.length() - 1);
                LocalTime time = LocalTime.parse(byTime);
                Task.Deadline newDeadline = new Task.Deadline(taskName, date, time);
                if (taskDone) {
                    newDeadline.setDone();
                }
                listOfThings.add(newDeadline);
                break;
            case 'E':
                taskName = data.substring(10, data.indexOf("(from:") - 1);
                String fromWhen = data.substring(data.indexOf("from:") + 6, data.indexOf("to:") - 1);
                String toWhen = data.substring(data.indexOf("to:") + 4, data.length() - 1);
                Task.Event newEvent = new Task.Event(taskName, fromWhen, toWhen);
                if (taskDone) {
                    newEvent.setDone();
                }
                listOfThings.add(newEvent);
                break;
            default:
                break;
            }
        }
        return listOfThings;
    }


    /**
     * Saves the save file to a specified location.
     *
     * @param tasklist Tasklist to be saved
     */
    public static void save(Tasklist tasklist) throws IOException {
        FileWriter fw = new FileWriter(SAVE_LOCATION);
        fw.write("");
        fw.close();
        fw = new FileWriter(SAVE_LOCATION, true); // create a FileWriter in append mode
        for (int i = 0; i < tasklist.size(); i++) {
            fw.write(i + 1 + ". " + tasklist.get(i) + System.lineSeparator());
        }
        fw.close();
    }
}
