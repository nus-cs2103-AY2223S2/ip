import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> history = new ArrayList<>(100);
        String input;
        String sep =  "-----------------------------------------\n";
        String greeting = sep + "Hello! I'm Duke, what can I do for you?\n" + sep;
        String goodbye = sep + "Bye! Hope to see you again soon!\n" + sep;

        System.out.println(greeting);

        while (true) {
            input = sc.nextLine();
            String[] tokens = input.split(" ", 2);
            String command = tokens[0];
            try {
                if (command.equals("mark")) {
                    if (tokens.length != 2) {
                        throw new DukeException("Please enter an index to mark!");
                    }
                    try {
                        int index = Integer.parseInt(tokens[1]);
                        if (index <= 0 || index > history.size()) {
                            throw new DukeException("Not a valid index!");
                        }
                        history.get(index - 1).markDone();
                        System.out.println(sep + "I've marked this task as done:\n" + history.get(index - 1)
                                    + "\n" + sep);
                    }
                    catch (NumberFormatException nfe) {
                        throw new DukeException("Index must be an integer!");
                    }
                }
                else if (command.equals("unmark")) {
                    if (tokens.length != 2) {
                        throw new DukeException("Please enter an index to unmark!");
                    }
                    try {
                        int index = Integer.parseInt(tokens[1]);
                        if (index <= 0 || index > history.size()) {
                            throw new DukeException("Not a valid index!");
                        }
                        history.get(index - 1).markUndone();
                        System.out.println(sep + "I've unmarked this task as not done yet:\n" + history.get(index - 1)
                                + "\n" + sep);
                    }
                    catch (NumberFormatException nfe) {
                        throw new DukeException("Index must be an integer!");
                    }
                }
                else if (command.equals("list")) {
                    System.out.print(sep);
                    for (int i = 0; i < history.size(); i++) {
                        System.out.println((i + 1) + ". " + history.get(i));
                    }
                    System.out.print(sep);
                }
                else if (command.equals("todo")) {
                    if (tokens.length != 2) {
                        throw new DukeException("Please enter a todo task!");
                    }

                    history.add(new Todo(tokens[1]));
                    System.out.println(sep +
                            "added: " + history.get(history.size() - 1) + "\n" +
                            "Now you have " + history.size() + " task(s) in the list.\n" +
                            sep);
                }
                else if (command.equals("deadline")) {
                    if (tokens.length != 2) {
                        throw new DukeException("Please enter a deadline task!");
                    }

                    String[] taskTime = tokens[1].split("/", 2);
                    history.add(new Deadline(taskTime[0], String.join(",", taskTime[1].split("/"))));
                    System.out.println(sep +
                            "added: " + history.get(history.size() - 1) + "\n" +
                            "Now you have " + history.size() + " task(s) in the list.\n" +
                            sep);

                }
                else if (command.equals("event")) {
                    if (tokens.length != 2) {
                        throw new DukeException("Please enter an event task!");
                    }

                    String[] taskTime = tokens[1].split("/", 2);
                    history.add(new Event(taskTime[0], String.join(",", taskTime[1].split("/"))));
                    System.out.println(sep +
                            "added: " + history.get(history.size() - 1) + "\n" +
                            "Now you have " + history.size() + " task(s) in the list.\n" +
                            sep);

                }
                else if (command.equals("bye")) {
                    System.out.println(goodbye);
                    break;
                }
                else {
                    throw new DukeException("Not a valid command!");
                }
            }
            catch (DukeException de) {
                System.out.println(de);
            }



        }


    }

}

