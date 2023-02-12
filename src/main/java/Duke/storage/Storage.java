package Duke.storage;

import Duke.Command.Command;
import Duke.Exceptions.DontKnow;
import Duke.Exceptions.EmptyDescription;
import Duke.Exceptions.EmptyTime;
import Duke.Exceptions.NoSuchTask;
import Duke.task.*;

import java.io.*;

/**
 * Class deals with loading tasks from the file
 * and saving tasks in the file
 */
public class Storage {
  public TaskList tasks;
  public String filePath;

  /**
   * Constructor for Storage
   *
   * @param filePath receives String indicating the path of the data file.
   */
  public Storage(String filePath) {
    this.filePath = filePath;
    this.tasks = new TaskList();
  }

  /**
   * method manages to write certain String information
   * into the data file
   *
   * @param textToAppend receives task information
   *                     which should be saved in
   *                     data file.
   */
  private void appendToFile(String textToAppend) throws IOException {
    FileWriter fw = new FileWriter(this.filePath, false);
    fw.write(textToAppend);
    fw.close();
  }

  /**
   * method manages to update the data file
   * based on the task int eh current taskList
   */
  public void updateList() throws IOException {
    appendToFile(TaskList.arrayListToString());
  }

  /**
   * method loads data from the file to return a taskList
   */
  public TaskList loading() throws IOException,
      DontKnow, EmptyDescription, EmptyTime, NoSuchTask {
    File filename = new File(filePath);
    InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
    BufferedReader br = new BufferedReader(reader);
    String info;
    Task curTask;
    while ((info = br.readLine()) != null) {
      String[] strArr = info.split(" ");
      String[] strArrP = info.split("/");
      Command command = Command.searchCommand(strArr[0]);
      switch (command) {
        case TODO: {
          if (strArr.length < 2) {
            throw new EmptyDescription(new Todo(""));
          }
          String input = info.substring(5).trim();
          curTask = new Todo(input);
          tasks.addTask(curTask);
          break;
        }
        case DEADLINE: {
          if (strArr.length < 2) {
            throw new EmptyDescription(new Deadline("",
                "2022-01-01 0000"));
          }
          if (strArrP.length < 2) {
            throw new EmptyTime(new Deadline("",
                    "2022-01-01 0000"));
          }
          String[] division = info.substring(9).
              split(" /by ");
          String input = division[0].trim();
          String time = division[1].trim();
          curTask = new Deadline(input, time);
          tasks.addTask(curTask);
          break;
        }
        case EVENT: {
          if (strArr.length < 2) {
            throw new EmptyDescription(new Event("",
                "2022-01-01 0000", "2022-01-01 0000"));
          }
          if (strArrP.length < 3) {
            throw new EmptyTime(new Event("",
                "2022-01-01 0000", "2022-01-01 0000"));
          }
          String[] division = info.substring(6).split(" /from ");
          String input = division[0].trim();
          String[] timeDivision = division[1].split(" /to ");
          String startTime = timeDivision[0].trim();
          String endTime = timeDivision[1].trim();
          curTask = new Event(input, startTime, endTime);
          tasks.addTask(curTask);
          break;
        }
        case MARK: {
          int curIndex = Integer.parseInt(strArr[1]) - 1;
          if (curIndex > tasks.size()) {
            throw new NoSuchTask(curIndex);
          }
          Task object = tasks.get(curIndex);
          object.mark();
          break;
        }
        case UNMARK: {
          int curIndex = Integer.parseInt(strArr[1]) - 1;
          if (curIndex > tasks.size()) {
            throw new NoSuchTask(curIndex);
          }
          Task object = tasks.get(curIndex);
          object.unmark();
          break;
        }
        case DELETE: {
          int curIndex = Integer.parseInt(strArr[1]) - 1;
          if (curIndex > tasks.size()) {
            throw new NoSuchTask(curIndex);
          }
          tasks.delete(curIndex);
          break;
        }
      }
    }
    return this.tasks;
  }
}
