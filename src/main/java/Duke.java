import java.util.*;
public class Duke {
    public static void main(String[] args) {
        List<Task> list = new ArrayList<>(100);
        greet();
        //echo();
        processInputs(list);
    }
    private static void greet(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n What can I do for you?\n");
    }

    // echoes strings inputted by the user
    private static void echo(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while(input.equals("bye") == false){
            System.out.println("---------------------------------------");
            System.out.println(input);
            System.out.println("---------------------------------------");
            input = sc.next();
        }
        exit();
    }

    //exits the application when "exit" is inputted
    private static void exit(){
        System.out.println("---------------------------------------");
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    //adds items into the list and prints it when "list" is the input
    //our list takes in Tasks that are marked with a boolean.
    //processes the list with inputs from the user with list and Tasks operations.
    private static void processInputs(List<Task> list){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while( input.equals("bye") == false){
            if(input.equals("list")){
                System.out.println("---------------------------------------");
                for(int i = 0; i<list.size(); i++) {
                    Task element = list.get(i);
                    System.out.println(String.format("%d.[%s] %s",i+1,element.getStatusIcon(), element.getDescription()));
                }
                System.out.println("---------------------------------------");
            } else if(input.equals("mark")) {
                int item = sc.nextInt();
                Task curr = list.get(item-1);
                curr.setDone();
                System.out.println("---------------------------------------");
                System.out.println(String.format("Nice, this task has been marked as done:\n [%s] %s",curr.getStatusIcon(), curr.getDescription()));
                System.out.println("---------------------------------------");
            } else if(input.equals("unmark")) {
                int item = sc.nextInt();
                Task curr = list.get(item-1);
                curr.setUndone();
                System.out.println("---------------------------------------");
                System.out.println(String.format("ok, this task has been marked as not done yet:\n [%s] %s",curr.getStatusIcon(), curr.getDescription()));
                System.out.println("---------------------------------------");
            }
            else {
                Task t = new Task(input);
                list.add(t);
                System.out.println("---------------------------------------");
                System.out.println(String.format("added: %s", input));
                System.out.println("---------------------------------------");

            }
            input = sc.next();
        }
        exit();
    }



    }





