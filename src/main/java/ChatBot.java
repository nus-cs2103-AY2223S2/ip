public class ChatBot {
    private TodoList todoList;
    private enum Instructions {todo, deadline, event, mark, unmark}

    public ChatBot(TodoList todoList) {
        this.todoList = todoList;
    }

    public void execute(String command) throws DukeExceptions{
        String[] split_command = command.split(" ", 2);
        String instruction = split_command[0];

        if (instruction.equals("list")) {
            if (split_command.length == 1) {
                String shown_list = todoList.toString();
                System.out.println(shown_list);
                return;
            }
            else {
                throw new DukeExceptions("OOPS!!! The description of a list cannot have other parameters");
            }
        }

        //check for valid instructions
        for (Instructions validInstruction: Instructions.values()) {
            if (validInstruction.name().equals(instruction)) {
                if (split_command.length == 1) {
                    throw new DukeExceptions(String.format("OOPS!!! The description of a %s cannot be empty.", instruction));
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
                        todoList.add(instruction, split_command[1]);
                        System.out.println(String.format("Now I have %d tasks in the list.", todoList.number_of_tasks()));
                        return;
                        }
                }
            }
        throw new DukeExceptions("OOPS!!! I'm sorry, but I don't know what that means.");
    }
}
