import java.util.Scanner;

public class Duke {
    protected static String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    protected static TaskList lst = new TaskList();
    protected static boolean cont = true;
    protected static String path = "src/data/duke.txt";
    protected static Storage storage = new Storage(path);
    protected static Ui ui = new Ui();
    public static void main(String[] args) {
        try {
            storage.loadFileInto(lst);
        } catch (DukeException e) {
            ui.showError(e.toString());
        }
        Scanner input = new Scanner(System.in);
        // run()
        // cont = true
        ui.showWelcome();

        while(cont) {
            String in = input.nextLine();
            String[] commands = in.trim().split(" ", 2);
            boolean single = commands.length < 2;
            try {
                switch (commands[0]) {
                    case "bye":
                        //ui.showExit();
                        cont = false;
                        break;
                    case "list":
                        ui.showList(lst);
                        in = input.nextLine();
                        break;
                    case "mark":
                        if (single) {
                            throw new DukeException(commands[0]);
                        }
                        lst.mark(Integer.parseInt(commands[1]) - 1);
                        in = input.nextLine();
                        break;
                    case "unmark":
                        if (single) {
                            throw new DukeException(commands[0]);
                        }
                        lst.unmark(Integer.parseInt(commands[1]) - 1);
                        in = input.nextLine();
                        break;
                    case "todo":
                        if (single) {
                            throw new DukeException(commands[0]);
                        }
                        String t = in.trim().split(" ", 2)[1];
                        Todo.processTodo(t, lst);
                        in = input.nextLine();
                        break;
                    case "deadline":
                        if (single) {
                            throw new DukeException(commands[0]);
                        }
                        String d = in.trim().split(" ", 2)[1];
                        Deadline.processDeadline(d, lst);
                        in = input.nextLine();
                        break;
                    case "event":
                        if (single) {
                            throw new DukeException(commands[0]);
                        }
                        String e = in.trim().split(" ", 2)[1];
                        Event.processEvent(e, lst);
                        in = input.nextLine();
                        break;
                    case "delete":
                        if (single) {
                            throw new DukeException(commands[0]);
                        }
                        lst.deleteTask(Integer.parseInt(commands[1]) - 1);
                        in = input.nextLine();
                        break;
                    default:
                        throw new DukeException("none");
                        // update saved file
                        //in = input.nextLine();
                }
                storage.saveToFile(lst);
            } catch (DukeException e) {
                ui.showError(e.toString());
                in = input.nextLine();
            } catch (IndexOutOfBoundsException e) {
                ui.showError("OOPS!! This index is out of bounds!");
                in = input.nextLine();
            } catch (NumberFormatException e) {
                ui.showError("Oh no! You have entered too many whitespaces!");
                in = input.nextLine();
            }
        }
        ui.showExit();
        System.exit(0);
    }
}
