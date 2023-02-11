package duke;

import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        TaskList list = new TaskList();
        try {
            Storage.loadFile(list);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        Ui.greet();
        Scanner sc = new Scanner(System.in);
        

        while(true){
            String userInput = sc.nextLine();
            String[] splitInput = userInput.split("\\s+");
            Parser.parseInput(splitInput);
            try {
                Storage.saveToFile(list);
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
        }
    }
}



