import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

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
                    System.out.println("Task is already marked as done.");
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
                System.out.println("Task is already unmarked.");
                return false;
            }
            else{
                chatbot.toggleTaskIndex(index);
                System.out.println("Nice! I've marked this task as undone:");
                System.out.println("\t" + chatbot.getTask(index));
            }
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
            tasks.add(new Task(nextLine));
            System.out.println("added: " + nextLine);
        }

    }

    public boolean getTaskState(int index){
        if(tasks.size() < index)
            return false;
        return tasks.get(index).getState();
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
