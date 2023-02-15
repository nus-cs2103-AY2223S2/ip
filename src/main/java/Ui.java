import java.util.Scanner;

public class Ui {
  private final String WELCOME_MESSAGE = "Hey Buddy, I'm Duke\nWhat can I do for you?";
  private final String GOODBYE_MESSAGE = "Goodbye friend. Hope to see you again soon!";
  private Scanner scanner;

  public Ui() {
    this.scanner = new Scanner(System.in);
  }

  public void welcomeUser() {
    System.out.println(WELCOME_MESSAGE);
  }

  public String readInput() {
    System.out.println("=======ME=======");
    String textInput = this.scanner.nextLine();
    return textInput;
  }

  public void goodbyeUser() {
    System.out.println("======DUKE======");
    System.out.println(GOODBYE_MESSAGE);
    this.scanner.close();
    System.out.println("================");
  }

  public void listTasks(TaskList taskList) {
    System.out.println("======DUKE======");
    System.out.println(taskList.toString());
  }

  public void printResponse(String response) {
    System.out.println("======DUKE======");
    System.out.println(response);
  }
}