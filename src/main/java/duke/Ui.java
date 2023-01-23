package duke;

import java.util.Scanner;

public class Ui {
    public void showLoadingError(){
        System.out.println("Unable to load tasks from storage");
    }
    public void welcomeMessage(){
        String chopper =
                "           /\\_/\\\n" +
                        "          ( o.o )\n" +
                        "           > ^ <\n";
        System.out.println(chopper);
        System.out.println("    Hello I'm chopper\n" +
                "    My commands are the following:\n" +
                "      1. todo <description>\n" +
                "      2. deadline <description> /by <yyyy-MM-dd HHmm(optional)>\n" +
                "      3. event <description> /from <date> /to <date>\n" +
                "      4. delete <task number>\n" +
                "      5. mark <task number>\n" +
                "      6. unmark <task number>\n" +
                "      7. list\n" +
                "      8. bye\n" +
                "    What can I do for you?");
    }
    public void showLine(){
        System.out.println("____________________________________________");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
