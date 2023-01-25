import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class TaskList {
    private final ArrayList<Task> list;
    private final String filePath;
    public TaskList() {
        this.list = new ArrayList<>();
        this.filePath = "./data/tasks.txt";
    }

    public void saveTask() throws TaskIOException {
        try {
            FileWriter fileWriter = new FileWriter(this.getFile());
            for (Task task : this.list) {
                fileWriter.write(task.encode() + '\n');
            }
            fileWriter.flush();
        } catch (IOException ex) {
            throw new TaskIOException("Cannot save task");
        }
    }

    public void loadTask() {
        try {
            Scanner scanner = new Scanner(this.getFile());
            while (scanner.hasNextLine()) {
                list.add(Task.decode(scanner.nextLine()));
            }
        } catch (TaskIOException | FileNotFoundException ex) {
            System.out.println(ex);
        }
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
    public void addTask(String type, String input) throws EmptyDescriptionException, TaskIOException{
        Task task;
        if (Dudu.Command.DEADLINE.equals(type)) {
            if (input.trim().length() == 8) {
                throw new EmptyDescriptionException(type, "Missing task description");
            }
            if (!input.contains(" /by ")) {
                input = input.concat(" /by null");
            }
            String[] inputStr = input.substring(9).split(" /by ");
            task = new Deadline(inputStr[0], inputStr[1]);
        } else if (Dudu.Command.TODO.equals(type)) {
            if (input.trim().length() == 4) {
                throw new EmptyDescriptionException(type, "Missing task description");
            }
            task = new Todo(input.substring(5));
        } else {
            if (input.trim().length() == 5) {
                throw new EmptyDescriptionException(type, "Missing task description");
            }
            String[] inputStr = input.substring(6).split(" /from ");
            String[] dateStr = inputStr[1].split(" /to ");
            task = new Event(inputStr[0],dateStr[0],dateStr[1]);
        }
        list.add(task);
        saveTask();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println(getTotalTask());
    }

    public String getTotalTask() {
        String secondHalf;
        if (list.size() <= 1) {
            secondHalf = " task in the list.";
        } else {
            secondHalf = " tasks in the list.";
        }
        return "Now you have " + list.size() + secondHalf;
    }
    public Task getTask(int index) throws TaskNumRangeException {
        if (index >= list.size() || index < 0) {
            throw new TaskNumRangeException(String.valueOf(index));
        }
        return list.get(index);
    }

    public void delete(int index) {
        list.remove(index);
    }
    public void printList() {
        if (list.size() == 0) {
            System.out.println("There is no task in your list");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                Task currTask = list.get(i);
                System.out.println(i + 1 + "." + currTask);
            }
        }
    }
}
