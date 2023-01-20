import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        String greeting = "Hello! I'm Alpha Beast What can I do for you?";
        greeting(greeting);
        /* create a class of array*/
        ArrayList<String> memory = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();
            echo(input);
            if(input.equals("bye"))
                break;
        }
    }

    static void greeting(String message){
        System.out.println(message);
    }

    static void echo(String input) {
        if (input.equals("bye"))
            System.out.println("Bye. Hope to see you again soon!\n");
        else
            System.out.println(input);
    }

}
