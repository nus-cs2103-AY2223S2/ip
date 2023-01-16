public class Task {
  String description;
  int id;
  static int runningId = 1;

  public Task(String description) {
    this.id = runningId;
    runningId++;
    this.description = description;
  }

  @Override
  public String toString() {
    return String.format("%d. %s\n", this.id, this.description);
  }
}
