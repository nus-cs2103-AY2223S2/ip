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
    public static void main(String[] args) throws IOException, EmptyNumberException{
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

    private static boolean runCommand() throws IOException, EmptyNumberException{
        String[] word;
        System.out.print("Type your input below: \n");
        word = br.readLine().split(" ");
        if(word[0].toUpperCase().equals("BYE")) {
            horizontalLine();
            System.out.println("Bye. Hope to see you again soon!");
            return false;
        } else if(word[0].toUpperCase().equals("LIST")){
            printList();
        } else if(word[0].equals("mark")){
            try{
                Task t = getTask(Integer.parseInt(word[1]));
                if(t != null){
                    t.mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t);
                }
            } catch(NumberFormatException e){
                System.out.println("Invalid number. Please enter a number");
            } catch(ArrayIndexOutOfBoundsException e){
                throw new EmptyNumberException("The number for mark cannot be empty.");
            }
        } else if(word[0].equals("unmark")) {
            try { 
                Task t = getTask(Integer.parseInt(word[1]));
                if(t != null){
                    t.unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(t);
            }
            } catch(NumberFormatException e){
                System.out.println("Invalid number. Please enter a number");
            } catch(ArrayIndexOutOfBoundsException e){
                throw new EmptyNumberException("The number for unmark cannot be empty. Please try again!");
            }
        } else if(word[0].equals("todo")){
            String[] sliceWord = Arrays.copyOfRange(word, 1, word.length);
            specialTask(sliceWord, 'T');
        } else if(word[0].equals("deadline")){
            String[] sliceWord = Arrays.copyOfRange(word, 1, word.length);
            specialTask(sliceWord, 'D');
        } else if(word[0].equals("event")){
            String[] sliceWord = Arrays.copyOfRange(word, 1, word.length);
            specialTask(sliceWord, 'E');
        } else if(word[0].equals("delete")) {
            try { 
                deleteTask(Integer.parseInt(word[1]));
            } catch(NumberFormatException e){
                System.out.println("Invalid number. Please enter a number");
            }
        }
        else {
            System.out.println(("Invalid command. Please try again!"));

        }
        horizontalLine();
        return true;
    }



    // Function to print a double horizontal line
    private static void horizontalLine(){ 
        for (int i = 0; i < 20; i++){
            System.out.print("=");
        }
        System.out.println("");
    }

    /**
     * Function to print a list
     * 
     */ 
    private static void printList(){
        for (int i = 0; i < strArr.size(); i++){
            System.out.println((i+1) + ". " + strArr.get(i));
        }
    }

    private static Task getTask(int num){
        try {
            return strArr.get(num-1);
        } catch(IndexOutOfBoundsException e){
            System.out.println("Number out of range. Please try again");
            return null;
        }
    }

    private static void specialTask(String[] word, char type){
        Task t;
        if (word.length == 0) {
            String errMsg = (type == 'T') ? "todo" : (type == 'D')
                                            ? "deadline"
                                            : "event";
            System.out.println("The description of " + errMsg + " cannot be empty. Pleas try again");
            return;
        }
        if(type == 'T'){
            t = new Todo(String.join(" ", word ));
        } else {
            boolean firstOccurence = false;
            for (int i = 0; i < word.length; i++){
                if(!firstOccurence && word[i].contains("/")){
                    firstOccurence = true;
                    word[i] = word[i].replace("/", "(");
                    word[i] += ":";
                } else if(firstOccurence && word[i].contains("/")){
                    word[i] = word[i].replace("/", "");
                    word[i] += ":";
                } 
            }
            String mergeWord = String.join(" ", word);
            mergeWord += ")";
            if(type == 'D') 
                t = new Deadline(String.join(" ", mergeWord ));
            else
                t = new Event(String.join(" ", mergeWord ));
        }
        strArr.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + strArr.size() + " tasks in the list.");
    }

    private static void deleteTask(int num){
        Task selectedTask = getTask(num);
        if(selectedTask != null){
            strArr.remove(selectedTask);
            System.out.println("Noted. I've removed this task:");
            System.out.println(selectedTask);
            System.out.println("Now you have " + strArr.size() + " tasks in the list.");
        }
    }
}
