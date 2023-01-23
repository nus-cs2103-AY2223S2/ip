package duke.main;

public class Ui {
  public static void printLogo() {
    String logo = " ____        _        \n"
          + "|  _ \\ _   _| | _____ \n"
          + "| | | | | | | |/ / _ \\\n"
          + "| |_| | |_| |   <  __/\n"
          + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);    
  }

  public static void print(String args) {
    System.out.println(args);
  }

  public static void print(String format, Object ...args) {
    print(String.format(format, args));
  }
}
