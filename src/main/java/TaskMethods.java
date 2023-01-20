import Tasks.Task;
import Tasks.Deadline;
import Tasks.Todo;
import Tasks.Event;

public class TaskMethods {
    protected Task[] taskList;
    protected int index;

    public TaskMethods() {
        this.taskList = new Task[100];
        this.index = 0;
    }

    public Task[] getTaskList() {
        return this.taskList;
    }

    public int getIndex() {
        return this.index;
    }

    public void add(String input) {
        Task newTask = new Task(input);
        this.taskList[index] = newTask;
        index++;
        System.out.println("\t--------------------------");
        System.out.println("\tadded: " + input);
        System.out.println("\t--------------------------");
    }
    public void addTodo(String input) {
        Task newTodo = new Todo(input);
        this.taskList[this.index] = newTodo;
        this.index++;
        System.out.println("\t--------------------------");
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + newTodo.toString());
        System.out.println("\tNow you have " + this.index + " tasks in the list.");
        System.out.println("\t--------------------------");
    }

    public void addDeadline(String input, String dueDate) {
        Task newDeadline = new Deadline(input, dueDate);
        this.taskList[this.index] = newDeadline;
        this.index++;
        System.out.println("\t--------------------------");
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + newDeadline.toString());
        System.out.println("\tNow you have " + this.index  + " tasks in the list.");
        System.out.println("\t--------------------------");
    }

    public void addEvent(String input, String startingTime, String endingTime) {
        Task newEvent = new Event(input, startingTime, endingTime);
        this.taskList[this.index] = newEvent;
        this.index++;
        System.out.println("\t--------------------------");
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + newEvent.toString());
        System.out.println("\tNow you have " + this.index + " tasks in the list.");
        System.out.println("\t--------------------------");
    }
    public void list() {
        System.out.println("\t--------------------------");
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < this.index; i++) {
            Task currTask = this.taskList[i];
            System.out.println("\t" + (i+1) + ". " + currTask.toString());
        }
        System.out.println("\t--------------------------");
    }

    public void mark(int input) {
        Task currTask = this.taskList[input - 1];
        currTask.markDone();
        System.out.println("\t--------------------------");
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t[X] " + currTask.getDescription());
        System.out.println("\t--------------------------");
    }

    public void unmark(int input) {
        Task currTask = this.taskList[input - 1];
        currTask.markNotDone();
        System.out.println("\t--------------------------");
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t[ ] " + currTask.getDescription());
        System.out.println("\t--------------------------");
    }

    public void bye() {
        System.out.println("\t--------------------------");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t--------------------------");
    }

}
