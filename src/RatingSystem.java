import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class RatingSystem {
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }

    public void showAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Тізім бос.");
            return;
        }

        sortByAverageDescending();

        System.out.println("\n================== РЕЙТИНГ ЖҮЙЕСІ ==================");
        System.out.printf("%-5s %-20s %-10s %-10s %-10s %-10s %-5s\n",
                "№", "Аты-жөні", "1-балл", "2-балл", "3-балл", "Орташа", "Әріп");

        for (int i = 0; i < students.size(); i++) {
            System.out.printf("%-5d %s\n", i + 1, students.get(i));
        }
    }

    public void sortByAverageDescending() {
        students.sort(Comparator.comparingDouble(Student::getAverage).reversed());
    }

    public Student findBestStudent() {
        if (students.isEmpty()) return null;
        sortByAverageDescending();
        return students.get(0);
    }

    public Student findStudentByName(String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }

    public boolean deleteStudent(String name) {
        Student student = findStudentByName(name);
        if (student != null) {
            students.remove(student);
            return true;
        }
        return false;
    }

    public boolean updateStudent(String oldName, String newName, double s1, double s2, double s3) {
        Student student = findStudentByName(oldName);
        if (student != null) {
            student.setName(newName);
            student.setScore1(s1);
            student.setScore2(s2);
            student.setScore3(s3);
            return true;
        }
        return false;
    }

    public void showStatistics() {
        if (students.isEmpty()) {
            System.out.println("Тізім бос.");
            return;
        }

        double total = 0;
        double max = students.get(0).getAverage();
        double min = students.get(0).getAverage();

        for (Student s : students) {
            double avg = s.getAverage();
            total += avg;
            if (avg > max) max = avg;
            if (avg < min) min = avg;
        }

        System.out.println("\n========== СТАТИСТИКА ==========");
        System.out.println("Студент саны: " + students.size());
        System.out.printf("Жалпы орташа балл: %.2f\n", total / students.size());
        System.out.printf("Ең жоғары орташа балл: %.2f\n", max);
        System.out.printf("Ең төмен орташа балл: %.2f\n", min);
    }

    public void saveToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Student s : students) {
                writer.println(s.getName() + "," + s.getScore1() + "," + s.getScore2() + "," + s.getScore3());
            }
            System.out.println("Деректер файлға сақталды.");
        } catch (IOException e) {
            System.out.println("Файлға сақтау қатесі.");
        }
    }

    public void loadFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return;
        }

        students.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String name = data[0];
                    double s1 = Double.parseDouble(data[1]);
                    double s2 = Double.parseDouble(data[2]);
                    double s3 = Double.parseDouble(data[3]);
                    students.add(new Student(name, s1, s2, s3));
                }
            }
        } catch (IOException e) {
            System.out.println("Файлдан оқу қатесі.");
        }
    }
}
