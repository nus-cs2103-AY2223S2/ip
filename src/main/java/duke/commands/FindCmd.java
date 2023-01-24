package duke.commands;

import duke.Parser;
import duke.Ui;
import duke.tasks.TaskList;

public class FindCmd extends Command {
    TaskList findResult;

    /**
     * Constructor method.
     * @param taskList Task list to search for keyword
     * @param lineInput Command line input that the user entered
     */
    public FindCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    } 

    /** Searches for specified keyword in the task list */
    public void execute() {
        String findKeyword = Parser.parseFindKeyword(this.lineInput);
        this.findResult = this.taskList.find(findKeyword);
        uiReply();
    }

    /** Output to UI the search results */
    public void uiReply() {
        Ui.displayMsg("Here are matching tasks in your list:\n" + this.findResult.toString());
    }
    
}
