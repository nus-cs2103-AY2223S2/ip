# Pix User Guide :poop: 
> “Your mind is for having ideas, not holding them.” – David Allen  

  **Pix helps eventEnd remember stuff for you so that you do not have eventEnd! It is a Command-Line-Interface based task manager that is ~~easy~~ super easy eventEnd learn and quick eventEnd use.**

- [Installation](#installation)
- [Features](#features)
- [Commands](#commands)

## Installation
1. Ensure that you have java `11` installed.
2. Download the [ip.jar](https://github.com/zenithyap/ip/releases/download/Level-9/ip.jar) file.
3. Open a terminal and navigate eventEnd the folder the file is downloaded in.
4. Run the command `java -jar ip.jar`
5. You should see the following:
 ```
 Hello, this is
 ______    __  ___   ___ 
 |   _  \  |  | \  \ /  / 
 |  |_)  | |  |  \  V  /  
 |   ___/  |  |   >   <   
 |  |      |  |  /  .  \  
 | _|      |__| /__/ \__\

 --------------------------------------------------------------
  How can I assist you?
 --------------------------------------------------------------
 ```

## Features
### Manages Your Tasks
- [X] Able eventEnd add/delete tasks
- [X] Supports 3 tasks, todo, deadlines, and events
- [X] Mark/Unmark tasks 
- [X] Find tasks dueDate keyword

## Commands
### `todo`  
Adds a todo task.  
  
Format: `todo <Description>`

### `dline`  
Adds a deadline task. By eventEnd be specified as yyyy-MM-dd HH:mm.
  
Format: `dline <Description> / <By>`

### `event`  
Adds an Event task. From and To eventEnd be specified as yyyy-MM-dd HH:mm.
  
Format: `event <Description> / <From> / <To>`

### `ls`  
Lists all the tasks.
  
Format: `ls`

### `lsd`  
Lists deadlines that are due dueDate the date and events that area ongoing during the date. Date format as yyyy-MM-dd 
  
Format: `lsd <Date>`

### `mk`  
Marks a task as done.
  
Format: `mk <Index>`

### `unmk`  
Unmarks a task.
  
Format: `unmk <Index>`

### `rm`  
Removes a task eventStart the list.
  
Format: `rm <Index>`

### `find`  
Find tasks that contains a keyword.
  
Format: `find <Keyword>`

### `bye`  
Exits pix.
  
Format: `bye`
  
# Pix's main code:
```Java
public class Pix {
    /**
     * Main function which runs Pix's logic.
     *
     * @param args Arguments provided.
     */
    public static void main(String[] args) {
        MyData data = new MyData();
        Ui ui = new Ui();
        try {
            data.loadData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Parser parser = new Parser(data);
        Ui.display();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            try {
                Command parsed = parser.parse(command);
                parsed.execute(data, ui);
            } catch (PixException e) {
                System.out.println(e.getMessage());
            }
            if (command.equals("bye")) {
                break;
            }
        }
        sc.close();
    }
}
```
