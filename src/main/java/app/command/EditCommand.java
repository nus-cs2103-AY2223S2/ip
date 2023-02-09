package app.command;

import java.util.Map;

import app.chatbot.Response;
import app.chatbot.Storage;
import app.chatbot.Ui;
import app.task.Task;
import app.task.TaskFieldNotFoundException;
import app.task.TaskList;



public class EditCommand extends Command {
    private final String editAtIndex;
    private final Map<String, String> args;
    public EditCommand(String editAtIndex, Map<String, String> args) {
        this.editAtIndex = editAtIndex;
        this.args = args;
        this.isExit = false;
        this.isSave = true;
        this.args.remove("Command");
        this.args.remove("edit");
    }

    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) throws Exception {
        if (this.args.isEmpty()) {
            return new Response("Please specify a field to change using '/<field> <value> :)").toString();
        }
        Response r = new Response("Edited task at index " + this.editAtIndex + "!");
        r.addLine("Old:")
                .addLine(tl.getTask(editAtIndex).toString())
                .addBlankLine()
                .addLine("New: ");
        Task editedTask = tl.editTask(editAtIndex, this.args);
        r.addLine(editedTask.toString());
        return r.toString();
    }
}
