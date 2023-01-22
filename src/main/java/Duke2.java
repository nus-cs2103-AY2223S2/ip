import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke2 {
  public static void listTask(ArrayList<Task1> tasks) {
    System.out.println("---------------------------");
    System.out.println("Here are the tasks in your list:");
    for (int i = 0; i < tasks.size(); i++) {
      Task1 curTask = tasks.get(i);
      System.out.print(i + 1 + ".");
      curTask.printType();
      if (!curTask.mark) {
        System.out.print("[ ] ");
      } else {
        System.out.print("[X] ");
      }
      curTask.printTime();
    }
    System.out.println("---------------------------");
  }

  public static String combineStrArr(String[] strArr) {
    StringBuilder curStr = new StringBuilder();
    for (int i = 1; i < strArr.length; i++) {
      curStr.append(" ").append(strArr[i]);
    }
    return curStr.toString();
  }

  public static void addTodo(String[] strArr, ArrayList<Task1> tasks) {
    System.out.println("---------------------------");
    System.out.println("Got it. I've added this task:");
    String curString = combineStrArr(strArr);
    System.out.println(" [T][ ] " + curString);
    Task1 curTask = new Todo(curString);
    tasks.add(curTask);
    int taskNum = tasks.size();
    System.out.println("Now you have " + taskNum + " tasks in the list.");
    System.out.println("---------------------------");
  }

  public static void addDeadline(String str, ArrayList<Task1> tasks) {
    System.out.println("---------------------------");
    System.out.println("Got it. I've added this task:");
    String[] strArr1 = str.split("/");
    String timeWithBy = strArr1[strArr1.length - 1];
    String[] strArr2 = timeWithBy.split(" ");
    String time = combineStrArr(strArr2);
    String[] strArr3 = strArr1[0].split(" ");
    String task = combineStrArr(strArr3);
    System.out.println(" [D][ ] " + task + " (by: " + time + ")");
    Task1 curTask = new Deadline(task, time);
    tasks.add(curTask);
    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    System.out.println("---------------------------");
  }

  public static void addEvent(String str, ArrayList<Task1> tasks) {
    System.out.println("---------------------------");
    System.out.println("Got it. I've added this task:");
    String[] strArr1 = str.split("/");
    String timeWithFrom = strArr1[strArr1.length - 2];
    String timeWithTo = strArr1[strArr1.length - 1];
    String[] strArr2 = timeWithFrom.split(" ");
    String[] strArr3 = timeWithTo.split(" ");
    String[] strArr4 = strArr1[0].split(" ");
    String startTime = combineStrArr(strArr2);
    String endTime = combineStrArr(strArr3);
    String task = combineStrArr(strArr4);
    System.out.println(" [E][ ] " + task + " (from: "
        + startTime + " to: " + endTime + ")");
    Task1 curTask = new Event(task, startTime, endTime);
    tasks.add(curTask);
    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    System.out.println("---------------------------");
  }

  public static void markTask(Task1 curTask, Boolean mark) {
    System.out.println("---------------------------");
    if (mark) {
      System.out.println("Nice! I've marked this task as done:");
      curTask.mark();
    } else {
      System.out.println("OK, I've marked this task as not done yet:");
      curTask.demark();
    }
    String curTaskStr = curTask.string;
    curTask.printType();
    if (mark) {
      System.out.print("[X] " + curTaskStr);
    } else {
      System.out.print("[ ] " + curTaskStr);
    }
    curTask.printTime();
    System.out.println("---------------------------");
  }

  public static void main(String[] args) throws IOException {
    String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);
    System.out.println("---------------------------");
    System.out.println("Hello! I'm Duke\n What can I do for you?");
    System.out.println("---------------------------");
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    String[] strArr = str.split(" ");
    ArrayList<Task1> tasks = new ArrayList<>();
    while (!str.equals("bye")) {
      if (str.equals("list")) {
        listTask(tasks);
      } else if (strArr[0].equals("mark")) {
        int curIndex = Integer.parseInt(strArr[1]) - 1;
        Task1 curTask = tasks.get(curIndex);
        markTask(curTask, true);
      } else if (strArr[0].equals("unmark")) {
        int curIndex = Integer.parseInt(strArr[1]) - 1;
        Task1 curTask = tasks.get(curIndex);
        markTask(curTask, false);
      } else if (strArr[0].equals("todo")) {
        addTodo(strArr, tasks);
      } else if (strArr[0].equals("deadline")) {
        addDeadline(str, tasks);
      } else if (strArr[0].equals("event")) {
        addEvent(str, tasks);
      }
      str = br.readLine();
      strArr = str.split(" ");
    }
    System.out.println("---------------------------");
    System.out.println("Bye. Hope to see you again soon!");
    System.out.println("---------------------------");
    System.exit(0);
  }
}
