import java.io.*;
import java.util.ArrayList;

public class DukeRerun {
    public static void listTask(ArrayList<Task1> tasks) {
    }

    public static String combineStrArr(String[] strArr) {
      StringBuilder curStr = new StringBuilder();
      for (int i = 1; i < strArr.length; i++) {
        curStr.append(" ").append(strArr[i]);
      }
      return curStr.toString();
    }

    public static void addTodo(String[] strArr, ArrayList<Task1> tasks) throws EmptyDescription {
      if (strArr.length < 2) {
        throw new EmptyDescription(new Todo(""));
      } else {
        String curString = combineStrArr(strArr);
        Task1 curTask = new Todo(curString);
        tasks.add(curTask);
      }
    }

    public static void addDeadline(String str, ArrayList<Task1> tasks) throws EmptyDescription, EmptyTime {
      String[] strArr = str.split(" ");
      String[] strArrP = str.split("/");
      if (strArr.length < 2) {
        throw new EmptyDescription(new Deadline("", ""));
      } else if (strArrP.length < 2) {
        throw new EmptyTime(new Deadline("", ""));
      } else {
        String[] strArr1 = str.split("/");
        String timeWithBy = strArr1[strArr1.length - 1];
        String[] strArr2 = timeWithBy.split(" ");
        String time = combineStrArr(strArr2);
        String[] strArr3 = strArr1[0].split(" ");
        String task = combineStrArr(strArr3);
        Task1 curTask = new Deadline(task, time);
        tasks.add(curTask);
      }
    }
    public static void addEvent(String str, ArrayList<Task1> tasks) throws EmptyDescription, EmptyTime {
      String[] strArr = str.split(" ");
      String[] strArrP = str.split("/");
      if (strArr.length < 2) {
        throw new EmptyDescription(new Event("", "", ""));
      } else if (strArrP.length < 3) {
        throw new EmptyTime(new Event("", "", ""));
      } else {
        String[] strArr1 = str.split("/");
        String timeWithFrom = strArr1[strArr1.length - 2];
        String timeWithTo = strArr1[strArr1.length - 1];
        String[] strArr2 = timeWithFrom.split(" ");
        String[] strArr3 = timeWithTo.split(" ");
        String[] strArr4 = strArr1[0].split(" ");
        String startTime = combineStrArr(strArr2);
        String endTime = combineStrArr(strArr3);
        String task = combineStrArr(strArr4);
        Task1 curTask = new Event(task, startTime, endTime);
        tasks.add(curTask);
      }
    }

    public static void markTask(Task1 curTask, Boolean mark) {
      if (mark) {
        curTask.mark();
      } else {
        curTask.demark();
      }
      String curTaskStr = curTask.string;
    }

    public static void throwDontKnow() throws DontKnow{
      throw new DontKnow();
    }

    public static void throwNoSuchTask(int i) throws NoSuchTask{
      throw new NoSuchTask(i);
    }

    public static void deleteTask(Task1 task, ArrayList<Task1> tasks) {
      tasks.remove(task);
    }

    public static void loading(ArrayList<Task1> tasks) throws IOException {
      File filename = new File("data/duke.txt");
      InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
      BufferedReader br = new BufferedReader(reader);
      String str;
      while ((str = br.readLine()) != null) {
        String[] strArr = str.split(" ");
        if (str.equals("list")) {
          listTask(tasks);
        } else if (strArr[0].equals("mark")) {
          int curIndex = Integer.parseInt(strArr[1]) - 1;
          if(curIndex > tasks.size()) {
            try{
              throwNoSuchTask(curIndex);
            } catch (NoSuchTask ignored) {
            }
          } else {
            Task1 curTask = tasks.get(curIndex);
            markTask(curTask, true);
          }
        } else if (strArr[0].equals("unmark")) {
          int curIndex = Integer.parseInt(strArr[1]) - 1;
          if(curIndex > tasks.size()) {
            try{
              throwNoSuchTask(curIndex);
            } catch (NoSuchTask ignored) {
            }
          } else {
            Task1 curTask = tasks.get(curIndex);
            markTask(curTask, false);
          }
        } else if (strArr[0].equals("todo")) {
          try {
            addTodo(strArr, tasks);
          } catch (EmptyDescription ignored) {
          }
        } else if (strArr[0].equals("deadline")) {
          try {
            addDeadline(str, tasks);
          } catch (EmptyDescription | EmptyTime ignored) {
          }
        } else if (strArr[0].equals("event")) {
          try {
            addEvent(str, tasks);
          } catch (EmptyDescription | EmptyTime ignored) {
          }
        } else if(strArr[0].equals("delete")) {
          int curIndex = Integer.parseInt(strArr[1]) - 1;
          if(curIndex > tasks.size()) {
            try{
              throwNoSuchTask(curIndex);
            } catch (NoSuchTask ignored) {
            }
          } else {
            Task1 curTask = tasks.get(curIndex);
            deleteTask(curTask, tasks);
          }
        } else {
          try {
            throwDontKnow();
          } catch (DontKnow ignored) {
          }
        }
      }
    }
  }


