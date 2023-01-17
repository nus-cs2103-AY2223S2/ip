import java.util.Scanner;

public class Connor {

    private static String getCommand(String input) {
        if (input.indexOf(' ') == -1) {
            return input.toUpperCase();
        } else {
            return input.substring(0, input.indexOf(' ')).toUpperCase();
        }
    }

    private static String getTask(String input) throws InvalidTaskException {
        if (input.indexOf(' ') < 0) {
            throw new InvalidTaskException();
        }
        return input.substring(input.indexOf(' ') + 1);
    }

    private static void validateName(String input) throws InvalidTaskException {
        if (input.trim().length() < 1) {
            throw new InvalidTaskException();
        }
    }

    private static String[] getNameDeadlinePair(String input) throws InvalidTaskException {
        int byIndex = input.indexOf("/by");
        if (byIndex < 1) {
            throw new InvalidTaskException();
        }
        String[] pair = new String[2];
        pair[0] = input.substring(0, byIndex - 1);
        validateName(pair[0]);
        pair[1] = input.substring(byIndex + 4);
        return pair;
    }

    private static String[] getNameStartEndTuple(String input) throws InvalidTaskException {
        int fromIndex = input.indexOf("/from");
        int byIndex = input.indexOf("/to");
        if (fromIndex < 1 || byIndex < 1) {
            throw new InvalidTaskException();
        }
        String[] tuple = new String[3];
        tuple[0] = input.substring(0, fromIndex - 1);
        validateName(tuple[0]);
        tuple[1] = input.substring(fromIndex + 6, byIndex - 1);
        tuple[2] = input.substring(byIndex + 4);
        return tuple;
    }

    public static void main(String[] args) {
        String name = "Connor";
        System.out.println("        Hello! I'm " + name + ", the android sent by Cyberlife");
        System.out.println("        Please type in your command below.");
        Scanner sc = new Scanner(System.in);
        TaskList list = new TaskList();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String command = getCommand(input);
            try {
                if (command.equals("HI")) {
                    Response.greetings("HI");
                } else if (command.equals("BYE")) {
                    Response.greetings("BYE");
                    sc.close();
                    break;
                } else if (command.equals("MARK")) {
                    list.markDone(Integer.parseInt(getTask(input)));
                } else if (command.equals("UNMARK")) {
                    list.markUndone(Integer.parseInt(getTask(input)));
                } else if (command.equals("LIST")) {
                    list.getList();
                } else if (command.equals("TODO")) {
                    validateName(getTask(input));
                    TODO todo = new TODO(getTask(input));
                    list.addTask(todo);
                } else if (command.equals("DEADLINE")) {
                    String[] pair = getNameDeadlinePair(getTask(input));
                    Deadline deadline = new Deadline(pair[0], pair[1]);
                    list.addTask(deadline);
                } else if (command.equals("EVENT")) {
                    String[] tuple = getNameStartEndTuple(getTask(input));
                    Event event = new Event(tuple[0], tuple[1], tuple[2]);
                    list.addTask(event);
                } else if (command.equals("DELETE")) {
                    list.deleteTask(getTask(input));
                } else {
                    throw new InvalidCommandException();
                }
            } catch (InvalidCommandException | InvalidTaskException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
