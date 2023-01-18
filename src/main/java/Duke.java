import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Duke {
  private static class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
      this.description = description;
      this.isDone = false;
    }

    public Character getMarker() {
      return isDone ? 'X' : ' ';
    }

    public void markAsDone() {
      this.isDone = true;
    }

    public void markAsUndone() {
      this.isDone = false;
    }

    @Override
    public String toString() {
      return String.format("[%c] %s", getMarker(), this.description);
    }
  }

  private static class Todo extends Task {

    public Todo(String description) {
      super(description);
    }

    @Override
    public String toString() {
      return String.format("[T]%s", super.toString());
    }
  }

  private static class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
      super(description);
      this.by = by;
    }

    public String parseDateTime(LocalDateTime dateTime) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
      return dateTime.format(formatter);
    }

    @Override
    public String toString() {
      return String.format("[D]%s (by: %s)", super.toString(), parseDateTime(this.by));
    }
  }

  private static class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
      super(description);
      this.from = from;
      this.to = to;
    }

    public String parseDateTime(LocalDateTime dateTime) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
      return dateTime.format(formatter);
    }

    @Override
    public String toString() {
      return String.format("[E]%s (from: %s to: %s)", super.toString(), parseDateTime(this.from),
          parseDateTime(this.to));
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
      } catch (ListException e) {
        System.out.println(String.format("  %s", e.getMessage()));
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
          System.out.println(String.format("  %d.%s", i + 1, taskList.get(i).toString()));
        }
      }
    } else if (userInput.toLowerCase().contains("mark")) {
      boolean isMark = !userInput.toLowerCase().contains("unmark");
      int taskNumber = Integer.parseInt(userInput.substring(isMark ? 5 : 7));
      try {
        handleMarkTask(taskNumber, taskList, isMark);
      } catch (TaskException e) {
        System.out.println(String.format("  %s", e.getMessage()));
      }
    } else if (userInput.toLowerCase().contains("delete")) {
      int taskNumber = Integer.parseInt(userInput.substring(7));
      try {
        deleteTask(taskNumber, taskList);
      } catch (TaskException e) {
        System.out.println(String.format("  %s", e.getMessage()));
      }
    } else {
      if (taskList.size() < 100) {
        try {
          handleTaskTypes(userInput, taskList);
        } catch (InvalidInputException e) {
          System.out.println(String.format("  %s", e.getMessage()));
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
      System.out.println(String.format("    %s", taskList.get(taskNum - 1)));
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
        String[] deadline = userInput.substring(9).split(" /by ");
        if ("".equals(deadline[0].trim()))
          throw new InvalidInputException(
              "OOPS!!! The deadline must be in the format: deadline <task> /by <date>, <task> and <date> cannot be empty.");
        Deadline newDeadline = new Deadline(deadline[0], storeDateTime(deadline[1]));
        addTask(newDeadline, taskList);
      } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
        throw new InvalidInputException(
            "OOPS!!! The deadline must be in the format: deadline <task> /by <date>, <task> and <date> cannot be empty.");
      } catch (DateTimeParseException e) {
        throw new InvalidInputException("OOPS!!! Dates must be in the format: dd/mm/yyyy HHmm (e.g. 12/12/2023 1800)");
      }
    } else if (userInput.toLowerCase().contains("event")) {
      try {
        String[] event = userInput.substring(6).split(" /from ");
        if ("".equals(event[0].trim())) {
          throw new InvalidInputException(
              "OOPS!!! The event must be in the format: event <task> /from <date> /to <date>, <task> and <date> cannot be empty.");
        }
        String[] eventTime = event[1].split(" /to ");
        LocalDateTime fromDateTime = storeDateTime(eventTime[0]);
        LocalDateTime toDateTime = storeDateTime(eventTime[1]);
        if (fromDateTime.isAfter(toDateTime)) {
          throw new InvalidInputException("OOPS!!! The start date must be before the end date.");
        }
        Event newEvent = new Event(event[0], fromDateTime, toDateTime);
        addTask(newEvent, taskList);
      } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
        throw new InvalidInputException(
            "OOPS!!! The event must be in the format: event <task> /from <date> /to <date>, <task> and <date> cannot be empty.");
      } catch (DateTimeParseException e) {
        throw new InvalidInputException("OOPS!!! Dates must be in the format: dd/mm/yyyy HHmm (e.g. 12/12/2023 1800)");
      }
    } else {
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

  private static LocalDateTime storeDateTime(String dateTimeString) throws DateTimeParseException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    return LocalDateTime.parse(dateTimeString, formatter);
  }
}