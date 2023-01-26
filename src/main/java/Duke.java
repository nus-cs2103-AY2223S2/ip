import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * A talking robot
 * CS2103T
 * AY22/23 Semester 2.
 *
 * @author Lyndon Lim Liang Hng 
 */

public class Duke {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<Task> strArr = new ArrayList<>();
    private static String FILE_PATH = "./data/eren.txt";
    private static String DIR_PATH = "./data/";

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Eren\nWhat can I do for you?");
        boolean bool;
        loadFile();
        while(true) {
            bool = runCommand();
            if(!bool)
                break;
        }

    }

    private static boolean runCommand() throws IOException {
        String[] words;
        System.out.print("Type your input below: \n");
        words = br.readLine().split(" ");
        words[0] = words[0].toUpperCase();
        try { 
            switch(words[0]) {
            case "BYE":
                horizontalLine();
                System.out.println("Bye. Hope to see you again soon!");
                return false;
            case "LIST":
                printList();
                break;
            case "MARK":
                Task markTask = getTask(Integer.parseInt(words[1]));
                markTask.mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(markTask);
                break;
            case "UNMARK":
                Task unmarkTask = getTask(Integer.parseInt(words[1]));
                unmarkTask.unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(unmarkTask);
                break;
            case "TODO":
            case "DEADLINE":
            case "EVENT":
                String[] sliceWords = Arrays.copyOfRange(words, 1, words.length);
                specialTask(sliceWords, words[0]);
                break;
            case "DELETE":
                deleteTask(Integer.parseInt(words[1]));
                break;
            default:
                throw new InvalidCommandException("");
            }
            if(words[0] != "LIST") {
                saveFile();
            }
            horizontalLine();
            return true;
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Number out of range/empty. Please try again!");
        } catch(NumberFormatException e) {
            System.out.println("Invalid number. Please enter a number!");
        } catch(InvalidCommandException e) {
            System.out.println("Invalid command. Pleas try again!");
        } 
        horizontalLine();
        return true;
    }



    /**
     * Function to print a double horizontal line
     */ 
    private static void horizontalLine() { 
        for (int i = 0; i < 20; i++) {
            System.out.print("=");
        }
        System.out.println("");
    }

    /**
     * Function to print a list
     */ 
    private static void printList() {
        for (int i = 0; i < strArr.size(); i++) {
            System.out.println((i+1) + ". " + strArr.get(i));
        }
    }

    /**
     * Function to retrieve the Task object given an index
     * 
     * @param num Index of the task to retrieve
     * @return returns a Task object
     */ 
    private static Task getTask(int num) throws IndexOutOfBoundsException {
        return strArr.get(num-1);
    }

    /**
     * Function to add a Task to the list given the description
     * 
     * @param words Array of strings to be added to the list
     * @param type The type of task
     * 
     */ 
    private static void specialTask(String[] words, String type) {
        Task task;
        if (words.length == 0) {
            System.out.println("The description of " + type + " cannot be empty. Pleas try again");
            return;
        }
        if(type.equals("TODO")) {
            task = new Todo(String.join(" ", words ));
        } else {
            boolean hasFirstOccured = false;
            for (int i = 0; i < words.length; i++) {
                if(!hasFirstOccured && words[i].contains("/")) {
                    hasFirstOccured = true;
                    words[i] = words[i].replace("/", "(") + ":";
                } else if(hasFirstOccured && words[i].contains("/")) {
                    words[i] = words[i].replace("/", "") + ":";
                } 
            }
            String mergeWord = String.join(" ", words) + ")";
            if(type.equals("DEADLINE")) {
                task = new Deadline(String.join(" ", mergeWord ));  
            }
            else {
                task = new Event(String.join(" ", mergeWord ));
            }
        }
        strArr.add(task);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + strArr.size() + " tasks in the list.");
    }

    /**
     * Function to add delete a Task from the list given the index
     * 
     * @param num Index of the task to be deleted
     * 
     */ 
    private static void deleteTask(int num) throws IndexOutOfBoundsException {
        Task selectedTask = getTask(num);
        strArr.remove(selectedTask);
        System.out.println("Noted. I've removed this task:\n" + selectedTask);
        System.out.println("Now you have " + strArr.size() + " tasks in the list.");
    }

    /**
     * Function to save list of task in txt file
     */ 
    private static void saveFile() {
        String fileContent;
        fileContent = "  TYPE  | COMPLETED | DETAILS\n";
        for (Task t : strArr){
            if(t.type == 'T')
                fileContent += "  Todo  |";
            else if(t.type == 'D')
                fileContent += "Deadline|";
            else
                fileContent += "  Event |";
            fileContent += t.isDone ? "    YES    |" : "    NO     |";
            fileContent += t.description + "\n";
        }
        File file = new File(FILE_PATH);
        try {
            Files.createDirectories(Paths.get(DIR_PATH));
        } catch(IOException e){
            System.out.println("Error creating folder");
        }
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error saving to file");
            }
        }
        writeToFile(fileContent);
    }

    /**
     * Function to write to file
     * 
     * @param description File content to be written to the file
     * 
     */ 
    private static void writeToFile(String description) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write(description);
            fw.close();
          } catch (IOException e) {
            System.out.println("Error writing to file");
            e.printStackTrace();
          }
    }


    /**
     * Function to load the list of task from txt file
     */ 
    private static void loadFile() {
        File file = new File(FILE_PATH);
        try {   
            if (file.exists()) {
                BufferedReader fileReader = new BufferedReader(new FileReader(file));
                String currLine;
                Boolean hasSkipped = true;
                String[] splitInput;
                Task task;
                while ((currLine = fileReader.readLine()) != null) {
                    if(!hasSkipped) {
                        splitInput = currLine.split("\\|");
                        if(splitInput[0].contains("Deadline")) {
                            task = new Deadline(splitInput[2]);
                        } else if(splitInput[0].contains("Todo")) {
                            task = new Todo(splitInput[2]);
                        } else {
                            task = new Event(splitInput[2]);
                        }
                        if(splitInput[1].contains("YES"))
                            task.mark();
                        strArr.add(task);
                    }
                    hasSkipped = false;
                }
                fileReader.close();
            }
        } catch (IOException e) {
            System.out.println("Error when loading file, a new file will be created");
        }
    }
}

