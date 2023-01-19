import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        while (true) {
            String data = scanner.nextLine();
            if (data.equals("bye")) {
                System.out.println("Goodbye! It's been a pleasure talking to you!");
                scanner.close();
                break;
            } else if (data.equals("todo") || data.equals("deadline") || data.equals("event") || data.equals("todo ") || data.equals("deadline ") || data.equals("event ")) {
                try {
                    if (data.endsWith(" ")) {
                        data = data.substring(0, data.length() - 1);
                    }
                    throw new RuntimeException("Oops!!!! The description of a " + data + " can't be left blank!\n");
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (data.equals("mark") || data.equals("unmark") || data.equals("mark ") || data.equals("unmark ") || data.equals("delete") || data.equals("delete ")) {
                try {
                    if (data.endsWith(" ")) {
                        data = data.substring(0, data.length() - 1);
                    }
                    throw new RuntimeException("Oops!!!! The target for " + data + " can't be left blank!\n");
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (data.equals("list")) {
                System.out.println(taskList);
            } else if (data.startsWith("mark ")) {
                try {
                    String marked_index = data.substring(5);
                    System.out.println(taskList.markTask(marked_index));
                } catch (NumberFormatException e) {
                    System.out.println("Target was not a number!\n");
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (data.startsWith("unmark ")) {
                try {
                    String unmarked_index = data.substring(7);
                    System.out.println(taskList.unMarkTask(unmarked_index));
                } catch (NumberFormatException e) {
                    System.out.println("Target was not a number!");
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (data.startsWith("delete ")) {
                try {
                    String deleted_index = data.substring(7);
                    System.out.println(taskList.deleteTask(deleted_index));
                } catch (NumberFormatException e) {
                    System.out.println("Target was not a number!");
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (data.startsWith("todo ") || data.startsWith("deadline ") || data.startsWith("event ")) {
                try {
                    Task task = Task.makeTask(data);
                    System.out.println(taskList.addTask(task));
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (data.equals("")) {
                continue;
            } else {
                try {
                    throw new RuntimeException("Huh? I don't know what that means :(\n");
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
