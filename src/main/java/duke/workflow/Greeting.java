package duke.workflow;

import duke.util.TaskList;
public class Greeting extends Event {
    int status;

    public Greeting() {
        super(false);
        this.status = -1;
    }

    public Greeting(int inPlay) {
        super(false);
        this.status = inPlay;
    }

    public Event toNext() {
        if (this.status == 0) {
            return new Ending();
        } else {
            return new DoTask();
        }
    }

    public TaskList getTaskList() {
        return new TaskList();
    }

    @Override
    public String toString() {
        return "SHALL WE PLAY A GAME?";
    }
}