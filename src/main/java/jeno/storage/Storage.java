package jeno.storage;

import jeno.Jeno;
import jeno.parser.Parser;
import jeno.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for Storage
 */
public class Storage {
    private static String filePath;

    /**
     * Constructor for Storage
     * @param newFilePath File path to task log
     */
    public Storage(String newFilePath) {
        filePath = newFilePath;
    }

    /**
     * Loads tasks from task log file
     * @return An ArrayList of Tasks from task log
     */
    public ArrayList<Task> loadTasksFromTaskLog() {
        File taskLog = new File(filePath);
        Scanner taskLogScanner = null;
        try {
            taskLogScanner = new Scanner(taskLog);
        } catch (FileNotFoundException e) {
            System.out.println("Task log file not found");
        }
        ArrayList<Task> tasks = new ArrayList<>();
        while (taskLogScanner.hasNextLine()) {
            String taskLogLine = taskLogScanner.nextLine();
            tasks.add(Parser.translateTaskLogToTask(taskLogLine));
        }
        return tasks;
    }

    /**
     * Save tasks to task log file
     * @param taskList TaskList to be saved into task log file
     */
    public static void saveTasksToTaskLog(TaskList taskList) {
        try {
            FileWriter taskLog = new FileWriter(Jeno.getTaskLogPath());
            for (Task i : taskList.getTasks()) {
                taskLog.write(i.toLog());
            }
            taskLog.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Delete all tasks in task log.
     */
    public static void deleteAllTasks() {
        try {
            PrintWriter writer = new PrintWriter(Jeno.getTaskLogPath());
            writer.print("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if log file exists. If it does not, create a new log file.
     * @param filePath File path to log file.
     */
    public static void logFileExists(String filePath) {
        File logFile = new File(filePath);
        try {
            if (!logFile.exists()) {
                logFile.getParentFile().mkdir();
                logFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Log file cannot be created");
        }
    }

    /**
     * Loads tasks from task log file
     * @return An ArrayList of Tasks from task log
     */
    public static String loadNotesFromFile() {
        File notes = new File(Jeno.getNotesPath());
        Scanner noteScanner = null;
        try {
            noteScanner = new Scanner(notes);
        } catch (FileNotFoundException e) {
            System.out.println("Notes file not found");
        }
        String note = "";
        while (noteScanner.hasNextLine()) {
            String noteLine = noteScanner.nextLine();
            note += (noteLine + "\n");
        }
        return note;
    }

    /**
     * Save current notes to note file.
     * @param notes Current notes.
     */
    public static void saveNotesToFile(Note notes) {
        try {
            FileWriter noteFile = new FileWriter(Jeno.getNotesPath());
            noteFile.write(notes.getNotes());
            noteFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Checks if note file exists. If it does not, create a new note file.
     * @param filePath File path to note file.
     */
    public static void notesFileExists(String filePath) {
        File notesFile = new File(filePath);
        try {
            if (!notesFile.exists()) {
                notesFile.getParentFile().mkdir();
                notesFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Notes file cannot be created");
        }
    }

    /**
     * Delete all tasks in task log.
     */
    public static void deleteNotes() {
        try {
            PrintWriter writer = new PrintWriter(Jeno.getNotesPath());
            writer.print("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
