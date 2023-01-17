public class Duke {
    UIText ui = new UIText();
    List list = new List();
    public static void main(String[] args) {
        new Duke().run();

    }

    public void run() {
        boolean isClosed = false;

        System.out.println(ui.greet());
        while (!isClosed) {
            String input = ui.getInput();
            if (input.equals("bye")) {
                System.out.println(ui.exit());
                isClosed = true;
            } else if (input.equals("list")) {
                list.display();
            } else {
                list.add(input);
            }
        }
    }
}