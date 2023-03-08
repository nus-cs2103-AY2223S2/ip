import java.time.DateTimeException;
import java.util.ArrayList;

public class Parser {


    public Parser() {
    }

    public void parse(String input) throws BroException{
        validateInput(input);
        Command command;
        if (input.equalsIgnoreCase("bye")) {
            command = new ExitCommand();

        } else if (input.equalsIgnoreCase("display")) {
            command = new DisplayCommand();
        } else if (input.length() >= 6 && input.substring(0,4).equalsIgnoreCase("mark")) {

        } else if (input.length() > 7 && input.substring(0,6).equalsIgnoreCase("delete")) {

        } else if (input.substring(0, 4).equalsIgnoreCase("todo")) {

        } else if (input.substring(0, 8).equalsIgnoreCase("deadline")) {

        } else {

        }

        switch()
        // level-1 feature: exit when user types "bye"
        if (input.equalsIgnoreCase("bye")) {
            System.out.println("ok see you brother all love no cringe!");
            break;
        }

        // PARSER -> PARSE()

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
                storage.add(new Todo(input.substring(5)));
            } else if (input.substring(0, 5).equalsIgnoreCase("event")) {
                int startIndex = input.indexOf("/from ");
                int x = input.indexOf("/to ");
                int endIndex = x + 4;
                String description = input.substring(6, startIndex - 1);
                String start = input.substring(startIndex + 6, x - 1);
                String end = input.substring(endIndex);
                try {
                    storage.add(new Event(description, start, end));
                } catch (DateTimeException e) {
                    System.out.println("Invalid Date and Time input brother. Here's the correct format:\ndd/MM/yyyy HHmm");
                    continue;
                }

            } else if (input.substring(0, 8).equalsIgnoreCase("deadline")) {
                int startIndex = input.indexOf("/by "); // exception
                String description = input.substring(9, startIndex - 1);
                String deadline = input.substring(startIndex + 4);
                try {
                    storage.add(new Deadline(description, deadline));
                } catch (DateTimeException e) {
                    System.out.println("Invalid Date and Time input brother. Here's the correct format:\ndd/MM/yyyy HHmm");
                    continue;
                }
            } else {
                storage.add(new Task(input));
            }
            int x = storage.size();
            System.out.println("added to list my brother: \n" + x + "." + storage.get(x - 1).toString() + "\nNow you have " + x + " tasks!");
        }


    }


    private static void validateInput(String input) throws BroException {
        if (!input.contains("todo") && !input.contains("event") && !input.contains("display") && !input.contains("deadline") && !input.contains("mark") && !input.contains("bye") && !input.contains("delete")) {
            throw new BroException("OOPS! invalid command la bro");
        }
        if (input.length() > 4 && input.substring(0, 4).equalsIgnoreCase("todo") && input.length() <= 5) {
            throw new BroException("OOPS wrong format my brother! consider this format: \ntodo xxx");
        }

        if (input.length() > 5 && input.substring(0, 5).equalsIgnoreCase("event") && (!input.contains("/from") || input.indexOf("/from") == 6 || !input.contains("/to") || input.indexOf("/from") > input.indexOf("/to"))) {
            throw new BroException("OOPS wrong format my brother! consider this format: \nevent xxxx /from xxx /to xxx");
        }

        if (input.length() > 6 && input.substring(0, 6).equalsIgnoreCase("delete")) {
            try {
                Integer.parseInt(input.substring(7));
            } catch(NumberFormatException e) {
                throw new BroException("OOPS wrong format my brother! consider this format: \ndelete INSERT_NUMBER");
            }
            if (Integer.parseInt(input.substring(7)) > storage.size()) {
                throw new BroException("OOPS inserted number is invalid");
            }
        }
        if (input.length() > 8 && input.substring(0, 8).equalsIgnoreCase("deadline") && (!input.contains("/by") || input.indexOf("/by") == 9)) {
            throw new BroException("OOPS wrong format my brother! consider this format: \nevent xxxx /from xxx /to xxx");
        }
    }
}
