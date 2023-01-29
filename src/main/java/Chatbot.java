import Tasks.DeadlineTask;
import Tasks.EventTask;
import Tasks.Task;
import Tasks.TodoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.BiPredicate;

public class Chatbot {

    private List<Task> tasks = new ArrayList<Task>();
    public static final Map<String, BiPredicate<Chatbot, String>> commands = new HashMap<String, BiPredicate<Chatbot, String>>();

    private boolean isChatbotActive = true;

    private File savefile;

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
            TodoTask toAdd = new TodoTask(args.trim());
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


            DeadlineTask toAdd = new DeadlineTask(inputs[0].trim(), inputs[1].trim());
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


            EventTask toAdd = new EventTask(inputs[0].trim(), inputs[1].trim(), inputs[2].trim());
            chatbot.addTask(toAdd);
            return true;
        });

        commands.put(Messages.MESSAGE_DELETE, (chatbot, args) -> {
            Integer index = -1;
            try{
                index = Integer.valueOf(args);
            }
            catch (NumberFormatException ex){
                ex.printStackTrace();
                System.out.println("Error: Index not specified");

                return false;
            }
            index -= 1;
            chatbot.removeTask(index);

            return true;
        });

        commands.put(Messages.MESSAGE_DELETE_ALL_DATA, (chatbot, args) -> {
            for(int i = 0 ; i < chatbot.tasks.size() ; i++) {
                chatbot.removeTask(0);
            }
            System.out.println("Deletion complete!");
            return true;
        });

    }

    public static Chatbot loadFromData(File saveFile) throws FileNotFoundException {
        Scanner fileReader = new Scanner(saveFile);
        Chatbot result = new Chatbot();
        while(fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            result.loadData(data);
        }
        result.savefile = saveFile;
        fileReader.close();
        return result;
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
        String upperCaseUserCommand = userCommand[0].toUpperCase();
        if (commands.containsKey(upperCaseUserCommand)) {
            boolean processedCommandState = commands.get(upperCaseUserCommand).test(this, userCommand.length > 1 ? userCommand[1] : "");
            if (!processedCommandState) {
                isChatbotActive = false;
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
        saveData();
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

    private void saveData() {
        if(savefile == null) {
            return;
        }

        try {
            FileWriter writerObj = new FileWriter(savefile.getPath(), false);
            writerObj.write(toSaveData());
            writerObj.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public String toSaveData() {
        String output = new String();
        for(Task nextTask : tasks) {
            output += nextTask.toSaveData();
            output += "\n";
        }
        return output;
    }

    public void loadData(String data) throws IllegalArgumentException {
        if(data.length() == 0) {
            return;
        }
        Task todo;
        switch(data.charAt(0)) {
            case 'T':
                todo = TodoTask.loadData(data);
                break;
            case 'D':
                todo = DeadlineTask.loadData(data);
                break;
            case 'E':
                todo = EventTask.loadData(data);
                break;
            default:
                throw new IllegalArgumentException();
        }

        tasks.add(todo);
    }



}
