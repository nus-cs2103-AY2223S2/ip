package UserCommands;

import Features.DukeException;
import Features.TaskList;
import Features.Ui;

import java.util.Scanner;

public class CommandList extends Command{

    public void print(Scanner userScan, TaskList taskList) throws DukeException {
        // ERROR: list format is anything other than [ list ]
        Ui ui = new Ui();
        if (userScan.nextLine().length()>0) {
            throw new DukeException(ui.formatCommandError("list",
                    "list"));
        }
        if (taskList.size()==0) {
            ui.print("You don't have anything to do right now!");
        }
        else {
            StringBuilder toPrint = new StringBuilder();
            for (int i = 1; i < taskList.size()+1; i++) {
                toPrint.append(i).append(". ").append(taskList.get(i-1).toString());
                if (i < taskList.size()){
                    toPrint.append("\n");
                }
            }
            ui.print("Here are your tasks:\n" + toPrint);
        }
    }

    @Override
    public TaskList handle(Scanner userScan, TaskList taskList) throws DukeException {
        return new TaskList();
    }
}
