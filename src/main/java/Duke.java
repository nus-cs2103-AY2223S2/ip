import java.util.Arrays;
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
        Task[] storage = new Task[100];
        int storeIndex = 0;



        while(true) {
            if (inputScanner.hasNextLine()) {
                input = inputScanner.nextLine();
                try {
                    validateInput(input);
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
                    for(int i = 0; i < storeIndex; i++) {
                        System.out.println((i + 1) + ". " + storage[i].toString());
                    }
                    System.out.println("Anything else I can do for you top G" );
                } else if (input.length() >= 6 && input.substring(0,4).equalsIgnoreCase("mark")) {
                    int i = Integer.parseInt(input.substring(5)) - 1;
                    storage[i].markAsDone();
                    System.out.println("Marked as you wish my brother:");
                    System.out.println((i + 1) + ". " + storage[i].toString());
                    System.out.println(" Whats next?");
                } else {
                    // Level-4 feature: Todo, Deadline, Event
                    // note existing exception: indexOf() is case sensitive so /by etc must be in right caps
                    if (input.substring(0, 4).equalsIgnoreCase("todo")) {
                        storage[storeIndex] = new Todo(input);
                    } else if (input.substring(0, 5).equalsIgnoreCase("event")) {
                        int startIndex = input.indexOf("/from ");
                        int x = input.indexOf("/to ");
                        int endIndex = x + 4;
                        String description = input.substring(6, startIndex);
                        String start = input.substring(startIndex + 6, x);
                        String end = input.substring(endIndex);
                        storage[storeIndex] = new Event(description, start, end);
                    } else if (input.substring(0, 8).equalsIgnoreCase("deadline")) {
                        int startIndex = input.indexOf("/by "); // exception
                        String description = input.substring(9, startIndex);
                        String deadline = input.substring(startIndex + 4);
                        storage[storeIndex] = new Deadline(description, deadline);
                    } else {
                        storage[storeIndex] = new Task(input);
                    }
                    System.out.println("added to list my brother: \n" + storage[storeIndex].toString() + "\nNow you have " + (storeIndex + 1) + " tasks!");
                    storeIndex++;
                }
            }


        }



    }

    public static void validateInput(String input) throws DukeException {
        if (input.indexOf("todo") < 0 && input.indexOf("event") < 0 && input.indexOf("display") < 0 && input.indexOf("deadline") < 0 && input.indexOf("mark") < 0) {
            throw new DukeException("OOPS! invalid command la bro");
        }
        if (input.substring(0, 4).equalsIgnoreCase("todo") && input.length() <= 5) {
            throw new DukeException("OOPS wrong format my brother! consider this format: \ntodo xxx");
        }

        if (input.substring(0, 5).equalsIgnoreCase("event") && (input.indexOf("/from") < 0 || input.indexOf("/to") < 0 || input.indexOf("/from") > input.indexOf("/to"))) {
            throw new DukeException("OOPS wrong format my brother! consider this format: \nevent xxxx /from xxx /to xxx");
        }

        if (input.substring(0, 8).equalsIgnoreCase("deadline") && input.indexOf("/by") < 0) {
            throw new DukeException("OOPS wrong format my brother! consider this format: \nevent xxxx /from xxx /to xxx");
        }



    }



}

