import java.util.Scanner;

public class Sam {
	public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
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
					talk("Bye!");
					break;
				case "list":
					talk("Here is your list:");
					break;
				default:
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

abstract class Assets {
  public static final String LOGO = 
        "\t  ██████╗ █████╗ ███╗   ███╗\n"
      + "\t ██╔════╝██╔══██╗████╗ ████║\n"
      + "\t ╚█████╗ ███████║██╔████╔██║\n"
      + "\t  ╚═══██╗██╔══██║██║╚██╔╝██║\n"
      + "\t ██████╔╝██║  ██║██║ ╚═╝ ██║\n"
      + "\t ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝";
  public static final String USER =
      " ███████\n"
    + "████▀██▀█\n"
    + "████▄██▄█\n"
    + " ▀▀▀▀▀▀▀";
  public static final String SAM =
      "\t\t\t\t        ▄\n"
    + "\t\t\t\t ▒▒██▒▒▓▓▀\n"
    + "\t\t\t\t▒▒▀██▀▒▒▓▓\n"
    + "\t\t\t\t █▄██▄███▓▓\n"
    + "\t\t\t\t  ▀▀▀▀▀▀ ▓";
}