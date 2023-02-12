package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Storage is a class that finds, loads and saves data into the file data/duke.txt
 */
public class Storage {
    private File file;
    private boolean hasFileData;
    private TasksList list;

    /**
     * Returns a storage object that contains a list of Tasks objects
     * @param list ArrayList of Tasks Objects
     */
    public Storage(TasksList list) {
        this.list = list;
        Path path = Paths.get("data/duke.txt");
        this.hasFileData = java.nio.file.Files.exists(path);
        this.file = path.toFile();
    }

    /**
     * finds the past data from data/duke.txt file throws the
     * PastDataDoesNotExistException exception if the file does
     * not exists
     */
    public void findData() {
        try {
            DukeExceptions.checkPastData(hasFileData);
        } catch (PastDataDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads the data of the past data into the arraylist of storage
     */
    public void loadData() {
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNextLine()) {
                String[] input = sc.nextLine().split(" \\| ");
                Tasks task;
                if (input[0].equals("T")) {
                    task = new Todo(input[2]);
                } else if (input[0].equals("D")) {
                    String dateTime = input[3];
                    String newDateTime = dateTimeFormat(dateTime);
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime formattedDeadline = LocalDateTime.parse(newDateTime, dateTimeFormatter);
                    task = new Deadline(input[2], formattedDeadline);
                } else {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    String[] split = input[3].split(" - ");
                    String from = dateTimeFormat(split[0]);
                    String by = dateTimeFormat(split[1]);
                    LocalDateTime formattedstartTime = LocalDateTime.parse(from, dateTimeFormatter);
                    LocalDateTime formattedendTime = LocalDateTime.parse(by, dateTimeFormatter);
                    task = new Event(input[2], formattedstartTime, formattedendTime);
                }
                if (input[1].equals("1")) {
                    task.markAsDone();
                }
                list.addTask(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * Saves the data into data/duke.txt when ther bye command is executed
     */
    public void saveData() {
        try {
            FileWriter writer = new FileWriter("data/duke.txt");
            for (int i = 0; i < list.getSize(); i++) {
                String line = list.getTask(i).log();
                writer.write(line);

            }
            writer.close();
        } catch (IOException e) {
            System.out.println("☹ OOPS!!! I cannot write to the file!");
        }
    }

    /**
     * Returns a string of date and time of a certain format
     * @param datetime String of date and time
     * @return formatted string of date and time
     */
    private String dateTimeFormat(String datetime) {
        String year = datetime.substring(0, 4);
        String month = datetime.substring(5, 7);
        String day = datetime.substring(8, 10);
        String hour = datetime.substring(11, 13);
        String min = datetime.substring(14, 16);
        String newDateTime = day + "/" + month + "/" + year + " " + hour + min;
        return newDateTime;
    }
}
