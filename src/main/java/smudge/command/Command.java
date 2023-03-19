package smudge.command;

import smudge.main.SmudgeException;
import smudge.main.Storage;
import smudge.main.Tasklist;
import smudge.main.Ui;

import java.io.IOException;

public abstract class Command {
    private boolean shouldExit = false;

    public abstract String execute(Tasklist taskList, Ui ui, Storage storage) throws IOException, SmudgeException;

    public void switchExitCondition() {
        this.shouldExit = !(this.shouldExit);
    }

    public boolean isExit() {
        return this.shouldExit;
    }
}