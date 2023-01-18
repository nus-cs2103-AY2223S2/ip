import java.util.Scanner;
public class Duke {
    public static Integer taskCount;
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        */

        //Greets User
        String lineBreak = "-----------------";
        System.out.println( lineBreak + '\n' +
                            "Hello! I'm Panda" + '\n' +
                            "What can I do for you?" + '\n' +
                            lineBreak);

        Scanner sc = new Scanner(System.in);
        String user_input = sc.nextLine();

        String[] tasks = new String[100];
        // #Edit code from here onwards#

        taskCount = 0;
        //Echo action unless user says bye
        while(!user_input.equals("bye")) {
            if(user_input.equals("list")) {
                System.out.println(lineBreak);
                for(int i=0; i<taskCount; i++) {
                    System.out.println("" + (i+1) + ". " + tasks[i]);
                }
                System.out.println(lineBreak);
            } else {
                tasks[taskCount] = user_input;
                System.out.println(lineBreak + '\n' +
                        "added: " + user_input + '\n' +
                        lineBreak);
                taskCount++;
            }
            user_input = sc.nextLine();
        }
        System.out.println(lineBreak + '\n' +
                "Bye. Hope to see you again soon!" + '\n' +
                lineBreak);
    }
}
