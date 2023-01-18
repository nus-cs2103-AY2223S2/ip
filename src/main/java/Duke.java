import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("---------------------------");
        System.out.println("Hello! I'm Duke\n What can I do for you?");
        System.out.println("---------------------------");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String[] strings = new String[100];
        int index = 0;
        while (!str.equals("bye")) {
            if(str.equals("list")) {
                System.out.println("---------------------------");
                for(int i = 0; i < index; i++) {
                    System.out.println(i + 1 + ". " + strings[i]);
                }
                System.out.println("---------------------------");
            }
            else {
                System.out.println("---------------------------");
                System.out.println("add: " + str);
                System.out.println("---------------------------");
                strings[index] = str;
                index++;
            }
            str = br.readLine();

        }
        System.out.println("---------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------");
        System.exit(0);
        }
    }

