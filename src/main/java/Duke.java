import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);

        String comm = "";

        List<String> list = new ArrayList<>();

        while(!comm.equals("bye")) {
            comm = sc.nextLine();
            if(comm.equals("list")) {
                System.out.println(line);
                for(int i=0; i < list.size(); i++) {
                    int num = i+1;
                    System.out.println(num + ". " + list.get(i) );
                }
                System.out.println(line);
            } else {
                list.add(comm);
                System.out.println(line);
                System.out.println("added: " + comm);
                System.out.println(line);
            }
        }
            System.out.println(line);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(line);



    }
}
