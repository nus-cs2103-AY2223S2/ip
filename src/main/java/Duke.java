import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner usrInput = new Scanner(System.in);
        String input = "";
        List<String> lst = new ArrayList<>();
        while(true){
            input = usrInput.nextLine();
            switch (input) {
                case "bye":
                    System.out.println("Goodbye!");
                    usrInput.close();
                    System.exit(0);
                case "list":
                    for (int j = 0; j < lst.size(); j++) {
                        System.out.println((j + 1) + ":" + lst.get(j));
                    }
                    break;
                default:
                    System.out.println("added:" + input);
                    lst.add(input);
                    
            }
        }
    }

}
