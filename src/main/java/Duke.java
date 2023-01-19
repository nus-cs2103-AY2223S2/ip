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
        int index = 0;
        while(true) {
            Scanner myObj = new Scanner(System.in);
            String input = myObj.nextLine();
            if(input.equals("list")){
                System.out.println("Here are your tasks:");
                for(int i = 0; i<index; i++){
                    if(done[i]){
                        System.out.println(i + 1 + ".[x] " + list[i]);
                    }
                    else {
                        System.out.println(i + 1 + ":[ ] " + list[i]);
                    }
                }
            }
            else if(input.split(" ")[0].equals("mark")){
                int temp = parseInt(input.split(" ")[1]);
                System.out.println("marking task " + temp);
                done[temp-1] = true;

            }
            else if(input.split(" ")[0].equals("unmark")){
                int temp = parseInt(input.split(" ")[1]);
                System.out.println("unmarking task " + temp);
                done[temp-1] = false;

            }
            else if(input.equals("bye")){
                System.out.println("bai");
                break;
            }
            else {
                System.out.println("added: " +input);
                done[index] = false;
                list[index] = input;
                index++;
            }
        }
    }
}
