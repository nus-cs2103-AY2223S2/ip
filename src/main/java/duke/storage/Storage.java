package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeBadInstructionFormatException;
import duke.task.Task;

/**
 * Encapsulates the 'hard disc' implementation <code>Duke</code>.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class Storage {
    /**
     * Symbol to split fields of task in txt file.
     */
    public static final String SPLITTER = "@";
    /**
     * The file path of duke.txt.
     */
    private String taskFilePath;

    /**
     * Constructor for an instance of <code>Storage</code>.
     * @param filePath The filepath to tasks.txt.
     */
    public Storage(String filePath) {
        makeDukeDirectoryAndFile(filePath);
    }
    /**
     * Appends a <code>Task</code> to the end of tasks.txt.
     * @param t The <code>Task</code> to be appended.
     */
    public void fileAppend(Task t) {
        try {
            String textToAdd = t.getFileFormatString() + System.lineSeparator();
            FileWriter fw = new FileWriter(this.taskFilePath, true);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("Could not append to file");
        }
    }
    /**
     * Marks a <code>Task</code> in tasks.txt.
     * @param index The index of the <code>Task</code> to be marked.
     * @throws DukeBadInstructionFormatException If the format of the user input is wrong.
     */
    public void fileMarkTask(int index) throws DukeBadInstructionFormatException {
        int i = 0;

        try {
            Scanner scanner = new Scanner(new File(this.taskFilePath));
            StringBuffer buffer = new StringBuffer();

            while (scanner.hasNext()) {
                if (i != index) {
                    buffer.append(scanner.nextLine() + System.lineSeparator());
                } else {
                    String tmp = scanner.nextLine();
                    Task oldTask = Task.getTaskFromFileFormat(tmp);
                    oldTask.markAsDone();
                    buffer.append(oldTask.getFileFormatString() + System.lineSeparator());
                }
                i++;
            }

            String newFileContents = buffer.toString();
            scanner.close();


            FileWriter fw = new FileWriter(this.taskFilePath, false);
            fw.write(newFileContents);
            fw.close();
        } catch (IOException e) {
            System.out.println("Could not edit file");
        }

    }
    /**
     * Unmarks a <code>Task</code> in tasks.txt.
     * @param index The index of the <code>Task</code> to be unmarked.
     * @throws DukeBadInstructionFormatException If the format of the user input is wrong.
     */
    public void fileUnmarkTask(int index) throws DukeBadInstructionFormatException {
        int i = 0;

        try {
            Scanner scanner = new Scanner(new File(this.taskFilePath));
            StringBuffer buffer = new StringBuffer();

            while (scanner.hasNext()) {
                if (i != index) {
                    buffer.append(scanner.nextLine() + System.lineSeparator());
                } else {
                    String tmp = scanner.nextLine();
                    Task oldTask = Task.getTaskFromFileFormat(tmp);
                    oldTask.markAsNotDone();
                    buffer.append(oldTask.getFileFormatString() + System.lineSeparator());
                }
                i++;
            }

            String newFileContents = buffer.toString();
            scanner.close();


            FileWriter fw = new FileWriter(this.taskFilePath, false);
            fw.write(newFileContents);
            fw.close();
        } catch (IOException e) {
            System.out.println("Could not edit file");
        }

    }
    /**
     * Deletes a <code>Task</code> from tasks.txt.
     * @param index The index of the <code>Task</code> to be deleted.
     */
    public void fileDeleteTask(int index) {
        int i = 0;

        try {
            Scanner scanner = new Scanner(new File(this.taskFilePath));
            StringBuffer buffer = new StringBuffer();

            while (scanner.hasNext()) {
                if (i != index) {
                    buffer.append(scanner.nextLine() + System.lineSeparator());
                } else {
                    scanner.nextLine();
                }
                i++;
            }

            String newFileContents = buffer.toString();
            scanner.close();


            FileWriter fw = new FileWriter(this.taskFilePath, false);
            fw.write(newFileContents);
            fw.close();
        } catch (IOException e) {
            System.out.println("Could not edit file");
        }

    }

    /**
     * Translate the <code>Task</code>s from task.txt to an <code>ArrayList</code>
     * of <code>Task</code>s.
     * @return The <code>ArrayList</code> of <code>Task</code>s.
     * @throws DukeBadInstructionFormatException If the tasks stored were in the wrong format.
     */
    public ArrayList<Task> load()
            throws DukeBadInstructionFormatException {
        try {
            File taskFile = new File(this.taskFilePath);
            Scanner scanner = new Scanner(taskFile);
            ArrayList<Task> returnList = new ArrayList<>();

            while (scanner.hasNext()) {
                String taskInFileFormat = scanner.nextLine();
                Task curTask = Task.getTaskFromFileFormat(taskInFileFormat);
                returnList.add(curTask);
            }
            System.out.println("Existing tasks found. Loaded existing tasks.");
            return returnList;
        } catch (FileNotFoundException e) {
            System.out.println("No previous tasks found. Initialising fresh start.");
            ArrayList<Task> newList = new ArrayList<>();
            return newList;
        }

    }

    /**
     * Creates /filepath/tasks.txt if it doesn't yet exist.
     * @param filePath The file path to tasks.txt.
     */
    public void makeDukeDirectoryAndFile(String filePath) {
        String[] dirAndFileName = filePath.split("/");
        String dukeDirectory = dirAndFileName[0];
        File directory = new File(dukeDirectory);

        if (!directory.exists()) {
            directory.mkdir();
        }

        this.taskFilePath = dukeDirectory + '/' + dirAndFileName[1];
    }

    /**
     * Removes all <code>Task</code>s from tasks.txt.
     */
    public void clearAllTasks() {
        try {
            PrintWriter writer = new PrintWriter(this.taskFilePath);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Tried to clear storage when it doesn't exist.");
        }
    }
}
