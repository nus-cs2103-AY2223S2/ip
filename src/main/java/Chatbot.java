import Tasks.Deadline;
import Tasks.Events;
import Tasks.Task;
import Tasks.Todo;

import java.rmi.UnexpectedException;
import java.util.*;
import java.util.function.BiPredicate;

public class Chatbot {

    private List<Task> tasks = new ArrayList<Task>();
    public static final Map<String, BiPredicate<Chatbot, String>> commands = new HashMap<String, BiPredicate<Chatbot, String>>();

    private boolean isChatbotActive = true;
    static{
        commands.put(Messages.MESSAGE_END, (chatbot, args) -> {
            System.out.println(Messages.MESSAGE_END);
            chatbot.isChatbotActive = false;
            return true;
        });

        commands.put(Messages.MESSAGE_LIST, (chatbot, args) -> {
            System.out.println("Here are the tasks in your list:");
            int i = 0;
            for(Task entry : chatbot.tasks){
                i += 1;
                System.out.println(i + "." + entry);
            }
            return true;
        });

        commands.put(Messages.MESSAGE_MARK, (chatbot, args) -> {

                Integer index = -1;
                try{
                    index = Integer.valueOf(args);
                }
                catch (NumberFormatException ex){
                    ex.printStackTrace();
                    System.out.println("Error: Index not specified");

                    return true;
                }
                index -= 1;
                if (chatbot.getTaskState(index)){
                    System.out.println("Task is already marked as done.");
                    return true;
                }
                else{
                    chatbot.toggleTaskIndex(index);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("\t" + chatbot.getTask(index));
                }
                return true;

        });
        commands.put(Messages.MESSAGE_UNMARK, (chatbot, args) -> {

            Integer index = -1;
            try{
                index = Integer.valueOf(args);
            }
            catch (NumberFormatException ex){
                ex.printStackTrace();
                System.out.println("Error: Index not specified");
                return true;
            }
            index -= 1;
            if (!chatbot.getTaskState(index)){
                System.out.println("Task is already unmarked.");
                return true;
            }
            else{
                chatbot.toggleTaskIndex(index);
                System.out.println("Nice! I've marked this task as undone:");
                System.out.println("\t" + chatbot.getTask(index));
            }
            return true;

        });

        commands.put(Messages.MESSAGE_TODO, (chatbot, args) -> {
            if (args.trim() == ""){
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                return true;
            }
            Todo toAdd = new Todo(args.trim());
            chatbot.addTask(toAdd);
            return true;
        });

        commands.put(Messages.MESSAGE_DEADLINE, (chatbot, args) -> {
            String[] inputs = args.split("/by", 2);

            if(inputs.length != 2){
                System.out.println("Error: Invalid number of args. Pls add a /by in your command, " +
                        "or ensure task name is not not empty");

                return true;
            } else if (inputs[0].trim() == "" || inputs[1].trim() == "") {
                System.out.println("Description cannot be empty.");
                return true;
            }


            Deadline toAdd = new Deadline(inputs[0].trim(), inputs[1].trim());
            chatbot.addTask(toAdd);
            return true;
        });

        commands.put(Messages.MESSAGE_EVENT, (chatbot, args) -> {
            String[] inputs = args.split("(/from | /to)", 3);

            if(inputs.length != 3){
                System.out.println("Error: Invalid number of args. Pls add a /from and /to in your command," +
                        " or ensure task name is not not empty");
                return true;
            } else if (inputs[0].trim() == "" || inputs[1].trim() == "" || inputs[2].trim() == "") {
                System.out.println("Description cannot be empty.");
                return true;
            }


            Events toAdd = new Events(inputs[0].trim(), inputs[1].trim(), inputs[2].trim());
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
    public void processInput(String nextLine){
        //Assuming commands start with a space.
        String[] userCommand = nextLine.split(" ", 2);
        if (commands.containsKey(userCommand[0])) {
            boolean processedCommandState = commands.get(userCommand[0]).test(this, userCommand.length > 1 ? userCommand[1] : "");
            if (!processedCommandState) {
                //isChatbotActive = false;
                throw new RuntimeException();
            }
        } else {
            //Command not found.

            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    public void addTask(Task toAdd){
        System.out.println("Got it. I've added this task:");
        tasks.add(toAdd);
        onEditTask(toAdd);
    }

    public void onEditTask(Task change){
        System.out.println("\t" + change);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    public void removeTask(int toRemove){
        if(tasks.size() < toRemove){
            System.out.println("Error: Index specified must be smaller than current list size.");
            return;
        }
        System.out.println("Got it. I've removed this task:");
        Task removedTask = tasks.get(toRemove);
        tasks.remove(toRemove);
        onEditTask(removedTask);

    }
    public boolean getTaskState(int index){
        if(tasks.size() < index){
            System.out.println("Error: Index specified must be smaller than current list size.");
            return false;
        }

        return tasks.get(index).getCompletionStatus();
    }

    public String getTask(int index){
        return tasks.get(index).toString();
    }
    public void toggleTaskIndex(int index){
        if(tasks.size() < index){
            System.out.println("Error: Index specified must be smaller than current list size.");
            return;
        }
        tasks.get(index).toggleState();

    }

}
