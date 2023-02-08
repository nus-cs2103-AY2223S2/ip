import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;


public class Duke {
    static private final String MAKE_DEADLINE = "deadline";
    static private final String MAKE_TODO = "todo";
    static private final String MAKE_EVENT = "event";
    static private final String DUE_DATE_FLAG = "/by ";
    static private final String EVENT_START_FLAG = "/from ";
    static private final String EVENT_END_FLAG = "/to ";

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String logo = " _____  _               _____   _\n"
                + "|  ___|| |     _    _  |  ___| | |  _\n"
                + "| |    | |    | |  | | | |     | |/ /\n"
                + "| |___ | |___ | |__| | | |___  |    \\\n"
                + "|_____||_____||______| |_____| |_| \\_\\\n";

        System.out.println(logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Howdy! I'm Cluck!\n" +
                "    What can I cluck-a-doodle-do for you?\n");
        System.out.println("    ____________________________________________________________");

        boolean loop = true;
        List<Task> toDoList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (loop) {
            String input = sc.nextLine();
            String[] words = input.split(" ");

                switch (words[0]) {
                case "bye":
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Buh-cluck, see ya!");
                    System.out.println("    ____________________________________________________________");
                    loop = false;
                    break;

                case "list":
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < toDoList.size(); i++) {
                        System.out.println("    " + (i + 1) + ": " + toDoList.get(i).toString());
                    }
                    System.out.println("    ____________________________________________________________");
                    break;

                case "mark":
                    if (words.length == 1) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    Mucka blucka - Buh cluck! Which task do you wanna mark?   ");
                        System.out.println("    ____________________________________________________________");
                    }
                    else if (isNumeric(words[1])) {
                        Integer itemNumber = Integer.parseInt(words[1]);
                        if (itemNumber > toDoList.size() || itemNumber <= 0) {
                            System.out.println("    ____________________________________________________________");
                            System.out.println("    That's not...? In the list...? Buh caw?");
                            System.out.println("    ____________________________________________________________");
                        } else {
                            toDoList.get(itemNumber - 1).mark();
                            System.out.println("    ____________________________________________________________");
                            System.out.println(String.format("    Marked it! Cluck-a-doodle-done!\n     %s", toDoList.get(itemNumber - 1).toString()));
                            System.out.println("    ____________________________________________________________");
                        }
                    } else {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    Ya gotta give me a working number, bucko!");
                        System.out.println("    ____________________________________________________________");
                    }
                    break;

                case "unmark":
                    if (words.length == 1) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    Which task do you wanna unmark? Muckah buck!");
                        System.out.println("    ____________________________________________________________");
                    } else if (isNumeric(words[1])) {
                        Integer itemNumber = Integer.parseInt(words[1]);
                        if (itemNumber > toDoList.size() || itemNumber <= 0) {
                            System.out.println("    ____________________________________________________________");
                            System.out.println("    That's not...? In the list...? Buh caw?");
                            System.out.println("    ____________________________________________________________");
                        } else {
                            toDoList.get(itemNumber - 1).unmark();
                            System.out.println("    ____________________________________________________________");
                            System.out.println(String.format("    Unmarked it! Cluckiddy cluck!\n     %s", toDoList.get(itemNumber - 1).toString()));
                            System.out.println("    ____________________________________________________________");
                        }
                    } else {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    Ya gotta give me a working number, bucko!");
                        System.out.println("    ____________________________________________________________");
                    }
                    break;

                case MAKE_TODO:
                    Task newTodo = new ToDo(input.substring(5));
                    toDoList.add(newTodo);
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    added todo: \n    " + newTodo.toString());
                    System.out.println("    Now there's " + toDoList.size() + " items in your list!");
                    System.out.println("    ____________________________________________________________");
                    break;

                case MAKE_DEADLINE:
                    String body = input.substring(9);
                    if (body.contains(DUE_DATE_FLAG)) {
                        String[] fields = body.split(DUE_DATE_FLAG);
                        String description = fields[0];
                        String dueDate = fields[1];
                        Task currDeadline = new Deadline(description, dueDate);
                        toDoList.add(currDeadline);
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    added deadline: " + currDeadline.toString());
                        System.out.println("    Now there's " + toDoList.size() + " items in your list!");
                        System.out.println("    ____________________________________________________________");
                        break;
                    }

                    System.out.println("    ____________________________________________________________");
                    System.out.println("    You're missing the '/by' flag, bucko!");
                    System.out.println("    ____________________________________________________________");
                    break;

                case MAKE_EVENT:
                    String substring = input.substring(6);
                    if (substring.contains(EVENT_START_FLAG) && substring.contains(EVENT_END_FLAG)) {
                        String[] fields = substring.split("/\\w{2,4}\\s");
                        System.out.println(Arrays.toString(fields));
                        Task currEvent = new Event(fields[0], fields[1], fields[2]);
                        toDoList.add(currEvent);
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    added event: " + currEvent.toString());
                        System.out.println("    Now there's " + toDoList.size() + " items in your list!");
                        System.out.println("    ____________________________________________________________");
                        break;
                    }
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    You're missing the either the '/from' or '/to' flag, or both! Buhcock!");
                    System.out.println("    ____________________________________________________________");
                    break;

                case "delete":
                    if (words.length == 1) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    Mucka blucka - Buh cluck! Which task do you wanna delete?   ");
                        System.out.println("    ____________________________________________________________");
                    }
                    else if (isNumeric(words[1])) {
                        Integer itemNumber = Integer.parseInt(words[1]);
                        if (itemNumber > toDoList.size() || itemNumber <= 0) {
                            System.out.println("    ____________________________________________________________");
                            System.out.println("    That's not...? In the list...? Buh caw?");
                            System.out.println("    ____________________________________________________________");
                        } else {
                            System.out.println("    ____________________________________________________________");
                            System.out.println(String.format("   Buh cuck! Removed the following:\n     %s", toDoList.get(itemNumber - 1).toString()));
                            System.out.println("    ____________________________________________________________");
                            toDoList.remove(itemNumber - 1);
                        }
                    } else {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    Ya gotta give me a working number, bucko!");
                        System.out.println("    ____________________________________________________________");
                    }
                    break;


                default:
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    You gotta give me a command!");
                    System.out.println("    ____________________________________________________________");

                }
        }
    }
}
