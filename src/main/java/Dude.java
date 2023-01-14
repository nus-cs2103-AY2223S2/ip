import java.util.Scanner;

public class Dude {
    private static String[] todoList = new String[100];
    private static int itemCount = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        getIntro();

        while(true) {
            String command = sc.nextLine();
            getCommands(command);
            if (command.equals("bye")) {
                break;
            }
        }
    }

    public static void getIntro() {
        String logo = "  _____          __     \n" +
                " |  __ \\ __ __   | |  ___  \n" +
                " | |  | | | | |/ _` |/ _ \\\n" +
                " | |__| | |_| | (_| |  __/\n" +
                " |_____/ \\__,_|\\__,_|\\___|\n";

        System.out.println(logo);
        System.out.println(" ____________________________________________________________");
        System.out.println("\tYo! I'm dude");
        System.out.println("\tWhat you want me do for you?");
        System.out.println(" ____________________________________________________________\n");
    }

    public static void getCommands(String command) {
        switch(command) {
            case "list":
                System.out.println(" ____________________________________________________________");
                getList();
                System.out.println(" ____________________________________________________________\n");
                break;
            case "blah":
                System.out.println(" ____________________________________________________________");
                System.out.println("\tblah");
                System.out.println(" ____________________________________________________________\n");
                break;
            case "bye":
                System.out.println(" ____________________________________________________________");
                System.out.println("\tCiaos! See you next time.");
                System.out.println(" ____________________________________________________________\n");
                break;
            default:
                System.out.println(" ____________________________________________________________");
                addItem(command);
                System.out.println("\tAdd liao: " + command);
                System.out.println(" ____________________________________________________________\n");
        }
    }

    public static void addItem(String item) {
        todoList[itemCount] = item;
        itemCount++;
    }

    public static void getList() {
        for(int i = 0; i < itemCount; i++) {
            System.out.println("\t" + (i+1) + ". " + todoList[i]);
        }
    }
}
