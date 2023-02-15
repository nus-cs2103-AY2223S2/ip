import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
  private String filePath;

  public Storage(String filePath) {
    this.filePath = filePath;
  }

  public ArrayList<Task> load() {
    ArrayList<Task> taskList = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
          String[] parts = line.split("\\|");
          if (parts[0].strip().equalsIgnoreCase("T")) {
              Task t = new Task.Todo(parts[2].strip());
              taskList.add(t);
              if (parts[1].strip().equalsIgnoreCase("1")) {
                  t.markDone();
              }
          } else if (parts[0].strip().equalsIgnoreCase("D")) {
              Task t = new Task.Deadline(parts[2].strip(), parts[3].strip());
              taskList.add(t);
              if (parts[1].strip().equalsIgnoreCase("1")) {
                  t.markDone();
              }
          } else {
              Task t = new Task.Event(parts[2].strip(), parts[3].strip(), parts[4].strip());
              taskList.add(t);
              if (parts[1].strip().equalsIgnoreCase("1")) {
                  t.markDone();
              }
          }
      }
      return taskList;
    } catch (IOException e) {
        System.out.println("An error occurred while reading the file: " + e.getMessage());
        return new ArrayList<>();
    }
  }

  public void writeTxt(TaskList list) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        String toWrite = list.toWrite();
        writer.write(toWrite);
    } catch (IOException e) {
        System.out.println("An error occurred while writing to the file: " + e.getMessage());
    }
}

}
