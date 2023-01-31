package duke.commands;

import duke.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public abstract class Command {
    private boolean isEnd;
    private String tag;

    public Command(String tag){
        this.isEnd = tag.equals("END");
        this.tag = tag;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit(){return isEnd;}
}