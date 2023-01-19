import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    static String[] arr = new String[100];
    static String str = "------------------------------------------------------------";


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printResponse("Hello! I'm Duke\n" + "What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String word = scanner.nextLine();
            if (word.equals("bye")) {
                printResponse("Bye. Hope to see you again soon!");
                break;
            } else if (word.equals("list")){
                list();
            } else {
                add(word);
            }
        }
    }

    public static void printResponse(String response) {
        System.out.println(str);
        System.out.println(response);
        System.out.println(str);
    }

    public static void add(String response) {
        for(int i = 0; i < 100; i++) {
            if(arr[i] == null) {
                arr[i] = response;
                printResponse("Added: " + response);
                break;
            }
        }
    }

    public static void list() {
        int x = 1;
        String lst = "";
        for(int i = 0; i < 100; i++) {
            if(arr[i] != null) {
                if(!lst.equals("")) {
                    lst = lst + "\n";
                }
                lst = lst + x + ". " + arr[i];
                x++;
            }
        }
        printResponse(lst);
    }
}
