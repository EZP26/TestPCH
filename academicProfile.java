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
        this.graduationRate = graduationRate / 100.0;
    }

    public double calculateAcademicScore() {
        double newSatScore = 100.22186 * Math.log(0.001648 * SAT);
        
        double newActScore = 100.22186 * Math.log(0.0749 * ACT);
        
        double newGpaScore = 1.4427 * Math.log(GPA) - 1;
        
        double classRankNormalized = top10Percent;
        
        double gradScore = graduationRate * 100;

        double rawScore = ((newSatScore + newActScore) * 0.20 +
                           gradScore * 0.40 +
                           (newGpaScore + classRankNormalized) * 0.20);

        double scaledScore = 2.0 + ((rawScore - 50) / 50.0) * 2.0;

        scaledScore = Math.max(2.0, Math.min(4.0, scaledScore));

        return scaledScore * 25;
    }
}
