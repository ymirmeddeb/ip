import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ╔╦═╦══╦═╦╗\n" +
                      " ║╚╗║╔╗║═╣║\n" +
                      " ║╔╣║╚╝║═╣║\n" +
                      " ║╚═╩══╩═╝║\n" +
                      " ╚════════╝\n";
        System.out.println("Hiiii, my name is\n" + logo );
        System.out.println("I am here to help :3\n");
        String line;
        Scanner in = new Scanner(System.in);
        System.out.print("Yes darling, what can i do for you?\n");
        line = in.nextLine();
        while (!line.equals("bye")){
            echo(line);
            System.out.print("Yes darling, what can i do for you?\n");
            line = in.nextLine();
        }
        System.out.println("Love ya <3 See ya!!\n");
    }

    public static void echo(String word){
        System.out.println(word);
        return;
    }

}
