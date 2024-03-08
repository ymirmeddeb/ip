import java.util.List;

public class Ui {
    public void printWelcome() {
        String logo = " ╔╦═╦══╦═╦╗\n" +
                " ║╚╗║╔╗║═╣║\n" +
                " ║╔╣║╚╝║═╣║\n" +
                " ║╚═╩══╩═╝║\n" +
                " ╚════════╝\n";
        System.out.println("Hiiii, my name is\n" + logo);
        System.out.println("I am here to help :3");
    }

    public void printHelp() {
        System.out.println("How can I help you darling?");
    }

    public void printBye() {
        System.out.println("Love ya <3 See ya!!");
    }

    public void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Your list is empty ;_;");
            return;
        }
        int index = 1;
        for (Task task : tasks) {
            System.out.println(index + ". " + task);
            index++;
        }
    }

    public void printError(String message) {
        System.err.println("Error: " + message);
    }

    public void printTaskAdded(Task task, List<Task> tasks) {
        System.out.println("Alrightyyy, I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list <3");
    }

    public void printTaskRemoved(Task task, List<Task> tasks) {
        System.out.println("Okayyyy, I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list <3");
    }

    public void printMarkedTask(Task task, boolean isDone) {
        if (isDone) {
            System.out.println("Sure thinggg! I've marked this task as done, just for you:");
        } else {
            System.out.println("Sure thinggg! I've marked this task as not done yet just for you:");
        }
        System.out.println("  " + task);
    }
}

