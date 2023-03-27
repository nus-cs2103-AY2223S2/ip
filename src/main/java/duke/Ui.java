package duke;

public class Ui {
  public static void greet() {
    System.out.println("Duke: Yoooz it's your boy Duke! What's up?");
  }

  public static void farewell() {
    System.out.println("Duke: Good talk man, catch you again some other time!");
  }

  public static void markTask(TaskList tasks, String input) throws DukeException {
    int index;
    try {
      index = Integer.parseInt(input.split(" ")[1]) - 1;
      System.out.println("Duke: Good job man!");
      System.out.println(tasks.get(index).mark());
    } catch (NumberFormatException | IndexOutOfBoundsException e) {
      System.out.println("Input a valid task number.");
    }
  }

  public static void unmarkTask(TaskList tasks, String input) throws DukeException {
    int index;
    try {
      index = Integer.parseInt(input.split(" ")[1]) - 1;
      System.out.println("Duke: Did you forget something?");
      System.out.println(tasks.get(index).unmark());
    } catch (NumberFormatException | IndexOutOfBoundsException e) {
      System.out.println("Input a valid task number.");
    }
  }

  public static void confirmAddedTask(TaskList tasks, Task task) {
    System.out.println("New task added:");
    System.out.println(task.toString());
    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
  }

  public static void addToDo(TaskList tasks, String input) throws DukeException {
    String[] spaceSplit = input.split(" ", 2);
    if (spaceSplit.length < 2) {
      throw new DukeException("Duke: The description of a todo cannot be empty.");
    }
    Task task = new ToDo(spaceSplit[1]);
    tasks.add(task);
    confirmAddedTask(tasks, task);
  }

  public static void addDeadline(TaskList tasks, String input) {
    String[] spaceSplit = input.split(" ", 2);
    String[] bySplit = spaceSplit[1].split("/by", 2);
    Task task = new Deadline(bySplit[0], bySplit[1]);
    tasks.add(task);
    confirmAddedTask(tasks, task);
  }

  public static void addEvent(TaskList tasks, String input) {
    String[] spaceSplit = input.split(" ", 2);
    String[] fromSplit = spaceSplit[1].split("/from", 2);
    String[] toSplit = fromSplit[1].split("/to", 2);
    Task task = new Event(fromSplit[0], toSplit[0], toSplit[1]);
    tasks.add(task);
    confirmAddedTask(tasks, task);
  }

  public static void deleteTask(TaskList tasks, String input) throws DukeException {
    int index;
    try {
      index = Integer.parseInt(input.split(" ")[1]) - 1;
      System.out.println("Duke: Noted. I've removed this task:");
      Task toDel = tasks.get(index);
      tasks.remove(index);
      System.out.println(toDel);
      System.out.println("Now you have " + tasks.size() + "in the list.");
    } catch (NumberFormatException | IndexOutOfBoundsException e) {
      throw new DukeException("Input a valid task number.");
    }
  }

  public void showLoadingError() {
    System.out.println("Unable to find any save file.");
    System.out.println("Creating new task list...");
  }
}
