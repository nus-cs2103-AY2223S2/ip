package command;
import task.TaskManager;
import util.DukeException;
import util.Parser;

public class DeleteCommand {
    private String input;
    private Parser parser;
    private TaskManager taskManager;

    public DeleteCommand(String input, TaskManager taskManager) {
        this.input = input;
        this.taskManager = taskManager;
    }

    public int getIndex() throws DukeException {
        String[] tmp = input.split(" ", 2);
        System.out.println(tmp[0]);
        System.out.println(tmp[1]);
        if (!parser.isNumeric(tmp[1])) {
            throw new DukeException("The input is not numeric!");
        } else {
            return Integer.parseInt(tmp[1]) - 1;
        }
    }
    public void executeDeleteCommand() throws DukeException {
        int index;
        try {
            String[] tmp = input.split(" ", 2);
            System.out.println(tmp[0]);
            System.out.println(tmp[1]);
            if (!parser.isNumeric(tmp[1])) {
                throw new DukeException("The input is not numeric!");
            }
            //int index = getIndex();
            index = Integer.parseInt(tmp[1]) - 1;
            System.out.println("index: " + index);
            this.taskManager.deleteTask(index);
        } catch (NullPointerException e) {
            System.out.println("Item does not exist in list! Please check your list again.");
        }

    }

}
