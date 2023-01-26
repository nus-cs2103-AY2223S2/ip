package duke.tasks;
// Task class: parent class of Deadline, Event, To do
public class Task {
    private final String name;
    private boolean checkMark;

    public Task(String name) {
        this.name = name;
        this.checkMark = false;
    }

    public void toBeMarked() {
        this.checkMark = true;
    }

    public void toBeUnmarked() {
        this.checkMark = false;
    }

    @Override
    public String toString() {
        return (checkMark ? "[X] " : "[] ") + name;
    }
}
