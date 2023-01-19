public class ChatBot {
    TodoList todoList;

    public ChatBot(TodoList todoList) {
        this.todoList = todoList;
    }

    public void execute(String command) {
        if (command.equals("list")) {
            String shown_list = todoList.toString();
            System.out.println(shown_list);
        }
        else if (Character.isDigit(command.charAt(command.length() - 1))) {
            String[] input = command.split(" ");
            int digit = Integer.parseInt(input[1]);
            if (input[0].equals("mark")) {
                todoList.mark(digit);
            } else {
                todoList.unmark(digit);
            }
        }
        else {
            todoList.add(command);
            System.out.println("added: " + command);
        }
    }

}
