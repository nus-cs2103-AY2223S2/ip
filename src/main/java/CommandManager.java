public class CommandManager {
    private static CommandQueue queue = new CommandQueue();
    public static void createCommand (String input){
        switch (input){
            default : {
                queue.add(new Store(input));
                break;
            }
            case "list": {
                queue.add(new List(input));
                break;
            }

            case "bye" : {
                queue.add(new Bye(input));
                break;
            }
        }
    }
    public static void executeQueue (User user){
        queue.executeQueue(user);
    }
}
