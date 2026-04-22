public class Student {
    private String name;
    private double score1;
    private double score2;
    private double score3;

    public Student(String name, double score1, double score2, double score3) {
        this.name = name;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
    }

    public String getName() {
        return name;
    }

    public double getScore1() {
        return score1;
    }

    public double getScore2() {
        return score2;
    }

    public double getScore3() {
        return score3;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore1(double score1) {
        this.score1 = score1;
    }

    public void setScore2(double score2) {
        this.score2 = score2;
    }

    public void setScore3(double score3) {
        this.score3 = score3;
    }

    public double getAverage() {
        return (score1 + score2 + score3) / 3.0;
    }

    public String getGrade() {
        double avg = getAverage();
        if (avg >= 90) return "A";
        if (avg >= 75) return "B";
        if (avg >= 50) return "C";
        return "F";
    }

    @Override
    public String toString() {
        return String.format("%-20s %-10.1f %-10.1f %-10.1f %-10.2f %-5s",
                name, score1, score2, score3, getAverage(), getGrade());
    }
}
