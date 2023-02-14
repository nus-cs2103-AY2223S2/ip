package app.command;

import java.util.Map;

import app.chatbot.Response;
import app.chatbot.Storage;
import app.task.InvalidDateTimeException;
import app.task.InvalidInputException;
import app.task.Task;
import app.task.TaskList;



public class EditCommand extends Command {
    private static final String NO_FIELDS_INDICATED_ERROR =
            "Please specify a field to change using '/<field> <value>' :)";
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
    public Response execute(TaskList tl, Storage storage) {
        if (this.args.isEmpty()) {
            return new Response(NO_FIELDS_INDICATED_ERROR, false);
        }
        Response r = new Response("Edited task at index " + this.editAtIndex + "!", true);
        r.addLine("Old:")
                .addLine(tl.getTask(editAtIndex).toString())
                .addBlankLine()
                .addLine("New: ");
        try {
            Task editedTask = tl.editTask(editAtIndex, this.args);
            r.addLine(editedTask.toString());
            return r;
        } catch (InvalidInputException | InvalidDateTimeException e) {
            return new Response(e.getMessage(), false);
        }
    }
}
