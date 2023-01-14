import java.util.*;

public class Duke {
  private static class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
      this.description = description;
      this.isDone = false;
    }

    public String getStatusIcon() {
      return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
      this.isDone = true;
    }

    public void markAsUndone() {
      this.isDone = false;
    }

    @Override
    public String toString() {
      return new StringBuilder("[").append(getStatusIcon()).append("] ").append(description).toString();
    }
  }

  public static class Todo extends Task {

    public Todo(String description) {
      super(description);
    }

    @Override
    public String toString() {
      return new StringBuilder("[T]").append(super.toString()).toString();
    }
  }

  public static class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
      super(description);
      this.by = by;
    }

    @Override
    public String toString() {
      return new StringBuilder("[D]").append(super.toString())
          .append(" (by: ").append(by).append(")").toString();
    }
  }

  public static class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
      super(description);
      this.from = from;
      this.to = to;
    }

    @Override
    public String toString() {
      return new StringBuilder("[E]").append(super.toString()).append(" (from:")
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
      handleListInput(userInput, taskList);
      System.out.println("  ------------------------------------\n");
      userInput = sc.nextLine().trim();
    }

    System.out.println("  ------------------------------------");
    System.out.println("  Bye. Hope to see you again soon!");
    System.out.println("  ------------------------------------");
    sc.close();
  }

  private static void handleListInput(String userInput, ArrayList<Task> taskList) {
    if (userInput.equalsIgnoreCase("list")) {
      if (taskList.isEmpty())
        System.out.println("  No tasks added yet");
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
      handleMarkTask(taskNumber, taskList, isMark);
    } else {
      if (taskList.size() < 100) {
        handleTaskTypes(userInput, taskList);
      } else
        System.out.println("  List is full!");
    }
  }

  private static void handleMarkTask(Integer taskNum, ArrayList<Task> taskList, boolean mark) {
    if (taskNum > taskList.size() || taskNum < 1)
      System.out.println("  Task does not exist!");
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

  private static void handleTaskTypes(String userInput, ArrayList<Task> taskList) { 
    if (userInput.toLowerCase().contains("todo")) {
      Todo newTodo = new Todo(userInput.substring(5));
      addAndPrintTask(newTodo, taskList);
    } else if (userInput.toLowerCase().contains("deadline")) {
      String[] deadline = userInput.substring(9).split("/by");
      Deadline newDeadline = new Deadline(deadline[0], deadline[1]);
      addAndPrintTask(newDeadline, taskList);
    } else if (userInput.toLowerCase().contains("event")) {
      String[] event = userInput.substring(6).split("/from");
      String[] eventTime = event[1].split("/to");
      Event newEvent = new Event(event[0], eventTime[0], eventTime[1]);
      addAndPrintTask(newEvent, taskList);
    }
  }

  private static void addAndPrintTask(Task newTask, ArrayList<Task> taskList) {
    taskList.add(newTask);
    System.out.println("  Got it. I've added this task:");
    System.out.println(new StringBuilder("  ").append(newTask.toString()));
    System.out.println(new StringBuilder("  Now you have ").append(taskList.size()).append(" tasks in the list."));
  }
}