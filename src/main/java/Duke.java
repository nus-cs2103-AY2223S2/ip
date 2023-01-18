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
                    System.out.println(cnt + "." + item.toString());
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
            } else if (str.length() >= 4 && str.substring(0, 4).equals("todo")) {
                String name = str.substring(5);
                Task toAdd = new ToDo(name);
                arr.add(toAdd);
                System.out.println("Got it. I've added this task:");
                System.out.println(toAdd.toString());
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else if (str.length() >= 4 && str.substring(0, 5).equals("event")) {
                String[] foo = separate(str.substring(6));
                String[] bar = getDuration(foo[1]);
                Task toAdd = new Event(foo[0], bar[0], bar[1]);
                arr.add(toAdd);
                System.out.println("Got it. I've added this task:");
                System.out.println(toAdd.toString());
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else if (str.length() >= 4 && str.substring(0, 8).equals("deadline")) {
                String[] foo = separate(str.substring(9));
                Task toAdd = new Deadline(foo[0], getDeadline(foo[1]));
                arr.add(toAdd);
                System.out.println("Got it. I've added this task:");
                System.out.println(toAdd.toString());
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            }
            else {
                System.out.println("Command not found");
            }
        }

        bf.close();
    }

    public static String[] separate(String line) {
        StringBuilder sb = new StringBuilder();

        int i;
        for (i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '/') {
                break;
            }
            sb.append(line.charAt(i));
        }

        //Handle i+1 exception
        return new String[] {sb.toString(), line.substring(i+1)};
    }

    public static String getDeadline(String line) {
        if (line.length() <= 3) {
            return "";
        } else {
            return line.substring(3);
        }
    }

    public static String[] getDuration(String line) {
        String[] arr = line.split("/");
        arr[0] = arr[0].substring(5);
        arr[1] = arr[1].substring(3);
        return arr;
    }
}
