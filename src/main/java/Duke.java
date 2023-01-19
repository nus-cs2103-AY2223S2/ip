import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Duke {
    private Set<String> commandMap = new HashSet<>(){{
        add("list");
        add("mark");
        add("unmark");
        add("todo");
        add("deadline");
        add("event");
        add("bye");
    }};

    private static boolean isNumeric(String str) {
        try {  
            Double.parseDouble(str);  
            return true;
        } catch(NumberFormatException e){  
            return false;  
        }  
    }

    public String[] checkInput(String[] input) throws DukeException {
        //Empty commands
        if(input == null) {
            throw new DukeException(0);
        }

        //Invalid command
        if(!commandMap.contains(input[0]) || input.length > 2 && (input[0].equals("mark") || input[0].equals("unmark"))) {
            throw new DukeException(1);
        }

        //Empty parameters
        if(input.length == 1) {
            throw new DukeException(2);
        }

        //Non-numerical parameters
        if(input[0].equals("mark") || input[0].equals("unmark")) {
            if(!isNumeric(input[1])) throw new DukeException(3); 
        }

        if(input[0].equals("deadline") || input[0].equals("event")) {
            boolean hasBy, hasFrom, hasTo;

            for (String s : input) {
                if(s.equals("/by")) hasBy = true;
                if(s.equals("/from")) hasFrom = true;
                if(s.equals("/to")) hasTo = true;
            }

            if(!hasBy || (hasFrom && !hasTo))
        }

        return input;
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        ArrayList<Task> lists = new ArrayList<>();
        boolean loop = true;

        String bracket = "\t_______________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String welcomeMsg = ("Hello from\n" + logo + "\nWhat can I do for you?");
        System.out.println(welcomeMsg);


        while (loop) {
        try {
            String[] input = inputScanner.nextLine().split(" ", 10);
        }catch (DukeException e) {
            System.out.println(e);
        }
        
        System.out.println(bracket);
        switch(input[0]) {
            case "list":
                System.out.println("\t Here are the tasks in your list:");
                for(int i = 0; i < lists.size(); i++) {
                    int index = i+1;
                    System.out.println("\t " + index + ". " + lists.get(i).toString());
                }
                break;
            case "mark": 
                Task markedTask = lists.get(Integer.parseInt(input[1]) - 1);
                markedTask.setStatus(true);
                System.out.println("Nice! One Task Down!");
                System.out.println("\t " + markedTask.toString());
                break;
            case "unmark": 
                Task unmarkedTask = lists.get(Integer.parseInt(input[1]) - 1);
                unmarkedTask.setStatus(false);
                System.out.println("I have unmarked the task as not done yet.");
                System.out.println("\t " + unmarkedTask.toString());
                break;    
            case "todo":
                String tName = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
                ToDo toDoObj = new ToDo(tName);
                lists.add(toDoObj);
                System.out.println("\tGot it. I've added this task: ");
                System.out.println("\t\t "+ toDoObj.toString());
                System.out.println(String.format("Now you have %d tasks in the list", lists.size()));
                break;
            case "deadline":
                int deadlineIndex = 1;
                for(int i = 0; i < input.length; i++) {
                    if(input[i].equals("/by")) {
                        deadlineIndex = i;
                        break;
                    }
                }
                String dName = String.join(" ", Arrays.copyOfRange(input, 1, deadlineIndex));
                String dDate = String.join(" ", Arrays.copyOfRange(input, deadlineIndex + 1, input.length));
                Deadline deadlineObj = new Deadline(dName, dDate);
                lists.add(deadlineObj);
                System.out.println("\tGot it. I've added this task: ");
                System.out.println("\t\t "+ deadlineObj.toString());
                System.out.println(String.format("Now you have %d tasks in the list", lists.size()));
                break;
            case "event":
                int fromIndex = -1;
                int toIndex = -1;
                for(int i = 0; i < input.length; i++) {
                    if(input[i].equals("/from")) {
                        fromIndex = i;
                    }

                    if(input[i].equals("/to")) {
                        toIndex = i;
                    }
                }
                String eName = String.join(" ", Arrays.copyOfRange(input, 1, fromIndex));
                String eFrom = String.join(" ", Arrays.copyOfRange(input, fromIndex + 1, toIndex));
                String eTo = String.join(" ", Arrays.copyOfRange(input, toIndex + 1, input.length));
                Event eventObj = new Event(eName, eFrom, eTo);
                lists.add(eventObj);
                System.out.println("\tGot it. I've added this task: ");
                System.out.println("\t\t "+ eventObj.toString());
                System.out.println(String.format("Now you have %d tasks in the list", lists.size()));
                break;
            case "bye":
                System.out.println("\tBye! See you soon!");
                loop = false;
                break;
            default:
                String combString = String.join(" ", input);
                ToDo tdObj = new ToDo(combString);
                lists.add(tdObj);
                System.out.println("\tGot it. I've added this task: ");
                System.out.println("\t\t "+ tdObj.toString());
                System.out.println(String.format("Now you have %d tasks in the list", lists.size()));
        }
        System.out.println(bracket);

        }

        inputScanner.close();
    }
}