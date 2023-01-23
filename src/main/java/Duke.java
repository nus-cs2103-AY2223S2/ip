import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static Task parse(String[] inputs) throws DukeException {
        String taskType = inputs[0];
        Task task;
        if (inputs.length <= 1) {
            throw new DukeException("Oops!! The description of a " + taskType + " cannot be empty!");
        } else {
            String rest = inputs[1];

            if (taskType.equals("todo")) {
                task = new Todo(rest);
            } else if (taskType.equals("deadline")) {
                String[] addon = rest.split(" /", 2);
                String description = addon[0];
                if (addon.length <= 1) {
                    throw new DukeException("Oops!! Please indicate when the deadline is by!");
                } else {
                    String byString = addon[1];
                    String[] byPart = byString.split(" ", 2);
                    String by = byPart[1];
                    task = new Deadline(description, by);
                }
            } else {
                String[] addon = rest.split(" /", 3);
                String description = addon[0];
                if (addon.length <= 1) {
                    throw new DukeException("Oops!! Please indicate when the event will be at using /from and /to!");
                } else {
                    String fromString = addon[1];
                    String[] fromPart = fromString.split(" ", 2);
                    String from = fromPart[1];
                    String toString = addon[2];
                    String[] toPart = toString.split(" ", 2);
                    String to = toPart[1];
                    task = new Event(description, from, to);
                }
            }
        }
        return task;
    }

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

            try {
                switch (command) {
                    case "bye":
                        System.out.println("Bye~ Hope to see you again soon!");
                        break label;
                    case "list":
                        StringBuilder reply = new StringBuilder();
                        for (int i = 0; i < tasks.size(); i++) {
                            reply.append(String.format("%d. " + tasks.get(i) + "\n", i + 1));
                        }
                        System.out.print(reply);
                        break;
                    case "mark":
                    case "unmark":
                    case "delete":
                        if (inputs.length <= 1) {
                            throw new DukeException("Please input the numbering of the task you want to" + command + "as well!");
                        } else {
                            String number = inputs[1]; // might have Number Format Exception here
                            int num = Integer.parseInt(number);
                            if (num > tasks.size()) {
                                String errorMessage = String.format("Task %d does not exist!", num);
                                throw new DukeException(errorMessage);
                            } else {
                                Task t = tasks.get(num - 1);
                                if (command.equals("mark")) {
                                    t.mark();
                                    System.out.println("Congratulations for completing the task ^^ I've marked it as done:");
                                    System.out.println(t);
                                } else if (command.equals("unmark")) {
                                    t.unmark();
                                    System.out.println("Ok, I've unmarked the task for you:");
                                    System.out.println(t);
                                } else {
                                    // deleting a task
                                    System.out.println("Ok, I've deleted the following task for you:");
                                    System.out.println(t);
                                    tasks.remove(num - 1);
                                    System.out.printf("You now have %d task(s) in your list!\n", tasks.size());
                                }
                            }

                        }
                        break;
                    case "todo":
                    case "deadline":
                    case "event": // adding new task
                        // Have 3 types of tasks: todo, deadline and event
                        Task t = Duke.parse(inputs);
                        tasks.add(t);
                        System.out.println("Ok, I've added this task:");
                        System.out.println(t);
                        System.out.printf("You now have %d task(s) in your list!\n", tasks.size());
                        break;
                    default:
                        throw new DukeException("Sorry I don't understand this command! :(");

                }
            } catch(DukeException e) {
                System.out.println(e.getMessage());
            } catch(NumberFormatException e) {
                System.out.println(e.getStackTrace());
            }
        }
        scanner.close();
    }
}
