// import libraries here
import java.util.Scanner;

public class Acerizm {
    public static void main(String[] args) {
        String personal_logo = "                      - \n"
                             + "    /                (_) \n"
                             + "   /  \\   ___ ___ _ __ _ _____ __ ___ \n"
                             + "  / /  \\ / __/ _ \\ '__| |_  / '_ ` _ \\ \n"
                             + " / ____ \\ (_|  __/ |  | |/ /| | | | | | \n"
                             + "/_/    \\_\\___\\___|_|  |_/___|_| |_| |_| \n";

                System.out.println("Hi there! I am \n" + personal_logo);
                chat();
    }

    // Chat method is for level 1 of chatbot
    public static void chat(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("*-".repeat(100));
        System.out.println("What can I do for you?");
        System.out.println("*-".repeat(100));
        while(scanner.hasNextLine()){
            String input = scanner.nextLine();
            if(!input.equals("bye")) {
                System.out.println("*-".repeat(100));
                System.out.println(input);
                System.out.println("*-".repeat(100));
            } else {
                System.out.println("*-".repeat(100));
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("*-".repeat(100));
                break;
            }
        }
    }
}
