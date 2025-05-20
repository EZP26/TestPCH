import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<College> colleges = new ArrayList<College>();
         Scanner scanner = new Scanner(System.in);
        int maxTuition = 90000;
        int maxAcademicProfile = 100;
        int maxEarnings = 100000;

        System.out.println("Welcome to the College Ranking System!");
        System.out.println("Enter a value between 0 and 100 for each category.");
        
        System.out.print("How important is low tuition to you? ");
        int tuitionWeight = scanner.nextInt();

        System.out.print("How important is a school with high academic rigor to you? ");
        int academicProfileWeight = scanner.nextInt();

        System.out.print("How important is having a high income 10 years after graduation? ");
        int earningsWeight = scanner.nextInt();

        System.out.print("How important is racial diversity to you? ");
        int racialDiversityWeight = scanner.nextInt();

        academicProfile HarvardAP = new academicProfile(1550, 35, 3.94, 94.4, 98);
        racialDiversity HarvardRD = new racialDiversity(7110-1029-87, 826, 668, 2249, 23, 1674, 9, 545);
        College Harvard = new College("Harvard", 56500, HarvardAP.calculateAcademicScore(), 101817, HarvardRD.calculateRacialDiversity());
        colleges.add(Harvard);

        academicProfile OberlinAP = new academicProfile(1440, 32, 3.69, 52,85);
        racialDiversity OberlinRD = new racialDiversity(2950-280-37, 243, 130, 1828, 0, 156, 1, 275);
        College Oberlin = new College("Oberlin", 63700, OberlinAP.calculateAcademicScore(), 58343, OberlinRD.calculateRacialDiversity());
        colleges.add(Oberlin);

        academicProfile UoVAP = new academicProfile(1360, 31, 3.83, 38, 77);
        racialDiversity UoVRD = new racialDiversity(12276-194-330, 671, 119, 10090, 7, 320, 1, 544);
        College UofVermont = new College("UofVermont", 42724, UoVAP.calculateAcademicScore(), 62472, UoVRD.calculateRacialDiversity());
        colleges.add(UofVermont);

        academicProfile SonomaAP = new academicProfile(1065, 20, 3.2, 10, 69);
        racialDiversity SonomaRD = new racialDiversity(8565-245-511, 2871, 193, 3707, 36, 428, 27, 547);
        College SonomaState = new College("SonomaState", 5742, SonomaAP.calculateAcademicScore(), 65986, SonomaRD.calculateRacialDiversity());
        colleges.add(SonomaState);
        
        rankColleges(colleges, tuitionWeight, academicProfileWeight, earningsWeight, racialDiversityWeight, maxTuition, maxAcademicProfile, maxEarnings);
        scanner.close();
     }

    public static void rankColleges(ArrayList<College> colleges, double tuitionWeight, double academicWeight, double earningsWeight, double racialDiversityWeight,
        double maxTuition, double maxAcademic, double maxEarnings) {
        for (College college : colleges) {
            college.calculateScore(tuitionWeight, academicWeight, earningsWeight, racialDiversityWeight, maxTuition, maxAcademic, maxEarnings);
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
        double totalWeight = earningsWeight + academicWeight + tuitionWeight + racialDiversityWeight;
        System.out.println("Your Rankings:");
        System.out.printf("Earnings: %.1f%% Academic Profile: %.1f%% Tuition: %.1f%% Racial Diversity: %.1f%%\n",
            (earningsWeight / totalWeight) * 100,
            (academicWeight / totalWeight) * 100,
            (tuitionWeight / totalWeight) * 100,
            (racialDiversityWeight / totalWeight) * 100);

        System.out.println("College Rankings:");
        for (int i = 0; i < colleges.size(); i++) {
            College c = colleges.get(i);
            System.out.printf("%d. %s (Score: %.2f)\n", i + 1, c.getName(), c.getScore());
        }
    }
}