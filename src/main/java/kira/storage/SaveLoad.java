package kira.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kira.exception.KiraException;
import kira.task.Deadline;
import kira.task.Event;
import kira.task.Task;
import kira.task.TaskType;
import kira.task.ToDo;

public class SaveLoad {
    
    /**
     * Saves current tasks list to a CSV file.
     * 
     * @throws KiraException File-Output-Issue
     */
    public static void save(List<Task> taskList, String pathname) throws KiraException {
        try {
            File saveFile = new File(pathname);
            FileWriter fw = new FileWriter(saveFile);
            for (Task t : taskList) {
                fw.write(t.saveFormat());
                fw.write("\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new KiraException("Error encountered when saving file :C");
        }
    }

    /**
     * Loads and parses the csv save file into a list of tasks.
     * 
     * @param pathname path where the csv file is stored.
     * @return a list of tasks
     * @throws KiraException File cannot be found.
     */
    public static List<Task> load(String pathname) throws KiraException {
        List<Task> taskList = new ArrayList<>();
        try {
            File loadFile = new File(pathname);
            Scanner sc = new Scanner(loadFile);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] parsed = task.split("\",\"");
                switch (TaskType.valueOf(parsed[0])) {
                case TODO:
                    ToDo tdo = new ToDo(parsed[1]);
                    if (parsed[2].equals("y")) {
                        tdo.mark();
                    }
                    taskList.add(tdo);
                    break;
                case DEADLINE:
                    Deadline deadline = new Deadline(parsed[1], parsed[3]);
                    if (parsed[2].equals("y")) {
                        deadline.mark();
                    }
                    taskList.add(deadline);
                    break;
                case EVENT:
                    Event evt = new Event(parsed[1], parsed[3], parsed[4]);
                    if (parsed[2].equals("y")) {
                        evt.mark();
                    }
                    taskList.add(evt);
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            StringBuilder msg = new StringBuilder("Error encountered when loading file.");
            msg.append("\nIgnore this if it is your first time using!");
            throw new KiraException(msg.toString());
        }
        return taskList;
    }

}
