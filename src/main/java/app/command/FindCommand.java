package app.command;

import app.chatbot.Storage;
import app.chatbot.Ui;
import app.task.Task;
import app.task.TaskList;

public class FindCommand extends Command {
    private final String searchContent;

    public FindCommand(String searchContent) {
        this.isExit = false;
        this.isSave = false;
        this.searchContent = searchContent.toLowerCase();
    }

    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        StringBuilder resultDisplay = new StringBuilder("");
        boolean isSearchSuccessful = false;
        for (int i = 0; i < tl.size(); i++) {
            Task t = tl.getTask(i);
            String desc = t.getDesc();
            if (desc.toLowerCase().contains(this.searchContent)) {
                resultDisplay.append(i+1);
                resultDisplay.append(": " + t);
                resultDisplay.append("\n");
                isSearchSuccessful = true;
            }
        }
        if (!isSearchSuccessful) {
            return ("Ah I didn't find any tasks matching '" + this.searchContent + "'." + "\n" );
        } else {
            return ("Here's the tasks matching '" + this.searchContent + "':\n" +
                    resultDisplay + "\n");
        }
    }
}
