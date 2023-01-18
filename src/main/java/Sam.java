import java.util.Scanner;

public class Sam {
	public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
		MyList list = new MyList();
    boolean live = true;

    System.out.println(Assets.LOGO);
    talk("Hello, I am Sam!");

    while (live) {
      System.out.println();
      System.out.println(Assets.USER);
      System.out.print("> ");
      String input = scanner.nextLine();

			switch (input) {
				case "bye":
					live = false;
					talk("Goodbye!");
					break;
				case "list":
          String listString = list.generateList();
					talk("Here is your list:\n\n" + listString);
					break;
				default:
					list.addItem(input);
					talk("I've added \"" + input + "\" to your list");
			}
    }
    scanner.close();
  }

  private static void talk(String message) {
    System.out.println(Assets.SAM);
    System.out.println("┌───────────────────────────────────────────┐");
    System.out.println("  " + message);
    System.out.println("└───────────────────────────────────────────┘");
  }
}
