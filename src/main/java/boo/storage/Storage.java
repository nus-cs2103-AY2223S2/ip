package boo.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;
import java.util.Scanner;

import boo.datetime.DateTime;
import boo.exception.BooException;
import boo.task.Deadline;
import boo.task.Event;
import boo.task.Task;
import boo.task.ToDo;
import boo.tasklist.TaskList;

/**
 * Responsible for file-related operations, such as reading and writing of the data file when dealing with loading
 * and saving of task list.
 */
public class Storage {
    //CHECKSTYLE.OFF: Checker
    /** Absolute file path where the folder containing the data file is stored in. */
    private String dataFileFolderPath;

    /** Absolute file path where the data file should be stored in. **/
    private String dataFilePath;
    //CHECKSTYLE.ON: Checker

    /**
     * Constructs a {@code Storage} instance.
     *
     * @param folderName Name of the folder to be created to store data file.
     * @param fileName Name of the data file to be created.
     */
    public Storage(String folderName, String fileName) {
        dataFileFolderPath = Paths.get(System.getProperty("user.dir"), folderName).toString();
        dataFilePath = Paths.get(System.getProperty("user.dir"), folderName, fileName).toString();
    }

    /**
     * Prepares the file for storage of task list data.
     *
     * @return true if file was created successfully, else return false.
     */
    public boolean prepareFile() {
        //Make the directory
        File directory = new File(dataFileFolderPath);
        if (!directory.exists()) {
            boolean makeDirectoryStatus = directory.mkdir();
            if (!makeDirectoryStatus) {
                System.out.println("Folder cannot be created.");
                return false;
            }
        }
        //Create the file
        File dataFile = new File(dataFilePath);
        try {
            dataFile.createNewFile();
        } catch (IOException ioException) {
            System.out.println("File cannot be created.");
            System.out.println("The following error occurred: ");
            System.out.println(ioException.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Reads and loads task history from the data file.
     *
     * @param tasks {@code TaskList} to store the tasks read from the data file.
     * @return true if loading was successful, else return false.
     */
    public boolean loadTasksFromFile(TaskList tasks) {
        try {
            File dataFile = new File(dataFilePath);
            Scanner s = new Scanner(dataFile);

            //Iterate through line by line
            while (s.hasNext()) {
                String currentTask = s.nextLine();
                String[] currentTaskArray = currentTask.split(" \\| ");
                String commandType = currentTaskArray[0];

                //Convert line into task
                switch (commandType) {
                case "T":
                    Task toDoTask = new ToDo(currentTaskArray[2]);
                    if (currentTaskArray[1].equals("1")) {
                        toDoTask.setDoneStatus();
                    }
                    tasks.addTask(toDoTask);
                    break;
                case "D":
                    try {
                        //Checks if the deadline is a valid date and time
                        Task deadlineTask = new Deadline(currentTaskArray[2], currentTaskArray[3],
                                DateTime.getDateTimeObject(currentTaskArray[3]));
                        if (currentTaskArray[1].equals("1")) {
                            deadlineTask.setDoneStatus();
                        }
                        tasks.addTask(deadlineTask);
                        break;
                    } catch (DateTimeParseException e) {
                        System.out.println("Dates stored in the data file are corrupted. Cannot read.");
                        return false;
                    }
                case "E":
                    try {
                        //Create new event task
                        Temporal start = DateTime.getDateTimeObject(currentTaskArray[3]);
                        Temporal end = DateTime.getDateTimeObject(currentTaskArray[4]);
                        if (!DateTime.isValidDuration(start, end)) {
                            throw new BooException("Start date must be before end date.");
                        }
                        Task eventTask = new Event(currentTaskArray[2], currentTaskArray[3], currentTaskArray[4],
                                start, end);
                        if (currentTaskArray[1].equals("1")) {
                            eventTask.setDoneStatus();
                        }
                        tasks.addTask(eventTask);
                        break;
                    } catch (BooException booException) {
                        System.out.println(booException.getMessage());
                        return false;
                    } catch (DateTimeParseException e) {
                        System.out.println("Dates stored are corrupted. Cannot read from data file.");
                        System.out.println("Create new data file from scratch...");
                        return false;
                    }
                default:
                    //Should never reach here because data file should only be to-do, deadline or event. Hence, if
                    //reach here, means data file was tempered
                    return false;
                }
            }
            s.close();
            return true;
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Cannot read from data file.");
            System.out.println("Create new data file from scratch...");
            return false;
        }
    }

    /**
     * Saves the tasks stored in the list into the data file.
     *
     * @param tasks {@code TaskList} that contains the tasks to be saved.
     * @return true if save is successful, else false.
     */
    public boolean saveTasks(TaskList tasks) {
        try {
            new File(dataFilePath);
            FileWriter fw = new FileWriter(dataFilePath);
            //Reset content of file
            fw.write("");
            fw.flush();
            fw = new FileWriter(dataFilePath, true);



            //Append new content into file
            for (int i = 0; i < tasks.getSizeOfTaskList(); i = i + 1) {
                Task currentTask = tasks.getTask(i);
                String taskStatus = (currentTask.getStatusOfTask())
                        ? "1 | "
                        : "0 | ";
                String lineToAdd = (currentTask instanceof ToDo)
                        ? "T | " + taskStatus + currentTask.getNameOfTask()
                        : (currentTask instanceof Deadline)
                        ? "D | " + taskStatus + currentTask.getNameOfTask() + " | "
                        + ((Deadline) currentTask).getRawDeadline()
                        : "E | " + taskStatus + currentTask.getNameOfTask() + " | "
                        + ((Event) currentTask).getRawStartDate() + " | "
                        + ((Event) currentTask).getRawEndDate();
                if (i != tasks.getSizeOfTaskList() - 1) {
                    lineToAdd += "\n";
                }
                fw.write(lineToAdd);
            }
            fw.flush();
            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println("Could not save the tasks locally. The following error occurred: ");
            System.out.println(e.getMessage());
            return false;
        }
    }
}
