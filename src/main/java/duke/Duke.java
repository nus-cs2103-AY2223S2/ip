package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.TextUi;

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
                Parser.listCommand(taskList);
            } else {
                try {
                    Parser.checkCommand(taskList, command);
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }
            Parser.nextCommand();
            command = sc.nextLine();
        }
        Parser.byeCommand();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
