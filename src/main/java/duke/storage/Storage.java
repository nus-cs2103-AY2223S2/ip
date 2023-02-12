package duke.storage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.TaskList;
import duke.exceptions.DirectoryNotFoundException;
import duke.ui.Ui;


public class Storage {


    protected String path;

    public Storage(String path) {
        this.path = path;
    }


    /**
     * Loads the tasks from the text file and store them in the task list
     *
     * @return A file object to be given to the task list as input
     * @throws DirectoryNotFoundException
     * @throws FileNotFoundException
     */
    public File load() throws DirectoryNotFoundException, FileNotFoundException {
        Path directory = Paths.get("data");
        Path file = Paths.get(path);
        boolean fileExists = java.nio.file.Files.exists(file);
        boolean directoryExists = java.nio.file.Files.exists(directory);
        if (!directoryExists) {
            throw new DirectoryNotFoundException();
        }
        if (!fileExists) {
            throw new FileNotFoundException();
        }
        assert  directoryExists;
        assert fileExists;
        File f = new File(path); // create a File for the given file path
        return f;

    }


    /**
     * Writes the tasks to the text file when there is a change
     *
     * @param tasks The task list
     */
    public void write(TaskList tasks) {
        try {
            Path directory = Paths.get("data");
            Path file = Paths.get(path);
            boolean fileExists = java.nio.file.Files.exists(file);
            boolean directoryExists = java.nio.file.Files.exists(directory);
            if (!directoryExists) {
                throw new DirectoryNotFoundException();
            }
            if (!fileExists) {
                throw new DirectoryNotFoundException();
            }
            FileWriter writer = new FileWriter("./data/duke.txt");
            String output = "";
            for (int j = 0; j < tasks.getLength(); j++) {
                String status = tasks.getTask(j).getStatusIcon().equals("X") ? "1" : "0";
                if (!tasks.getTask(j).getType().equals("T")) {
                    output = output + (tasks.getTask(j).getType() + " | " + status + " | "
                        + tasks.getTask(j).getDetail() + "| " + tasks.getTask(j).getTime() + "\n");
                } else {
                    output = output + (tasks.getTask(j).getType() + " | " + status + " | "
                        + tasks.getTask(j).getDetail() + "\n");
                }
            }
            writer.write(output);
            writer.close();
        } catch (DirectoryNotFoundException e) {
            Ui.directoryExceptionUi();
        } catch (FileNotFoundException e) {
            Ui.fileExceptionUi();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
