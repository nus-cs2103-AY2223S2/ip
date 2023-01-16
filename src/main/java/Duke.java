import java.io.*;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str = bufferedReader.readLine();
        ArrayList<Task> store = new ArrayList<>();
        while (!str.equals("bye")) {
            if (str.equals("list")) {
                int number = 1;
                for(Task stored : store) {
                    String storedString = stored.getStr();
                    boolean checked = stored.isChecked();
                    if (checked) {
                        System.out.println(number + ". [X] " + storedString);
                    } else {
                        System.out.println(number + ". [ ] " + storedString);
                    }

                    number++;
                }
            } else if (str.startsWith("mark ") && isNumber(str.replace("mark ", ""))) {
                int index = Integer.parseInt(str.replace("mark ", ""));
                Task task = store.get(index - 1);
                task.setChecked(true);
                System.out.println("Nice! I've marked this task as done: \n" + "[x] " + task.getStr());
            } else if (str.startsWith("unmark ") && isNumber(str.replace("unmark ", ""))) {
                int index = Integer.parseInt(str.replace("unmark ", ""));
                Task task = store.get(index - 1);
                task.setChecked(false);
                System.out.println("OK, I've marked this task as not done yet: \n" + "[ ] " + task.getStr());
            } else {
                Task task = new Task(str, false);
                store.add(task);
                System.out.println(str);
            }
            str = bufferedReader.readLine();

        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static boolean isNumber(String s)
    {
        for (int i = 0; i < s.length(); i++)
            if (!Character.isDigit(s.charAt(i)))
                return false;

        return true;
    }

}
