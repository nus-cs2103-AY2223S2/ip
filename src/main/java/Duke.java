import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<String> taskStore = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("  insert ingenious greeting here");

        while (true) {
            Scanner myScanner = new Scanner(System.in);
            String command = myScanner.nextLine();

            if (command.equals("bye")) {
                System.out.println("  Bye. Hope to see you soon again!");
                break;
            }
            else if (command.equals("list")) {
                for (int i = 0; i < taskStore.size(); i++) {
                    System.out.println("  " + String.valueOf(i + 1) + ". " + taskStore.get(i));
                }
            }
            // add task
            else {
                taskStore.add(command);
                System.out.println("  added: " + command);
            }
        }
    }
}
