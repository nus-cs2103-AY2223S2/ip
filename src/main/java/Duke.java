import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, Duke here. How can I help you?");
        Scanner sc = new Scanner(System.in);
        String[] list = new String[100];
        int itemCount = 0;
        String userLine = "";
        while (!userLine.equals("bye")) {
            userLine = sc.nextLine();
            switch (userLine) {
                case "bye":
                    System.out.println("Bye, hope to see you again.");
                    break;
                case "list":
                    for (int i = 0; i < itemCount; i++) {
                        System.out.printf("%d: %s%n",i + 1,list[i]);
                    }
                    break;
                default:
                    list[itemCount] = userLine;
                    itemCount++;
                    System.out.println("Added: " + userLine);
            }
        }
    }
}
