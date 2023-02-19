package jarvis.commands;

import jarvis.backend.Parser;
import jarvis.backend.TaskList;
import jarvis.tasks.Task;

import java.util.ArrayList;

abstract class Make extends Command {
    protected String description;
    protected TaskList tasklist;
    protected Parser parser;

    Make(String description, TaskList tasklist, Parser parser) {
        this.description = description;
        this.tasklist = tasklist;
        this.parser = parser;
    }

    public Task findDuplicates() {
        ArrayList<Task> list = tasklist.getWholeList();
        String lowerCaseDescription = this.description.toLowerCase();
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            String duplicateName = t.getDescription().toLowerCase();
            if (duplicateName.equals(lowerCaseDescription)) {
                return t;
            }
        }
        return null;
    }

    public String duplicateFound(Task newTask, Task duplicate) {
        parser.handleDuplicates(newTask);
        return "I've found a an existing task with the same name:\n"
                + duplicate
                + "\nDo you still want to add this new task?\n"
                + newTask;
    }
}
