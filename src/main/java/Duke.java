import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
public class Duke {
    public static void main(String[] args) throws DukeException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am Duke\nWhat can I do for you?");
        boolean isBye = false;
        Harddrive harddrive = new Harddrive();
        TaskList taskList = new TaskList();
        Scanner sc = new Scanner(System.in);
        StringParser sp = new StringParser();
        while (!isBye) {
            try {
                //generate filepath
                File f = new File("data/duke.txt");
                //if filepath does not exist
                if (!f.exists()){
                    try {
                        //create a new file
                        f.createNewFile();
                    } catch (java.io.IOException e){
                        System.out.println("File already exists");
                    }
                }
                String taskFilePath = f.getAbsolutePath();
                String answer = sc.nextLine();
                String[] wordArr = answer.split(" ", 2);
                String command = wordArr[0].toUpperCase();
                System.out.println("___________________");
                if (wordArr[0].equals("mark")) {
                    int index = Integer.parseInt(wordArr[1]);
                    System.out.println(taskList.markTask(index - 1));
                    //submit index to hardrive object
                } else if (wordArr[0].equals("unmark")) {
                    int index = Integer.parseInt(wordArr[1]);
                    System.out.println(taskList.unmarkTask(index - 1));
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
                    harddrive.updateFile(taskList);
                } else if (wordArr[0].equals("deadline")) {
                    String desc = wordArr[1].split(" /by ")[0];
                    String by = wordArr[1].split(" /by ")[1];
                    System.out.println(taskList.addTask(desc, by));

                } else if (wordArr[0].equals("event")) {
                    String desc = wordArr[1].split(" ", 2)[0];
                    String from = wordArr[1].split(" /from ")[1].split(" /to ")[0];
                    String to = wordArr[1].split(" /to ", 2)[1];
                    System.out.println(taskList.addTask(desc, from, to));
                } else{
                    throw new DukeException("Sorry, I don't know that command");
                }
                harddrive.updateFile(taskList);
            } catch(DukeException e){
                System.out.println(e.getMessage());
            }
            System.out.println("___________________");
        }

    }

}
