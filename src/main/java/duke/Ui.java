package duke;

import duke.command.Command;
/**
 * handles Ui interactions
 */
public class Ui {
    public Ui() {
        //UI does not need to store anything
    }
    public void printLogo() {
        //CHECKSTYLE.OFF: checkStyleTest
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        //CHECKSTYLE.ON: checkStyleTest
        System.out.println("Hello from\n" + logo);
    }
    public void showLoadingError() {
        System.out.println("error loading file");
    }
    public void line(int l) {
        System.out.print('\n');
        for (int i = 0; i < l + 15; i++) {
            System.out.print('_');
        }
        System.out.print('\n');
    }
    public String parseIn(String in, TaskList taskList, Ui ui, Storage storage) {
        try {
            Command c = Parser.parseIn(in);
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }
    public String showList(TaskList taskList) {
        return taskList.showList();
    }
    public void print(String word) {
        System.out.println(word);
    }
}
