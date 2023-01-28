package duke.storage;

import duke.task.Task;
import duke.exception.DukeBadInstructionFormatException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    /**
     * The file path of duke.txt.
     */
    private String taskFilePath;

    private ArrayList<Task> tasksFromFile;

    public Storage(String filePath) {
        makeDukeDirectoryAndFile(filePath);
    }

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

    public void makeDukeDirectoryAndFile(String filePath) {
        String[] dirAndFileName = filePath.split("/");
        String dukeDirectory = dirAndFileName[0];
        File directory = new File(dukeDirectory);

        if (!directory.exists()) {
            directory.mkdir();
        }

        this.taskFilePath = dukeDirectory + '/' + dirAndFileName[1];
    }

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
