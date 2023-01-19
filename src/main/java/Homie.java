import java.util.List;
import java.util.ArrayList;

import java.util.Scanner;

public class Homie {

    private static final List<Task> taskList = new ArrayList<>();

    public static void print(String s) {
        System.out.println(s);
    }

    public static void listTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            Homie.print("   > " + (i + 1) + ". " + task.toString());
        }
    }

    public static void modifyTask(String[] command) throws NoTaskException {

        if (command.length < 2) {
            throw new NoTaskException("");
        }

        String operation = command[0];
        int idx = Integer.parseInt(command[1]);
        Task t = taskList.get(idx - 1);

        if (operation.equals("mark")) {
            t.markAsDone();
            Homie.print("   > Task masked as done: " + t);
        } else {
            t.markAsUndone();
            Homie.print("   > Task masked as undone: " + t);
        }
    }

    public static void addTask(String[] command) throws HomieException {

        if (command.length < 2) {
            throw new EmptyDescriptionException("");
        }

        String taskType = command[0];
        String description = command[1];

        switch (taskType) {
            case "todo":
                Task todo = new ToDo(description);
                taskList.add(todo);
                Homie.print("   > Got chu homie, task added: " + todo);
                break;

            case "deadline":
                String[] deadlineDescription = description.split("/by", 2);

                Task deadline = new Deadline(deadlineDescription[0], deadlineDescription[1]);
                taskList.add(deadline);
                Homie.print("   > Got chu homie, task added: " + deadline);
                break;

            case "event":
                String[] eventDescription = description.split("/from", 2);

                // Parse the string to get to and from dates of the event
                String[] fromAndTo = eventDescription[1].split("/to", 2);
                String from = fromAndTo[0];
                String to = fromAndTo[1];

                Task event = new Event(eventDescription[0], from, to);
                taskList.add(event);
                Homie.print("   > Got chu homie, task added: " + event);
                break;
        }

        Homie.print("   > Chu have " + taskList.size() + " tasks in the list.");

    }

    public static void shutdown() {
        Homie.print("   > Aight imma head out");
    }

    public static void deleteTask(String[] command) throws NoTaskException {

        if (command.length < 2) {
            throw new NoTaskException("");
        }

        int idx = Integer.parseInt(command[1]);
        Task t = taskList.get(idx - 1);
        taskList.remove(t);
        Homie.print("   > Task deleted: " + t);
        Homie.print("   > Chu have " + taskList.size() + " tasks remained.");
    }

    public static void interact() {
        String input;
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                input = sc.nextLine();

                // If input is bye, terminate
                if (input.equals("bye")) {
                    Homie.shutdown();
                    break;
                }

                // If input is list, list out the content in task list
                if (input.equals("list")) {
                    Homie.listTasks();
                    continue;
                }

                // Split strings into 2, first part is the instruction, 2nd part is the description
                String[] command = input.split(" ", 2);

                // If command is to mark or unmark task, do accordingly
                if (command[0].equals("mark") || command[0].equals("unmark")) {
                    Homie.modifyTask(command);
                    continue;
                }

                // Else input is a task, add to task list
                if (command[0].equals("todo") || command[0].equals("deadline") || command[0].equals("event")) {
                    Homie.addTask(command);
                    continue;
                }

                if (command[0].equals("delete")) {
                    Homie.deleteTask(command);
                    continue;
                }
                // If reached here, bot do not understand
                throw new IndecipherableTextException("");

            } catch (HomieException e) {
                Homie.print(e.toString());
            }
        }
    }

    public static void main(String[] args) {

        Homie.print("   > What's up dawg");
        Homie.interact();
    }
}
