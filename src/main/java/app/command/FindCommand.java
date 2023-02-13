package app.command;

import app.chatbot.Response;
import app.chatbot.Storage;
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
    public Response execute(TaskList tl, Storage storage) {
        Response response = new Response(true);
        StringBuilder searchHitsDisplay = new StringBuilder();
        boolean isSearchSuccessful = false;
        for (int i = 0; i < tl.size(); i++) {
            Task t = tl.getTask(i);
            String desc = t.getDesc();
            if (desc.toLowerCase().contains(this.searchContent)) {
                searchHitsDisplay.append(i + 1)
                        .append(": ")
                        .append(t)
                        .append("\n");
                isSearchSuccessful = true;
            }
        }
        String responseString;
        if (!isSearchSuccessful) {
            assert searchHitsDisplay.toString().equals("");
            responseString = "Ah I didn't find any tasks matching '" + this.searchContent + "'." + "\n";
        } else {
            responseString = "Here's the tasks matching '" + this.searchContent + "':\n"
                    + searchHitsDisplay + "\n";
        }
        return response.addLine(responseString);
    }
}
