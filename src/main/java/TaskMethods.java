import Tasks.Task;
import Tasks.Deadline;
import Tasks.Todo;
import Tasks.Event;
import Exceptions.NoDescriptionException;
import Exceptions.NoDeadlineException;


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
    public void delete(int input) {
        Task currTask = this.taskList[input - 1];

        for (int i = input; i < this.index; i++) {
            this.taskList[i] = this.taskList[i + 1];
        }
        this.index--;
        System.out.println("\t--------------------------");
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + currTask.toString());
        System.out.println("\tNow you have " + this.index + " tasks in the list.");
        System.out.println("\t--------------------------");

    }
    public void addTodo(String input) throws NoDescriptionException {
        if (input.trim().equals("")) {
            throw new NoDescriptionException("The description of a todo cannot be empty.");
        }
        Task newTodo = new Todo(input);
        this.taskList[this.index] = newTodo;
        this.index++;
        System.out.println("\t--------------------------");
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + newTodo.toString());
        System.out.println("\tNow you have " + this.index + " tasks in the list.");
        System.out.println("\t--------------------------");
    }

    public void addDeadline(String input) throws NoDescriptionException,
            NoDeadlineException {
        String[] splitDesWithBy = input.split(" /by ", 2);
        String description = splitDesWithBy[0].trim();

        if (description.equals("")) {
            throw new NoDescriptionException("The description of a deadline cannot be empty.");
        } else if (splitDesWithBy.length != 2) {
            throw new NoDeadlineException("The due date cannot be empty.");
        }

        String dueDate = splitDesWithBy[1].trim();

        Task newDeadline = new Deadline(description, dueDate);
        this.taskList[this.index] = newDeadline;
        this.index++;
        System.out.println("\t--------------------------");
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + newDeadline.toString());
        System.out.println("\tNow you have " + this.index  + " tasks in the list.");
        System.out.println("\t--------------------------");
    }

    public void addEvent(String input) throws NoDeadlineException,
            NoDescriptionException{
        String[] splitDesWithFrom = input.split(" /from ", 2);
        String description = splitDesWithFrom[0].trim();

        if (description.equals("")) {
            throw new NoDescriptionException("The description of an event cannot be empty.");
        } else if (splitDesWithFrom.length != 2) {
            throw new NoDeadlineException("The starting time cannot be empty");
        }

        String[] period = splitDesWithFrom[1].split(" /to ", 2);
        String startingTime = period[0].trim();

        if (startingTime.equals("")) {
            throw new NoDeadlineException("The starting time cannot be empty.");
        } else if (period.length != 2) {
            throw new NoDeadlineException("The ending time cannot be empty.");
        }

        String endingTime = period[1].trim();



        Task newEvent = new Event(description, startingTime, endingTime);
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
        if (this.index == 0) {
            System.out.println("\tThere is no task in the list.");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < this.index; i++) {
                Task currTask = this.taskList[i];
                System.out.println("\t" + (i + 1) + ". " + currTask.toString());
            }
        }
        System.out.println("\t--------------------------");
    }

    public void mark(int input) {
        Task currTask = this.taskList[input - 1];
        currTask.markDone();
        System.out.println("\t--------------------------");
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + currTask.toString());
        System.out.println("\t--------------------------");
    }

    public void unmark(int input) {
        Task currTask = this.taskList[input - 1];
        currTask.markNotDone();
        System.out.println("\t--------------------------");
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t\t" + currTask.toString());
        System.out.println("\t--------------------------");
    }

    public void bye() {
        System.out.println("\t--------------------------");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t--------------------------");
    }

}
