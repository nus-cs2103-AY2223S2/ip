package command;

import duke.Ui;
import task.TaskList;
import task.Todo;

public class TodoCommand {

    private String[] inputs;
    private static StringBuilder strBuild = new StringBuilder();

    public TodoCommand(String[] inputs) {
        this.inputs = inputs;
    }

    public void create() {
        if (inputs.length == 1) {
            Ui.printTodoError();
            String s = Ui.readCommand();
            Todo t = new Todo(s, false);
            strBuild.setLength(0);
            TaskList.addToList(t);
            Ui.printDefault(t);
        } else {
            for (int i = 1; i < inputs.length; i++) {
                strBuild.append(inputs[i]);
                if (i + 1 != inputs.length) {
                    strBuild.append(" ");
                }
            }
            Todo t = new Todo(strBuild.toString(), false);
            strBuild.setLength(0);
            TaskList.addToList(t);
            Ui.printDefault(t);
        }
    }
}
