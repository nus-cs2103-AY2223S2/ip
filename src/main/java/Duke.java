import Duke.TaskMethods;
import Duke.TaskList;
import Exceptions.DukeMainExceptions;
import Duke.Storage.Storage;
import Duke.Ui;
import Duke.Parser;

import Exceptions.CommandNotFoundException;

import java.io.IOException;
import java.util.Scanner;


public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;
    public Duke(String filePath) throws IOException {
        taskList = new TaskList();
        storage = new Storage(filePath);
        ui = new Ui();
        parser = new Parser(taskList, storage);


        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (IOException | DukeMainExceptions errMsg) {
            System.out.println(errMsg);
        }
    }

    public void run() {
        ui.greet();
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        while (!input.equals("bye")) {
            parser.parse(input);
            input = scanner.nextLine();
//            String splitDescription = input.split(" ", 2).length == 2
//                    ? input.split(" ", 2)[1]
//                    : "";
//
//            if (splitCommand[0].equals("bye")) {
//                ui.bye();
//                break;
//            } else if (splitCommand[0].equals("list")) {
//                t.list();
//            } else if (splitCommand[0].equals("mark")) {
//                t.mark(Integer.parseInt(splitCommand[1]));
//            } else if (splitCommand[0].equals("unmark")) {
//                t.unmark(Integer.parseInt(splitCommand[1]));
//            } else if (splitCommand[0].equals("delete")) {
//                t.delete(Integer.parseInt(splitCommand[1]));
//            } else if (splitCommand[0].equals("todo")) {
//                t.addTodo(splitDescription);
//            } else if (splitCommand[0].equals("deadline")) {
//                t.addDeadline(splitDescription);
//            } else if (splitCommand[0].equals("event")) {
//                t.addEvent(splitDescription);
//            } else {
//                throw new CommandNotFoundException("I'm sorry, but I don't know what that means :-(");
//            }
        }
        ui.bye();
    }

    public static void main(String[] args) throws CommandNotFoundException, IOException {
        new Duke("data/duke.txt").run();

    }
}
