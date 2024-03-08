import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Joe {
    public static void main(String[] args) {
        int maxTasks = 100;
        Storage storage = new Storage("joe.txt");
        List<Task> tasks;
        try {
            tasks = storage.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
                        userInput.startsWith("event ") ||
                        userInput.startsWith("delete "))) {
                    throw new JoeException("Babe come on, what am i gonna do with this???? For the love of God, please give me a valid command.");
                }
            } catch (JoeException e) {
                System.err.println("Error: " + e.getMessage());
                printHelp();
            }
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                printTasks(tasks);
                printHelp();
            } else if (userInput.startsWith("mark ")) {
                markTask(tasks, userInput);
                try {
                    storage.save(tasks); // Save the updated list
                } catch (IOException e) {
                    e.printStackTrace();
                }
                printHelp();
            } else if (userInput.startsWith("unmark ")) {
                unmarkTask(tasks, userInput);
                try {
                    storage.save(tasks); // Save the updated list
                } catch (IOException e) {
                    e.printStackTrace();
                }
                printHelp();
            } else if (userInput.startsWith("todo ")) {
                try {
                    if (userInput.replace("todo ", "").trim().isEmpty()) {
                        throw new JoeException("Listen, you can't tell me to make a todo task for you and then not give me any info on what it is!!! Provide me with some info babes");
                    }
                    ToDo userToDo = new ToDo(userInput.replace("todo ", "").trim());
                    tasks.add(userToDo);
                    try {
                        storage.save(tasks); // Save the updated list
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Alrightyy, im gonna add " + userInput.replace("todo ", "").trim() + " to your ToDo list!!!");
                    printHelp();
                } catch (JoeException e) {
                    System.err.println("Error: " + e.getMessage());
                    printHelp();
                }
            } else if (userInput.startsWith("deadline ")) {
                int due = userInput.indexOf("/by");
                String by = userInput.substring(due + 3).trim();
                try {
                    if (userInput.replace("deadline ", "").trim().isEmpty()) {
                        throw new JoeException("Listen, you can't tell me to make a deadline for you and then not give me any info on what it is!!! Provide me with some info babes");
                    }
                    if (due == -1 || by.isEmpty() || userInput.substring(8, due).trim().isEmpty()) {
                        throw new JoeException("Come on cupcake, you have to tell me when your deadline is. Here's a hint, you can use the format: /by [deadline time]");
                    }
                    String deadline = userInput.substring(8, due).trim();
                    Deadline userDeadline = new Deadline(deadline, by);
                    tasks.add(userDeadline);
                    try {
                        storage.save(tasks); // Save the updated list
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Alrightyy, im gonna add " + deadline + " by " + by + " to your deadlines!!!");
                    printHelp();
                } catch (JoeException e) {
                    System.err.println("Error: " + e.getMessage());
                    printHelp();
                }
            } else if (userInput.startsWith("event ")) {
                int splitFrom = userInput.indexOf("/from");
                int splitTo = userInput.indexOf("/to");
                String to = userInput.substring(splitTo + 3).trim();
                try {
                    if (userInput.replace("event", "").trim().isEmpty()) {
                        throw new JoeException("Listen, you can't tell me to make a event for you and then not give me any info on what it is!!! Provide me with some info babes");
                    }
                    if ((splitTo - splitFrom) < 3 || splitFrom == -1 || userInput.substring(5, splitFrom).trim().isEmpty() || userInput.substring(splitFrom + 5, splitTo).trim().isEmpty() || to.isEmpty()) {
                        throw new JoeException("Come on cupcake, you have to tell me when your event is. Here's a hint, you can use the format: event [description] /from [start time] /to [end time]");
                    }
                    String from = userInput.substring(splitFrom + 5, splitTo).trim();
                    String event = userInput.substring(5, splitFrom).trim();
                    Event userEvent = new Event(event, from, to);
                    tasks.add(userEvent);
                    try {
                        storage.save(tasks); // Save the updated list
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Alrightyy, im gonna add " + event + " from " + from + " to " + to + " to your events!!!");
                    printHelp();
                } catch (JoeException e) {
                    System.err.println("Error: " + e.getMessage());
                    printHelp();
                }
            }
            else if (userInput.startsWith("delete ")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.substring(7).trim());
                    if (taskNumber <= 0 || taskNumber > tasks.size()) {
                        System.err.println("Invalid task number!");
                        printHelp();
                    } else {
                        Task taskToDelete = tasks.remove(taskNumber - 1);
                        try {
                            storage.save(tasks);
                            System.out.println("Sure thing, I've removed: ");
                            System.out.println("  " + taskToDelete);
                            System.out.println("from the list.");
                        } catch (IOException e) {
                            System.err.println("Error saving tasks: " + e.getMessage());
                            tasks.add(taskNumber - 1, taskToDelete);
                        }
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error: The task number is invalid. Please enter a valid number.");
                }
            }
            else if (userInput.equalsIgnoreCase("bye")) {
                printBye();
            }
        }
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
    public static void printTasks(List<Task> tasks) {
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

    public static void markTask(List<Task> tasks, String userInput) {
        int index = Integer.parseInt(userInput.substring(5)) - 1;
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.markAsDone();
            System.out.println("Okayyy I marked it!\n" + task);
        }

    }

    public static void unmarkTask(List<Task> tasks, String userInput) {
        int index = Integer.parseInt(userInput.substring(7)) - 1;
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.markAsNotDone();
            System.out.println("Okayyy I unmarked it!\n" + task);
        }
    }
}