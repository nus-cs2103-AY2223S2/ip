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
    private static final int INDEX_OF_TYPE = 4;
    private static final int INDEX_OF_DONE = 7;

    /**
     * Loads the save file from a fixed location
     *
     * @return Tasklist loaded from the save file
     */
    public static Tasklist load() throws FileNotFoundException {
        Tasklist listOfThings = new Tasklist();
        File saveFile = new File(SAVE_LOCATION);
        assert saveFile.exists() : "File does not exist!";
        Scanner fileReader = new Scanner(saveFile);
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            char taskType = data.charAt(INDEX_OF_TYPE);
            switch (taskType) {
            case 'T':
                listOfThings.add(loadTodo(data));
                break;
            case 'D':
                listOfThings.add(loadDeadline(data));
                break;
            case 'E':
                listOfThings.add(loadEvent(data));
                break;
            default:
                break;
            }
        }
        return listOfThings;
    }


    private static Task loadTodo(String saveString) {
        String taskName = saveString.substring(10, saveString.length());
        boolean taskDone = (saveString.charAt(INDEX_OF_DONE) == 'X');
        Task.Todo newTodo = new Task.Todo(taskName);
        if (taskDone) {
            newTodo.setDone();
        }
        return newTodo;
    }

    private static Task loadDeadline(String saveString) {
        boolean taskDone = saveString.charAt(INDEX_OF_DONE) == 'X';
        String taskName = saveString.substring(10, saveString.indexOf("(by:") - 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        String byWhen = saveString.substring(saveString.indexOf("by:") + 4, saveString.length() - 7);
        LocalDate date = LocalDate.parse(byWhen, formatter);
        String byTime = saveString.substring(saveString.length() - 6, saveString.length() - 1);
        LocalTime time = LocalTime.parse(byTime);
        Task.Deadline newDeadline = new Task.Deadline(taskName, date, time);
        if (taskDone) {
            newDeadline.setDone();
        }
        return newDeadline;
    }

    private static Task loadEvent(String saveString) {
        boolean taskDone = saveString.charAt(INDEX_OF_DONE) == 'X';
        String taskName = saveString.substring(10, saveString.indexOf("(from:") - 1);
        String fromWhen = saveString.substring(saveString.indexOf("from:") + 6, saveString.indexOf("to:") - 1);
        String toWhen = saveString.substring(saveString.indexOf("to:") + 4, saveString.length() - 1);
        Task.Event newEvent = new Task.Event(taskName, fromWhen, toWhen);
        if (taskDone) {
            newEvent.setDone();
        }
        return newEvent;
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
