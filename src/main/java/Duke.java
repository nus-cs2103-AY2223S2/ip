import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help you?");
        String list[] = new String[100];
        Boolean done[] = new Boolean[100];
        Boolean deleted[] = new Boolean[100];
        String Type[] = new String[100];
        int index = 0;
        while(true) {
            Scanner myObj = new Scanner(System.in);
            String input[] = myObj.nextLine().split(" ",2);
            String command = input[0];
            String params = "";
            if(input.length>1){
                params = input[1];
            }
            if(command.equals("list")){
                System.out.println("Here are your tasks:");
                int num = 1;
                for(int i = 0; i<index; i++){
                    if(deleted[i]){
                        continue;
                    }
                    if(done[i]){
                        System.out.println(num + ".["+ Type[i]+ "]"+"[x] " + list[i]);
                    }
                    else {
                        System.out.println(num + ".["+ Type[i]+ "]"+"[ ] " + list[i]);
                    }
                    num++;
                }
            }
            else if(command.equals("delete")) {
                if(params.equals("")){
                    System.out.println("missing parameters.");
                    continue;
                }
                deleted[parseInt(params)-1] = true;
                System.out.println("Task deleted.");

            }
            else if(command.equals("mark")){
                if(params.equals("")){
                    System.out.println("missing parameters.");
                    continue;
                }
                int temp = parseInt(params);
                System.out.println("marking task " + temp);
                done[temp-1] = true;

            }
            else if(command.equals("unmark")){
                if(params.equals("")){
                    System.out.println("missing parameters.");
                    continue;
                }
                int temp = parseInt(params);
                System.out.println("unmarking task " + temp);
                done[temp-1] = false;

            }
            else if(command.equals("bye")){
                System.out.println("bai");
                break;
            }
            else if(command.equals("todo")) {
                if(params.equals("")){
                    System.out.println("missing parameters.");
                    continue;
                }
                System.out.println("added: " + params);
                done[index] = false;
                deleted[index] = false;
                list[index] = params;
                Type[index] = "T";
                index++;
                System.out.println("You have "+index+" tasks.");
            }
            else if(command.equals("deadline")) {
                if(params.equals("")){
                    System.out.println("missing parameters.");
                    continue;
                }
                System.out.println("added: " + params);
                done[index] = false;
                deleted[index] = false;
                list[index] = params;
                Type[index] = "D";
                index++;
                System.out.println("You have "+index+" tasks.");
            }
            else if(command.equals("event")) {
                if(params.equals("")){
                    System.out.println("missing parameters.");
                    continue;
                }
                System.out.println("added: " + params);
                done[index] = false;
                deleted[index] = false;
                list[index] = params;
                Type[index] = "E";
                index++;
                System.out.println("You have "+index+" tasks.");
            }
            else{
                System.out.println(command + " command not recognized.");
            }
        }
    }
}
