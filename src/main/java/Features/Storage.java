package Features;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.ToDo;

/**
 * Saves and loads state of taskList to/from data/duke.txt.
 */
public class Storage {

    /**
     * Loads taskList from data/duke.txt, if any.
     * @return the loaded taskList.
     */
    public TaskList loadTaskList() {

        TaskList toLoad = new TaskList();
        File dukePath = new File("data");
        File dukeSave = new File("data/duke.txt");

        try {
            if (!dukePath.exists()) {
                if (dukePath.mkdirs()) {
                    new Ui().print("No save files detected. Creating new save file...");
                }
                if (!dukeSave.exists()) {
                    if (dukeSave.createNewFile()) {
                        new Ui().print("New save file created!");
                    }
                }
            }
        } catch (IOException err) {
            new Ui().print("Sorry, could not save that.");
        }

        try {
            Scanner fileScan = new Scanner(dukeSave);
            while (fileScan.hasNextLine()) {
                String toEval = fileScan.nextLine();
                String[] evalArray = toEval.split("=");
                switch (evalArray[0]) {
                case ("T"):
                    ToDo addToDo = new ToDo(evalArray[2]);
                    if (evalArray[1].equals("X")) {
                        addToDo.markDone();
                    }
                    toLoad.add(addToDo);
                    break;
                case ("D"):
                    Deadline addDeadline = new Deadline(evalArray[2], evalArray[3]);
                    if (evalArray[1].equals("X")) {
                        addDeadline.markDone();
                    }
                    toLoad.add(addDeadline);
                    break;
                case("E"):
                    Event addEvent = new Event(evalArray[2], evalArray[3], evalArray[4]);
                    if (evalArray[1].equals("X")) {
                        addEvent.markDone();
                    }
                    toLoad.add(addEvent);
                    break;
                default:
                }
            }
        } catch (FileNotFoundException err) {
            return toLoad;
        }
        return toLoad;
    }

    /**
     * Saves taskList to data/duke.txt
     * @param toSave The TaskList object to save.
     * @throws IOException  If TaskList cannot be saved.
     */
    public void saveTaskList(TaskList toSave) throws IOException {
        FileWriter dukeWrite = new FileWriter("data/duke.txt");
        for (int i = 0; i < toSave.size(); i++) {
            dukeWrite.write(toSave.get(i).toSaveString() + "\n");
        }
        dukeWrite.close();
    }

}
