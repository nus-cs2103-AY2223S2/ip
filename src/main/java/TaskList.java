import java.util.LinkedList;

public class TaskList {
    protected LinkedList<Task> lst;

    public TaskList() {
        this.lst = new LinkedList<>();
    }

    public void mark(int taskNum) throws DukeException{
        if (taskNum < 0 || taskNum > lst.size() - 1) {
            throw new DukeException("bounds");
        }
        Task t = this.getTask(taskNum);
        t.markAsDone();
        Ui.showLine();
        System.out.println("Okay! I've marked this task as done:");
        System.out.println(t);
        Ui.showLine();
    }

    public void unmark(int taskNum) throws DukeException{
        if (taskNum < 0 || taskNum > lst.size() - 1) {
            throw new DukeException("bounds");
        }
        Task t = this.getTask(taskNum);
        t.markAsUndone();
        Ui.showLine();
        System.out.println("Okay! I've marked this task as not done yet:");
        System.out.println(t);
        Ui.showLine();
    }

    public void deleteTask(int taskNum) throws DukeException{
        if (taskNum < 0 || taskNum > lst.size() - 1) {
            throw new DukeException("bounds");
        }
        Task t = this.lst.remove(taskNum);
        Ui.showLine();
        System.out.println("Okay! I've removed this task from the list:");
        System.out.println(t);
        printSize();
        Ui.showLine();

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

    public void addTask(Task t) { this.lst.add(t); }

    public void addTaskFromString(String data) throws DukeException{
        Task task = null;
        if (data.trim().isEmpty()) {
            throw new DukeException("empty line in file");
        }
        String[] details = data.split(" \\| ");
        // T/D/E || completed || descr || deadline/start || end
        String taskType = details[0];
        String doneData = details[1];
        String descr = details[2];
        try {
            switch (taskType) {
                case "T":
                    task = Todo.toTodoFromFileStr(descr, doneData);
                    break;
                case "D":
                    String deadlineData = details[3];
                    task = Deadline.toDeadlineFromFileStr(descr, doneData, deadlineData);
                    break;
                case "E":
                    String startData = details[3];
                    String endData = details[4];
                    task = Event.toEventFromFileStr(descr, doneData, startData, endData);
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("missing details");
        }
        this.lst.add(task);
    }

    public int getSize() {
        return this.lst.size();
    }

    public void printSize() {
        System.out.println(String.format(
                "Now you have %d tasks in the list!", this.getSize()));
    }

}
