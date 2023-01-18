import java.util.Scanner;
import java.util.ArrayList;

public class James {
    public static void main(String[] args) {
        ArrayList<Task> inputs = new ArrayList<>();
        System.out.println("Hello! I'm James.");
        System.out.println("How can I help you today?");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you soon!");
                break;
            }
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < inputs.size(); i++) {
                    System.out.println((i + 1) + "." + "[" + inputs.get(i).getStatusIcon() + "] " + inputs.get(i).getDescription());
                }
            } else if(input.startsWith("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < inputs.size()) {
                    inputs.get(index).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + inputs.get(index).getStatusIcon() + "]" + inputs.get(index).getDescription());
                }
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < inputs.size()) {
                    inputs.get(index).markAsUnDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + inputs.get(index).getStatusIcon() + "]" + inputs.get(index).getDescription());
                }
            }
            else {
                inputs.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
        scanner.close();
    }

}
