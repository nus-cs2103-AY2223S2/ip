package Duke;

import Duke.Command.Parser;
import Duke.Exceptions.*;
import Duke.storage.Storage;
import Duke.task.TaskList;
import Duke.ui.UI;

import java.io.*;

/**
 * Main Class to run duke programme.
 */
public class Duke {

  private final UI ui;

  /**
   * Constructor for Duke.
   * Handles exceptions during loading process
   *
   * @param filePath receives the path of the data file
   */
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
        File data = new File("data");
        data.mkdirs();
      }
      ui = new UI(new Parser(tasks, storage));
    }

  /**
   * method to access UI so that duke responds to user input
   */
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
  /**
   * main duke method that runs with its data file
   */
    public static void main(String[] args) throws IOException {
      try {
        new Duke("data/duke.txt").run();
      } catch (FileNotFoundException e) {
        File data = new File("data");
        data.mkdirs();
        File duke = new File("data/duke.txt");
        new Duke(duke.getPath()).run();
      }
    }

  /**
   * method to print greetings when user starts duke programme.
   */
    public void greeting() {
      String logo = " ____        _        \n"
          + "|  _ \\ _   _| | _____ \n"
          + "| | | | | | | |/ / _ \\\n"
          + "| |_| | |_| |   <  __/\n"
          + "|____/ \\__,_|_|\\_\\___|\n";
      String sayHi = "Hello from\n" + logo +
          "\nHello! I'm Duke.Duke\n What can I do for you?";
      System.out.println(sayHi);
  }
}
