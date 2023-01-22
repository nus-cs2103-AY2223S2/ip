import java.util.*;

enum Command {
    LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE;
}

public class Duke {
    static String separator = "‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿";
    static String descriptionNotFound = "☹ Ohno! The description cannot be empty.";
    static String timeNotFound = "☹ Ohno! seems like you forgot to put a deadline.";
    static String numberNotFound = "☹ Ohno! I don't know which task to mark/unmark :(";
    static String invalidIndex = "☹ Ohno! I can't find this task in your to-do list... ";
    static String eventTimeNotFound = "☹ Ohno! seems like you forgot to put the time of the event ";
    static String endNotFound = "☹ Ohno! seems like you forgot to put the ending time of the event ";
    static String unknownInput = " ☹ Ohno! I'm sorry, but I don't know what that means ";
    static String deleteNumberNotFound = "☹ Ohno! I don't know which task to delete :(";

    public static void main(String[] args) throws DukeExceptions {
        String logo =
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡶⠶⢦⣄⠀⠀⠀⠀⠀⣴⠟⠛⢧⣠⣶⣿⠻⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠁⡟⠦⠌⠛⠉⠉⠉⢹⠇⢠⣶⣼⣷⣞⢙⣧⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣤⠃⠀⠀⠀⠀⠀⠀⣿⠀⠈⢻⡃⠀⢸⡿⡄⠈⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠁⠀⠀⠀⠀⠀⠀⠀⠘⠷⠖⠛⠛⠛⢿⡗⢋⣴⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠛⢻⡀⢀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡶⠾⣷⠆⠀⠀⣤⡀⠀⠀⠀⠀⠀⠀⠀⢀⣤⡀⠀⠐⢺⡟⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⢿⡦⠀⠀⠛⠃⠀⠀⢠⢶⣄⠀⠀⠈⠛⠀⠀⠀⣺⠓⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⣼⢧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡿⣖⡀⣀⣀⡀⠀⠈⠉⠉⠀⠀⣀⣀⣀⠀⣲⣯⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠶⡆\n" +
                        "⢻⡈⠻⣦⣀⣀⣀⣀⣀⠀⠀⠀⠁⣴⠟⠉⠁⠀⠉⠛⢦⡀⢀⡴⠛⠉⠁⠈⠙⠻⣄⠀⠁⣀⣠⣤⣤⣤⣤⡤⠖⠋⣸⠇\n" +
                        "⡿⠳⣤⣀⡀⠀⠀⠉⠉⠉⠳⢦⣼⠃⠀⠀⠀⠀⠀⠀⠀⠿⠋⠀⠀⠀⠀⠀⠀⠀⠹⣦⡞⠉⠀⠀⠀⠀⠀⢀⣠⠶⢻⡆\n" +
                        "⠻⣦⣀⠀⠀⠀⡴⠶⢦⡀⠀⠈⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⡴⠚⠳⡄⠈⢉⣀⣠⡾⠁\n" +
                        "⠀⠸⣍⣉⣁⡀⣇⠀⠀⠑⠀⢠⡿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⢷⡀⠀⠓⠀⢀⡇⢤⣈⣭⠿⠁⠀\n" +
                        "⠀⠀⠀⠙⠷⠤⠿⠶⠦⠶⠞⠋⠘⢻⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡼⠃⠈⠻⠦⠴⠖⠻⠶⠶⠛⠁⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⠻⢦⣄⠀⠀⠀⠀⠀⠀⠀⠀⣠⡴⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠶⣄⡀⢀⣤⠶⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n";
        System.out.println(logo);
        System.out.println(separator);
        System.out.println("   Hello I'm tyy\n   What can I do for you today?");
        System.out.println(separator);

        ArrayList<Task> toDoList = new ArrayList<Task>();
        Scanner scan = new Scanner(System.in);

        while (true) {
            String input = scan.nextLine();
            try {
                String[] split = input.split(" ");
                Command command = Command.valueOf(split[0].toUpperCase());
                if (command == Command.BYE) {
                    System.out.println(separator + "\n" + "   Ciao ~ see you again soon!" + "\n" + separator);
                    break;
                }
                switch (command) {
                    case LIST:
                        list(toDoList);
                        break;
                    case MARK:
                        mark(toDoList, input);
                        break;
                    case UNMARK:
                        unmark(toDoList, input);
                        break;
                    case TODO:
                        todo(toDoList, input);
                        break;
                    case DEADLINE:
                        deadline(toDoList, input);
                        break;
                    case EVENT:
                        event(toDoList, input);
                        break;
                    case DELETE:
                        delete(toDoList, input);
                        break;
                }
            } catch (IllegalArgumentException i) {
                throw new DukeExceptions(unknownInput);
            }
        }
    }

    private static void list(ArrayList<Task> toDoList) {
        if (toDoList.size() > 0) {
            System.out.println(separator + "\n" + "   Here are the tasks in your list:");
            for (int i = 0; i < toDoList.size(); i++) {
                int num = i + 1;
                System.out.print("    " + num + ". " + toDoList.get(i) + "\n");
            }
        } else {
            System.out.print(separator + "\n" + "   You don't have any tasks at the moment" + "\n");
        }
        System.out.println(separator);
    }

    private static void mark(ArrayList<Task> toDoList, String input) throws DukeExceptions {
        if (input.length() <= 5 || !Character.isDigit(input.charAt(5))) {
            throw new DukeExceptions(numberNotFound);
        }
        int index = Integer.parseInt(input.substring(5));
        if (index > toDoList.size()) {
            throw new DukeExceptions(invalidIndex);
        }
        Task task = toDoList.get(index - 1);
        task.mark();
        System.out.println("   good job! I've marked this task as done: " + "\n" + task);
    }

    private static void unmark(ArrayList<Task> toDoList, String input) throws DukeExceptions {
        if (input.length() <= 7 || !Character.isDigit(input.charAt(7))) {
            throw new DukeExceptions(numberNotFound);
        }
        int index = Integer.parseInt(input.substring(7));
        if (index > toDoList.size()) {
            throw new DukeExceptions(invalidIndex);
        }
        Task task = toDoList.get(index - 1);
        task.unmark();
        System.out.println("   okie dokie, I've marked this task as not done yet: " + "\n" + task);
    }

    private static void todo(ArrayList<Task> toDoList, String input) throws DukeExceptions {

        if (input.length() <= 5) {
            throw new DukeExceptions(descriptionNotFound);
        }
        Todo tdTask = new Todo(input.substring(5, input.length()));
        System.out.println("   okie dokie. I've added this task:" + "\n" + tdTask);
        toDoList.add(tdTask);
        System.out.println("   Now you have " + toDoList.size() + " tasks in the list.");
    }

    private static void deadline(ArrayList<Task> toDoList, String input) throws DukeExceptions {
        int index_ddl = input.indexOf("/");
        if (input.length() > 9 && !input.contains("/")) {
            throw new DukeExceptions(timeNotFound);
        }
        if (index_ddl - 1 < 9) {
            throw new DukeExceptions(descriptionNotFound);
        }
        Ddl ddlTask = new Ddl(input.substring(9, index_ddl - 1), input.substring(index_ddl + 4, input.length()));
        System.out.println("   okie dokie. I've added this task:" + "\n" + ddlTask);
        toDoList.add(ddlTask);
        System.out.println("   Now you have " + toDoList.size() + " tasks in the list.");
    }

    private static void event(ArrayList<Task> toDoList, String input) throws DukeExceptions {

        int index_e1 = input.indexOf("/");
        int index_e2 = input.lastIndexOf("/");
        if (input.length() > 5 && !input.contains("/")) {
            throw new DukeExceptions(eventTimeNotFound);
        }
        if (!input.substring(index_e1 + 1, input.length()).contains("/")) {
            throw new DukeExceptions(endNotFound);
        }
        if (input.length() <= 5) {
            throw new DukeExceptions(descriptionNotFound);
        }
        Event eventTask = new Event(input.substring(6, index_e1 - 1),
                input.substring(index_e1 + 6, index_e2 - 1),
                input.substring(index_e2 + 4, input.length()));
        System.out.println("   okie dokie. I've added this task:" + "\n" + eventTask);
        toDoList.add(eventTask);
        System.out.println("   Now you have " + toDoList.size() + " tasks in the list.");
    }

    private static void delete(ArrayList<Task> toDoList, String input) throws DukeExceptions {
        if (input.length() < 7) {
            throw new DukeExceptions(deleteNumberNotFound);
        }
        int index_de = Integer.parseInt(input.substring(7));
        Task task = toDoList.get(index_de - 1);
        toDoList.remove(index_de - 1);
        System.out.println("   okie dokie. I've removed this task:\n" + task);
        System.out.println("   Now you have " + toDoList.size() + " tasks in the list.");

    }
}



