package duke;

import duke.task.Task;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility used by TaskList to manage file I/O and data storage.
 * The default save location is {@code data.txt}.
 *
 * @see Task
 * @see TaskList
 */
public class Storage {
  private String fileName = "data.txt";

  /**
   * Saves the given tasks to the last accessed location.
   *
   * @param tasks the tasks to be saved
   */
  public void save(List<Task> tasks) {
    try {
      FileOutputStream fos = new FileOutputStream(fileName);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(tasks);
      oos.close();
    } catch (IOException e) {
      System.out.println("\t[ERROR] While saving, the following error occurred: \n\t" + e);
    }
  }

  /**
   * Saves the given tasks to the given file.
   *
   * @param tasks    the tasks to be saved
   * @param fileName the name of the file to be used to store tasks.
   */
  public void save(List<Task> tasks, String fileName) {
    this.fileName = fileName;
    this.save(tasks);
  }

  /**
   * Loads the tasks from the last accessed location.
   *
   * @return the tasks stored in the last accessed location
   * @throws IOException            if the file cannot be found or created.
   * @throws ClassNotFoundException if the file cannot be deserialized.
   */
  public List<Task> load() throws IOException, ClassNotFoundException, ClassCastException {
    FileInputStream fin = new FileInputStream(fileName);
    ObjectInputStream ois = new ObjectInputStream(fin);
    @SuppressWarnings("unchecked") List<Task> tasks = (ArrayList<Task>) ois.readObject();
    fin.close();
    return tasks;
  }

  /**
   * Loads the tasks from the given file.
   *
   * @param fileName the name of the file used to store tasks.
   * @return the tasks stored in the given file
   * @throws IOException            if the file cannot be found or created.
   * @throws ClassNotFoundException if the file cannot be deserialized.
   */
  public List<Task> load(String fileName)
      throws IOException, ClassNotFoundException, ClassCastException {
    this.fileName = fileName;
    return this.load();
  }
}
