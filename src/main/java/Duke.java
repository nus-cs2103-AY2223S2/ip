import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        ArrayList<Task> lists = new ArrayList<>();
        boolean loop = true;

        String bracket = "\t_______________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String welcomeMsg = ("Hello from\n" + logo + "\nWhat can I do for you?");
        System.out.println(welcomeMsg);


        while (loop) {
        String[] input = inputScanner.nextLine().split(" ", 3);

        System.out.println(bracket);
        switch(input[0]) {
            case "list":
                System.out.println("\t Here are the tasks in your list:");
                for(int i = 0; i < lists.size(); i++) {
                    int index = i+1;
                    System.out.println("\t " + index + ". " + lists.get(i).toString());
                }
                
                break;
            case "bye":
                System.out.println("\tBye! See you soon!");
                loop = false;
                break;
            default:
                String combString = String.join(" ", input);
                lists.add(new Task(combString));
                System.out.println("\t added " + combString);
        }
        System.out.println(bracket);

        }

        inputScanner.close();
    }
}