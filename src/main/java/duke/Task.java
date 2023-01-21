package duke;
public class Task {
        boolean status;
        String des;

        public Task(boolean status, String des) {
                this.status = status;
                this.des = des;
        }

        public void setStatus(boolean status) {
                this.status = status;
        }

        public void setDes(String des) {
                this.des = des;
        }

        public boolean getStatus() {
                return status;
        }

        public String getDes() {
                return des;
        }

        public void printStatus() {
                String s = (status)? "X":" ";
                System.out.println("[" +s+ "] " + this.des);
        }

        public String toString() {
                String s = (status)? "X":" ";
                return "  | " + s + this.des;
        }
}
