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
                } else if (command.equals("TODO") || command.equals("DEADLINE") || command.equals("EVENT")) {
                    Task task = TaskFactory.parseCommand(command, getTask(input));
                    list.addTask(task);
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
