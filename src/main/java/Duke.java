import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //ArrayList<String> tasks = new ArrayList<String>();
        Task[] tasks = new Task[100];
        int taskNum = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome to: \n" + logo);
        System.out.println(
                "    ____________________________________________________________" +
                "\n     Hello! I'm Duke" +
                "\n     What can I do for you?" +
                "\n    ____________________________________________________________");

        String input = sc.nextLine();
        String[] inputArr = input.split(" ");

        while (!inputArr[0].equalsIgnoreCase("bye")) {
            switch (inputArr[0]) {
                case "list":
                    System.out.println("    ____________________________________________________________");
                    for (int i = 0; i < taskNum; i++) {
                        System.out.println("     " + i + ". " + tasks[i].toString());
                    }
                    System.out.println("    ____________________________________________________________");
                    break;
                case "mark":
                    int selectedNum = Integer.parseInt(inputArr[1]);
                    tasks[selectedNum].markDone();
                    System.out.println(
                            "    ____________________________________________________________" +
                            "\n     Nice! I've marked this task as done:" +
                            tasks[selectedNum].toString() +
                            "\n    ____________________________________________________________");
                    break;
                default:
                    tasks[taskNum] = new Task(input);
                    taskNum++;
                    //tasks.add(input);
                    String output = "    ____________________________________________________________"
                            + "\n      added: "
                            + input
                            + "\n    ____________________________________________________________";
                    System.out.println(output);
                    break;
            }
            input = sc.nextLine();
            inputArr = input.split(" ");
        }

        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");

        sc.close();

    }
}
