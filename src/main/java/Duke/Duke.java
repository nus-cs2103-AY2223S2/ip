package Duke;

import Command.Parser;
import Exceptions.*;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke<Ui> {

  private final UI ui;

    public Duke(String filePath) {
      Storage storage = new Storage(filePath);
      TaskList tasks = new TaskList();
      try {
        tasks = storage.loading();
      } catch (DontKnow e) {
        System.out.println(e.dontKnow);
      } catch (EmptyTime e) {
        System.out.println(e.emptyTime);
      } catch (EmptyDescription e) {
        System.out.println(e.emptyDescription);
      } catch (NoSuchTask e) {
        System.out.println(e.noSuchTask);
      } catch (IOException e) {
        System.out.println("io exception!");
      }
      ui = new UI(new Parser(tasks, storage));
    }

    public void run() throws IOException {
      greeting();
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String str = br.readLine();
      while (!str.equals("bye")) {
        System.out.println(ui.respondInput(str));
        str = br.readLine();
      }
      System.out.println(ui.respondInput(str));
      System.exit(0);
    }

    public static void main(String[] args) throws IOException {
      new Duke("data/duke.txt").run();
    }

    public void greeting() {
      String logo = " ____        _        \n"
          + "|  _ \\ _   _| | _____ \n"
          + "| | | | | | | |/ / _ \\\n"
          + "| |_| | |_| |   <  __/\n"
          + "|____/ \\__,_|_|\\_\\___|\n";
      String sayHi = "Hello from\n" + logo +
          "\nHello! I'm Duke\n What can I do for you?";
      System.out.println(sayHi);
  }
}
