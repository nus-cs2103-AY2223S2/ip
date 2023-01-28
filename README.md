# Pix User Guide :poop: 
> “Your mind is for having ideas, not holding them.” – David Allen  

  **Pix helps to remember stuff for you so that you do not have to! It is a Command-Line-Interface based task manager that is ~~easy~~ super easy to learn and quick to use.**

- [Installation](https://github.com/zenithyap/ip#installation)
- [Features](https://github.com/zenithyap/ip#features)
- [Commands](https://github.com/zenithyap/ip#commands)

## Installation
1. Ensure that you have java `11` installed.
2. Download the [ip.jar](https://github.com/zenithyap/ip/releases/download/Level-9/ip.jar) file.
3. Open a terminal and navigate to the folder the file is downloaded in.
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
- [X] Able to add/delete tasks
- [X] Supports 3 tasks, todo, deadlines, and events
- [X] Mark/Unmark tasks 
- [X] Find tasks by keyword

## Commands
### `todo`  
Adds a todo task.  
  
Format: `todo <Description>`

### `dline`  
Adds a deadline task. By to be specified as yyyy-MM-dd HH:mm.
  
Format: `dline <Description> / <By>`

### `event`  
Adds an Event task. From and To to be specified as yyyy-MM-dd HH:mm.
  
Format: `event <Description> / <From> / <To>`

### `ls`  
Lists all the tasks.
  
Format: `ls`

### `lsd`  
Lists deadlines that are due by the date and events that area ongoing during the date. Date format as yyyy-MM-dd 
  
Format: `lsd <Date>`

### `mk`  
Marks a task as done.
  
Format: `mk <Index>`

### `unmk`  
Unmarks a task.
  
Format: `unmk <Index>`

### `rm`  
Removes a task from the list.
  
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
