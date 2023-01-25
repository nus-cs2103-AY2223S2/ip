package duke.helper;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
  private File f;

  public Storage(TaskList tl) throws IOException {
    File dir = new File("data");
    if (!dir.exists()) {
      dir.mkdir();
    }
    File f = new File("data/taskData.txt");
    if (!f.exists()) {
      f.createNewFile();
    }
    this.f = f;
    loadTasks(tl, f);
  }

  public void loadTasks(TaskList tl, File f) throws FileNotFoundException {
    Scanner sc = new Scanner(f);
    while (sc.hasNext()) {
      tl.addTask(makeTask(sc.nextLine()));
    }
    sc.close();
  }

  public Task makeTask(String input) {
    String[] words = input.split("\\|");
    switch (words[0]) {
      case "T":
        return new ToDo(words[2]);
      case "D":
        return new Deadline(words[2], words[3]);
      default:
        return new Event(words[2], words[3], words[4]);
    }
  }

  public void saveTasks(TaskList tl) throws IOException {
    FileWriter fw = new FileWriter(f);
    for (Task t : tl.getTasks()) {
      fw.write(t.fileOutput() + "\n");
    }
    fw.close();
  }
}
