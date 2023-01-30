package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import exception.DukeException;
import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDos;

import java.time.LocalDateTime;

/**
 * Stores the task from the user.
 */
public class Storage {
    private File file;
    private String fileName;

    /**
     * Creates a Storage instance
     * 
     * @param fileName the relative pathname
     */
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * loads the tasks from the storage file.
     * 
     * @return TaskList the TaskList will all the tasks
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(fileName);
            if (!file.exists()) {
                return tasks;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String input;
            while ((input = br.readLine()) != null) {
                String[] splitInput = input.split("[|]");
                String taskType = Character.toString(splitInput[0].charAt(3));

                Task task;
                switch (taskType) {
                    case "T":
                        task = new ToDos(splitInput[1]);
                        break;
                    case "D":
                        LocalDateTime storageDateTime = Parser.parseDateStorage(splitInput[2]);
                        task = new Deadlines(splitInput[2], storageDateTime);
                        break;
                    case "E":
                        String[] splitDates = splitInput[2].split(":");
                        String startDate = splitDates[0].substring(1);
                        String endDate = splitDates[1].substring(0, splitDates[1].length() - 1);
                        LocalDateTime start = Parser.parseDateStorage(startDate);
                        LocalDateTime end = Parser.parseDateStorage(endDate);
                        task = new Events(splitInput[1], start, end);
                        break;
                    default:
                        throw new DukeException(
                                "Error occurred during file parsing, unexpected task type encountered.");
                }

                if (Integer.parseInt(splitInput[1]) == 1) {
                    task.mark();
                }
                tasks.add(task);
            }
        } catch (Exception e) {
            throw new DukeException("An error occurred during file parsing.");
        }

        return tasks;
    }

    /**
     * Saves the Task into the file
     * 
     * @param list the TaskList to store
     */
    public void save(TaskList list) {
        this.checkAndCreateFile();
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 1; i < list.size() + 1; i++) {
                bufferedWriter.write(list.get(i).toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("File cannot be written to.");
        }
    }

    /**
     * Checks and create a file to store.
     */
    private void checkAndCreateFile() {
        this.file = new File(this.fileName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
