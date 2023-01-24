import java.util.*;
public class Duke {
    public static void main(String[] args) throws DukeException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am Duke\nWhat can I do for you?");
        boolean isBye = false;
        TaskList taskList = new TaskList();
        Scanner sc = new Scanner(System.in);
        StringParser sp = new StringParser();
        while (!isBye) {
            try {
                String answer = sc.nextLine();
                String[] wordArr = answer.split(" ", 2);
                String command = wordArr[0].toUpperCase();

                System.out.println("___________________");
                if (wordArr[0].equals("mark")) {
                    String index = wordArr[1];
                    System.out.println(taskList.markTask(index));
                } else if (wordArr[0].equals("unmark")) {
                    String index = wordArr[1];
                    System.out.println(taskList.unmarkTask(index));
                } else if (answer.equals("bye")) {
                    isBye = true;
                    System.out.println("Goodbye");
                } else if (wordArr[0].equals("delete")) {
                    String index = wordArr[1];
                    System.out.println(taskList.deleteTask(index));
                } else if (answer.equals("list")) { //return list of tasks
                    System.out.println(taskList.listTasks());
                } else if (wordArr[0].equals("todo")) {
                    sp.parseTodo(answer);
                    String desc = wordArr[1];
                    System.out.println(taskList.addTask(desc));
                } else if (wordArr[0].equals("deadline")) {
                    String desc = wordArr[1].split("/by", 2)[0];
                    String by = wordArr[1].split("/by", 2)[1];
                    System.out.println(taskList.addTask(desc, by));

                } else if (wordArr[0].equals("event")) {
                    String desc = wordArr[1].split(" ", 2)[0];
                    String from = wordArr[1].split("/from", 2)[1].split("/to", 2)[0];
                    String to = wordArr[1].split("/to", 2)[1];
                    System.out.println(taskList.addTask(desc, from, to));
                } else{
                    throw new DukeException("Sorry, I don't know that command");
                }
            } catch(DukeException e){
                System.out.println(e.getMessage());
            }
            System.out.println("___________________");
        }

    }

}
