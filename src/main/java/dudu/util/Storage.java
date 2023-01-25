package dudu.util;

import dudu.exception.TaskIOException;
import dudu.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public void saveTask(ArrayList<Task> list) throws TaskIOException {
        try {
            FileWriter fileWriter = new FileWriter(this.getFile());
            for (Task task : list) {
                fileWriter.write(task.encode() + '\n');
            }
            fileWriter.flush();
        } catch (IOException ex) {
            throw new TaskIOException("Cannot save task");
        }
    }

    public void deleteTask(ArrayList<Task> list) throws TaskIOException {
        saveTask(list);
    }
    public ArrayList<Task> loadTask() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.getFile());
            while (scanner.hasNextLine()) {
                list.add(Task.decode(scanner.nextLine()));
            }
        } catch (TaskIOException | FileNotFoundException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public File getFile() throws TaskIOException {
        File file = new File(filePath);
        File parentFile = file.getParentFile();
        try {
            parentFile.mkdir();
            file.createNewFile();
        } catch (IOException ex) {
            throw new TaskIOException("cannot create file");
        }
        return file;
    }
}
