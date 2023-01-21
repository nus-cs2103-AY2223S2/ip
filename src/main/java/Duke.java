import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Chiwa, your personal chatbot (◔◡◔✿)");
        System.out.println("What can I do for you today?");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        label:
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] inputs = input.split(" ", 2);
            String command = inputs[0];

            switch (command) {
                case "bye":
                    System.out.println("Bye~ Hope to see you again soon!");
                    break label;
                case "list":
                    StringBuilder reply = new StringBuilder();
                    for (int i = 0; i < tasks.size(); i++) {
                        reply.append(String.format("%d. " + tasks.get(i) + "\n", i + 1));
                    }
                    System.out.println(reply);
                    break;
                case "mark":
                case "unmark":
                    if (inputs.length <= 1) {
                        System.out.println("Please input the number of the task you want to mark or unmark!");
                    } else {
                        String number = inputs[1];
                        int num = Integer.parseInt(number);
                        if (num > tasks.size()) {
                            System.out.printf("Task %d does not exist!%n", num);
                        } else {
                            Task t = tasks.get(num - 1);
                            if (command.equals("mark")) {
                                t.mark();
                                System.out.println("Congratulations for completing the task ^^ I've marked it as done:");
                                System.out.println(t);
                            } else {
                                t.unmark();
                                System.out.println("Ok, I've unmarked the task for you:");
                                System.out.println(t);
                            }
                        }

                    }
                    break;
                default:  // adding new task
                    Task task = new Task(input);
                    tasks.add(task);
                    System.out.println("task added: " + input + "\n");
                    break;
            }
        }
        scanner.close();
    }
}
