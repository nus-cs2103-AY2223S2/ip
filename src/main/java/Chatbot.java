import Tasks.Deadline;
import Tasks.Events;
import Tasks.Task;
import Tasks.Todo;

import java.util.*;
import java.util.function.BiPredicate;

public class Chatbot {

    private List<Task> tasks = new ArrayList<Task>();
    public static final Map<String, BiPredicate<Chatbot, String>> commands = new HashMap<String, BiPredicate<Chatbot, String>>();

    private boolean isChatbotActive = true;
    static{
        commands.put(Commands.MESSAGE_END, (chatbot, args) -> {
            System.out.println(Commands.MESSAGE_END);
            chatbot.isChatbotActive = false;
            return true;
        });

        commands.put(Commands.MESSAGE_LIST, (chatbot, args) -> {
            System.out.println("Here are the tasks in your list:");
            int i = 0;
            for(Task entry : chatbot.tasks){
                i += 1;
                System.out.println(i + "." + entry);
            }
            return true;
        });

        commands.put(Commands.MESSAGE_MARK, (chatbot, args) -> {

                Integer index = -1;
                try{
                    index = Integer.valueOf(args);
                }
                catch (NumberFormatException ex){
                    ex.printStackTrace();
                    return false;
                }
                index -= 1;
                if (chatbot.getTaskState(index)){
                    System.out.println("Tasks.Task is already marked as done.");
                    return false;
                }
                else{
                    chatbot.toggleTaskIndex(index);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("\t" + chatbot.getTask(index));
                }
                return true;

        });
        commands.put(Commands.MESSAGE_UNMARK, (chatbot, args) -> {

            Integer index = -1;
            try{
                index = Integer.valueOf(args);
            }
            catch (NumberFormatException ex){
                ex.printStackTrace();
                return false;
            }
            index -= 1;
            if (!chatbot.getTaskState(index)){
                System.out.println("Tasks.Task is already unmarked.");
                return false;
            }
            else{
                chatbot.toggleTaskIndex(index);
                System.out.println("Nice! I've marked this task as undone:");
                System.out.println("\t" + chatbot.getTask(index));
            }
            return true;

        });

        commands.put(Commands.MESSAGE_TODO, (chatbot, args) -> {
            Todo toAdd = new Todo(args);
            chatbot.addTask(toAdd);
            return true;
        });

        commands.put(Commands.MESSAGE_DEADLINE, (chatbot, args) -> {
            String[] inputs = args.split("/by", 2);

            if(inputs.length != 2)
                return false;

            Deadline toAdd = new Deadline(inputs[0], inputs[1]);
            chatbot.addTask(toAdd);
            return true;
        });

        commands.put(Commands.MESSAGE_EVENT, (chatbot, args) -> {
            String[] inputs = args.split("(/from | /to)", 3);

            if(inputs.length != 3)
                return false;

            Events toAdd = new Events(inputs[0], inputs[1], inputs[2]);
            chatbot.addTask(toAdd);
            return true;
        });

    }



    public void readInput(){
        Scanner input = new Scanner(System.in);
        while(isChatbotActive){
            String nextLine = input.nextLine();
            processInput(nextLine);

        }
    }
    public void processInput(String nextLine) {
        //Assuming commands start with a space.
        String[] userCommand = nextLine.split(" ", 2);
        if (commands.containsKey(userCommand[0])) {
            boolean processedCommandState = commands.get(userCommand[0]).test(this, userCommand.length > 1 ? userCommand[1] : "");
            if (!processedCommandState) {
                isChatbotActive = false;
                //Error
            }
        } else {
            //Command not found.
            System.out.println(nextLine);
        }

    }

    public void addTask(Task toAdd){
        System.out.println("Got it. I've added this task:");
        tasks.add(toAdd);
        System.out.println("\t" + toAdd);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }
    public boolean getTaskState(int index){
        if(tasks.size() < index)
            return false;
        return tasks.get(index).getCompletionStatus();
    }

    public String getTask(int index){
        return tasks.get(index).toString();
    }
    public boolean toggleTaskIndex(int index){
        if(tasks.size() < index)
            return false;

        tasks.get(index).toggleState();
        return true;
    }

}
