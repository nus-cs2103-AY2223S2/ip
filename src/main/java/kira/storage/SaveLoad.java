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

/**
 * SaveLoad handles all the saving of tasklist and loading of tasklist into the bot.
 */
public class SaveLoad {

    /**
     * Saves current tasks list to a CSV file.
     *
     * @throws KiraException File-Output-Issue
     */
    public static void save(List<Task> taskList, String pathname) throws KiraException {
        try {
            File saveFile = new File(pathname);
            FileWriter fileWriter = new FileWriter(saveFile);
            for (Task t : taskList) {
                fileWriter.write(t.saveFormat());
                fileWriter.write("\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new KiraException("Error encountered when saving file :C");
        }
    }

    /**
     * Loads and parses the csv save file into a list of tasks.
     *
     * @param pathname path where the csv file is stored
     * @return a list of tasks
     * @throws KiraException File-Not-Found
     */
    public static List<Task> load(String pathname) throws KiraException {
        List<Task> taskList = new ArrayList<>();
        try {
            File loadFile = new File(pathname);
            Scanner scanner = new Scanner(loadFile);
            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                String[] parsedTasks = task.split("\",\"");
                TaskType taskType = TaskType.valueOf(parsedTasks[0]);
                switch (taskType) {
                case TODO:
                    parseAndAddTodo(taskList, parsedTasks);
                    break;
                case DEADLINE:
                    parseAndAddDeadline(taskList, parsedTasks);
                    break;
                case EVENT:
                    parseAndAddEvent(taskList, parsedTasks);
                    break;
                default:
                    assert false : taskType;
                    // Should never reach here. Programmer error!
                    throw new KiraException("Unexpected Error!");
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            StringBuilder message = new StringBuilder("Error encountered when loading file.");
            message.append("\nIgnore this if it is your first time using!");
            throw new KiraException(message.toString());
        } catch (KiraException e) {
            throw new KiraException("It seems that the save file is corrupted...");
        }
        return taskList;
    }

    private static void parseAndAddEvent(List<Task> taskList, String[] data) throws KiraException {
        if (data.length != 5) {
            throw new KiraException();
        }
        Event event = new Event(data[1], data[3], data[4]);
        if (data[2].equals("y")) {
            event.mark();
        }
        taskList.add(event);
    }

    private static void parseAndAddDeadline(List<Task> taskList, String[] data) throws KiraException {
        if (data.length != 4) {
            throw new KiraException();
        }
        Deadline deadline = new Deadline(data[1], data[3]);
        if (data[2].equals("y")) {
            deadline.mark();
        }
        taskList.add(deadline);
    }

    private static void parseAndAddTodo(List<Task> taskList, String[] data) throws KiraException {
        if (data.length != 3) {
            throw new KiraException();
        }
        ToDo todo = new ToDo(data[1]);
        if (data[2].equals("y")) {
            todo.mark();
        }
        taskList.add(todo);
    }

}
