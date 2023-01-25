package duke.task;
public class Todo extends Task {
   public Todo(String description, boolean isDone) {
       super(description, isDone);
   }

   @Override
   public String toString() {
       return "[T][" + this.getStatusIcon() + "] " + this.getDescription();
   }

   public String toFile() {
       return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
   }
}
