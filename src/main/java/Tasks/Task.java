package tasks;

import exceptions.NoTaskDescriptionException;
import exceptions.UnknownTaskException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Task {
    private static ArrayList<Task> arr = new ArrayList<>();
    private static int curr = 0;

    protected String name;
    protected Boolean isChecked = false;

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    protected Task(String name, String type) throws NoTaskDescriptionException {
        if (name.isBlank()) {
            throw new NoTaskDescriptionException(type);
        } 
        this.name = name;
    }
    
    public static void listTasks() {
        if (curr == 0) {
            System.out.println("There are currently no items in your list. ");
        } else {
            System.out.println("Here are the items in your list: \n");
            for (int i = 0; i < curr; i++) {
                System.out.println("    " + (i+1) + ") " + arr.get(i));
            }
        }
    }

    public static void addTask(String command, String userInput) throws UnknownTaskException, NoTaskDescriptionException {
        String[] dates = userInput.split("/");
        TaskType tt;
        try {
            tt = TaskType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownTaskException(command);
        }

        switch(tt) {
            case TODO: 
                arr.add(new Todo(userInput));
                break;
            case DEADLINE:
                arr.add(new Deadline(dates[0], dates[1]));
                break;
            case EVENT:
                arr.add(new Event(dates[0], dates[1], dates[2]));
                break;
        }
        System.out.println("The following task has been added to your list: \n    " + arr.get(curr) 
                            + "\n \nCurrently, your list has " + ++curr + (curr== 1 ? " task" : " tasks."));
    }

    public static void deleteTask(int task) {
        System.out.println("The following task has been removed: \n    " + arr.get(task) 
                            + "\n \nCurrently, you have " + --curr + (curr==1 ? " task" : " tasks") + " left in your list.");
        arr.remove(task);
    }

    public static void markTasks(int task) {
        arr.get(task).isChecked = true;
        System.out.println("This task is marked as done: \n    " + arr.get(task));
    }

    public static void unmarkTasks(int task) {
        arr.get(task).isChecked = false;
        System.out.println("Okay. This task is marked as not done yet: \n    " + arr.get(task));
    }

    protected String markToString() {
        return this.isChecked ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return markToString() + " " + this.name;
    }

    protected String taskToSave() {
        return this.isChecked.toString() + "|" + this.name;
    }

    /**
     * Saves the most updated list into a txt file
     * 
     * @throws IOException
     */
    public static void save() throws IOException {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "DataDuke");
        File file = Paths.get(path.toString(), "Data.txt").toFile();

        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                
            } catch (IOException e) {
                System.out.println("Unable to write to " + path.toString());
            }
        }
        
        PrintStream stream;
        try { 
            stream = new PrintStream(file.toString());
        } catch (FileNotFoundException e) {
                Files.createDirectories(path);
                stream = new PrintStream(file.toString());
        } 


        for (int i = 0; i < curr; i++) {
            stream.println(arr.get(i).taskToSave());
        }

        stream.close();
    }

}
