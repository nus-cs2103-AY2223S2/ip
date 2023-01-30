import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //init
        Storage storage = new Storage("./ip-data/Ip-data.txt");
        Scanner sc = new Scanner(System.in);
        ToDoList ls;

        ls = Duke.startUp(storage);
        Duke.input(sc, ls);
        Duke.shutDown(storage, ls);
    }

    private static ToDoList startUp(Storage storage) {
        try {
            UI.welcomeMsg();
            return storage.load();
        } catch (Exception e) {
            return new ToDoList();
        }
    }

    private static void shutDown(Storage storage, ToDoList ls) {
        try {
            storage.save(ls);
            UI.exitMsg();
        } catch (Exception e) {

        }
    }

    private static void input(Scanner sc, ToDoList ls) {
        while (true) {
            try {
                String[] input = Duke.commandHandler(sc.nextLine(), " ", 2, 1);
                String command = input[0];
                int index;

                switch (command) {
                case "bye":
                    return;
                case "list":
                    UI.listMsg(ls.toString());
                    break;
                case "mark":
                    index = Integer.parseInt(input[1]);
                    ls.markTask(index); //numbersformatexception to be handled
                    UI.taskMarking(ls, index, command);
                    break;
                case "unmark":
                    index = Integer.parseInt(input[1]);;
                    ls.unmarkTask(index); //numbersformatexception to be handled
                    UI.taskMarking(ls, index, command);
                    break;
                case "delete":
                    index = Integer.parseInt(input[1]);
                    Task removed = ls.delete(index); //numbersformatexception to be handled
                    UI.taskAddDelete(ls, removed, command);
                    break;
                case "todo":
                case "event":
                case "deadline":
                    Duke.taskCommandHandler(input, ls);
                    break;
                default:
                    throw new DukeException("The Duke does not understand your words!");
                }
            } catch (Exception e) {
                UI.errorMsg(e.getMessage());
            }
        }
    }

    private static String[] commandHandler(String input, String regex, int limit, int minSize) throws DukeException {
        String[] sub = input.split(regex, limit);
        if (sub.length < minSize) {
            throw new InputDukeException();
        }
        return sub;
    }

    private static void taskCommandHandler(String[] input, ToDoList ls) throws DukeException {
        String command = input[0];

        if (input.length < 2) {
            throw new InputDukeException();
        }
        if (command.equals("todo")) {
            ToDoTask toAdd = new ToDoTask(input[1]);
            ls.add(toAdd);
            UI.taskAddDelete(ls, toAdd, "add");
            return;
        }
        if (command.equals("deadline")) {
            String[] sub = Duke.commandHandler(input[1], " /by ", 2, 2);
            DeadlineTask toAdd = new DeadlineTask(sub[0], sub[1]);
            ls.add(toAdd);
            UI.taskAddDelete(ls, toAdd, "add");
            return;
        }
        if (command.equals("event")) {
            String[] sub = Duke.commandHandler(input[1], " /from ", 2, 2);
            String[] subDuration = Duke.commandHandler(sub[1], " /to ", 2, 2);
            EventTask toAdd = new EventTask(sub[0], subDuration[0], subDuration[1]);
            ls.add(toAdd);
            UI.taskAddDelete(ls, toAdd, "add");
        }
    }

}
