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


        String[] arr = new String[100];
        int curr = 0;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String str;
        while ((str = bf.readLine()) != null) {
            if (str.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (str.toLowerCase().equals("list")) {
                int cnt = 1;
                while (cnt <= curr) {
                    System.out.println(cnt + ". " + arr[cnt - 1]);
                    cnt++;
                }
            } else {
                arr[curr] = str;
                System.out.println("added: " + str);
                curr++;
            }
        }

        bf.close();
    }
}
