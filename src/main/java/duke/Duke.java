package duke;

import duke.parser.Parser;

import java.io.IOException;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {
        Ui ui = new Ui();
        Storage storage = new Storage("data\\save.txt");
        Parser parser = new Parser();
        TaskList taskList = new TaskList(storage.readFile());

        try (Scanner sc = new Scanner(System.in)) {
            ui.printASCII();

            String command;
            String body;

            do {
                command = sc.next();
                body = sc.nextLine();

                switch (parser.parseCommand(command)) {
                    case -1:
                        break;
                    case 1:
                        taskList.listTask();
                        break;
                    case 2:
                        taskList.markTaskDone(body);
                        break;
                    case 3:
                        taskList.markTaskNotDone(body);
                        break;
                    case 4:
                        taskList.deleteTask(body);
                        break;
                    case 5:
                        taskList.addTodo(body);
                        break;
                    case 6:
                        taskList.addDeadline(body);
                        break;
                    case 7:
                        taskList.addEvent(body);
                        break;
                    default:
                        System.out.println(ui.ERROR_UNKNOWN_COMMAND);
                        break;
                }
            } while (!command.equals("bye"));

        } catch (Exception e) {
            ui.printException(e);
        } finally {
            storage.writeFile(taskList.getTaskList());
            System.out.println("Goodbye!");
        }
    }
}
