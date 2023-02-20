package botanic.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import botanic.exception.BotanicException;
import botanic.parser.Parser;
import botanic.task.Deadline;
import botanic.task.Event;
import botanic.task.Task;
import botanic.task.ToDo;

/**
 * Encapsulates the related fields and behavior of the Storage.
 * This class represents the class that reads and writes to the storage file in the hard disk.
 */
public class Storage {
    private String dirPath;
    private String fileName;
    private String filePath;
    private File myFile;

    /**
     * Instantiates Storage.
     *
     * @param dirPath The path to the directory that the file is stored in.
     * @param fileName The name of the storage file.
     */
    public Storage(String dirPath, String fileName) {
        this.dirPath = dirPath;
        this.fileName = fileName;
        filePath = dirPath + "/" + this.fileName;
    }

    /**
     * Creates directory at the specified directory path if it does not already exist.
     * Creates file at the specified file path if it does not already exist.
     */
    public void createFile() {
        //create directory if it does not already exist
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //create file if it does not already exist
        //Solution adapted from https://www.w3schools.com/java/java_files_create.asp
        try {
            myFile = new File(filePath);
            if (!myFile.exists()) {
                myFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("New storage file cannot be created.");
            e.printStackTrace();
        }
    }

    /**
     * Reads data stored in the hard disk.
     *
     * @return An ArrayList containing existing tasks stored in the storage.
     * @throws BotanicException If file to be read cannot be found.
     */
    public ArrayList<Task> read() throws BotanicException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            createFile();
            //Solution adapted from https://www.w3schools.com/java/java_files_read.asp
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] splitDatas = data.split(" \\| ");
                String taskType = splitDatas[0];
                boolean isDone = splitDatas[1].equals("1");

                if (taskType.equals("T")) {
                    ToDo t = new ToDo(splitDatas[2], isDone);
                    tasks.add(t);
                } else if (taskType.equals("D")) {
                    LocalDate end = Parser.parseDate(splitDatas[3]);
                    Deadline d = new Deadline(splitDatas[2], end, isDone);
                    tasks.add(d);
                } else {
                    LocalDate start = Parser.parseDate(splitDatas[3]);
                    LocalDate end = Parser.parseDate(splitDatas[4]);
                    Event e = new Event(splitDatas[2], start, end, isDone);
                    tasks.add(e);
                }
            }
            sc.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new BotanicException(e + "\nData file not found.");
        }
    }

    /**
     * Writes the given string input to file using given fileWriter.
     *
     * @param fileWriter The fileWriter to use to write input.
     * @param input The input to write into file.
     */
    private void writeUsingFileWriter(FileWriter fileWriter, String input) {
        try {
            fileWriter.write(input + "\n");
        } catch (IOException e) {
            System.out.println("Unable to write to data file.");
            e.printStackTrace();
        }
    }

    /**
     * Updates the storage file in hard disk with the given list of tasks.
     *
     * @param tasks Varargs of tasks.
     */
    public void writeToFile(Task... tasks) {
        try {
            //Solution adapted from https://www.w3schools.com/java/java_files_create.asp
            FileWriter fileWriter = new FileWriter(filePath);
            Arrays.stream(tasks)
                    .map(t -> t.formatForStorage())
                    .forEach(t -> writeUsingFileWriter(fileWriter, t));
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to write to data file.");
            e.printStackTrace();
        }
    }
}
