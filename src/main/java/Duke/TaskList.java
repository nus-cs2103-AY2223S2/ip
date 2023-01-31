package Duke;

import Duke.Tasks.Task;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Todo;
import Exceptions.NoDeadlineException;
import Exceptions.NoDescriptionException;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> taskList;
    private static int index;

    public TaskList() {
        this.taskList = new ArrayList<>(100);
        this.index = 0;
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void delete(int input) {
        Task currTask = this.taskList.get(input - 1);
        this.taskList.remove(input);

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
//        this.taskList[this.index] = newTodo;
        this.taskList.add(newTodo);
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
//        this.taskList[this.index] = newDeadline;
        this.taskList.add(newDeadline);
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
//        this.taskList[this.index] = newEvent;
        this.taskList.add(newEvent);
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
                Task currTask = this.taskList.get(i);
                System.out.println("\t" + (i + 1) + ". " + currTask.toString());
            }
        }
        System.out.println("\t--------------------------");
    }

    public void mark(int input) {
        Task currTask = this.taskList.get(input - 1);
        currTask.markDone();
        System.out.println("\t--------------------------");
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + currTask.toString());
        System.out.println("\t--------------------------");
    }

    public void unmark(int input) {
        Task currTask = this.taskList.get(input - 1);
        currTask.markNotDone();
        System.out.println("\t--------------------------");
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t\t" + currTask.toString());
        System.out.println("\t--------------------------");
    }

}
