import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<Task> taskList;

    public Storage() {
        this.taskList = new ArrayList<>();
    }

    public void editStorage(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getStorage() {
        return this.taskList;
    }

    public void loadFromFile(File file) {
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(file));
            taskList.clear();
            String line = bufReader.readLine();
            while (line != null) {
                try {
                    taskList.add(Task.dataToTask(line));
                } catch (InvalidTaskTypeException e) {
                    System.out.println(Duke.HORIZONTAL_LINE + "\n" + e.getMessage() +
                            "\n" + Duke.HORIZONTAL_LINE);
                }
                line = bufReader.readLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found when trying to read data file.");
        } catch (IOException e) {
            throw new RuntimeException("Error while loading from file: " + e);
        }
    }

    public void saveToFile(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : taskList) {
                writer.write(task.taskToData());
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving file: " + e);
        }

    }

    public static void main(String[] args) {
        Storage data = new Storage();
        List<Task> newTaskList = new ArrayList<>();
        newTaskList.add(new Deadline("Do task", "September"));
        newTaskList.add(new ToDo(("Todo task")));
        newTaskList.add(new Event("Event task", "October", "November"));
        data.editStorage(newTaskList);
        data.saveToFile(new File("savedFile.txt"));
        data.loadFromFile(new File("savedFile.txt"));
    }



}
