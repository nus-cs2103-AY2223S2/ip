import java.util.Scanner; 

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);  
        String word = "";
        while(true) {
            System.out.print("Type your input: ");
            word = sc.nextLine();
            if(word.toUpperCase().equals("BYE")) {
                horizontalLine();
                break;
            }
            System.out.println((word));
            horizontalLine();
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
}
