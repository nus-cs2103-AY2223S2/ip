import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> storage = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        String spacingIndent = "    ";
        String welcomeMsg = spacingIndent + " "
                + "Hello! I'm Duke\n"
                + spacingIndent + " "
                + "How can I help you?";
        String exitMsg = spacingIndent + " "
                + "Farewell! See you soon!";
        String dashes = spacingIndent
                + "______________________________"
                + "______________________________";
        String curInput = "";
        boolean status = true;
        System.out.println(dashes);
        System.out.println(welcomeMsg);
        System.out.println(dashes);

        while (status) {
            curInput = sc.nextLine();

            if (curInput.equals("bye")){
                status = false;
            } else if (curInput.equals("list")) {
                System.out.println(dashes);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < storage.size(); i++) {
                    int number = i + 1;
                    System.out.println(spacingIndent + " "
                            + number + ". " + storage.get(i));
                }
                System.out.println(dashes);
            } else if (curInput.startsWith("mark")) {
                int targetIndex = Integer.parseInt(curInput.substring(5)) - 1;
                storage.set(targetIndex, storage.get(targetIndex).markAsDone());
                System.out.println(dashes);
                System.out.println(spacingIndent + "Nice! I've marked this task as done:");
                System.out.println(spacingIndent + "   " + storage.get(targetIndex));
                System.out.println(dashes);
            } else if (curInput.startsWith("unmark")) {
                int targetIndex = Integer.parseInt(curInput.substring(7)) - 1;
                storage.set(targetIndex, storage.get(targetIndex).markAsDone());
                System.out.println(dashes);
                System.out.println(spacingIndent + "OK, I've marked this task as not done yet:");
                System.out.println(spacingIndent + "   " + storage.get(targetIndex));
                System.out.println(dashes);
            }
            else {
                storage.add(new Task(curInput));
                System.out.println(dashes);
                System.out.println(spacingIndent
                        + " " + "added: " + curInput);
                System.out.println(dashes);
            }
        }

        System.out.println(dashes);
        System.out.println(exitMsg);
        System.out.println(dashes);

    }
}
