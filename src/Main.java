import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RatingSystem system = new RatingSystem();
        String fileName = "students.txt";

        system.loadFromFile(fileName);

        while (true) {
            System.out.println("\n========== МӘЗІР ==========");
            System.out.println("1. Студент қосу");
            System.out.println("2. Барлық студенттерді көру");
            System.out.println("3. Ең үздік студент");
            System.out.println("4. Студентті іздеу");
            System.out.println("5. Студент мәліметін өзгерту");
            System.out.println("6. Студентті өшіру");
            System.out.println("7. Статистика");
            System.out.println("8. Файлға сақтау");
            System.out.println("9. Шығу");

            int choice = InputHelper.readMenuChoice(sc);

            switch (choice) {
                case 1:
                    System.out.print("Аты-жөні: ");
                    String name = sc.nextLine();
                    double s1 = InputHelper.readScore(sc, "1-балл: ");
                    double s2 = InputHelper.readScore(sc, "2-балл: ");
                    double s3 = InputHelper.readScore(sc, "3-балл: ");
                    system.addStudent(new Student(name, s1, s2, s3));
                    System.out.println("Студент қосылды.");
                    break;

                case 2:
                    system.showAllStudents();
                    break;

                case 3:
                    Student best = system.findBestStudent();
                    if (best == null) {
                        System.out.println("Тізім бос.");
                    } else {
                        System.out.println("\nЕң үздік студент:");
                        System.out.printf("%-20s %-10s %-10s %-10s %-10s %-5s\n",
                                "Аты-жөні", "1-балл", "2-балл", "3-балл", "Орташа", "Әріп");
                        System.out.println(best);
                    }
                    break;

                case 4:
                    System.out.print("Ізделетін студенттің аты: ");
                    String searchName = sc.nextLine();
                    Student found = system.findStudentByName(searchName);
                    if (found == null) {
                        System.out.println("Студент табылмады.");
                    } else {
                        System.out.println("\nТабылған студент:");
                        System.out.printf("%-20s %-10s %-10s %-10s %-10s %-5s\n",
                                "Аты-жөні", "1-балл", "2-балл", "3-балл", "Орташа", "Әріп");
                        System.out.println(found);
                    }
                    break;

                case 5:
                    System.out.print("Өзгертілетін студенттің аты: ");
                    String oldName = sc.nextLine();

                    Student existing = system.findStudentByName(oldName);
                    if (existing == null) {
                        System.out.println("Студент табылмады.");
                    } else {
                        System.out.print("Жаңа аты-жөні: ");
                        String newName = sc.nextLine();
                        double newS1 = InputHelper.readScore(sc, "Жаңа 1-балл: ");
                        double newS2 = InputHelper.readScore(sc, "Жаңа 2-балл: ");
                        double newS3 = InputHelper.readScore(sc, "Жаңа 3-балл: ");

                        system.updateStudent(oldName, newName, newS1, newS2, newS3);
                        System.out.println("Мәлімет жаңартылды.");
                    }
                    break;

                case 6:
                    System.out.print("Өшірілетін студенттің аты: ");
                    String deleteName = sc.nextLine();
                    if (system.deleteStudent(deleteName)) {
                        System.out.println("Студент өшірілді.");
                    } else {
                        System.out.println("Студент табылмады.");
                    }
                    break;

                case 7:
                    system.showStatistics();
                    break;

                case 8:
                    system.saveToFile(fileName);
                    break;

                case 9:
                    system.saveToFile(fileName);
                    System.out.println("Бағдарлама аяқталды.");
                    return;

                default:
                    System.out.println("Қате таңдау.");
            }
        }
    }
}
