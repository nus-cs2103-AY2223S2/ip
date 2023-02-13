package app.command;

import java.util.List;

import app.chatbot.Response;
import app.chatbot.Storage;
import app.task.Task;
import app.task.TaskList;
import app.task.TaskTypes;


/**
 * Summary - serves as a Command to gather all Tasks and provide
 * a concise summary of each Task types to the user.
 */
public class SummaryCommand extends Command{
    private static final String SUMMARY_INTRO =
            "Good day sir, here's a summary of what's on your plate.";
    private static final String SUMMARY_INTRO_EMPTY =
            "Good day sir, you have no saved tasks! Feed me tasks to track :)";
    private static final String SUMMARY_CLOSING =
            "Use 'list', or 'find <keyword>' to get a specific task and its index to modify at.";
    public SummaryCommand() {
        this.isExit = false;
        this.isSave = false;
    }

    /**
     * Returns a summary view of all the tasks in the TaskList.
     * The summary is split by the type of tasks, and sorted within each type
     * (by the date if present, if not, alphabetically).
     * @param tl
     * @param s
     * @return
     */
    public Response execute(TaskList tl, Storage s) {
        Response r = new Response(SUMMARY_INTRO, true).addBlankLine();
        if (tl.size() == 0) {
            return new Response(SUMMARY_INTRO_EMPTY, true);
        }

        List<Task> events = tl.getSortedTasksByType(TaskTypes.Type.EVENT);
        r.addLine("Events - " + events.size() + " items");
        for (Task t : events) {
            r.addLine(t.toString());
        }

        r.addBlankLine();
        List<Task> deadlines = tl.getSortedTasksByType(TaskTypes.Type.DEADLINE);
        r.addLine("Deadlines - " + deadlines.size() + " items");
        for (Task t : deadlines) {
            r.addLine(t.toString());
        }

        r.addBlankLine();
        List<Task> todos = tl.getSortedTasksByType(TaskTypes.Type.TODO);
        r.addLine("Todo - " + todos.size() + " items");
        for (Task t : todos) {
            r.addLine(t.toString());
        }

        r.addBlankLine();
        return r.addLine(SUMMARY_CLOSING);
    }
}
