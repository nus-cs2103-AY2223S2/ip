package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import dukeexeption.InvalidArgumentException;
import dukeexeption.UnknownCommandException;

import java.time.LocalDate;
import java.util.ArrayList;

import command.DeadlineCommand;
import command.EventCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;

import task.Task;

/**
 * Local storage class that abstracts the communications between local storage and the program task list.
 */
public class LocalStorage {
    private File dataFile;

    /**
     * Local storage constructor.
     *
     * @param filepath filepath string to data file
     */
    public LocalStorage(String filepath) {
        if ("".equals(filepath.trim())) {
            throw new InvalidArgumentException("Filepath cannot be empty.");
        }
        File dataFile = new File(filepath);
        dataFile.getParentFile().mkdirs();

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException error) {
                error.printStackTrace();
            }
        } else if (dataFile.exists() && dataFile.isDirectory()) {
            throw new InvalidArgumentException("Directory path provided, change filepath to a path to a file.");
        }

        this.dataFile = dataFile;
    }

    /**
     * Creates a task list.
     *
     * @return An array of tasks.
     */
    public ArrayList<Task> createTaskList() {
        TaskList taskList = new TaskList();
        try {
            FileReader fr = new FileReader(this.dataFile);
            BufferedReader reader = new BufferedReader(fr);
            String s;
            int currIndex = 0;
            while ((s = reader.readLine()) != null) {
                String[] data = s.split(" \\| ");
                try {
                    String taskType = data[0];
                    String taskIsCompleted = data[1];
                    String task = data[2];

                    switch (taskType) {
                    case "T":
                        new TodoCommand(task).run(taskList);
                        break;
                    case "D":
                        String deadlineString = data[3];
                        new DeadlineCommand(task, LocalDate.parse(deadlineString)).run(taskList);
                        break;
                    case "E":
                        String eventStartTimeString = data[3];
                        String eventEndTimeString = data[4];
                        new EventCommand(task, LocalDate.parse(eventStartTimeString),
                                LocalDate.parse(eventEndTimeString))
                                .run(taskList);
                        break;
                    default:
                        System.out.println("Error occurs at: " + s);
                        throw new UnknownCommandException("Datafile provided is corrupted,"
                                + "create a new file or follow the format.");
                    }

                    switch (taskIsCompleted) {
                    case "0":
                        new UnmarkCommand(currIndex).run(taskList);
                        break;
                    case "1":
                        new MarkCommand(currIndex).run(taskList);
                        break;
                    default:
                        System.out.println("Error occurs at: " + s);
                        throw new UnknownCommandException("Datafile provided is corrupted,"
                                + "create a new file or follow the format.");
                    }

                    currIndex++;
                } catch (IndexOutOfBoundsException error) {
                    throw new InvalidArgumentException("Datafile provided is corrupted,"
                            + "create a new file or follow the format.");
                }
            }

            reader.close();
            fr.close();
            return taskList.indexTask();
        } catch (IOException error) {
            throw new InvalidArgumentException(error.toString());
        }
    }

    /**
     * Writes tasks to the file stored.
     *
     * @param taskList task list to load the data from
     */
    public void writeFromProgramTaskList(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.dataFile, false);
            BufferedWriter writer = new BufferedWriter(fw);

            for (Task task : taskList.indexTask()) {
                writer.write(task.toDataString());
                writer.newLine();
            }

            writer.close();
            fw.close();
        } catch (IOException error) {
            throw new InvalidArgumentException(error.toString());
        }
    }
}
