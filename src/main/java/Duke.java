import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    // Length of horizontal line
    private static final int HORIZONTAL_LINE_LENGTH = 80;

    public static void printHorizontalLine() {
        for (int i = 0; i < HORIZONTAL_LINE_LENGTH; i++) {
            System.out.print("\u2500");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        // Greeting
        printHorizontalLine();
        System.out.println(
            "Karen:\n" +
            "Can I speak to your manager?\n" +
            "Just kidding, this is Karen. How can I help you today?"
        );
        printHorizontalLine();

        // Commands
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            printHorizontalLine();
            System.out.println("Karen:");
            try {
                if (command.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        Task currTask = tasks.get(i);
                        System.out.println((i + 1) + ". " + currTask.toString());
                    }
                } else if (command.contains("mark") || command.contains("unmark")) {
                    int taskNumber = Integer.parseInt(command.split(" ")[1]);
                    String action = command.split(" ")[0];
                    Task currTask = tasks.get(taskNumber - 1);

                    if (action.equals("mark")) {
                        currTask.markAsDone();
                        System.out.println(
                            "Congrats, I guess you get a medal?\n" +
                            currTask.toString()
                        );
                    } else if (action.equals("unmark")) {
                        currTask.markAsNotDone();
                        System.out.println(
                            "Why are you so lazy?\n" +
                            currTask.toString()
                        );
                    }
                } else {
                    String type = command.split(" ")[0];
                    String[] commandArr = command.split(" ", 2);

                    switch (type) {
                        case "todo":
                            if (commandArr.length == 1) {
                                throw new DukeException("How are you gonna do an empty todo?");
                            }

                            String todoDescription = commandArr[1];
                            Todo todo = new Todo(todoDescription);
                            tasks.add(todo);
                            System.out.println(
                                "You better finish this soon:\n" +
                                todo.toString() +
                                "\nCan you finish all " + tasks.size() + " tasks in your list?"
                            );
                            break;
                        case "deadline":
                            if (commandArr.length == 1) {
                                throw new DukeException("Deadline for...?");
                            }

                            String[] deadlineArr = commandArr[1].split(" /by ");

                            if (deadlineArr.length == 1) {
                                throw new DukeException("What's the deadline for your task??");
                            }

                            Deadline deadline = new Deadline(deadlineArr[0], deadlineArr[1]);
                            tasks.add(deadline);
                            System.out.println(
                                "You better finish this soon:\n" +
                                deadline.toString() +
                                "\nCan you finish all " + tasks.size() + " tasks in your list?"
                            );
                            break;
                        case "event":
                            if (commandArr.length == 1) {
                                throw new DukeException("What event is this??");
                            }

                            String[] eventArr = commandArr[1].split(" /from ");

                            if (eventArr.length == 1) {
                                throw new DukeException("When does your event start??");
                            }

                            String[] fromToArr = eventArr[1].split(" /to ");

                            if (fromToArr.length == 1) {
                                throw new DukeException("When does your event end??");
                            }

                            Event event = new Event(eventArr[0], fromToArr[0], fromToArr[1]);
                            tasks.add(event);
                            System.out.println(
                                "You better finish this soon:\n" +
                                event.toString() +
                                "\nCan you finish all " + tasks.size() + " tasks in your list?"
                            );
                            break;
                        default:
                            throw new DukeException("Sorry I don't understand what you're talking about.");
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Can you please double check your task number?");
            } catch (NumberFormatException e) {
                System.out.println("Can you please pass in a number?");
            }

            printHorizontalLine();
            command = sc.nextLine();
        }

        // Exit
        printHorizontalLine();
        System.out.println("Karen:\n" + "Bye. This was of great inconvenience.");
        printHorizontalLine();
    }
}
