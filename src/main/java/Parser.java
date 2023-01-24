public class Parser {
    TaskList tl;
    Storage storage;

    Parser(TaskList tl, Storage storage) {
        this.tl = tl;
        this.storage = storage;
    };

    public void send(String input) {
        // Handle control flow
        switch (input) {
            case "bye":
                // Exit Duke
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            case "list":
                // List tasks
                System.out.println(tl.toString());
                break;
            default:
                if (input.matches("occurs .*")) {
                    // Handle occurs
                    String dateTime = input.substring(7);

                    // Print tasks
                    try {
                        tl.printTasksOnDate(dateTime);
                    } catch (InvalidDateFormatException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (input.matches("mark \\d+")) {
                    // Marking a task as done
                    Integer idx = Integer.valueOf(input.split(" ")[1]) - 1;

                    // Verify if task number is invalid:
                    if (idx < 0 || idx >= tl.numberOfTasks()) {
                        System.out.println("Task number is invalid!");
                        break;
                    }

                    tl.markTask(idx);
                    System.out.println("Nice! I've marked this task as done:\n" + tl.getTask(idx));
                } else if (input.matches("unmark \\d+")) {
                    // Unmarking a task
                    Integer idx = Integer.valueOf(input.split(" ")[1]) - 1;

                    // Verify if task number is invalid:
                    if (idx < 0 || idx >= tl.numberOfTasks()) {
                        System.out.println("Task number is invalid!");
                        break;
                    }

                    tl.unmarkTask(idx);
                    System.out.println("OK, I've marked this task as not done yet:\n" + tl.getTask(idx));
                } else if (input.matches("delete \\d+")) {
                    // Deleting a task
                    Integer idx = Integer.valueOf(input.split(" ")[1]) - 1;

                    // Verify if task number is invalid:
                    if (idx < 0 || idx >= tl.numberOfTasks()) {
                        System.out.println("Task number is invalid!");
                        break;
                    }

                    Task t = tl.deleteTask(idx);
                    System.out.println("Noted. I've removed this task:\n" + t +
                            "\nNow you have " + tl.numberOfTasks() + (tl.numberOfTasks() > 1 ? " tasks" : " task") + " in the list.");
                } else  {
                    // Add task
                    try {
                        Task t = tl.addTask(input);
                        System.out.println("Got it. I've added this task:\n" + t +
                                "\nNow you have " + tl.numberOfTasks() + (tl.numberOfTasks() > 1 ? " tasks" : " task") + " in the list.");
                    } catch (CommandNotFoundException e) {
                        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    } catch (EmptyCommandException e) {
                        System.out.println("☹ OOPS!!! The description of a " + e.command + " cannot be empty.");
                    }
                }

                // Save tasks after every change to the list
                storage.save(this.tl);
        }
    }
}
