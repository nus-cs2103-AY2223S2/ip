public class Task {
  public String string;
  public Boolean mark;
  public Boolean todo;
  public Boolean deadline;
  public Boolean event;
  public String time;
  public String startTime;
  public String endTime;

  public Task(String string) {
    this.string = string;
    this.mark = false;
    this.todo =false;
    this.deadline = false;
    this.event = false;
  }
  public void mark() {
    this.mark = true;
  }
  public void demark() {
    this.mark = false;
  }
  public void todo() {
    this.todo = true;
  }
  public void deadline() {
    this.deadline = true;
  }

  public void event() {
    this.event = true;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public void setTime(String starTime, String endTime) {
    this.startTime = starTime;
    this.endTime = endTime;
  }
}
