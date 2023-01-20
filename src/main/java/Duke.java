import tasks.*;
import java.util.*;
public class Duke {
    static ArrayList<Task> todoList;
    static String separator = "\u200E✽ ✾ \u200E✽ ✾ \u200E✽ ✾ \u200E✽ ✾";
    static String invalidIndexMessage = "⚠ oh no...there's no task with this number\ntry asking for 'list' to see what numbers there are\n" + separator;
    static String forgottenArgumentMessage = "⚠ oops...seems like you forgot a description, please try again\n" + separator;
    static String unknownCommandMessage = "⚠ oh no...we don't know what that means, please try again\n" + separator;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        todoList = new ArrayList<>();

        System.out.println("｡ﾟﾟ･｡･ﾟﾟ｡\n" + "。 welcome to tigerlily to-do\n" + "　ﾟ･｡･ﾟ\n" + "✎ . . . . write a command");

        while(s.hasNext()) {
            try {
                String input = s.nextLine();

                if (input.equals("bye")) {
                    System.out.println("(\\\\ (\\\\ \n" + "(„• ֊ •„)\n" + "━━O━O━━━━━━━━━━━━━━━\n" +
                            "bye, see you again soon!\n" + "━━━━━━━━━━━━━━━━━━━━\n");
                    break;
                } else if (input.equals("list")) {
                    System.out.println(separator + "\nhere's your list:");
                    for (int i = 0; i < todoList.size(); i++) {
                        Task thisTask = todoList.get(i);
                        System.out.println((i + 1) + "." + thisTask.toString());
                    }
                    System.out.println(separator);

                } else if (input.startsWith("todo")) {
                    try {
                        String description = input.substring(5);
                        ToDo newToDo = new ToDo(description);
                        addTask(newToDo);
                    } catch(StringIndexOutOfBoundsException e) {
                        throw new DukeExceptions(forgottenArgumentMessage);
                    }
                } else if (input.startsWith("deadline")) {
                    String[] s1 = input.substring(9).split("/by");
                    Deadline newDeadline = new Deadline(s1[0].trim(), s1[1].trim());
                    addTask(newDeadline);

                } else if (input.startsWith("event")) {
                    String[] s1 = input.substring(6).split("/from");
                    String[] s2 = s1[1].split("/to");
                    Event newEvent = new Event(s1[0].trim(), s2[0].trim(), s2[1].trim());
                    addTask(newEvent);

                } else if (input.startsWith("mark")) {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    if(index >= todoList.size()) {
                        throw new DukeExceptions(invalidIndexMessage);
                    } else {
                        Task thisTask = todoList.get(index);
                        thisTask.markDone();
                        message("\nwell done! you've completed this task: " + thisTask + "\n");
                    }

                } else if (input.startsWith(("unmark"))) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if(index >= todoList.size()) {
                        throw new DukeExceptions(invalidIndexMessage);
                    } else {
                        Task thisTask = todoList.get(index);
                        thisTask.unmarkDone();
                        message("\noops...this task is now marked as not done yet: " + thisTask + "\n");
                    }
                } else {
                    throw new DukeExceptions(unknownCommandMessage);
                }
            } catch(DukeExceptions e) {
                System.out.println(e);
            }
        }
        s.close();
    }

    public static void addTask(Task task) {
        todoList.add(task);
        message("\nperf, your task has been added:\n" + task.toString() + "\nthere are now  " + todoList.size() + " tasks in the list\n");
    }

    public static void message(String message) {
        System.out.println(separator + message + separator);
    }
}