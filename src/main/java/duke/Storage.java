package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Storage class encapsulates
 * loading and saving operations.
 */
public class Storage {
    private static final String SAVE_DATA_LOCATION = "savedata.txt";
    private static final int INDEX_OF_TYPE = 3;
    private static final int INDEX_OF_DONE = 6;
    private static final int INDEX_OF_DESCRIPTION = 9;

    /**
     * Loads the save file from a fixed location
     *
     * @return Tasklist loaded from the save file
     */
    public static Tasklist load(int saveNo) throws FileNotFoundException {
        String saveLocation = "duke" + saveNo + ".txt";
        Tasklist listOfThings = new Tasklist();
        File saveFile = new File(saveLocation);
        assert saveFile.exists() : "File does not exist!";
        Scanner fileReader = new Scanner(saveFile);
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            int firstDot = data.indexOf(".");
            char taskType = data.charAt(firstDot + INDEX_OF_TYPE);
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

    public static int getCurrentSaveNumber() throws FileNotFoundException {
        File saveDataFile = new File(SAVE_DATA_LOCATION);
        Scanner saveDataReader = new Scanner(saveDataFile);
        return Integer.parseInt(saveDataReader.nextLine());
    }

    public static SaveList readSaveData() throws FileNotFoundException {
        SaveList saveList = new SaveList();
        File saveDataFile = new File(SAVE_DATA_LOCATION);
        Scanner saveDataReader = new Scanner(saveDataFile);
        saveDataReader.nextLine();

        while (saveDataReader.hasNextLine()) {
            String saveDataLine = saveDataReader.nextLine();
            String[] splitted = saveDataLine.split(". ", 2);
            Save save = new Save(Integer.parseInt(splitted[0]), splitted[1]);
            saveList.addSave(Integer.parseInt(splitted[0]), save);
        }
        return saveList;
    }

    public static void writeSaveData(SaveList saveList, int saveNo) throws IOException {
        FileWriter saveDataWriter = new FileWriter(SAVE_DATA_LOCATION);
        String saveDataString = saveNo + "\n";
        saveDataString += saveList;
        saveDataWriter.write(saveDataString);
        saveDataWriter.close();
    }

    private static Task loadTodo(String saveString) {
        int firstDot = saveString.indexOf(".");
        String taskName = saveString.substring(firstDot + INDEX_OF_DESCRIPTION, saveString.length());
        boolean taskDone = (saveString.charAt(firstDot + INDEX_OF_DONE) == 'X');
        Task.Todo newTodo = new Task.Todo(taskName);
        if (taskDone) {
            newTodo.setDone();
        }
        return newTodo;
    }

    private static Task loadDeadline(String saveString) {
        int firstDot = saveString.indexOf(".");
        boolean taskDone = saveString.charAt(firstDot + INDEX_OF_DONE) == 'X';
        String taskName = saveString.substring(firstDot + INDEX_OF_DESCRIPTION, saveString.indexOf("(by:") - 1);
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
        int firstDot = saveString.indexOf(".");
        boolean taskDone = saveString.charAt(firstDot + INDEX_OF_DONE) == 'X';
        String taskName = saveString.substring(firstDot + INDEX_OF_DESCRIPTION, saveString.indexOf("(from:") - 1);
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
     * @param saveNo Slot to be saved at
     * @param saveInfo Short message to describe the save
     */
    public static void save(Tasklist tasklist, int saveNo, String saveInfo) throws IOException {
        String saveLocation = "duke" + saveNo + ".txt";
        File saveFile = new File(saveLocation);
        saveFile.createNewFile();
        FileWriter saveWriter = new FileWriter(saveLocation);
        Save save = new Save(saveNo, saveInfo);
        SaveList saveList = readSaveData();
        saveList.addSave(saveNo, save);
        writeSaveData(saveList, saveNo);
        saveWriter.write("");
        saveWriter.close();
        saveWriter = new FileWriter(saveLocation, true); // create a FileWriter in append mode
        for (int i = 0; i < tasklist.size(); i++) {
            saveWriter.write(i + 1 + ". " + tasklist.get(i) + System.lineSeparator());
        }
        saveWriter.close();
    }

    public static void save(Tasklist tasklist, int saveNo) throws IOException {
        String saveLocation = "duke" + saveNo + ".txt";
        FileWriter saveWriter = new FileWriter(saveLocation);
        saveWriter.write("");
        saveWriter.close();
        saveWriter = new FileWriter(saveLocation, true); // create a FileWriter in append mode
        for (int i = 0; i < tasklist.size(); i++) {
            saveWriter.write(i + 1 + ". " + tasklist.get(i) + System.lineSeparator());
        }
        saveWriter.close();
    }
}