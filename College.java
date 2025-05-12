public class College {
    private String name;
    private int tuition;
    private double academicProfile;
    private double earnings;
    private double score;

    public College(String name, int tuition, double academicProfile, double earnings){
        this.name = name;
        this.tuition = tuition;
        this.academicProfile = academicProfile;
        this.earnings = earnings;
    }

    public double calculateScore(double tuitionWeight, double academicProfileWeight, double earningsWeight, double maxTuition, double maxAcademic, double maxEarnings){
        double tuitionScore = 1 - (tuition / maxTuition);
        double academicProfileScore = academicProfile / maxAcademic;
        double earningsScore = earnings / maxEarnings;
        score = (tuitionScore * tuitionWeight + academicProfileScore * academicProfileWeight + earningsScore * earningsWeight);
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
