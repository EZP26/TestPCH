public class College {
    private String name;
    private int tuition;
    private double academicProfile;
    private double earnings;
    private double racialDiversity;
    private double score;

    public College(String name, int tuition, double academicProfile, double earnings, double racialDiversityScore) {
        this.name = name;
        this.tuition = tuition;
        this.academicProfile = academicProfile;
        this.earnings = earnings;
        this.racialDiversity = racialDiversityScore;
    }

    public double calculateScore(double tuitionWeight, double academicProfileWeight, double earningsWeight,
            double racialDiversityWeight, double economicMobilityWeight,
            double maxTuition, double maxAcademic, double maxEarnings, double hardCap) {

        double totalWeight = tuitionWeight + academicProfileWeight + earningsWeight + racialDiversityWeight
                + economicMobilityWeight;

        // Sets to the higher number between the user's inputted tuition and earnings,
        // and 1000.
        double safeTuition = Math.max(tuition, 1000);
        double safeEarnings = Math.max(earnings, 1000);

        /*
         * A penalty is applied if the tuition is above a specified hardCap.
         * The penalty uses a sigmoid funciton that sharply drops off after around 80%
         * of maxTuition
         * 
         * The tuition score decreases logarithmically as tuition increases.
         * This helps to model diminishing affordablity: tuition differences matter more
         * at the lower end
         * like 5k to 10k compared to 50k to 55k. This gives bonus points to extremely
         * cheapschools like
         * state schools.
         */
        double tuitionPenalty = (tuition > hardCap) ? 0.0
                : 1.0 / (1.0 + Math.exp((safeTuition - maxTuition * 0.8) / 2000));
        double tuitionScore = 1 - (Math.log(safeTuition) / Math.log(maxTuition));
        tuitionScore *= tuitionPenalty;

        // Academic profile is normalized based on the max value across all schools.
        // Gives us the comparison on a 0 to 1 scale.
        double academicProfileScore = academicProfile / maxAcademic;

        /*
         * Normalize earnings based on the log of the school's average graduate salary.
         * Meaning that higher
         * salaries increase this score, but gains diminish at the top. This is because
         * a small increase
         * at low salaries is worth proportionally more than a small increase at a
         * higher salary.
         * 
         * ROI (Return on Investment): Measures earnings relative to cost. This captures
         * how affordable
         * the school is compared to its financial payoffs.
         * 
         * Combines the raw earnings and ROI into a weighted score. ROI is slightly less
         * important than
         * absolute salary so it's only worth 40% of the score.
         */
        double earningsNormalized = Math.log(safeEarnings) / Math.log(maxEarnings);
        double roi = Math.log(safeEarnings / safeTuition) / Math.log(maxEarnings / 1000);
        double earningsScore = 0.6 * earningsNormalized + 0.4 * roi;

        // Seperate score that measures purely based on ROI. This reflects
        // economicmobility potential:
        // how much value students get from their tuition investment
        double economicMobilityScore = roi;

        // Combine all scores using their assigned weights, normalized by total weight,
        // then converted to 0-100 scale.
        score = ((tuitionScore * tuitionWeight) +
                (academicProfileScore * academicProfileWeight) +
                (earningsScore * earningsWeight) +
                (economicMobilityScore * economicMobilityWeight) +
                (racialDiversity * racialDiversityWeight)) / totalWeight * 100;

        return score;
    }

    // Accessors
    public String getName() {
        return name;
    }

    public int getTuition() {
        return tuition;
    }

    public double getAcademicProfile() {
        return academicProfile;
    }

    public double getScore() {
        return score;
    }

    public double getEarnings() {
        return earnings;
    }
}
