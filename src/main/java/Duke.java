import java.util.Scanner; 
import java.util.ArrayList;

/**
 * A talking robot
 * CS2103T
 * AY22/23 Semester 2.
 *
 * @author Lyndon Lim Liang Hng 
 */

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Eren\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);  
        String word = "";
        ArrayList<String> strArr = new ArrayList<>();
        while(true) {
            System.out.print("Type your input to store in a list: ");
            word = sc.nextLine();
            if(word.toUpperCase().equals("BYE")) {
                horizontalLine();
                break;
            } else if(word.toUpperCase().equals("LIST")){
                printList(strArr);
            } else {
                strArr.add(word);
                System.out.println(("added: " + word));
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
     * @param arrList Takes in a list that contains the user inputs
     */ 
    private static void printList(ArrayList arrList){
        for (int i = 0; i < arrList.size(); i++){
            System.out.println((i+1) + ". " + arrList.get(i));
        }
    }
}
