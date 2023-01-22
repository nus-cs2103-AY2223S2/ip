import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        talk("Meow I'm Toto! What can I do for you?");
        ArrayList<String> arrayList = new ArrayList<>(100);
        echo(arrayList);

    }

    public static void talk(String s) {
        System.out.println(s);
    }

    public static void echo(ArrayList<String> arrayList) {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        if (answer.equals("bye")) {
            System.out.println("Byebye see you again!");
            return;
        }
        if (answer.equals("list")) {
            list(arrayList);
            return;
        }
            System.out.println("added: " + answer);
            arrayList.add(answer);
            echo(arrayList);
    }

    public static void list(ArrayList<String> arrayList) {
        for (int i = 1; i < arrayList.size() + 1; i++) {
            System.out.println(i + ". " + arrayList.get(i - 1));
        }
        echo(arrayList);
    }
}
