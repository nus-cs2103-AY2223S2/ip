import commands.*;
import data.MyData;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        MyData mydata = new MyData();
        int id;

        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";

        String greet = "    ____________________________________________________________\n"+
                "    Hello! I'm Duke\n" +
                "    What can I do for you?\n" +
                "    ____________________________________________________________";

        System.out.println("    Hello from\n" + logo);
        System.out.println(greet);

        while(mydata.getRun()) {
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            String[] commandArr = command.split(" ");
            if (command.equals("bye")) {
                Bye exit = new Bye();
                exit.execute(mydata);
            } else if (command.equals("list")) {
                List toList = new List();
                toList.execute(mydata);
            } else if (commandArr[0].equals("mark")) {
                id = Integer.parseInt(commandArr[1]);
                Mark toMark = new Mark(id - 1);
                try {
                    toMark.execute(mydata);
                } catch (IndexOutOfBoundsException e) {
                    System.out.print("    Please enter a valid value.\n");
                }
            } else if (commandArr[0].equals("unmark")) {
                id = Integer.parseInt(commandArr[1]);
                Unmark toUnmark = new Unmark(id - 1);
                try {
                    toUnmark.execute(mydata);
                } catch (IndexOutOfBoundsException e) {
                    System.out.print("    Please enter a valid value.\n");
                }
            } else {
                Add toAdd = new Add(command);
                toAdd.execute(mydata);
            }
        }
    }
}
