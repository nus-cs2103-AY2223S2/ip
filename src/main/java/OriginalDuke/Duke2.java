package OriginalDuke;

import java.io.BufferedReader;
import java.io.FileWriter;
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
      System.out.print(curTask.string);
      curTask.printTime();
    }
    System.out.println("---------------------------");
  }

  public static String combineStrArr(String[] strArr) {
    StringBuilder curStr = new StringBuilder();
    curStr.append(strArr[1]);
    for (int i = 2; i < strArr.length; i++) {
      curStr.append(" ").append(strArr[i]);
    }
    return curStr.toString();
  }

  public static String arrayListToString(ArrayList<Task1> task1s) {
    StringBuilder arrayString = new StringBuilder();
    for (Task1 t : task1s) {
      arrayString.append(t.toString()).append(" ");
      arrayString.append(t.string);
      if(t instanceof Deadline) {
        arrayString.append(" /by").append(" ").append(((Deadline) t).strTime);
      }
      if(t instanceof Event) {
        arrayString.append(" /from").append(" ").append(((Event) t).strStartTime)
            .append(" ").append(" /to").append(" ").append(((Event) t).strEndTime);
      }
      arrayString.append("\n");
      if (t.mark) {
        arrayString.append("mark ").append(task1s.indexOf(t) + 1).append("\n");
      }
    }
    return arrayString.toString();
  }

  public static void addTodo(String[] strArr, ArrayList<Task1> tasks) throws EmptyDescription, IOException {
    if (strArr.length < 2) {
      throw new EmptyDescription(new Todo(""));
    } else {
      System.out.println("---------------------------");
      System.out.println("Got it. I've added this Duke.task:");
      String curString = combineStrArr(strArr);
      System.out.println(" [T][ ] " + curString);
      Task1 curTask = new Todo(curString);
      tasks.add(curTask);
      int taskNum = tasks.size();
      appendToFile(arrayListToString(tasks));
      System.out.println("Now you have " + taskNum + " tasks in the list.");
      System.out.println("---------------------------");
    }
  }

  public static void addDeadline(String str, ArrayList<Task1> tasks) throws EmptyDescription, EmptyTime, IOException {
    String[] strArr = str.split(" ");
    String[] strArrP = str.split("/");
    if (strArr.length < 2) {
      throw new EmptyDescription(new Deadline("", "2022-01-01 0000"));
    } else if (strArrP.length < 2) {
      throw new EmptyTime(new Deadline("", "2022-01-01 0000"));
    } else {
      System.out.println("---------------------------");
      System.out.println("Got it. I've added this Duke.task:");
      String[] strArr1 = str.split("/");
      String timeWithBy = strArr1[strArr1.length - 1];
      String[] strArr2 = timeWithBy.split(" ");
      String time = combineStrArr(strArr2);
      String[] strArr3 = strArr1[0].split(" ");
      String task = combineStrArr(strArr3);
      Deadline curTask = new Deadline(task, time);
      tasks.add(curTask);
	  appendToFile(arrayListToString(tasks));
      System.out.println(" [D][ ] " + task + " (by: " + curTask.time + ")");
      System.out.println("Now you have " + tasks.size() + " tasks in the list.");
      System.out.println("---------------------------");
    }
  }
  public static void addEvent(String str, ArrayList<Task1> tasks) throws EmptyDescription, EmptyTime, IOException {
    String[] strArr = str.split(" ");
    String[] strArrP = str.split("/");
    if (strArr.length < 2) {
      throw new EmptyDescription(new Event("", "2022-01-01 0000", "2022-01-01 0000"));
    } else if (strArrP.length < 3) {
      throw new EmptyTime(new Event("", "2022-01-01 0000", "2022-01-01 0000"));
    } else {
      System.out.println("---------------------------");
      System.out.println("Got it. I've added this Duke.task:");
      String[] strArr1 = str.split("/");
      String timeWithFrom = strArr1[strArr1.length - 2];
      String timeWithTo = strArr1[strArr1.length - 1];
      String[] strArr2 = timeWithFrom.split(" ");
      String[] strArr3 = timeWithTo.split(" ");
      String[] strArr4 = strArr1[0].split(" ");
      String startTime = combineStrArr(strArr2);
      String endTime = combineStrArr(strArr3);
      String task = combineStrArr(strArr4);
      Event curTask = new Event(task, startTime, endTime);
      tasks.add(curTask);
	  appendToFile(arrayListToString(tasks));
      System.out.println(" [E][ ] " + task + " (from: "
          + curTask.startTime + " to: " + curTask.endTime + ")");
      System.out.println("Now you have " + tasks.size() + " tasks in the list.");
      System.out.println("---------------------------");
    }
  }

  public static void markTask(Task1 curTask, Boolean mark) {
    System.out.println("---------------------------");
    if (mark) {
      System.out.println("Nice! I've marked this Duke.task as done:");
      curTask.mark();
    } else {
      System.out.println("OK, I've marked this Duke.task as not done yet:");
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

  public static void throwDontKnow() throws DontKnow{
    throw new DontKnow();
  }

  public static void throwNoSuchTask(int i) throws NoSuchTask{
    throw new NoSuchTask(i);
  }

  public static void deleteTask(Task1 task, ArrayList<Task1> tasks) throws IOException {
    System.out.println("---------------------------");
    System.out.println("Noted. I've removed this Duke.task:");
    System.out.print(" ");
    task.printType();
    if(task.mark){
      System.out.print("[X]");
    } else {
      System.out.print("[ ] ");
    }
    System.out.print(task.string);
    task.printTime();
    tasks.remove(task);
    appendToFile(arrayListToString(tasks));
    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    System.out.println("---------------------------");
  }

  private static void appendToFile(String textToAppend) throws IOException {
    FileWriter fw = new FileWriter("data/duke.txt", false);
    fw.write(textToAppend);
    fw.close();
  }

  public static void main(String[] args) throws IOException {
    ArrayList<Task1> tasks = new ArrayList<>();
    DukeRerun.loading(tasks);
    String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);
    System.out.println("---------------------------");
    System.out.println("Hello! I'm OriginalDuke.Duke\n What can I do for you?");
    System.out.println("---------------------------");
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    String[] strArr = str.split(" ");
    while (!str.equals("bye")) {
      if (str.equals("list")) {
        listTask(tasks);
      } else if (strArr[0].equals("mark")) {
        int curIndex = Integer.parseInt(strArr[1]) - 1;
        if(curIndex > tasks.size()) {
          try{
            throwNoSuchTask(curIndex);
          } catch (NoSuchTask nt) {
            System.out.println(nt.noSuchTask);
            System.out.println("---------------------------");
          }
        } else {
          Task1 curTask = tasks.get(curIndex);
          markTask(curTask, true);
          appendToFile(arrayListToString(tasks) + "\n");
        }
      } else if (strArr[0].equals("unmark")) {
        int curIndex = Integer.parseInt(strArr[1]) - 1;
        if(curIndex > tasks.size()) {
          try{
            throwNoSuchTask(curIndex);
          } catch (NoSuchTask nt) {
            System.out.println(nt.noSuchTask);
            System.out.println("---------------------------");
          }
        } else {
          Task1 curTask = tasks.get(curIndex);
          markTask(curTask, false);
          appendToFile(arrayListToString(tasks) + "\n");
        }
      } else if (strArr[0].equals("todo")) {
        try {
          addTodo(strArr, tasks);
        } catch (EmptyDescription ep) {
          System.out.println(ep.emptyDescription);
          System.out.println("---------------------------");
        }
      } else if (strArr[0].equals("deadline")) {
        try {
          addDeadline(str, tasks);
        } catch (EmptyDescription ep) {
          System.out.println(ep.emptyDescription);
          System.out.println("---------------------------");
        } catch (EmptyTime e) {
          System.out.println(e.emptyTime);
          System.out.println("---------------------------");
        }
      } else if (strArr[0].equals("event")) {
        try {
          addEvent(str, tasks);
        } catch (EmptyDescription ep) {
          System.out.println(ep.emptyDescription);
          System.out.println("---------------------------");
        } catch (EmptyTime e) {
          System.out.println(e.emptyTime);
          System.out.println("---------------------------");
        }
      } else if(strArr[0].equals("delete")) {
        int curIndex = Integer.parseInt(strArr[1]) - 1;
        if(curIndex > tasks.size()) {
          try{
            throwNoSuchTask(curIndex);
          } catch (NoSuchTask nt) {
            System.out.println(nt.noSuchTask);
            System.out.println("---------------------------");
          }
        } else {
          Task1 curTask = tasks.get(curIndex);
          deleteTask(curTask, tasks);
        }
      } else {
        try {
          throwDontKnow();
        } catch (DontKnow dontKnow) {
          System.out.println(dontKnow.dontKnow);
          System.out.println("---------------------------");
        }
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
