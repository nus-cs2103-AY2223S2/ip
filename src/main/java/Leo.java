import java.util.Objects;
import java.util.Scanner;

public class Leo {
    public static void main(String[] args) {
        Leo assistant = new Leo();
        assistant.greetings();
        assistant.readCommand();
    }


    public void greetings() {
        String hello = "__   __   ______   __       __        _____ \n" +
                "| |  | |  | ____|  | |      | |      /  __  \\ \n" +
                "| |__| |  | |____  | |      | |      | |  | |\n" +
                "|  __  |  | ____|  | |      | |      | |  | |\n" +
                "| |  | |  | |____  | |____  | |____  | |__| |\n" +
                "|_|  |_|  |______| |______| |______| \\_____/\n";
        System.out.println(hello);
        System.out.println("Good day, I am Leo!");
        System.out.println("How can I assist you today?");
    }

    public void readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!Objects.equals(command, "bye")) {
            System.out.println("Leo: " + command);
            command = scanner.nextLine();
        }
        exit();
    }

    public void exit() {
        System.out.println("Good bye, have a nice day ahead!");
        System.exit(0);
    }

}
