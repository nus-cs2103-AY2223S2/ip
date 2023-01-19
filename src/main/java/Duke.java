import java.util.Scanner;
public class Duke {
    public static void printResponse(String input) {
        String horizLine = "_____________________________";
        System.out.println(String.format("%s\n%s\n%s\n", horizLine, input, horizLine));
    }

    public static boolean checkEmptyString(String input) {
        return input.isEmpty() || input.trim().isEmpty();
    }

    public static void main(String[] args) {
        ToDoList lst = new ToDoList();
        printResponse("Hello! I'm Interrobang\nWhat can I do for you today?");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        command = command.trim();

        while (true) {
            if (!command.contains(" ")) {
                switch (command) {
                    case "bye":
                        printResponse("Bye! Hope to see you again soon!");
                        return;
                    case "list":
                        printResponse(lst.listItems());
                        break;
                    default:
                        printResponse("I'm not sure I understand that.");
                        break;
                }
            } else {
                String action = command.substring(0, command.indexOf(" "));
                String params = command.substring(command.indexOf(" "));
                switch (action) {
                    case "mark":
                    case "unmark":
                        int noOfParams = params.split(" ").length - 1;
                        if (noOfParams != 1) {
                            String reply = noOfParams < 1 ? "tell me a number" : "specify just one number";
                            printResponse(String.format("Sorry, but you need to %s with the %s keyword", reply, action));
                            break;
                        }
                        try {
                            int index = Integer.parseInt(params.trim());
                            printResponse(lst.changeState(index, action));
                        } catch (NumberFormatException e) {
                            printResponse(String.format("I'd love to %s that task", action) +
                               ", but I only understand numbers. Please try again with a number.");
                        }
                        break;
                    case "todo":
                        if (checkEmptyString(params)) {
                            printResponse("Sorry, todos need a description.");
                        } else {
                            printResponse(lst.add(new Todo(params)));
                        }
                        break;
                    case "deadline":
                        if (checkEmptyString(params)) {
                            printResponse("Sorry I cannot do that, deadlines require a date.");
                        } else if (!params.contains("/by")) {
                            printResponse("Hi sorry you need to specify the deadline using /by. Why don't you try again?");
                        } else {
                            String[] arguments = params.split("/by", 2);
                            printResponse(lst.add(new Deadline(arguments[0], arguments[1])));
                        }
                        break;
                    case "event":
                        if (checkEmptyString(params)) {
                            printResponse("I'm afraid I can't do that, events need a time range.");
                        } else if (!params.contains("/from") || !params.contains("/to")) {
                            printResponse("Sorry I don't understand. Please specify the time range using /from and /to.");
                        } else if (!params.split("/from", 2)[1].contains("/to")) {
                            printResponse("I think you may have mistyped. Please type /from before /to.");
                        } else {
                            String[] keywords = params.split("/from", 2);
                            String task = keywords[0];
                            String[] duration = keywords[1].split("/to", 2);
                            String from = duration[0];
                            String to = duration[1];
                            if (checkEmptyString(to)) {
                                printResponse("Maybe you forgot to specify an end-date. Please enter a time after the /to");
                            } else {
                                printResponse(lst.add(new Event(task, duration[0], duration[1])));
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
            command = scanner.nextLine();
        }
    }
}
