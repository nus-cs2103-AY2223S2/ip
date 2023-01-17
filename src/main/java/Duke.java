import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public enum ParseFunctions {
        SPLIT_ALL, TODO, DEADLINE, EVENT
    }
    public static ArrayList<Task> taskStore = new ArrayList<>();

    private static Task getTaskForMarking(String[] parsed) {
        int completedIndex = Integer.parseInt(parsed[1]) - 1; // index of the task completed
        Task completedTask = Duke.taskStore.get(completedIndex); // actual task
        return completedTask;
    }

    public static String[] parser(String input, ParseFunctions parse_type) {
        switch (parse_type) {
            case SPLIT_ALL:
                return input.split(" ");
            case TODO:
                return input.split(" ", 2); // split into 2
            case DEADLINE:
                String[] otherArgs = input.split(" ", 2);
                String[] taskAndTime = otherArgs[1].split(" /by ", 2);
                return new String[]{otherArgs[0], taskAndTime[0], taskAndTime[1]};
            case EVENT:
                otherArgs = input.split(" ", 2);
                taskAndTime = otherArgs[1].split(" /from ", 2);
                String[] toTime = taskAndTime[1].split(" /to ", 2);
                return new String[]{otherArgs[0], taskAndTime[0], toTime[0], toTime[1]};
        }
        return null;
    }
    public static int countTasks() {
        return Duke.taskStore.size();
    }

    public static void main(String[] args) {
        System.out.println("  insert ingenious greeting here");

        label:
        while (true) {
            Scanner myScanner = new Scanner(System.in);
            String command = myScanner.nextLine();

            String[] toFindFirstWord = parser(command, ParseFunctions.SPLIT_ALL);

            String first = toFindFirstWord[0];

            switch (first) {
                case "bye":
                    System.out.println("  Bye. Hope to see you soon again!");
                    break label;
                case "mark": {
                    Task completedTask = getTaskForMarking(toFindFirstWord);
                    completedTask.changeCompletion();

                    System.out.println("  You are done with: ");
                    System.out.println("    " + completedTask.toString());
                    break;
                }
                case "unmark": {
                    Task completedTask = getTaskForMarking(toFindFirstWord);
                    completedTask.changeCompletion();

                    System.out.println("  OK, continue working on: ");
                    System.out.println("    " + completedTask.toString());
                    break;
                }
                case "deadline": {
                    String[] parsed = parser(command, ParseFunctions.DEADLINE);
                    Task newDeadline = new Deadline(parsed[1], parsed[2]);
                    Duke.taskStore.add(newDeadline);

                    System.out.println("  new task added!");
                    System.out.println("    " + newDeadline.toString());
                    System.out.println("  Now you have " + String.valueOf(Duke.countTasks()) +
                            " tasks in the list!");
                    break;
                }
                case "event": {
                    String[] parsed = parser(command, ParseFunctions.EVENT);
                    Task newEvent = new Event(parsed[1], parsed[2], parsed[3]);
                    Duke.taskStore.add(newEvent);

                    System.out.println("  new event added!");
                    System.out.println("    " + newEvent.toString());
                    System.out.println("  Now you have " + String.valueOf(Duke.countTasks()) +
                            " tasks in the list!");
                    break;
                }
                case "todo": {
                    String[] parsed = parser(command, ParseFunctions.TODO);
                    ToDo newToDo = new ToDo(parsed[1]);
                    Duke.taskStore.add(newToDo);

                    System.out.println("  new todo added!");
                    System.out.println("    " + newToDo.toString());
                    System.out.println("  Now you have " + String.valueOf(Duke.countTasks()) +
                            " tasks in the list!");
                    break;
                }
                case "list":
                    for (int i = 0; i < taskStore.size(); i++) {
                        System.out.println("  " + String.valueOf(i + 1) + ". " + taskStore.get(i));
                    }
                    break;
                default:
                    System.out.println("error");
                    break;
            }
        }
    }
}
