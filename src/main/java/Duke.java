import java.util.*;
public class Duke {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(100);
        greet();
        //echo();
        processList(list);
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
    private static void processList(List<String> list){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while( input.equals("bye") == false){
            if(input.equals("list")){
                System.out.println("---------------------------------------");
                for(int i = 0; i<list.size(); i++) {
                    String element = list.get(i);
                    System.out.println(String.format("%d. %s",i+1,element));
                }
                System.out.println("---------------------------------------");
                input = sc.nextLine();
            } else {
                list.add(input);
                System.out.println("---------------------------------------");
                System.out.println(String.format("added: %s", input));
                System.out.println("---------------------------------------");
                input = sc.nextLine();
            }
        }
        exit();
    }
}
