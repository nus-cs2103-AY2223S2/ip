package duke.storage;

import duke.parser.Parser;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String dirPath;
    private String fileName;
    private String filePath;
    private File myFile;

    /**
     * Constructor for Storage.
     * @param dirPath The path to the directory the file is stored in.
     * @param fileName The path to storage file.
     */
    public Storage(String dirPath, String fileName) {
        this.dirPath = dirPath;
        this.fileName = fileName;
        this.filePath = dirPath + "/" + this.fileName;
    }

    /**
     * Creates file at the specified file path if it
     * does not already exist.
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
        }
        catch (IOException e) {
            System.out.println(e + "\nNew storage file cannot be created.");
            e.printStackTrace();
        }
    }

    /**
     * Reads data stored in hard disk.
     * @return An ArrayList containing tasks stored in the storage.
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
        }
        catch (FileNotFoundException e) {
            throw new DukeException(e + "\nData file not found.");
        }
    }

    /**
     * Updates the storage file in hard disk with the given list of tasks.
     * @param tasks The list of tasks.
     */
    public void write(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            int size = tasks.size();
            for (int i = 0; i < size; i++) {
                Task t = tasks.get(i);
                fw.write(t.formatStore());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e + "\nUnable to write to data file.");
            e.printStackTrace();
        }
    }
}
