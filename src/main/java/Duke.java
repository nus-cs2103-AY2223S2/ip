import java.util.Scanner;

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
        int index = 0;
        while(true) {
            Scanner myObj = new Scanner(System.in);
            String input = myObj.nextLine();
            if(input.equals("list")){
                for(int i = 0; i<index; i++){
                    System.out.println(i+1+": "+list[i]);
                }
            }
            if(input.equals("bye")){
                System.out.println("bai");
                break;
            }
            else {
                System.out.println("added: " +input);
                list[index] = input;
                index++;
            }
        }
    }
}
