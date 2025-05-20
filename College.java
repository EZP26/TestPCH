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

        double totalWeight = tuitionWeight + academicProfileWeight + earningsWeight + racialDiversityWeight + economicMobilityWeight;

        double safeTuition = Math.max(tuition, 1000); 
        double safeEarnings = Math.max(earnings, 1000); 

        // Tuition score
        double tuitionPenalty = (tuition > hardCap) ? 0.0 : 1.0 / (1.0 + Math.exp((safeTuition - maxTuition * 0.8) / 2000));
        double tuitionScore = 1 - (Math.log(safeTuition) / Math.log(maxTuition));
        tuitionScore *= tuitionPenalty;

        // Academic and earnings scores
        double academicProfileScore = academicProfile / maxAcademic;
        double earningsNormalized = Math.log(safeEarnings) / Math.log(maxEarnings);
        double roi = Math.log(safeEarnings / safeTuition) / Math.log(maxEarnings / 1000);
        double earningsScore = 0.6 * earningsNormalized + 0.4 * roi;

        // Economic mobility score (pure earnings-to-cost ratio)
        double economicMobilityScore = roi; // already defined above

        // Final score
        score = (
            (tuitionScore * tuitionWeight) +
            (academicProfileScore * academicProfileWeight) +
            (earningsScore * earningsWeight) +
            (economicMobilityScore * economicMobilityWeight) +
            (racialDiversity * racialDiversityWeight)
        ) / totalWeight * 100;

        return score;
    }

    // Accessors
    public String getName() { return name; }
    public int getTuition() { return tuition; }
    public double getAcademicProfile() { return academicProfile; }
    public double getScore() { return score; }
    public double getEarnings() { return earnings; }
}
