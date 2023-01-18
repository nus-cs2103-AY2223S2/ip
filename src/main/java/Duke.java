import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        ArrayList<Task> arr = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String str;
        while ((str = bf.readLine()) != null) {
            str = str.toLowerCase();

            if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (str.equals("list")) {
                int cnt = 1;
                while (cnt <= arr.size()) {
                    Task item = arr.get(cnt - 1);
                    System.out.println(cnt + ". " + "[" +  item.getStatus() + "] " + item.taskName);
                    cnt++;
                }
            } else if (str.length() >= 4 && str.substring(0, 4).equals("mark")) {
                // Handle exception here
                int numOfTask = Integer.parseInt(str.substring(5));

                // Handle when the index is out of bound -> create a separate function
                if (numOfTask <= 0 || numOfTask > arr.size()) {
                    System.out.println("Index of item is out of bound");
                    continue;
                }

                Task itemToMark = arr.get(numOfTask - 1);
                itemToMark.markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + itemToMark.taskName);

            } else if (str.length() >= 4 && str.substring(0, 6).equals("unmark")) {
                // Handle exception here
                int numOfTask = Integer.parseInt(str.substring(7));

                // Handle when the index is out of bound
                if (numOfTask <= 0 || numOfTask > arr.size()) {
                    System.out.println("Index of item is out of bound");
                    continue;
                }

                Task itemToMark = arr.get(numOfTask - 1);
                itemToMark.markUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[] " + itemToMark.taskName);
            } else {
                arr.add(new Task(str));
                System.out.println("added: " + str);
            }
        }

        bf.close();
    }
}
