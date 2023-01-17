import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;


public class Duke {
    public static void main(String[] args) {

        UI ui = new UI();
        TaskList taskList;
        try {
            taskList = new TaskList("data.txt");
        } catch (Exception e) {
            ui.loadError(e);
            taskList = new TaskList(new ArrayList<>());
        }
        Command[] commands = {
                new BasicCommand("exit"
                        , "exit the app"
                        , () -> new String[]{"Goodbye."}),
                new BasicCommand("help"
                        , "show this help message"
                        , () -> {
                            ui.print();
                            return new String[]{};
                        }),
                new BasicCommand("list"
                        , "list tasks"
                        , taskList::stringify),
                new ArgCommand("add"
                        , "add task"
                        , new String[]{"\\s"}
                        , taskList::add),
                new ArgCommand("mark"
                        , "mark/unmark task as done"
                        , new String[]{}
                        , taskList::mark),
                new ArgCommand("delete"
                        , "delete task"
                        , new String[]{}
                        , taskList::delete),
        };
        ui.setCommands(commands);
        Parser parser = new Parser(commands);
        ui.printIntro();

        Scanner scanner = new Scanner(System.in);
        String[] outputs, arguments;
        String[] lineParts;
        Command cmd;

        while(scanner.hasNextLine()) {
            try {
                lineParts = scanner.nextLine().split("\\s",2);
                cmd = parser.parseCommand(lineParts[0]);
                if (cmd.hasParams()){
                    if (lineParts.length < 2 || lineParts[1].isEmpty()) {
                        throw new IllegalArgumentException("Missing argument.");
                    }
                    arguments = Parser.parseArgs(lineParts[1], cmd);
                    outputs = cmd.execute(arguments);
                } else {
                    outputs = cmd.execute(new String[]{});
                }
                ui.print(outputs);
                if (cmd.getName().equals("exit")){
                    break;
                }
            } catch (Exception e) {
                ui.error(e);
                ui.print();
            }
        }
    }
}