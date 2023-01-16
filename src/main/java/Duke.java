import java.util.Scanner;

public class Duke {
  // static vars not good practice, in future may use sandwich() function.
  static String seperator = "____________________________________________________________\n";

  public static String greeting() {
    return seperator + "Nyahello! I'm Nyako!\n" + "What can I do for you nya?\n" + seperator;
  }

  public static String echo(String command) {
    return seperator + command + "\n" + seperator;
  }

  public static String bye() {
    return seperator + "Bye bye nya!\n" + seperator;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println(greeting());
    String command = sc.next();
    while (!command.equals("bye")) {
      System.out.println(echo(command));
      command = sc.next();
    }
    System.out.println(bye());
    sc.close();
  }
}
