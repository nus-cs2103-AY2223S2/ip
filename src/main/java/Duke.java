import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;


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
    public static void main(String[] args) throws IOException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Eren\nWhat can I do for you?");
        boolean bool;
        while(true) {
            bool = runCommand();
            if(!bool)
                break;
        }

    }

    private static boolean runCommand() throws IOException{
        String[] word;
        System.out.print("Type your input below: \n");
        word = br.readLine().split(" ");
        word[0] = word[0].toUpperCase();
        try{ 
            switch(word[0]){
                case "BYE":
                    horizontalLine();
                    System.out.println("Bye. Hope to see you again soon!");
                    return false;
                case "LIST":
                    printList();
                    break;
                case "MARK":
                    Task markTask = getTask(Integer.parseInt(word[1]));
                    markTask.mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(markTask);
                    break;
                case "UNMARK":
                    Task unmarkTask = getTask(Integer.parseInt(word[1]));
                    unmarkTask.unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(unmarkTask);
                    break;
                case "TODO":
                case "DEADLINE":
                case "EVENT":
                    String[] sliceWord = Arrays.copyOfRange(word, 1, word.length);
                    specialTask(sliceWord, word[0]);
                    break;
                case "DELETE":
                    deleteTask(Integer.parseInt(word[1]));
                    break;
                default:
                    throw new InvalidCommandException("");
            }
            horizontalLine();
            return true;
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Number out of range/empty. Please try again!");
        } catch(NumberFormatException e){
            System.out.println("Invalid number. Please enter a number!");
        } catch(InvalidCommandException e){
            System.out.println("Invalid command. Pleas try again!");
        } 
        horizontalLine();
        return true;
    }



    /**
     * Function to print a double horizontal line
     */ 
    private static void horizontalLine(){ 
        for (int i = 0; i < 20; i++){
            System.out.print("=");
        }
        System.out.println("");
    }

    /**
     * Function to print a list
     */ 
    private static void printList(){
        for (int i = 0; i < strArr.size(); i++){
            System.out.println((i+1) + ". " + strArr.get(i));
        }
    }

    /**
     * Function to retrieve the Task object given an index
     * 
     * @param num Index of the task to retrieve
     * @return returns a Task object
     */ 
    private static Task getTask(int num) throws IndexOutOfBoundsException{
        return strArr.get(num-1);
    }

    /**
     * Function to add a Task to the list given the description
     * 
     * @param word Array of strings to be added to the list
     * @param type The type of task
     * 
     */ 
    private static void specialTask(String[] word, String type){
        Task t;
        if (word.length == 0) {
            System.out.println("The description of " + type + " cannot be empty. Pleas try again");
            return;
        }
        if(type.equals("TODO")){
            t = new Todo(String.join(" ", word ));
        } else {
            boolean firstOccurence = false;
            for (int i = 0; i < word.length; i++){
                if(!firstOccurence && word[i].contains("/")){
                    firstOccurence = true;
                    word[i] = word[i].replace("/", "(") + ":";
                } else if(firstOccurence && word[i].contains("/")){
                    word[i] = word[i].replace("/", "") + ":";
                } 
            }
            String mergeWord = String.join(" ", word) + ")";
            if(type.equals("DEADLINE")) 
                t = new Deadline(String.join(" ", mergeWord ));
            else
                t = new Event(String.join(" ", mergeWord ));
        }
        strArr.add(t);
        System.out.println("Got it. I've added this task:\n" + t);
        System.out.println("Now you have " + strArr.size() + " tasks in the list.");
    }

    /**
     * Function to add delete a Task from the list given the index
     * 
     * @param num Index of the task to be deleted
     * 
     */ 
    private static void deleteTask(int num) throws IndexOutOfBoundsException{
        Task selectedTask = getTask(num);
        strArr.remove(selectedTask);
        System.out.println("Noted. I've removed this task:\n" + selectedTask);
        System.out.println("Now you have " + strArr.size() + " tasks in the list.");
    }
}
