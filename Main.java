import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<College> colleges = new ArrayList<College>();
        int maxTuition = 90000;
        int maxAcademicProfile = 100;
        int maxEarnings = 100000;

        //Change These Two Numbers from 0 to 100
        int tuitionWeight =  100;
        int academicProfileWeight = 50;
        int earningsWeight = 50;

        academicProfile HarvardAP = new academicProfile(1550, 35, 3.94, 94.4);
        College Harvard = new College("Harvard", 56500, HarvardAP.calculateAcademicScore(), 101817);
        colleges.add(Harvard);

        academicProfile OberlinAP = new academicProfile(1440, 32, 3.69, 52);
        College Oberlin = new College("Oberlin", 63700, OberlinAP.calculateAcademicScore(), 58343);
        colleges.add(Oberlin);

        academicProfile UoVAP = new academicProfile(1360, 31, 3.83, 38);
        College UofVermont = new College("UofVermont", 42724, UoVAP.calculateAcademicScore(), 62472);
        colleges.add(UofVermont);

        academicProfile SonomaAP = new academicProfile(1065, 20, 3.2, 10);
        College SonomaState = new College("SonomaState", 5742, SonomaAP.calculateAcademicScore(), 65986);
        colleges.add(SonomaState);
        
        rankColleges(colleges, tuitionWeight, academicProfileWeight, earningsWeight, maxTuition, maxAcademicProfile, maxEarnings);   
     }

    public static void rankColleges(ArrayList<College> colleges, double tuitionWeight, double academicWeight, double earningsWeight, double maxTuition, double maxAcademic, double maxEarnings) {
        for (College college : colleges) {
            college.calculateScore(tuitionWeight, academicWeight, earningsWeight, maxTuition, maxAcademic, maxEarnings);
        }
    
        // Insertion sort (descending order by score)
        for (int i = 1; i < colleges.size(); i++) {
            College key = colleges.get(i);
            int j = i - 1;
    
            while (j >= 0 && colleges.get(j).getScore() < key.getScore()) {
                colleges.set(j + 1, colleges.get(j));
                j = j - 1;
            }
            colleges.set(j + 1, key);
        }
    
        // Print the rankings
        System.out.println("College Rankings:");
        for (int i = 0; i < colleges.size(); i++) {
            College c = colleges.get(i);
            System.out.printf("%d. %s (Score: %.2f)\n", i + 1, c.getName(), c.getScore());
        }
    }
    
}
    

