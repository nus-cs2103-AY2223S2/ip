import java.util.LinkedList;

public class TaskList {
    protected LinkedList<Task> lst;

    public TaskList() {
        this.lst = new LinkedList<>();
    }

    public void mark(int taskNum) {
        Task t = this.getTask(taskNum);
        t.markAsDone();
        Duke.printLine();
        System.out.println("Okay! I've marked this task as done:");
        System.out.println(t);
        Duke.printLine();
    }

    public void unmark(int taskNum) throws DukeException{
        if (taskNum < 0 || taskNum > lst.size() - 1) {
            throw new DukeException("bounds");
        }
        Task t = this.getTask(taskNum);
        t.markAsUndone();
        Duke.printLine();
        System.out.println("Okay! I've marked this task as not done yet:");
        System.out.println(t);
        Duke.printLine();
    }

    public void deleteTask(int taskNum) throws DukeException{
        if (taskNum < 0 || taskNum > lst.size() - 1) {
            throw new DukeException("bounds");
        }
        Task t = this.lst.remove(taskNum);
        Duke.printLine();
        System.out.println("Okay! I've removed this task from the list:");
        System.out.println(t);
        printSize();
        Duke.printLine();

    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < lst.size(); i++) {
            String elem = lst.get(i).toString();
            System.out.println(String.format("%d. %s", i + 1, elem));
        }
    }

    public Task getTask(int index) {
        return this.lst.get(index);
    }

    public void addTask(Task t) {
        this.lst.add(t);
    }

    public int getSize() {
        return this.lst.size();
    }

    public void printSize() {
        System.out.println(String.format(
                "Now you have %d tasks in the list!", this.getSize()));
    }

}
