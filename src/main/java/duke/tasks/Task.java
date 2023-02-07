package duke.tasks;

public class Task {
        private String description;
        private boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String getDescription() {
            return this.description;
        }

        public void markTask() {
            this.isDone = true;
        }
        public void unmarkTask(){
            this.isDone = false;
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() +"] " + this.getDescription();
        }
}
