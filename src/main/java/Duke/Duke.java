package Duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;

import Duke.Commands.Tasks.Deadline;
import Duke.Commands.Tasks.Event;
import Duke.Commands.Tasks.Task;
import Duke.Commands.Tasks.ToDo;
import Duke.Commands.Parser;
import Duke.Commands.Command;
import Duke.Commands.Delete;
import Duke.Commands.Exit;
import Duke.Commands.Add;
import Duke.Commands.ListTasks;
import Duke.Commands.Unmark;
import Duke.Commands.Mark;

import Duke.dukeexception.DukeException;

/**
 * @author Shi Jiaao
 */

public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.printWelcome();
        TaskList toDoList = new TaskList();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            Parser parser = new Parser(command);
            Command curCommand;
            try {
                curCommand = parser.process();
                curCommand.execute(toDoList);
                ui.printCommandMessage(curCommand);
                ui.printList(toDoList);
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
                continue;
            }
            if (curCommand instanceof Exit) {
                ui.printGoodbye();
                break;
            }
        }
        sc.close();
    }
}
