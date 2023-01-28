import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.toString());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

//    public static void main(String[] args) {
//        Storage storage = new Storage();
//        ArrayList<Task> tasks = new ArrayList<>();
//
//        try {
//            tasks = storage.loadTasks();
//        } catch (IOException e) {
//            System.out.println("\t" + e);
//        }
//
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Hello, I am Duke. \nWhat can I do for you?");
//
//        String input = scanner.nextLine();
//
//        while (!input.equalsIgnoreCase("bye")) {
//            try {
//                if (input.equalsIgnoreCase("list")) {
//                    for (int i = 0; i < tasks.size(); i++) {
//                        System.out.println(i + 1 + ". " + tasks.get(i));
//                    }
//                } else if (input.startsWith("mark ")) {
//                    String number = input.substring(5);
//                    int index = Integer.parseInt((number)) - 1;
//                    if (index+1 == 0) {
//                        throw new DukeException("cannot mark a number not in the list!");
//                    }
//                    Task task = tasks.get(index);
//                    task.mark();
//                    storage.saveTasks(tasks);
//                    System.out.println("Nice! I've marked this task as done:");
//                    System.out.println(task);
//                } else if (input.startsWith("unmark ")) {
//                    String number = input.substring(7);
//                    int index = Integer.parseInt((number)) - 1;
//                    if (index+1 == 0) {
//                        throw new DukeException("cannot unmark a number not in the list!");
//                    }
//                    Task task = tasks.get(index);
//                    task.unmark();
//                    storage.saveTasks(tasks);
//                    System.out.println("OK, I've marked this task as not done yet:");
//                    System.out.println(task);
//                } else if (input.startsWith("delete ")) {
//                    String number = input.substring(7);
//                    int index = Integer.parseInt((number)) - 1;
//                    Task task = tasks.get(index);
//                    tasks.remove(index);
//                    storage.saveTasks(tasks);
//                    System.out.println("Noted. I've removed this task: \n" + task);
//                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
//                } else if (input.startsWith("todo ")) {
//                    if (input.length() < 6) {
//                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
//                    }
//                    String taskName = input.substring(5);
//                    Todo todo = new Todo(taskName);
//                    tasks.add(todo);
//                    storage.saveTasks(tasks);
//                    System.out.println("Got it. I've added this task: \n" + todo);
//                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
//                } else if (input.startsWith("deadline ")) {
//                    int dash_index = input.indexOf("/");
//                    String taskName = input.substring(9, dash_index);
//                    String by = input.substring(dash_index + 4);
//                    Deadline deadline = new Deadline(taskName, by);
//                    tasks.add(deadline);
//                    storage.saveTasks(tasks);
//                    System.out.println("Got it. I've added this task: \n" + deadline);
//                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
//                } else if (input.startsWith("event ")) {
//                    int first_dash_index = input.indexOf("/");
//                    int second_dash_index = input.lastIndexOf("/");
//                    String taskName = input.substring(6, first_dash_index);
//                    String from = input.substring(first_dash_index + 6, second_dash_index);
//                    String to = input.substring(second_dash_index + 4);
//                    Event event = new Event(taskName, from, to);
//                    tasks.add(event);
//                    storage.saveTasks(tasks);
//                    System.out.println("Got it. I've added this task: \n" + event);
//                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
//                } else {
//                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//                }
//            } catch (DukeException e) {
//                System.out.println(e.getMessage());
//            }
//
//            input = scanner.nextLine();
//        }
//
//        System.out.println("Bye. Hope to see you again soon!");
//
//        scanner.close();
//    }
}
