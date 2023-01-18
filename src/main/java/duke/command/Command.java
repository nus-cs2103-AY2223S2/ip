package duke.command;

public abstract class Command {
  private final String name;
  private final String helpStr;
  private final boolean hasBaseParam;
  private final String[] params;

  public Command(String name, String helpStr, boolean hasBaseParam, String[] params) {
    this.name = name;
    this.helpStr = helpStr;
    this.hasBaseParam = hasBaseParam;
    this.params = params;
  }

  public String getHelpStr() {
    return helpStr;
  }

  public String getName() {
    return name;
  }

  public String[] getParams() {
    return params;
  }

  public boolean hasParams() {
    return params.length > 0 || hasBaseParam;
  }

  public abstract String[] execute(String[] params);
}