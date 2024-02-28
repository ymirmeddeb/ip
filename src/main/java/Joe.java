import java.util.Scanner;

public class Joe {
    public static void main(String[] args) {
        int maxTasks = 100;
        Task[] tasks = new Task[maxTasks];
        Scanner scanner = new Scanner(System.in);
        String userInput;
        int taskCount = 0;
        String logo = " ╔╦═╦══╦═╦╗\n" +
                      " ║╚╗║╔╗║═╣║\n" +
                      " ║╔╣║╚╝║═╣║\n" +
                      " ║╚═╩══╩═╝║\n" +
                      " ╚════════╝\n";

        printWelcome(logo);
        printHelp();
        while (true) {
            userInput = scanner.nextLine();
            try {
                if (taskCount >= maxTasks) {
                    throw new JoeException("Honey, you have wayyyy too many tasks. Instead of adding more tasks, why don't you actually go do some of them \n");
                }
                if (!(userInput.equalsIgnoreCase("bye") ||
                        userInput.equalsIgnoreCase("list") ||
                        userInput.startsWith("mark ") ||
                        userInput.startsWith("unmark ") ||
                        userInput.startsWith("todo ") ||
                        userInput.startsWith("deadline ") ||
                        userInput.startsWith("event "))){
                    throw new JoeException("Babe come on, what am i gonna do with this???? For the love of God, please give me a valid command.");
                }
            }
            catch (JoeException e) {
                System.err.println("Error: " + e.getMessage());
                printHelp();
            }
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }
            else if (userInput.equalsIgnoreCase("list")) {
                printTasks(tasks, taskCount);
                printHelp();
            }
            else if (userInput.startsWith("mark ")) {
                markTask(tasks, userInput, taskCount);
                printHelp();
            }
            else if (userInput.startsWith("unmark ")) {
                unmarkTask(tasks, userInput, taskCount);
                printHelp();
            }
            else if (userInput.startsWith("todo ")) {
                try {
                    if (userInput.replace("todo ", "").trim().isEmpty()) {
                        throw new JoeException("Listen, you can't tell me to make a todo task for you and then not give me any info on what it is!!! Provide me with some info babes");
                    }
                    ToDo userToDo = new ToDo(userInput.replace("todo ", "").trim());
                    tasks[taskCount] = userToDo;
                    taskCount++;
                    System.out.println("Alrightyy, im gonna add " + userInput.replace("todo ", "").trim() + " to your ToDo list!!!");
                    printHelp();
                }
                catch (JoeException e) {
                    System.err.println("Error: " + e.getMessage());
                    printHelp();
                }
            }
            else if (userInput.startsWith("deadline ")) {
                int due = userInput.indexOf("/by");
                String by = userInput.substring(due + 3).trim();
                try {
                    if (userInput.replace("deadline ", "").trim().isEmpty()) {
                        throw new JoeException("Listen, you can't tell me to make a deadline for you and then not give me any info on what it is!!! Provide me with some info babes");
                    }
                    if (due == -1 || by.isEmpty() || userInput.substring(8, due).trim().isEmpty()){
                        throw new JoeException("Come on cupcake, you have to tell me when your deadline is. Here's a hint, you can use the format: /by [deadline time]");
                    }
                    String deadline = userInput.substring(8, due).trim();
                    Deadline userDeadline = new Deadline(deadline, by);
                    tasks[taskCount] = userDeadline;
                    taskCount++;
                    System.out.println("Alrightyy, im gonna add " + deadline + " by " + by + " to your deadlines!!!");
                    printHelp();
                }
                catch (JoeException e) {
                    System.err.println("Error: " + e.getMessage());
                    printHelp();
                }
            }
            else if (userInput.startsWith("event ")) {
                int splitFrom = userInput.indexOf("/from");
                int splitTo = userInput.indexOf("/to");
                String to = userInput.substring(splitTo + 3).trim();
                try {
                    if (userInput.replace("event", "").trim().isEmpty()) {
                        throw new JoeException("Listen, you can't tell me to make a event for you and then not give me any info on what it is!!! Provide me with some info babes");
                    }
                    if ((splitTo - splitFrom) < 3 || splitFrom == -1 || userInput.substring(5, splitFrom).trim().isEmpty() || userInput.substring(splitFrom + 5, splitTo).trim().isEmpty() || to.isEmpty()){
                        throw new JoeException("Come on cupcake, you have to tell me when your event is. Here's a hint, you can use the format: event [description] /from [start time] /to [end time]");
                    }
                    String from = userInput.substring(splitFrom + 5, splitTo).trim();
                    String event = userInput.substring(5, splitFrom).trim();
                    Event userEvent = new Event(event, from, to);
                    tasks[taskCount] = userEvent;
                    taskCount++;
                    System.out.println("Alrightyy, im gonna add " + event + " from " + from + " to " + to + " to your events!!!");
                    printHelp();
                }
                catch (JoeException e) {
                    System.err.println("Error: " + e.getMessage());
                    printHelp();
                }
            }
        }
        printBye();
        scanner.close();
    }

    public static void printWelcome(String logo){
        System.out.println("Hiiii, my name is\n" + logo );
        System.out.println("I am here to help :3");
    }

    public static void printHelp(){
        System.out.println("How can i help you darling?");
    }

    public static void printBye(){
        System.out.println("Love ya <3 See ya!!");
    }
    public static void printTasks(Task[] tasks, int nextTask){
        if (tasks[0] == null){
            System.out.println("Your list is empty ;_;");
            return;
        }
        for (int i = 0; i < nextTask; i++) {
            System.out.println(i+1 + ".     " + tasks[i] + "\n");
        }
    }

    public static void markTask(Task[] tasks, String userInput, int taskCount){
        int index = Integer.parseInt(userInput.substring(5)) - 1;
        if (index >= 0 && index < taskCount) {
            tasks[index].markAsDone();
            System.out.println("Okayyy i marked it!\n" + tasks[index]);
        }
    }

    public static void unmarkTask(Task[] tasks, String userInput, int taskCount){
        int index = Integer.parseInt(userInput.substring(7)) - 1;
        if (index >= 0 && index < taskCount) {
            tasks[index].markAsNotDone();
            System.out.println("Okayyy i unmarked it!\n" + tasks[index]);
        }
    }
}
