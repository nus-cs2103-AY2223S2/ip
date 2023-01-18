import java.util.Scanner;
public class Duke {
    public static Task[] taskList = new Task[100];
    public static Integer taskCount = 0;
    public static void main(String[] args) {
        //Greets User
        String lineBreak = "-----------------";
        System.out.println( lineBreak + '\n' +
                "Hello! I'm Panda" + '\n' +
                "What can I do for you?" + '\n' +
                lineBreak);

        Scanner sc = new Scanner(System.in);
        String user_input = sc.nextLine();
        // #Edit code from here onwards#

        while(!user_input.equals("bye")) {
            String[] inputArray = user_input.split(" ");
            switch (inputArray[0]) {
                case "list":
                    if (taskList[0] == null) {
                        System.out.println(lineBreak + '\n' +
                                "There is currently no task in your list, trying adding some!" + '\n' +
                                lineBreak);
                    } else {
                        System.out.println(lineBreak + '\n' +
                                "Here are the tasks in your list: ");
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println("" + (i + 1) + ". " + taskList[i]);
                        }
                        System.out.println(lineBreak);
                    }
                    break;
                case "mark": {
                    int taskNum = Integer.parseInt(inputArray[1]);
                    Task currTask = taskList[taskNum - 1];
                    currTask.check();
                    System.out.println(lineBreak + '\n' +
                            "Nice! I've marked this task as done:" + '\n' +
                            currTask + '\n' +
                            lineBreak);
                    break;
                }
                case "unmark": {
                    int taskNum = Integer.parseInt(inputArray[1]);
                    Task currTask = taskList[taskNum - 1];
                    currTask.uncheck();
                    System.out.println(lineBreak + '\n' +
                            "OK, I've marked this task as not done yet:" + '\n' +
                            currTask + '\n' +
                            lineBreak);
                    break;
                }
                default:
                    taskList[taskCount] = new Task(user_input);
                    System.out.println(lineBreak + '\n' +
                            "added: " + user_input + '\n' +
                            lineBreak);
                    taskCount++;
                    break;
            }
            user_input = sc.nextLine();
        }
        System.out.println(lineBreak + '\n' +
                "Bye. Hope to see you again soon!" + '\n' +
                lineBreak);
    }
}
