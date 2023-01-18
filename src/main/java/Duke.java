import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        ArrayList<String> lists = new ArrayList<>();
        boolean loop = true;

        String tabSpace = "    ";
        String bracket =  tabSpace + "_______________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String welcomeMsg = ("Hello from\n" + logo + "\nWhat can I do for you?");
        System.out.println(welcomeMsg);


        while (loop) {
        String input = inputScanner.nextLine();

        System.out.println(bracket);
        switch(input) {
            case "list":
                for(int i = 0; i < lists.size(); i++) {
                    System.out.println(tabSpace + " " + i+1 + " " + lists.get(i));
                }
                
                break;
            case "bye":
                System.out.println(tabSpace + "Bye! See you soon!");
                loop = false;
                break;
            default:
                lists.add(input);
                System.out.println(tabSpace + " added " + input);
        }
        System.out.println(bracket);

        }

        inputScanner.close();
    }
}