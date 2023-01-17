import java.util.Scanner;
public class Duke {
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
                            "Hello! I'm Duke" + '\n' +
                            "What can I do for you?" + '\n' +
                            lineBreak);

        Scanner sc = new Scanner(System.in);
        String user_input = sc.nextLine();

        //Echo action unless user says bye
        while(!user_input.equals("bye")) {
            System.out.println(lineBreak + '\n' +
                    user_input + '\n' +
                    lineBreak);
            user_input = sc.nextLine();
        }

        System.out.println(lineBreak + '\n' +
                "Bye. Hope to see you again soon!" + '\n' +
                lineBreak);
    }
}
