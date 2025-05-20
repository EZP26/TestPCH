public class College {
    private String name;
    private int tuition;
    private double academicProfile;
    private double earnings;
    private double racialDiversity;
    private double score;

    public College(String name, int tuition, double academicProfile, double earnings, double racialDiversityScore){
        this.name = name;
        this.tuition = tuition;
        this.academicProfile = academicProfile;
        this.earnings = earnings;
        this.racialDiversity = racialDiversityScore;
    }

    public double calculateScore(double tuitionWeight, double academicProfileWeight, double earningsWeight, double racialDiversityWeight,
    double maxTuition, double maxAcademic, double maxEarnings){
        
        double totalWeight = tuitionWeight + academicProfileWeight + earningsWeight + racialDiversityWeight;

            double tuitionScore = 1 - (tuition / maxTuition);
            double academicProfileScore = academicProfile / maxAcademic;
            double earningsScore = earnings / maxEarnings;

            score = (
                (tuitionScore * tuitionWeight) +
                (academicProfileScore * academicProfileWeight) +
                (earningsScore * earningsWeight) +
                (racialDiversity * racialDiversityWeight)
                ) / totalWeight * 100;
        
        return score;
    }

    public String getName() {
        return name;
    }

    public int getTuition() {
        return tuition;
    }

    public double getAcademicProfile() {
        return academicProfile;
    }

    public double getScore(){
        return score;
    }

    public double getEarnings() {
        return earnings;
    }
}
