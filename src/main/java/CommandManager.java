public class CommandManager {
    private static CommandQueue queue = new CommandQueue();
    public static void createCommand (String input){
        switch (input){
            default : {
                queue.add(new Echo(input));
                break;
            }
            case "bye" : {
                queue.add(new Bye(input));
                break;
            }
        }
    }
    public static void executeQueue (){
        queue.executeQueue();
    }
}
