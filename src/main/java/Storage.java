import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class Storage {
  private Path savePath;
  
  public Storage(String first, String ...more) {
    this.savePath = Path.of(first, more);
  }

  public void save(TaskList tasks) throws SamSaveFailedException {
    try {
      if (!Files.exists(savePath.getParent())) {
        Files.createDirectory(savePath.getParent());
      }
      if (!Files.exists(savePath)) {
        Files.createFile(savePath);
      }

      String saveString = "";
      if (tasks.count() > 0) {
        String[] list = new String[tasks.count()];
        for (int i = 0; i < tasks.count(); i++) {
          Task t = tasks.getTask(i + 1);
          list[i] = t.toSaveFormat();
        }
        saveString = String.join("\n", list);
      }

      Files.writeString(savePath, saveString);
    } catch (IOException e) {
      throw new SamSaveFailedException();
    }
  }

  public void load(TaskList tasks) throws SamLoadFailedException {
    try {
      if (!Files.exists(savePath)) {
        return;
      }
      List<String> lines = Files.readAllLines(savePath);
      for (String line : lines) {
        String[] arr = line.split(" [|] ");
        Task t = null;
        boolean isDone = arr[1].equals("1");
        switch (arr[0]) {
          case "T":
            t = new ToDo(arr[2], isDone);
            break; 
          case "E": {
            LocalDate from = Sam.parseDate(arr[3]);
            LocalDate to = Sam.parseDate(arr[4]);
            t = new Event(arr[2], from, to, isDone);
            break; 
          }
          case "D": {
            LocalDate by = Sam.parseDate(arr[3]);
            t = new Deadline(arr[2], by, isDone);
            break;
          }
        }
        if (t != null) {
          tasks.addTask(t);
        }
      }
    } catch (IOException | SamInvalidDateException e) {
      throw new SamLoadFailedException();
    }
  }
}
