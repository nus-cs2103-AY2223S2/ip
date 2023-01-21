import java.io.IOException;
import duke.Duke;

public final class Program {
  public final static void main(String[] vargs) throws IOException {
      String logo = " ____        _        \n"
              + "|  _ \\ _   _| | _____ \n"
              + "| | | | | | | |/ / _ \\\n"
              + "| |_| | |_| |   <  __/\n"
              + "|____/ \\__,_|_|\\_\\___|\n";
      System.out.println("Hello from\n" + logo);

      Duke instance = new Duke();
      instance.runDuke();
  }
}
