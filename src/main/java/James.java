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
                    System.out.println((i + 1) + "." + inputs.get(i));
                }
            }
            else if(input.startsWith("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < inputs.size()) {
                    inputs.get(index).markAsDone();
                    System.out.println(inputs.get(index).toString());
                }
            }
            else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < inputs.size()) {
                    inputs.get(index).markAsUnDone();
                    System.out.println(inputs.get(index).toString());
                }
            }
            else if (input.startsWith("todo")) {
                try {
                    input = input.replaceAll("todo", "");
                    inputs.add(new ToDo(input));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + inputs.get(inputs.size() - 1));
                    System.out.println("Now you have " + inputs.size() + " tasks in the list.");
                }   catch(JamesException e) {
                    System.out.println(e.getMessage());
                }
            }
            else if (input.startsWith("deadline")) {
                try {
                input = input.replaceAll("deadline", "");
                String[] parts = input.split("/", 2);
                if (parts.length != 2) {
                    throw new JamesException("OOPS!!! The description of a deadline task cannot be empty.");
                }
                inputs.add(new Deadline(parts[0], parts[1]));
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + inputs.get(inputs.size() - 1));
                System.out.println("Now you have " + inputs.size() + " tasks in the list.");
            }   catch(JamesException e) {
                    System.out.println(e.getMessage());
                }
            }
            else if(input.startsWith("event")) {
               try {
                   input = input.replaceAll("event", "");
                   String[] parts = input.split("/", 3);
                   if (parts.length != 3) {
                       throw new JamesException("OOPS!!! The description of a event cannot be empty.");
                   }
                   inputs.add(new Event(parts[0], parts[1], parts[2]));
                   System.out.println("Got it. I've added this task:");
                   System.out.println("  " + inputs.get(inputs.size() - 1));
                   System.out.println("Now you have " + inputs.size() + " tasks in the list.");
               } catch(JamesException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        scanner.close();
    }

}

