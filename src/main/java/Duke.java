import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> storage = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        String spacingIndent = "    ";
        String welcomeMsg = spacingIndent + " "
                + "Hello! I'm Duke\n"
                + spacingIndent + " "
                + "How can I help you?";
        String exitMsg = spacingIndent + " "
                + "Farewell! See you soon!";
        String dashes = spacingIndent
                + "______________________________"
                + "______________________________";
        String curInput = "";
        boolean status = true;
        System.out.println(dashes);
        System.out.println(welcomeMsg);
        System.out.println(dashes);

        while (status) {
            curInput = sc.nextLine();

            if (curInput.equals("bye")){
                status = false;
            } else if (curInput.equals("list")) {
                System.out.println(dashes);
                for (int i = 0; i < storage.size(); i++) {
                    int number = i + 1;
                    System.out.println(spacingIndent + " "
                            + number + ". " + storage.get(i));
                }
                System.out.println(dashes);
            }
            else {
                storage.add(curInput);
                System.out.println(dashes);
                System.out.println(spacingIndent
                        + " " + "added: " + curInput);
                System.out.println(dashes);
            }
        }

        System.out.println(dashes);
        System.out.println(exitMsg);
        System.out.println(dashes);

    }
}
