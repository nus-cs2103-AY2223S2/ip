# Duke-Gerty

> “Your mind is for having ideas, not holding them.” – David Allen ([source](https://dansilvestre.com/productivity-quotes))

Duke-Gerty frees your mind of having to remember things you need to do. It's,

- text-based
- easy to learn
- ~~FAST~~ *SUPER* FAST to use
- All you need to do is,

download it from [here](https://github.com/kyueran/ip).
1. double-click it.
2. add your tasks.
3. let it manage your tasks for you 😉

And it is **FREE!**

Features:

- [x] Managing tasks
- [ ] Managing deadlines (coming soon)
- [ ] Reminders (coming soon)

#

You should edit the file path in the main method to load your tasks from a custom file! Here's the main method:
```ruby
public static void main(String[] args) {
    new Duke("data/tasks.txt").run();
}
```