import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome to: \n" + logo);
        System.out.println(
                "    ____________________________________________________________" +
                "\n     Hello! I'm Duke" +
                "\n     What can I do for you?" +
                "\n    ____________________________________________________________");

        String input = sc.nextLine();
        String[] inputArr = input.split(" ");
        int selectedNum;
        int numTasks = 0;

        while (!inputArr[0].equalsIgnoreCase("bye")) {
            try {
                switch (inputArr[0]) {
                    case "list":
                        System.out.println("    ____________________________________________________________");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println("     " + (i + 1) + ". " + tasks.get(i).toString());
                        }
                        System.out.println("    ____________________________________________________________");
                        break;
                    case "todo":
                        String todoDesc;
                        try {
                            todoDesc = input.split(" ", 2)[1];
                        } catch (Exception e) {
                            throw new DukeException("Description of todo cannot be empty!!");
                        }
                        tasks.add(new ToDo(todoDesc));
                        numTasks++;
                        System.out.println(
                                "    ____________________________________________________________"
                                        + "\n     Got it. I've added this task:\n "
                                        + tasks.get(numTasks - 1).toString()
                                        + "\n     Now you have "
                                        + numTasks
                                        +" tasks in the list."
                                        + "\n    ____________________________________________________________");
                        break;
                    case "deadline":
                        String deadlineInput;
                        try {
                            deadlineInput = input.split(" ", 2)[1];
                        } catch (Exception e) {
                            throw new DukeException("Description of deadline cannot be empty!!");
                        }
                        String[] deadlineDesc = deadlineInput.split(" /by ");
                        tasks.add(new Deadline(deadlineDesc[0], deadlineDesc[1]));
                        numTasks++;
                        System.out.println(
                                "    ____________________________________________________________"
                                        + "\n     Got it. I've added this task:\n "
                                        + tasks.get(numTasks - 1).toString()
                                        + "\n     Now you have "
                                        + numTasks
                                        +" tasks in the list."
                                        + "\n    ____________________________________________________________");
                        break;
                    case "event":
                        String eventInput;
                        try {
                            eventInput = input.split(" ", 2)[1];
                        } catch (Exception e) {
                            throw new DukeException("Description of event cannot be empty!!");
                        }
                        String[] eventDescArr = eventInput.split(" /from ");
                        String eventDesc = eventDescArr[0];
                        String[] eventTimeArr = eventDescArr[1].split(" /to ");
                        String eventFrom = eventTimeArr[0];
                        String eventTo = eventTimeArr[1];
                        tasks.add(new Event(eventDesc, eventFrom, eventTo));
                        numTasks++;
                        System.out.println(
                                "    ____________________________________________________________"
                                        + "\n     Got it. I've added this task:\n "
                                        + tasks.get(numTasks - 1).toString()
                                        + "\n     Now you have "
                                        + numTasks
                                        +" tasks in the list."
                                        + "\n    ____________________________________________________________");
                        break;
                    case "mark":
                        selectedNum = Integer.parseInt(inputArr[1]) - 1;
                        tasks.get(selectedNum).markDone();
                        System.out.println(
                                "    ____________________________________________________________"
                                        + "\n     Nice! I've marked this task as done:"
                                        + tasks.get(selectedNum).toString()
                                        + "\n    ____________________________________________________________");
                        break;
                    case "unmark":
                        selectedNum = Integer.parseInt(inputArr[1]) - 1;
                        tasks.get(selectedNum).markUndone();
                        System.out.println(
                                "    ____________________________________________________________"
                                        + "\n     OK, I've marked this task as not done yet:"
                                        + tasks.get(selectedNum).toString()
                                        + "\n    ____________________________________________________________");
                        break;
                    case "delete":
                        int numToDelete;
                        try {
                            numToDelete = Integer.parseInt(input.split(" ", 2)[1]);
                        } catch (Exception e) {
                            throw new DukeException("Please enter a valid number to delete!");
                        }
                        Task selectedTask = tasks.get(numToDelete - 1);
                        tasks.remove(numToDelete - 1);
                        numTasks--;
                        System.out.println(
                                "    ____________________________________________________________"
                                        + "\n     OK, I've Deleted this task:"
                                        + selectedTask.toString()
                                        + "\n    ____________________________________________________________");
                        break;
                    default:
                        throw new DukeException("I don't get it!");
                }
            } catch (DukeException d) {
                System.out.println(d.getMessage());
            }

            input = sc.nextLine();
            inputArr = input.split(" ");
        }

        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");

        sc.close();

    }
}
