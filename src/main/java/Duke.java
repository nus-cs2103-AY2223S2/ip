import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String spacingIndent = "    ";
        String welcomeMsg = spacingIndent + " "
                + "Hello! I'm Duke\n"
                + spacingIndent + " "
                + "How can I help you?\n";
        String exitMsg = spacingIndent + " "
                + "Farewell! See you soon!\n";
        String dashes = spacingIndent
                + "______________________________"
                + "______________________________\n";
        String curInput = "";
        boolean status = true;
        System.out.println(dashes
                + welcomeMsg
                + dashes);

        while (status) {
            curInput = sc.nextLine();

            if (curInput.equals("bye")){
                status = false;
            } else {
                System.out.println(dashes
                        + spacingIndent + " " + curInput
                        + "\n" + dashes);
            }
        }

        System.out.println(dashes
                + exitMsg
                + dashes);

    }
}
