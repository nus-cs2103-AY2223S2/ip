import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /**
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         **/

        System.out.println("Hello Brother\nWelcome to Brother Bot\nWhats up what can I do for you mi amigo");

        Scanner inputScanner = new Scanner(System.in);
        String input;
        ArrayList<Task> storage = new ArrayList<>();

        while(true) {
            if (inputScanner.hasNextLine()) {
                input = inputScanner.nextLine();
                try {
                    validateInput(input, storage);
                } catch(DukeException x) {
                    System.out.println(x.getMessage());
                    continue;
                }

                // level-1 feature: exit when user types "bye"
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("ok see you brother all love no cringe!");
                    break;
                }

                // level-3 feature: use input to construct Task object and add to array + display array when required + mark Task as done
                if (input.equalsIgnoreCase("display")) {
                    System.out.println("Here you go my brother!" );
                    int i = 0;
                    for(Task task: storage) {
                        System.out.println((i + 1) + ". " + task.toString());
                        i++;
                    }
                    System.out.println("Anything else I can do for you top G" );
                } else if (input.length() >= 6 && input.substring(0,4).equalsIgnoreCase("mark")) {
                    int i = Integer.parseInt(input.substring(5)) - 1;
                    storage.get(i).markAsDone();
                    System.out.println("Marked as you wish my brother:");
                    System.out.println((i + 1) + ". " + storage.get(i).toString());
                    System.out.println(" Whats next?");
                } else if (input.length() > 7 && input.substring(0,6).equalsIgnoreCase("delete")) {
                    int i = Integer.parseInt(input.substring(7)) - 1;
                    Task removed = storage.get(i);
                    storage.remove(i);
                    System.out.println("Deleted this task for you my brother:\n" + removed.toString());
                    System.out.println("Now you have " + storage.size() + " tasks left");
                } else {
                    // Level-4 feature: Todo, Deadline, Event
                    // note existing exception: indexOf() is case sensitive so /by etc must be in right caps
                    if (input.substring(0, 4).equalsIgnoreCase("todo")) {
                        storage.add(new Todo(input));
                    } else if (input.substring(0, 5).equalsIgnoreCase("event")) {
                        int startIndex = input.indexOf("/from ");
                        int x = input.indexOf("/to ");
                        int endIndex = x + 4;
                        String description = input.substring(6, startIndex);
                        String start = input.substring(startIndex + 6, x);
                        String end = input.substring(endIndex);
                        storage.add(new Event(description, start, end));
                    } else if (input.substring(0, 8).equalsIgnoreCase("deadline")) {
                        int startIndex = input.indexOf("/by "); // exception
                        String description = input.substring(9, startIndex);
                        String deadline = input.substring(startIndex + 4);
                        storage.add(new Deadline(description, deadline));
                    } else {
                        storage.add(new Task(input));
                    }
                    int x = storage.size();
                    System.out.println("added to list my brother: \n" + x + "." + storage.get(x - 1).toString() + "\nNow you have " + x + " tasks!");
                }
            }


        }



    }

    public static void validateInput(String input, ArrayList<Task> storage) throws DukeException {
        if (input.indexOf("todo") < 0 && input.indexOf("event") < 0 && input.indexOf("display") < 0 && input.indexOf("deadline") < 0 && input.indexOf("mark") < 0 && input.indexOf("bye") < 0 && input.indexOf("delete") < 0) {
            throw new DukeException("OOPS! invalid command la bro");
        }
        if (input.length() > 4 && input.substring(0, 4).equalsIgnoreCase("todo") && input.length() <= 5) {
            throw new DukeException("OOPS wrong format my brother! consider this format: \ntodo xxx");
        }

        if (input.length() > 5 && input.substring(0, 5).equalsIgnoreCase("event") && (input.indexOf("/from") < 0 || input.indexOf("/to") < 0 || input.indexOf("/from") > input.indexOf("/to"))) {
            throw new DukeException("OOPS wrong format my brother! consider this format: \nevent xxxx /from xxx /to xxx");
        }

        if (input.length() > 6 && input.substring(0, 6).equalsIgnoreCase("delete")) {
            try {
                Integer.parseInt(input.substring(7));
            } catch(NumberFormatException e) {
                throw new DukeException("OOPS wrong format my brother! consider this format: \ndelete INSERT_NUMBER");
            }
            if (Integer.parseInt(input.substring(7)) > storage.size()) {
                throw new DukeException("OOPS inserted number is invalid");
            }
        }

        if (input.length() > 8 && input.substring(0, 8).equalsIgnoreCase("deadline") && input.indexOf("/by") < 0) {
            throw new DukeException("OOPS wrong format my brother! consider this format: \nevent xxxx /from xxx /to xxx");
        }



    }



}

