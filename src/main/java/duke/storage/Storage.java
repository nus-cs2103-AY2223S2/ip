package duke.storage;

import duke.TaskList;
import duke.exceptions.DirectoryNotFoundException;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public File load() throws DirectoryNotFoundException, FileNotFoundException{
            Path directory = Paths.get("data");
            Path file = Paths.get(path);
            boolean FileExists = java.nio.file.Files.exists(file);
            boolean directoryExists = java.nio.file.Files.exists(directory);
            if (!directoryExists) {
                throw new DirectoryNotFoundException();
            }
            if (!FileExists) {
                throw new FileNotFoundException();
            }
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
            boolean FileExists = java.nio.file.Files.exists(file);
            boolean directoryExists = java.nio.file.Files.exists(directory);
            if (!directoryExists) {
                throw new DirectoryNotFoundException();
            }
            if (!FileExists) {
                throw new DirectoryNotFoundException();
            }
            FileWriter Writer = new FileWriter("./data/duke.txt");
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
            Writer.write(output);
            Writer.close();
        } catch (DirectoryNotFoundException e) {
            Ui.DirectoryExceptionUi();
        } catch (FileNotFoundException e) {
            Ui.FileExceptionUi();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
