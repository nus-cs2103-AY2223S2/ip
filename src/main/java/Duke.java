import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I'm DonkeyChat!\nWhat can I do for you?");
        boolean isRunning = true;
        while (isRunning) {
            String currCommand = input.nextLine();
            switch (currCommand) {
                case "bye":
                    System.out.println("Adios!");
                    isRunning = false;
                    break;
                case "list":
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i));
                    }
                    break;
                default:
                    System.out.println("added: " + currCommand);
                    list.add(currCommand);
                    break;
            }
        }
    }
}
