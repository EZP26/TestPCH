public class academicProfile {
    private int SAT;
    private int ACT;
    private double GPA;
    private double top10Percent;
    private double graduationRate;

    public academicProfile(int SAT, int ACT, double GPA, double top10Percent, double graduationRate) {
        this.SAT = SAT;
        this.ACT = ACT;
        this.GPA = GPA;
        this.top10Percent = top10Percent;
        this.graduationRate = graduationRate;
    }

    public double calculateAcademicScore() {
        double newSatScore = 0;
        double newActScore = 0;
        double newGpaScore = 0;
        double classRankNormalized = top10Percent;
        double gradScore = graduationRate;
        /*
         * Explainations for Weighting:
         * 
         * -Test Scores (ACT & SAT): Combined, these make up 35% of the academic profile
         * score.
         * We chose to prioritize standardized test scores slightly over GPA and class
         * rank because
         * those metrics can be heavily influenced by the specific school a student
         * attends.
         * For example, it's often easier to earn a high GPA or be in the top 10% at a
         * less
         * academically rigorous school than at one with more challenging coursework and
         * competition.
         * Standardized tests, however, offer a level playing field: every student takes
         * the same test
         * under the same conditions, making it a more objective measure of academic
         * ability across
         * different school environments.
         * 
         * -GPA and Class Rank(Top 10%): Together, these account for 25% of the score.
         * Although these don't provide as much insight into a students academic ability
         * as test scores,
         * these metrics still matter a great deal to colleges, as they reflect a
         * student's consistency
         * and performance over their time in highschool. GPA reflects a sustained level
         * of academic
         * performance throughout high school, while class rank/top 10% gives context to
         * how students
         * perform relative to their peers.
         * 
         * -Graduation Rate: Worth 40% of the academic score on its own.
         * We think this is important because it shows the school's ability to support
         * students through
         * completion. A higher graduation rate often indicates strong advising,
         * adademic resources, and
         * student satisfaction. It also indicates the academic drive of the students
         * that attend the college,
         * and the overall competetiveness or rigour that each students is expected to
         * have. It's a key
         * indicator of both institutional quality and student success. Wew weighted it
         * more heavily than
         * any other category because it is an actual statistic from the after students
         * enter college which
         * shows not only the academic ability of the incoming student body but also how
         * they will perform
         * as they go through college and beyond.
         * 
         */
        double satWeight = 0.175;
        double actWeight = 0.175;
        double gpaWeight = 0.125;
        double classRankWeight = 0.125;
        double gradWeight = 0.4;

        /*
         * If any of the categories used to calculate academic profile score is missing,
         * the user can input -1
         * in order to append that data from the calculation. In the event that a
         * category is missing, the
         * weight that it previously held would be shifted over to the other score that
         * is weighted the same.
         * For example, if GPA is appended, class rank will then take GPA's weight of
         * 12.5% and become 25%.
         */

        /*
         * We used logarithmic functions for SAT, ACT, and GPA inputs to reduce the
         * impact of small changes
         * for already highscores compared to small changes for lower scores. For
         * example, the difference
         * between 1100 and 1200 on the SAT means a lot more than 1500 to 1600 for
         * college admissions. A
         * score above 1500 is already an elite score so this already tells the school
         * that this student is
         * elite so they will take into account other parts of the application. The log
         * function makes it
         * so that each additional point has diminishing returns the higher the input
         * is. All of them are
         * scaled so that they are within the ranges that are possible, SAT is between
         * 600 and 1600 because
         * 600 is the minimum score that you would get if you guess and 1600 is the
         * maximum score achieveable.
         * For ACT it is 0 and 36; For GPA it's 2.0 and 4.0.
         */
        if (SAT != -1 && SAT > 0) {
            double satInput = Math.max(0.001, 0.001648 * SAT); // Prevent log(0)
            newSatScore = 100.22186 * Math.log(satInput);
        } else {
            satWeight = 0;
            actWeight += 35 / 200.0;
        }

        if (ACT != -1 && ACT > 0) {
            double actInput = Math.max(0.001, 0.0749 * ACT);
            newActScore = 100.22186 * Math.log(actInput);
        } else {
            actWeight = 0;
            satWeight += 35 / 200.0;
        }

        if (GPA != -1 && GPA >= 2.0) {
            double gpaInput = Math.max(2.0, GPA);
            newGpaScore = 1.4427 * Math.log(gpaInput) - 1;
        } else {
            gpaWeight = 0;
            classRankWeight += 25 / 200.0;
        }

        double totalWeight = satWeight + actWeight + gpaWeight + classRankWeight + gradWeight;

        double rawScore = ((newSatScore * satWeight +
                newActScore * actWeight +
                newGpaScore * gpaWeight +
                classRankNormalized * classRankWeight +
                gradScore * gradWeight) / totalWeight);

        // This helps to rescale the raw academic score to between 50 and 100
        double scaledScore = 2.0 + ((rawScore - 50) / 50.0) * 2.0;
        scaledScore = Math.max(2.0, Math.min(4.0, scaledScore));

        return scaledScore * 25;

    }
}
