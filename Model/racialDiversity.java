public class racialDiversity {
    private int studentBodySum;
    private int blackStudents;
    private int whiteStudents;
    private int asianStudents;
    private int hispanicStudents;
    private int nativeStudents;
    private int pacificIslanderStudent;
    private int twoOrMoreStudents;

    public racialDiversity(int studentBody, int Hispanic, int Black, int White, int Native, int Asian,
            int pacificIslander, int twoOrMoreStudents) {
        this.studentBodySum = studentBody;
        this.blackStudents = Black;
        this.whiteStudents = White;
        this.asianStudents = Asian;
        this.hispanicStudents = Hispanic;
        this.nativeStudents = Native;
        this.pacificIslanderStudent = pacificIslander;
        this.twoOrMoreStudents = twoOrMoreStudents;
    }

    public double calculateRacialDiversity() {
        return (calculateSchoolDiversity() + calculateUSDiversity()) / 2 * 100;
    }

    /*
     * Compares each group's proportion in the school to national proportions from
     * the U.S. Census.
     * The differences are summed up and then subtracted from 1. A perfect score
     * would perfectly match
     * to the U.S. population and give a perfect score of 1.
     */
    public double calculateUSDiversity() {
        int totalDifference = 0;
        double[] usProportions = { 0.13, 0.60, 0.06, 0.19, 0.01, 0.002, 0.03 }; // Based on US Census data

        double[] proportions = {
                (double) blackStudents / studentBodySum,
                (double) whiteStudents / studentBodySum,
                (double) asianStudents / studentBodySum,
                (double) hispanicStudents / studentBodySum,
                (double) nativeStudents / studentBodySum,
                (double) pacificIslanderStudent / studentBodySum,
                (double) twoOrMoreStudents / studentBodySum
        };

        for (int i = 0; i < proportions.length; i++) {
            totalDifference += Math.abs(usProportions[i] - proportions[i]);
        }

        return (1 - totalDifference);
    }

    /*
     * Computes the standard deviation of the racial group proportions. A lower
     * standard deviation means
     * the proportions are evenly spaced across groups and therefore more diverse.
     */
    public double calculateSchoolDiversity() {
        double[] proportions = {
                (double) blackStudents / studentBodySum,
                (double) whiteStudents / studentBodySum,
                (double) asianStudents / studentBodySum,
                (double) hispanicStudents / studentBodySum,
                (double) nativeStudents / studentBodySum,
                (double) pacificIslanderStudent / studentBodySum,
                (double) twoOrMoreStudents / studentBodySum
        };

        double stdev = calculateStandardDeviation(proportions);
        return (1 - stdev);
    }

    public static double calculateStandardDeviation(double[] array) {

        // get the sum of array
        double sum = 0.0;
        for (double i : array) {
            sum += i;
        }

        // get the mean of array
        int length = array.length;
        double mean = sum / length;

        // calculate the standard deviation
        double standardDeviation = 0.0;
        for (double num : array) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation / length);
    }
}
