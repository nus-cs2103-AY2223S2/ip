import java.util.*;

public class Duke {
  private static class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
      this.description = description;
      this.isDone = false;
    }

    public String toggleDone() {
      return isDone ? "X" : " ";
    }

    public void markAsDone() {
      this.isDone = true;
    }

    public void markAsUndone() {
      this.isDone = false;
    }

    @Override
    public String toString() {
      return new StringBuilder("[").append(toggleDone()).append("] ").append(description).toString();
    }
  }

  private static class Todo extends Task {

    public Todo(String description) {
      super(description);
    }

    @Override
    public String toString() {
      return new StringBuilder("[T]").append(super.toString()).toString();
    }
  }

  private static class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
      super(description);
      this.by = by;
    }

    @Override
    public String toString() {
      return new StringBuilder("[D]").append(super.toString())
          .append("(by:").append(by).append(")").toString();
    }
  }

  private static class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
      super(description);
      this.from = from;
      this.to = to;
    }

    @Override
    public String toString() {
      return new StringBuilder("[E]").append(super.toString()).append("(from:")
          .append(from).append("to:").append(to).append(")").toString();
    }
  }

  public static void main(String[] args) {
    ArrayList<Task> taskList = new ArrayList<>();
    System.out.println("  ------------------------------------");
    System.out.println("  Hello! I'm Duke\n" + "  What can I do for you?");
    System.out.println("  ------------------------------------\n");
    Scanner sc = new Scanner(System.in);
    String userInput = sc.nextLine().trim();

    while (!userInput.equalsIgnoreCase("bye")) {
      System.out.println("  ------------------------------------");
      try {
        handleListInput(userInput, taskList);
      }
      catch (ListException e) {
        System.out.println(new StringBuilder("  ").append(e.getMessage()).toString());
      }
      System.out.println("  ------------------------------------\n");
      userInput = sc.nextLine().trim();
    }

    System.out.println("  ------------------------------------");
    System.out.println("  Bye. Hope to see you again soon!");
    System.out.println("  ------------------------------------");
    sc.close();
  }

  private static void handleListInput(String userInput, ArrayList<Task> taskList) throws ListException {
    if (userInput.equalsIgnoreCase("list")) {
      if (taskList.isEmpty())
        throw new ListException("No tasks added yet");
      else {
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
          System.out.println(new StringBuilder("  ").append(i + 1).append(".")
              .append(taskList.get(i).toString()));
        }
      }
    } else if (userInput.toLowerCase().contains("mark")) {
      boolean isMark = !userInput.toLowerCase().contains("unmark");
      int taskNumber = Integer.parseInt(userInput.substring(isMark ? 5 : 7));
      try {
        handleMarkTask(taskNumber, taskList, isMark);
      } catch (TaskException e) {
        System.out.println(new StringBuilder("  ").append(e.getMessage()).toString());
      }
    } else if (userInput.toLowerCase().contains("delete")) {
      int taskNumber = Integer.parseInt(userInput.substring(7));
      try {
        deleteTask(taskNumber, taskList);
      } catch (TaskException e) {
        System.out.println(new StringBuilder("  ").append(e.getMessage()).toString());
      }
    } 
    else {
      if (taskList.size() < 100) {
        try {
          handleTaskTypes(userInput, taskList);
        } catch (InvalidInputException e) {
          System.out.println(new StringBuilder("  ").append(e.getMessage()).toString());
        }
      } else
        throw new ListException("List is full!");
    }
  }

  private static void handleMarkTask(Integer taskNum, ArrayList<Task> taskList, boolean mark) throws TaskException {
    if (taskNum > taskList.size() || taskNum < 1)
      throw new TaskException("Task does not exist!");
    else {
      if (mark) {
        taskList.get(taskNum - 1).markAsDone();
        System.out.println("  Nice! I've marked this task as done:");
      } else {
        taskList.get(taskNum - 1).markAsUndone();
        System.out.println("  OK, I've marked this task as not done yet:");
      }
      System.out.println(new StringBuilder("    ").append(taskList.get(taskNum - 1).toString()));
    }
  }

  private static void handleTaskTypes(String userInput, ArrayList<Task> taskList) throws InvalidInputException {
    if (userInput.toLowerCase().contains("todo")) {
      try {
        Todo newTodo = new Todo(userInput.substring(5));
        addTask(newTodo, taskList);
      } catch (StringIndexOutOfBoundsException e) {
        throw new InvalidInputException("OOPS!!! The description of a todo cannot be empty.");
      }
    } else if (userInput.toLowerCase().contains("deadline")) {
      try {
        String[] deadline = userInput.substring(9).split("/by");
        if ("".equals(deadline[0].trim()))
          throw new InvalidInputException(
              "OOPS!!! The deadline must be in the format: deadline <task> /by <date>, <task> and <date> cannot be empty.");
        Deadline newDeadline = new Deadline(deadline[0], deadline[1]);
        addTask(newDeadline, taskList);
      } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
        throw new InvalidInputException(
            "OOPS!!! The deadline must be in the format: deadline <task> /by <date>, <task> and <date> cannot be empty.");
      }
    } else if (userInput.toLowerCase().contains("event")) {
      try {
        String[] event = userInput.substring(6).split("/from");
        if ("".equals(event[0].trim())) {
          throw new InvalidInputException(
              "OOPS!!! The event must be in the format: event <task> /from <date> /to <date>, <task> and <date> cannot be empty.");
        }
        String[] eventTime = event[1].split("/to");
        Event newEvent = new Event(event[0], eventTime[0], eventTime[1]);
        addTask(newEvent, taskList);
      } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
        throw new InvalidInputException(
            "OOPS!!! The event must be in the format: event <task> /from <date> /to <date>, <task> and <date> cannot be empty.");
      }
    }
    else {
      throw new InvalidInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
  }

  private static void addTask(Task newTask, ArrayList<Task> taskList) {
    taskList.add(newTask);
    System.out.println("  Got it. I've added this task:");
    printTask(newTask, taskList);
  }

  private static void deleteTask(Integer taskNumber, ArrayList<Task> taskList) throws TaskException {
    if (taskNumber > taskList.size() || taskNumber < 1)
      throw new TaskException("Task does not exist!");
    else {
      Task taskToRemove = taskList.get(taskNumber - 1);
      taskList.remove(taskNumber - 1);
      System.out.println("  Noted. I've removed this task:");
      printTask(taskToRemove, taskList);
    }
  }

  private static void printTask(Task task, ArrayList<Task> taskList) {
    System.out.println(new StringBuilder("  ").append(task.toString()));
    System.out.println(new StringBuilder("  Now you have ").append(taskList.size())
        .append(taskList.size() == 1 ? " task in the list." : " tasks in the list."));
  }
}