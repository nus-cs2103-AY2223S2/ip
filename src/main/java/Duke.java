import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {
    public static final String BYE_MESSAGE = "Goodbye!! Please return to Dukey again soon!! :)";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Welcome welcome!");
        initiateDukeyList();
        //echo();
    }

    public static void echo() {
        System.out.println("Start by typing something and Dukey will echo!! Type bye to exit!! ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println("Dukey: " + input);
            if (input.equals("Bye")) {
                System.out.println("Dukey: Did you mean 'bye'? Type 'bye' to exit!!");
            }
        }
        System.out.println("\nDukey: " + BYE_MESSAGE);
        scanner.close();

    }


    public static void initiateDukeyList() {
        //System.out.println("DukeyList: Type something and Dukey will add it to the list!! Type 'list' to view the list! Type 'bye' to exit!! ");
        ItemList.printInstruction();
        ItemList itemList = new ItemList();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.next();

            //add a new todo
            if (input.equals("todo")) {
                String restOfInput = scanner.nextLine();
                restOfInput = restOfInput.strip();
                if (restOfInput.equals("")) {
                    Error.raiseEmptyDescriptionError("todo");
                    continue;
                }
                itemList.addItem(new ToDo(restOfInput));
                continue;
            }

            //add a new deadline
            if (input.equals("deadline")) {
                String[] details = scanner.nextLine().split("/");
                if (details.length != 2) {
                    Error.raiseIncorrectFormatError();
                    continue;
                }
                for (int i = 0; i < 2; i += 1) {
                    details[i] = details[i].strip();
                }
                if (details[1].equals("")) {
                    Error.raiseMissingDeadlineError();
                    continue;
                }
                if (details[0].equals("")) {
                    Error.raiseEmptyDescriptionError("deadline");
                    continue;
                }
                itemList.addItem(new Deadlines(details[0], details[1]));
                continue;
            }

            //add a new event
            if (input.equals("event")) {
                String[] details = scanner.nextLine().split("/");
                if (details.length != 3) {
                    Error.raiseIncorrectFormatError();
                    continue;
                }
                for (int i = 0; i < 3; i += 1) {
                    details[i] = details[i].strip();
                }
                if (details[0].equals("")) {
                    Error.raiseEmptyDescriptionError("event");
                    continue;
                }
                if (details[1].equals("") || details[2].equals("")) {
                    Error.raiseMissingEventTimeError();
                    continue;

                }
                itemList.addItem(new Event(details[0], details[1], details[2]));
                continue;
            }

            //mark
            if (input.equals("mark")) {
                String restOfInput = scanner.nextLine();
                if (restOfInput.isEmpty()) {
                    Error.raiseMissingIndexError();
                    continue;
                }
                int itemNumber = parseInt(restOfInput.strip()) - 1;
                if (itemNumber >= itemList.getSize()) {
                    Error.raiseInvalidTaskNumberError(itemList.getSize());
                    continue;
                }
                if (itemNumber < 0) {
                    Error.raiseIndexError();
                    continue;
                }
                itemList.mark(itemNumber);
                continue;
            }

            //unmark
            if (input.equals("unmark")) {
                String restOfInput = scanner.nextLine();
                if (restOfInput.isEmpty()) {
                    Error.raiseMissingIndexError();
                    continue;
                }
                int itemNumber = parseInt(restOfInput.strip()) - 1;
                if (itemNumber >= itemList.getSize()) {
                    Error.raiseInvalidTaskNumberError(itemList.getSize());
                    continue;
                }
                if (itemNumber < 0) {
                    Error.raiseIndexError();
                    continue;
                }
                itemList.unmark(itemNumber);
                continue;
            }

            //delete
            if (input.equals("delete")) {
                String restOfInput = scanner.nextLine();
                if (restOfInput.isEmpty()) {
                    Error.raiseMissingIndexError();
                    continue;
                }
                int itemNumber = parseInt(restOfInput.strip()) - 1;
                if (itemNumber >= itemList.getSize()) {
                    Error.raiseInvalidTaskNumberError(itemList.getSize());
                    continue;
                }
                if (itemNumber < 0) {
                    Error.raiseIndexError();
                    continue;
                }
                itemList.delete(itemNumber);
                continue;
            }

            //exit
            if (input.equals("bye")) {
                System.out.println("DukeyList: " + BYE_MESSAGE);
                break;
            }

            //list
            if (input.equals("list")) {
                System.out.println("DukeyList:");
                itemList.readList();
                continue;
            }

            Error.raiseWrongCommandError();

        }

    }






}
