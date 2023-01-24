/**
 * Project name: Duke
 * Author: Tan Jun Da
 * Student Number: A0234893U
 *
 * This class is the main class for the duke ip.
 */

import java.util.Scanner;


public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;


    public enum Commands {
        bye,
        mark,
        unmark,
        list,
        todo,
        deadline,
        event,
        delete
    }

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        //Scanner to scan user input.
        Scanner sc = new Scanner(System.in);

        // User input.
        String input = "";

        // Description of the task.
        String description = "";

        ui.welcome();

        while(true) {
            description = "";
            input = sc.nextLine();
            Parser userParse = new Parser(input);
            System.out.println("____________________________________________________________");
            try {
                Commands userCommand = userParse.checkCommand(userParse.command);
                switch(userCommand) {
                    case bye:
                        ui.bye();
                        break;
                    case mark:
                        ui.markDisplay(tasks.mark(userParse), userParse);
                        break;
                    case unmark:
                        ui.unmarkDisplay(tasks.unmark(userParse), userParse);
                        break;
                    case list:
                        ui.list(tasks);
                        break;
                    case todo:
                        tasks.addTodo(description, userParse);
                        ui.addTodoMessage(tasks);
                        break;
                    case deadline:
                        tasks.addDeadline(description, userParse);
                        ui.addDeadlineMessage(tasks);
                        break;
                    case event:
                        tasks.addEvent(description, userParse);
                        ui.addEventMessage(tasks);
                        break;
                    case delete:
                        ui.deleteMessage(tasks, tasks.delete(userParse));
                        break;
                }
                storage.write(tasks);
                if(userCommand.name().equals("bye")) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("____________________________________________________________");
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
