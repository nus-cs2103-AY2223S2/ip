import java.util.Scanner;

public class Sam {
	public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean live = true;

		String logo =
				"\t  ██████╗ █████╗ ███╗   ███╗\n"
			+ "\t ██╔════╝██╔══██╗████╗ ████║\n"
			+ "\t ╚█████╗ ███████║██╔████╔██║\n"
			+ "\t  ╚═══██╗██╔══██║██║╚██╔╝██║\n"
			+ "\t ██████╔╝██║  ██║██║ ╚═╝ ██║\n"
			+ "\t ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝";

		System.out.println(logo);
		talk("Hello, I am Sam!");

    while (live) {
      System.out.println();
      System.out.print("> ");
      System.out.println();
      String input = scanner.nextLine();
      if (input.equals("bye")) {
        live = false;
        talk("Bye!");
      } else {
        talk(input);
      }
    }
    scanner.close();
	}

  private static void talk(String message) {
    System.out.println("┌───────────────────────────────────────────┐");
    System.out.println("  " + message);
    System.out.println("└───────────────────────────────────────────┘");
  }
}