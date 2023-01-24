package duke.storage;

import duke.exception.DukeException;
import duke.datetime.DateTime;
import duke.task.*;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;
import java.util.Scanner;

/**
 * Responsible for file-related operations, such as reading and writing of the data file when dealing with loading
 * and saving of task list.
 */
public class Storage {
    /**
     * Absolute file path where the folder containing the data file is stored in.
     */
    private String data_file_folder_path;

    /** Absolute file path where the data file should be stored in. **/
    private String data_file_path;

    /**
     * Constructs a Storage instance.
     *
     * @param folderName Name of the folder to be created to store data file.
     * @param fileName Name of the data file to be created.
     */
    public Storage(String folderName, String fileName) {
        data_file_folder_path = Paths.get(System.getProperty("user.dir"), folderName).toString();
        data_file_path = Paths.get(System.getProperty("user.dir"), folderName, fileName).toString();
    }

    /**
     * Prepares the file for storage of task list data.
     */
    public boolean prepareFile() {
        //Make the directory
        File directory = new File(data_file_folder_path);
        if (! directory.exists()) {
            boolean makeDirectoryStatus = directory.mkdir();
            if (! makeDirectoryStatus) {
                Ui.printStraightLine();
                System.out.println("Folder cannot be created.");
                Ui.printStraightLine();
                return false;
            }
        }

        //Create the file
        File dataFile = new File(data_file_path);
        try {
            dataFile.createNewFile();
        } catch (IOException ioException) {
            Ui.printStraightLine();
            System.out.println("File cannot be created.");
            System.out.println("The following error occurred: ");
            System.out.println(ioException.getMessage());
            Ui.printStraightLine();
            return false;
        }
        return true;
    }

    /**
     * Reads and loads task history from the data file.
     *
     * @param tasks TaskList to store the tasks read from the data file.
     */
    public boolean loadTasksFromFile(TaskList tasks) {
        try {
            File dataFile = new File(data_file_path);
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
                        Ui.printStraightLine();
                        System.out.println("Dates stored in the data file are corrupted. Cannot read.");
                        Ui.printStraightLine();
                        return false;
                    }
                case "E":
                    try {
                        //Create new event task
                        Temporal start = DateTime.getDateTimeObject(currentTaskArray[3]);
                        Temporal end = DateTime.getDateTimeObject(currentTaskArray[4]);
                        if (! DateTime.isValidDuration(start, end)) {
                            throw new DukeException("Start date must be before end date.");
                        }
                        Task eventTask = new Event(currentTaskArray[2], currentTaskArray[3], currentTaskArray[4],
                                start, end);
                        if (currentTaskArray[1].equals("1")) {
                            eventTask.setDoneStatus();
                        }
                        tasks.addTask(eventTask);
                        break;
                    } catch (DukeException dukeException) {
                        Ui.printStraightLine();
                        System.out.println(dukeException.getMessage());
                        Ui.printStraightLine();
                        return false;
                    } catch (DateTimeParseException e) {
                        Ui.printStraightLine();
                        System.out.println("Dates stored are corrupted. Cannot read from data file.");
                        System.out.println("Create new data file from scratch...");
                        Ui.printStraightLine();
                        return false;
                    }
                }
            }
            s.close();
            return true;
        } catch (FileNotFoundException fileNotFoundException) {
            Ui.printStraightLine();
            System.out.println("Cannot read from data file.");
            System.out.println("Create new data file from scratch...");
            Ui.printStraightLine();
            return false;
        }
    }


    /**
     * Saves the tasks stored in the list into the data file.
     *
     * @param tasks TaskList that contains the tasks to be saved.
     * @return true if save is successful, else false.
     */
    public boolean saveTasks(TaskList tasks) {
        try {
            File dataFile = new File(data_file_path);
            FileWriter fw = new FileWriter(data_file_path);
            //Reset content of file
            fw.write("");
            fw.flush();
            fw = new FileWriter(data_file_path, true);
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
            Ui.printStraightLine();
            System.out.println("Could not save the tasks locally. The following error occurred: ");
            System.out.println(e.getMessage());
            Ui.printStraightLine();
            return false;
        }
    }





}
