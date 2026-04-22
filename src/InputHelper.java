import java.util.Scanner;

public class InputHelper {
    public static double readScore(Scanner sc, String message) {
        double score;
        while (true) {
            System.out.print(message);
            if (sc.hasNextDouble()) {
                score = sc.nextDouble();
                sc.nextLine();
                if (score >= 0 && score <= 100) {
                    return score;
                } else {
                    System.out.println("Балл 0 мен 100 аралығында болуы керек.");
                }
            } else {
                System.out.println("Сан енгізіңіз.");
                sc.nextLine();
            }
        }
    }

    public static int readMenuChoice(Scanner sc) {
        int choice;
        while (true) {
            System.out.print("Таңдауыңыз: ");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
                return choice;
            } else {
                System.out.println("Сан енгізіңіз.");
                sc.nextLine();
            }
        }
    }
}
