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
          if (parts[0].equalsIgnoreCase("T")) {
              Task t = new Task.Todo(parts[2]);
              taskList.add(t);
              if (parts[1].equalsIgnoreCase("1")) {
                  t.markDone();
              }
          } else if (parts[0].equalsIgnoreCase("D")) {
              Task t = new Task.Deadline(parts[2], parts[3]);
              taskList.add(t);
              if (parts[1].equalsIgnoreCase("1")) {
                  t.markDone();
              }
          } else {
              Task t = new Task.Event(parts[2], parts[3] + " ", parts[4]);
              taskList.add(t);
              if (parts[1].equalsIgnoreCase("1")) {
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
    ArrayList<Task> taskList = list.get();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        int length = taskList.size();
        for (int i = 0; i < length; i++) {
            String item = taskList.get(i).toData();
            writer.write(item);
            writer.newLine();
        }
    } catch (IOException e) {
        System.out.println("An error occurred while writing to the file: " + e.getMessage());
    }
}

}
