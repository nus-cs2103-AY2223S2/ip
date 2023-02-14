package jane.task;

import jane.Jane;


public class Todo extends Task {
    public Todo(int num, String description) {
            super(num, description);
        }
        @Override
        public String save() {
            int i = 0;
            if (this.isDone== true) {
                i = 1;
            }
            return String.format("T|%d| %s", i, this.description);
        }
        @Override
        public String toString() {
            return String.format("%d. [T][%s]%s", this.num, this.getStatusIcon(), this.description);
        }


}
