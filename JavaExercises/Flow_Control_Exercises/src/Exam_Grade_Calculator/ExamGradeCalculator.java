public class ExamGradeCalculator {
    public static void main(String[] args) {
        int written_test_grade;
        int practical_test_grade;
        int final_grade;

        written_test_grade = Integer.parseInt(args[0]);
        practical_test_grade = Integer.parseInt(args[1]);
        final_grade = written_test_grade + practical_test_grade;

        if (written_test_grade <= 0 && (final_grade > 18 || practical_test_grade < 18)) {
            System.out.println("FAILED!");
        }
        /*else if (written_test_grade <= 0 && practical_test_grade < 18) {
            System.out.println("FAILED!");
        }*/
        else if (written_test_grade > 0 && final_grade < 18) {
            System.out.println("FAILED!");
        }
        else if (final_grade > 30) {
            System.out.println("CONGRATULATIONS! 30 WITH HONORS!");
        }
        else {
            System.out.println("PROMOTED! grade: " + final_grade);
        }
    }
}
