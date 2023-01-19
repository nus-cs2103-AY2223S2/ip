public class ChatBot {
    TodoList todoList;

    public ChatBot(TodoList todoList) {
        this.todoList = todoList;
    }

    public void execute(String command) {
        if (command.equals("list")) {
            String shown_list = todoList.toString();
            System.out.println(shown_list);
        } else {
            todoList.add(command);
            System.out.println("added: " + command);
        }
    }

}
