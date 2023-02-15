package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Encapsulates the related fields and behavior of the Storage.
 * Represents the class that reads and writes to the storage file in the hard disk.
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
        this.filePath = dirPath + "/" + this.fileName;
    }

    /**
     * Creates file at the specified file path if it does not already exist.
     */
    public void createFile() {
        File dir = new File(this.dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        //create file if it does not already exist
        try {
            myFile = new File(this.filePath);
            if (!myFile.exists()) {
                myFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e + "\nNew storage file cannot be created.");
            e.printStackTrace();
        }
    }

    /**
     * Reads data stored in the hard disk.
     *
     * @return An ArrayList containing existing tasks stored in the storage.
     * @throws DukeException if file to be read cannot be found.
     */
    public ArrayList<Task> read() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            this.createFile();
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] dataArr = data.split(" \\| ");
                String type = dataArr[0];
                boolean isDone = dataArr[1].equals("1");

                if (type.equals("T")) {
                    ToDo t = new ToDo(dataArr[2], isDone);
                    tasks.add(t);
                } else if (type.equals("D")) {
                    LocalDate end = Parser.parseDate(dataArr[3]);
                    Deadline d = new Deadline(dataArr[2], end, isDone);
                    tasks.add(d);
                } else {
                    LocalDate start = Parser.parseDate(dataArr[3]);
                    LocalDate end = Parser.parseDate(dataArr[4]);
                    Event e = new Event(dataArr[2], start, end, isDone);
                    tasks.add(e);
                }
            }
            sc.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException(e + "\nData file not found.");
        }
    }

    /**
     * Writes the given string input to file using given fileWriter.
     *
     * @param fileWriter The fileWriter to use to write input.
     * @param input The input to write into file
     */
    private void writeUsingFileWriter(FileWriter fileWriter, String input) {
        try {
            fileWriter.write(input + "\n");
        } catch (IOException e) {
            System.out.println(e + "\nUnable to write to data file.");
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
            FileWriter fileWriter = new FileWriter(this.filePath);
            Arrays.stream(tasks)
                    .map(t -> t.formatForStorage())
                    .forEach(t -> writeUsingFileWriter(fileWriter, t));
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e + "\nUnable to write to data file.");
            e.printStackTrace();
        }
    }
}
