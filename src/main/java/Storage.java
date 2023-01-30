import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected File file;

    public Storage(String fileInput) throws DukeException{
        try {
            this.file = new File(fileInput);
            if (!file.exists()) {
                Files.createDirectories(Paths.get(file.getParent()));
                this.file = Files.createFile(Path.of(fileInput)).toFile();
            }
        } catch (IOException err){
            throw new DukeException("Error, the directory of tasks cannot be found.");
        }
    }
    public ArrayList<Task> loadTasks() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner scFile = new Scanner(this.file);
            while (scFile.hasNextLine()) {
                String input = scFile.nextLine();
                String[] cur = input.split(",", 3);
                if (cur[0].equals("T")) {
                    tasks.add(new Todo(cur[2], Boolean.parseBoolean(cur[1])));
                } else if (cur[0].equals("D")) {
                    tasks.add(new Deadline(cur[2], Boolean.parseBoolean(cur[1])));
                } else {
                    tasks.add(new Event(cur[2], Boolean.parseBoolean(cur[1])));
                }
            }
            return tasks;
        } catch(FileNotFoundException err) {
            throw new DukeException("Error, your saved tasks cannot be loaded.");
        }
    }

    public void saveTasks(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Task cur : taskList.tasks) {
                fileWriter.write(cur.saveTask());
                fileWriter.write('\n');
            }
            fileWriter.close();
        } catch (IOException err) {
            throw new DukeException("Error, your tasks cannot be saved.");
        }
    }
}
