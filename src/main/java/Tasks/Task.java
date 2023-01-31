package tasks;

import exceptions.NoTaskDescriptionException;
import exceptions.UnknownTaskException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// import parsing.ParseDate;

/**
 * This class represents a Generic Task which could be a Todo, Deadline or Event Task
 */
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
        String[] taskStrings = userInput.split("/");

        // "* could not be parsed at index *" -- "please enter valid date format: yyyy-mm-dd hh:mm(:ss)"
        LocalDateTime[] dates = new LocalDateTime[2];

        for (int i = 0; i < taskStrings.length; i++) {
            taskStrings[i] = taskStrings[i].strip();
            if (i > 0) {
                dates[i-1] = LocalDateTime.parse(taskStrings[i]);
            }
        }
        
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
            arr.add(new Deadline(taskStrings[0], dates[0]));
            break;
        case EVENT:
            arr.add(new Event(taskStrings[0], dates[0], dates[1]));
            break;
        }

        System.out.println("The following task has been added to your list: \n    " + arr.get(curr) 
                            + "\n \nCurrently, your list has " + ++curr + (curr== 1 ? " task" : " tasks."));
    }

    public static void check(LocalDate date) {
        String output = "";
        int numTasks = 0;
        for (int i = 0; i < arr.size(); i++) {
            Task task = arr.get(i);
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                if (deadlineTask.isEqualDate(date)) {
                    output += (numTasks+1) + ") " + deadlineTask + "\n";
                    numTasks++;
                }
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                if (eventTask.isEqualDate(date)) {
                    output += (numTasks+1) + ") " + eventTask + "\n";
                    numTasks++;
                }
            }
        }

        if (numTasks == 0) {
            System.out.println("You have no tasks on " + date.toString());
        } else {
            String s = " tasks";
            if (numTasks == 1) {
                s = " task";
            }
            System.out.println("You have " + numTasks + s + " on " + date.toString());
            System.out.print(output);
        }
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

    /** 
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return markToString() + " " + this.name;
    }

    protected String stringifyTaskToSave() {
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
            stream.println(arr.get(i).stringifyTaskToSave());
        }

        stream.close();
    }

}
