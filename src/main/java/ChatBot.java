public class ChatBot {
    private TodoList todoList;

    public ChatBot(TodoList todoList) {
        this.todoList = todoList;
    }

    public void execute(String command) {
        String[] split_command = command.split(" ", 2);
        String instruction = split_command[0];
        if (instruction.equals("list")) {
            String shown_list = todoList.toString();
            System.out.println(shown_list);
        }
        else if (instruction.equals("mark")) {
            int digit = Integer.parseInt(split_command[1]);
            todoList.mark(digit);
        }
        else if (instruction.equals("unmark")) {
            int digit = Integer.parseInt(split_command[1]);
            todoList.unmark(digit);
        }
        else {
            System.out.println("Got it. I've added this task:");
            todoList.add(instruction, split_command[1]);
            System.out.println(String.format("Now I have %d tasks in the list.", todoList.number_of_tasks()));
        }
    }

}
