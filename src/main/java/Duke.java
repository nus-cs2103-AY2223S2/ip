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
        String[] strArr = str.split(" ");
        Task[] tasks = new Task[100];
        int index = 0;
        while (!str.equals("bye")) {
            if(str.equals("list")) {
                System.out.println("---------------------------");
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < index; i++) {
                    if(!tasks[i].mark) {
                        System.out.print(i + 1 + "." + "[ ] ");
                    } else {
                        System.out.print(i + 1 + "." + "[X] ");
                    }
                    System.out.println(tasks[i].string);
                }
                System.out.println("---------------------------");
            } else if(strArr[0].equals("mark")) {
                System.out.println("---------------------------");
                System.out.println("Nice! I've marked this task as done:");
                int curIndex = Integer.parseInt(strArr[1]) - 1;
                tasks[curIndex].mark();
                String curTask = tasks[curIndex].string;
                System.out.println("[X] " + curTask);
                System.out.println("---------------------------");
            } else if(strArr[0].equals("unmark")) {
                System.out.println("---------------------------");
                System.out.println("OK, I've marked this task as not done yet:");
                int curIndex = Integer.parseInt(strArr[1]) - 1;
                tasks[curIndex].demark();
                String curTask = tasks[curIndex].string;
                System.out.println("[ ]" + curTask);
                System.out.println("---------------------------");
            }
            else {
                System.out.println("---------------------------");
                System.out.println("add: " + str);
                System.out.println("---------------------------");
                tasks[index] =new Task(str);
                index++;
            }
            str = br.readLine();
            strArr = str.split(" ");
        }
        System.out.println("---------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------");
        System.exit(0);
        }
    }

