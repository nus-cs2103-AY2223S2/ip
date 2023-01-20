import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        line();
        indent("Hi, I'm Duke ");
        indent("What can I do for you :) ?\n");
        line();

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        try {
            FileReadWrite.readFile(list);
        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        int index = list.size();
        String input = scanner.nextLine();
        while(!input.equals("bye")){
            if (input.contains("unmark")){
                int i = Integer.parseInt(input.substring(7,8));
                Task t = unmarkTask(list, i);
                try {
                    FileReadWrite.writeUnmark(i, t);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                line();
                indent("Alright! I've unmarked this task :(\n");
                indent("  " + list.get(i - 1));
                line();
            }
            else if (input.contains("mark")){
                int i = Integer.parseInt(input.substring(5,6));
                Task t = markTask(list, i);
                try {
                    FileReadWrite.writeMark(i, t);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                line();
                indent("OK! I've marked this task as complete :)\n");
                indent("  " + list.get(i - 1));
                line();
            }
            else if (input.contains("delete")){
                int i = Integer.parseInt(input.substring(7,8));
                line();
                indent("OK! I've deleted this task :)\n");
                indent("  " + list.get(i - 1));
                indent(String.format("Now you have %d tasks in the list", index - 1));
                line();
                delete(list, i);
                try{
                    FileReadWrite.writeDelete(i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                index--;
            }
            else{
                switch (input){
                    case "list":
                        line();
                        indent("Here are the remaining tasks in your list:\n");
                        printList(list, index);
                        line();
                        break;
                    default:
                        try {
                            Task newTask = parseInput(input);
                            list.add(newTask);
                            try{
                                FileReadWrite.writeTask(list);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            index++;
                            line();
                            indent("Roger! I've added this task to the list:\n");
                            indent(newTask + "\n");
                            indent(String.format("Now you have %d tasks left in the list", index));
                            line();
                        } catch (EmptyArgException e) {
                            indent("Sorry! You provided an empty description. Pls provide a correct input :)");
                            line();
                        } catch (UnknownInputException u) {
                            indent("Sorry! I did not quite understand what you meant :( Pls try again!");
                            line();
                        }
                }

            }
            input = scanner.nextLine();
        }
        line();
        indent("Bye. Hope to hear from you again!");
        line();
    }

    public static void indent(String txt){
        System.out.println("     " + txt );
    }

    public static void line(){
        System.out.println("____________________________________________________________________________________");
    }

    public static void printList(ArrayList<Task> list, int index){
        for (int i = 0; i < index ; i++){
            int num = i + 1;
            String output = num + ". " + list.get(i);
            indent(output);
        }
    }

    public static Task markTask(ArrayList<Task> list, int index){

        Task  t = list.get(index - 1);
        t.mark();
        return t;
    }

    public static Task unmarkTask(ArrayList<Task> list, int index){

        Task t = list.get(index-1);
        t.unmark();
        return t;
    }

    public static void delete(ArrayList<Task> list, int index){
        list.remove(index - 1);
    }

    public static Task parseInput(String input) throws DukeException{
        Task newTask;
        if (input.contains("todo")){
            if (input.equals("todo")){
                throw new EmptyArgException("Did not provide argument");
            }
            newTask = new Todo(input.substring(5), false);
            return newTask;
        } else if (input.contains("deadline")){
            if (input.equals("deadline")){
                throw new EmptyArgException("Did not provide argument");
            }
            String[] arr = input.substring(9).split("/");
            newTask = new Deadline(arr[0],false, arr[1]);
            return newTask;
        } else if (input.contains("event")){
            if (input.equals("event")){
                throw new EmptyArgException("Did not provide argument");
            }
            String[] arr = input.substring(6).split("/");
            newTask = new Event(arr[0],false, arr[1], arr[2]);
            return newTask;
        } else {
            throw new UnknownInputException("Unknown Input!");
        }
    }
}
