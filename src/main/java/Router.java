public class Router {
    private TaskList list;

    public Router(TaskList list) {
        this.list =  list;
    }

    public boolean handleAndSignalExit(String input) {
        Task newTask = null;

            if(input.equals("bye")) {
                return true;
            }

            else if(input.equals("list")) {
                this.list.list();
                return false;
            }
            else if(input.contains("mark")) {
                String[] splitInput = input.split(" ");
                int inputIndex = Integer.parseInt(splitInput[1]);
                this.list.mark(inputIndex);
                return false;
            }
            else if(input.contains("unmark")) {
                String[] splitInput = input.split(" "); 
                int inputIndex = Integer.parseInt(splitInput[1]);
                this.list.unmark(inputIndex-1);
                return false;
            }
            else if(input.contains("todo")) {
                int startIndex = input.lastIndexOf("todo");
                String taskName = input.substring(startIndex);
                newTask = new ToDos(taskName);
                this.list.add(newTask);
                return false;
            }
            else if(input.contains("deadline")) {
                int startIndex = input.lastIndexOf("deadline");
                String taskNameWithDeadline = input.substring(startIndex);
                String[] splitTaskAndDeadline = taskNameWithDeadline.split("/");
                newTask = new Deadlines(splitTaskAndDeadline[0], splitTaskAndDeadline[1]);
                this.list.add(newTask);
                return false;
            }
            else if(input.contains("event")) {
                int startIndex = input.lastIndexOf("event");
                String taskNameWithDeadline = input.substring(startIndex);
                String[] splitTaskAndDeadline = taskNameWithDeadline.split("/");
                newTask = new Events(splitTaskAndDeadline[0], splitTaskAndDeadline[1], splitTaskAndDeadline[2]);
                this.list.add(newTask);
                return false;
            }
            else {
                System.out.println("error");
                return true;
            }
    }
}
