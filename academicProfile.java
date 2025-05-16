public class academicProfile {
    private int satScore;          // out of 1600
    private int actScore;          // out of 36
    private double gpa;            // 0.0 - 4.0
    private double top10Percent;   // % of students in top 10% of HS class
    private double graduationRate;

    public academicProfile(int satScore, int actScore, double gpa, double top10Percent, double graduationRate) {
        this.satScore = satScore;
        this.actScore = actScore;
        this.gpa = gpa;
        this.top10Percent = top10Percent;
        this.graduationRate = graduationRate/100;
    }

    public int getSatScore() {
        return satScore;
    }

    public int getActScore(){
        return actScore;
    }

    public double getGpa() {
        return gpa;
    }

    public double getTop10Percent() {
        return top10Percent;
    }

    public double calculateAcademicScore() {
        double satNormalized = satScore / 1600.0;      // 1600 is max SAT
        double actNormalized = actScore / 36;          // 36 is max ACT
        double gpaNormalized = gpa / 4.0;              // 4.0 is max GPA
        double classRankNormalized = top10Percent / 100.0;

        return ((satNormalized + actNormalized)* 0.20 + 
                graduationRate * 0.40 + 
                (gpaNormalized + classRankNormalized) * 0.20) * 100;
    }
}
