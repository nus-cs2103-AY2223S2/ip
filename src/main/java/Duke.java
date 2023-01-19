import java.util.ArrayList;
import java.io.*;


/**
 * A talking robot
 * CS2103T
 * AY22/23 Semester 2.
 *
 * @author Lyndon Lim Liang Hng 
 */

public class Duke {
    private static ArrayList<Task> strArr = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Eren\nWhat can I do for you?");
        String[] word;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            System.out.print("Type your input: ");
            word = br.readLine().split(" ");
            if(word[0].toUpperCase().equals("BYE")) {
                horizontalLine();
                break;
            } else if(word[0].toUpperCase().equals("LIST")){
                printList();
            } else if(word[0].equals("mark")){
                try{
                    Task t = getTask(Integer.parseInt(word[1]));
                    if(t == null){
                        continue;
                    }
                    else {
                        t.mark();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(t);
                    }
                } catch(NumberFormatException e){
                    System.out.println("Invalid number. Please enter a number");
                    continue;
                }


            } else if(word[0].equals("unmark")) {
                try { 
                    Task t = getTask(Integer.parseInt(word[1]));
                    if(t == null){
                        continue;
                    }
                    else {
                        t.unmark();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(t);
                    }
                } catch(NumberFormatException e){
                    System.out.println("Invalid number. Please enter a number");
                    continue;
                }
            }
            else {
                String mergeWord = String.join(" ", word);
                strArr.add(new Task(mergeWord));
                System.out.println(("added: " + mergeWord));
                horizontalLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
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
        if (num <= strArr.size()) {
            return strArr.get(num-1);
        } else {
            System.out.println("Number out of range/Invalid. Please try again");
            return null;
        }
    }
}
