public class CommandManager {
    private static CommandQueue queue = new CommandQueue();
    public static void createCommand (String input){
        switch (input){
            default : {
                if (input.matches("mark+ [0-9]+")) {
                    queue.add(new Mark(input));
                } else if (input.matches("unmark+ [0-9]+")) {
                    queue.add(new Unmark(input));
                } else {
                    queue.add(new Store(input));
                }
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
