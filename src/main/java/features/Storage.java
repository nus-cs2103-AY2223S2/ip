package features;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

/**
 * Saves and loads state of taskList to/from data/duke.txt.
 */
public class Storage {

    /**
     * Loads taskList from data/duke.txt, if any.
     * @return the loaded taskList.
     */
    public TaskList loadTaskList() {

        TaskList newTaskList = new TaskList();
        File dukeDirectory = new File("data");
        File dukeSavePath = new File("data/duke.txt");
        checkSaveFile(dukeDirectory, dukeSavePath);
        try {
            Scanner fileScan = new Scanner(dukeSavePath);
            while (fileScan.hasNextLine()) {
                String fileLine = fileScan.nextLine();
                String[] evalArray = fileLine.split("=");
                assert (evalArray[0].length() == 1);
                switch (evalArray[0]) {
                case ("T"):
                    ToDo newToDo = new ToDo(evalArray[2]);
                    if (evalArray[1].equals("X")) {
                        newToDo.markDone();
                    }
                    newTaskList.add(newToDo);
                    break;
                case ("D"):
                    Deadline newDeadline = new Deadline(evalArray[2], evalArray[3]);
                    if (evalArray[1].equals("X")) {
                        newDeadline.markDone();
                    }
                    newTaskList.add(newDeadline);
                    break;
                case("E"):
                    Event newEvent = new Event(evalArray[2], evalArray[3], evalArray[4]);
                    if (evalArray[1].equals("X")) {
                        newEvent.markDone();
                    }
                    newTaskList.add(newEvent);
                    break;
                default:
                }
            }
        } catch (FileNotFoundException err) {
            return newTaskList;
        }
        return newTaskList;
    }

    /**
     * Saves taskList to data/duke.txt
     * @param toSave The TaskList object to save.
     * @throws IOException  If TaskList cannot be saved.
     */
    public void saveTaskList(TaskList toSave) throws IOException {
        FileWriter dukeWriter = new FileWriter("data/duke.txt");
        for (int i = 0; i < toSave.size(); i++) {
            assert (!toSave.get(i).toSaveString().equals(""));
            dukeWriter.write(toSave.get(i).toSaveString() + "\n");
        }
        dukeWriter.close();
    }

    /**
     * Checks if data/duke.txt exists, and creates it otherwise.
     * @param fileDirectory The directory name of the save file.
     * @param fileName The path to the save file.
     */
    public void checkSaveFile(File fileDirectory, File fileName) {
        try {
            if (!fileDirectory.exists()) {
                if (fileDirectory.mkdirs(
                )) {
                    System.out.println("No save files detected. Creating new save file...");
                }
                if (!fileName.exists()) {
                    if (fileName.createNewFile()) {
                        System.out.println("New save file created!");
                    }
                }
            }
        } catch (IOException err) {
            System.out.println("Sorry, could not save that.");
        }
    }
}
