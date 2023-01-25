import exception.DukeException;
import parser.Parser;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.TextUi;

import java.time.LocalDate;
import java.util.Scanner;

public class Duke {

    private TextUi ui;
    private TaskList taskList;

    public Duke() {
        this.ui = new TextUi();
        this.taskList = new TaskList();
    }

    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        Parser parser = new Parser();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                parser.listCommand(taskList);
            } else {
                try {
                    parser.checkCommand(taskList, command);
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }
            parser.nextCommand();
            command = sc.nextLine();
        }
        parser.byeCommand();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
