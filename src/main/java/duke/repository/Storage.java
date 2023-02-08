package duke.repository;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private final String ERROR_MSG = "Unsuccessful... Something went wrong... :(";
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        String[] splitPath = filePath.split("/");
        if (splitPath.length != 1) {
            String directoryPath = splitPath[0];
            File dataDirectory = new File(directoryPath);
            if (!dataDirectory.exists()) {
                dataDirectory.mkdir();
            }
        }
        File dukeFile = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        if (!dukeFile.exists()) {
            try {
                dukeFile.createNewFile();
            } catch (IOException io) {
                throw new DukeException(io.getMessage());
            }
        }
        try {
            Scanner scan = new Scanner(dukeFile);
            while (scan.hasNext()) {
                String[] oneTaskString = scan.nextLine().split(" \\| ");
                Task tempTask = null;
                if (oneTaskString[0].equals("T")) {
                    tempTask = new Todo(oneTaskString[2]);
                } else if (oneTaskString[0].equals("D")) {
                    LocalDateTime tempDateTime = LocalDateTime.parse(oneTaskString[3].replace("/", "-"));
                    tempTask = new Deadline(oneTaskString[2], tempDateTime);
                } else if (oneTaskString[0].equals("E")) {
                    String[] dateSplit = oneTaskString[3].split(">");
                    LocalDateTime startDateTime = LocalDateTime.parse(dateSplit[0]);
                    LocalDateTime endDateTime = LocalDateTime.parse(dateSplit[1]);
                    tempTask = new Event(oneTaskString[2], startDateTime, endDateTime);
                }
                if (tempTask != null) {
                    if (oneTaskString[1].equals("1")) {
                        tempTask.markTask();
                    }
                    taskList.add(tempTask);
                }

            } // end of while-loop
            scan.close();
            return taskList;
        } catch (FileNotFoundException fileNotFoundException) {
            throw new DukeException("Unable to load tasks :(");
        }
    } // end of load()

    public void saveEntry(String entry) throws DukeException {
        try {
            File dukeFile = new File(this.filePath);
            FileWriter dukeWriter = new FileWriter(dukeFile, true);
            dukeWriter.write(entry + "\n");
            dukeWriter.close();
        } catch (IOException io) {
            throw new DukeException(ERROR_MSG);
        }
    }
    public void deleteEntry(int taskId) throws DukeException {
        try {
            File dukeFile = new File(this.filePath);
            Scanner scan = new Scanner(dukeFile);
            File tempFile = new File("data/temp-duke.txt");
            Files.deleteIfExists(tempFile.toPath());
            tempFile.createNewFile();
            FileWriter tempWriter = new FileWriter(tempFile, true);
            int counter = 0;
            while (scan.hasNext()) {
                String taskEntry = scan.nextLine();
                if (counter != taskId) {
                    tempWriter.write(taskEntry + "\n");
                }
                counter++;
            }
            tempWriter.close();
            scan.close();
            Files.deleteIfExists(dukeFile.toPath());
            tempFile.renameTo(dukeFile);

        } catch (IOException io) {
            throw new DukeException(ERROR_MSG);
        }

    }
    public void markEntry(int taskId, boolean isMark) throws DukeException {
        try {
            File dukeFile = new File(this.filePath);
            Scanner scan = new Scanner(dukeFile);
            File tempFile = new File("data/temp-duke.txt");
            Files.deleteIfExists(tempFile.toPath());

            tempFile.createNewFile();
            FileWriter tempWriter = new FileWriter(tempFile, true);
            int counter = 0;

            while (scan.hasNext()) {
                String taskEntry = scan.nextLine();
                if (counter == taskId) {
                    String target = isMark ? "| 0 |" : "| 1 |";
                    String replacement = isMark ? "| 1 |" : "| 0 |";
                    taskEntry = taskEntry.replace(target, replacement);

                }

                tempWriter.write(taskEntry + "\n");
                counter++;
            }
            tempWriter.close();
            scan.close();
            Files.deleteIfExists(dukeFile.toPath());
            tempFile.renameTo(new File("data/duke.txt"));


        } catch (IOException io) {
            throw new DukeException(ERROR_MSG);
        }
    }
}
