import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import duke.Duke;
import duke.task.Task;

public final class Program {
  public final static void main(String[] vargs) throws IOException {
    if (Files.exists(Paths.get("data.dat"))) {
      try (
        final FileInputStream ifstream = new FileInputStream("data.dat");
        final ObjectInputStream objStream = new ObjectInputStream(ifstream);
      ) {
        @SuppressWarnings("unchecked")
        List<Task> tasks = (List<Task>) objStream.readObject();
        Duke instance = new Duke(tasks);
        instance.runDuke();
        return;
      } catch (IOException e) {
        System.out.format("An IOException occurred while reading your tasks from disk%s\n", e.getMessage());
      } catch (ClassNotFoundException e) {
        System.out.println("Your data file was corrupted, so we were unable to read your tasks from there");
      }
    }

    Duke instance = new Duke();
    instance.runDuke();
  }
}
