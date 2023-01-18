public class Duke {
    UIText ui = new UIText();
    List list = new List();
    public static void main(String[] args) {
        new Duke().run();

    }

    public void run() {
        boolean isClosed = false;
        TaskHandler handler = new TaskHandler(list);

        System.out.println(ui.greet());
        while (!isClosed) {
            String input = ui.getInput();
            System.out.println(ui.separate());
            if (input.equals("bye")) {
                System.out.println(ui.exit());
                isClosed = true;
            } else if (input.equals("list")) {
                System.out.println(handler.display());
            } else if (input.startsWith("mark")) {
                System.out.println(handler.markAsDone(input));
            } else if (input.startsWith("unmark")) {
                System.out.println(handler.markAsUndone(input));
            } else if (input.startsWith("event")) {
                System.out.println(handler.eventHandler(input));
            } else if (input.startsWith("todo")) {
                System.out.println(handler.todoHandler(input));
            } else if (input.startsWith("deadline")) {
                System.out.println(handler.deadlineHandler(input));
            } else {
                System.out.println("Not a valid command.");

            }
            System.out.println(ui.separate());
        }
    }
}