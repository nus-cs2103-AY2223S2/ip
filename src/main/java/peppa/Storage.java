package peppa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a file for storing tasks and handles all file I/O-related logic.
 */
public class Storage {
    private String dirPath;
    private Peppa p;
    private File f;
    private ArrayList<File> dataSources = new ArrayList<>();

    /**
     * Constructs a storage object with the specified file path.
     *
     * @param dirPath The path of the directory containing pre-existing task files, if any.
     */
    public Storage(Peppa p, String dirPath) {
        this.p = p;
        this.dirPath = dirPath;
        setDataSources();
        initialiseStorage();
    }

    public File getFile() {
        return this.f;
    }

    public ArrayList<File> getDataSources() {
        return this.dataSources;
    }

    /**
     * Initialises the current Storage object.
     * If more than 1 possible data source is detected, don't define file yet.
     */
    public void initialiseStorage() {
        try {
            int fileCount = dataSources.size();
            if (fileCount > 1) {
                return;
            } else if (fileCount == 0) {
                File file = new File(dirPath + "/todo.txt");
                file.createNewFile();
                this.f = file;
            } else if (fileCount == 1) {
                this.f = dataSources.get(0);
            }
            loadData(new TaskList());
        } catch (IOException | SecurityException e) {
            Ui.displayMessage("Boink! There seems to be an I/O error. Please try again.");
        }
    }

    /**
     * Loads existing task data from local file.
     *
     * @param taskList Tasklist to store tasks into.
     */
    public void loadData(TaskList taskList) {
        try {
            Scanner sc = new Scanner(this.f);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                Parser.parseFileEntry(line, taskList);
            }
            p.setTasks(taskList);
            sc.close();
        } catch (FileNotFoundException e) {
            Ui.displayMessage("Boink! Peppa could not locate the file. Please try again.");
        } catch (PeppaException e) {
            Ui.displayMessage(e.getMessage());
        }
    }

    /**
     * Updates the file when tasks are added, deleted or modified.
     *
     * @param tasks List of tasks.
     */
    public void saveChanges(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < tasks.getLength(); i++) {
                fw.write(tasks.retrieveTask(i).toFormatString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            Ui.displayMessage("Boink! Peppa could not save changes to the task list. Please try again.");
        }
    }

    public void setDataSources() {
        File dir = new File(dirPath);
        dir.mkdir();
        assert (dir.exists());

        File[] children = dir.listFiles();
        for (File child : children) {
            if (child.isFile()) {
                dataSources.add(child);
            }
        }
    }

    public void setFile(File f) {
        this.f = f;
    }
}
