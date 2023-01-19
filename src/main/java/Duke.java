import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");

        Scanner sc = new Scanner (System.in);
        // List<String> arr = new ArrayList<String>();
        List<Task> arr = new ArrayList<>();

        while(sc.hasNextLine()) {            String ip = sc.nextLine();
            if(ip.equalsIgnoreCase("bye")){
                break;
            }
            else if (ip.equalsIgnoreCase("list")){
                for(int i = 0; i < arr.size(); i++){
                    System.out.printf("%d.[%S] %s\n", i+1, arr.get(i).getStatusIcon(), arr.get(i).getDesc());
                }
                System.out.println();
            }
            // level 1
            // System.out.println(ip + "\n");

            else if (ip.contains("mark")){
                String[] parts = ip.split(" ");
                if (parts.length != 2){
                    System.out.println("invalid\n");
                    continue;
                }
                int index = Integer.parseInt(parts[1]) - 1;
                if (parts[0].equalsIgnoreCase("mark")){
                    System.out.println("Nice! I've marked this task as done:");
                    arr.get(index).setStatus(parts[0]);
                }
                else if (parts[0].equalsIgnoreCase("unmark")){
                    System.out.println("OK, I've marked this task as not done yet:");
                    arr.get(index).setStatus(parts[0]);
                }
                System.out.printf("[%S] %s\n\n", arr.get(index).getStatusIcon(), arr.get(index).getDesc());
            }

            else{
                // level 2
                System.out.println("added: " + ip + "\n");
                // arr.add(ip);
                Task t = new Task(ip);
                arr.add(t);
            }

        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
