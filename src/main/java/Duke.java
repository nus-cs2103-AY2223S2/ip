import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {


        begin();

        //open to do list file stored
        java.nio.file.Path path = java.nio.file.Paths.get(".", "src", "main", "ToDoListCS2103.txt");
        boolean doesDirectoryExist = java.nio.file.Files.exists(path);
        TodoList todoList = new TodoList();
        try {
            if (doesDirectoryExist) {
                File previousToDoList = new File(path.toString());
                FileInputStream fis = new FileInputStream(previousToDoList);
                ObjectInputStream ois = new ObjectInputStream(fis);
                todoList = (TodoList) ois.readObject();
                ois.close();
                fis.close();
            } else {
                File previousToDoList = new File(path.toString());
                previousToDoList.createNewFile();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
         }

        ChatBot bot = new ChatBot(todoList);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            try {
                bot.execute(input);
            } catch (DukeExceptions error) {
                System.out.println(error.getErrorMessage());
            }
            endCommand();
            input = scanner.nextLine(); //ready for next input
        }
        scanner.close();

        //save to do list before close the program
        try{
            FileOutputStream fos = new FileOutputStream(path.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(todoList);
            oos.flush();
            oos.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        bye();
    }

    private static void begin() {
        endCommand();

        System.out.println("Hello from Duke");
        System.out.println("What can I do for you?");

        endCommand();
    }

    private static void endCommand() {
        System.out.println("____________________________________________________________");
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        endCommand();
    }
}
