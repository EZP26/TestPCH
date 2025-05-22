import java.util.ArrayList;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
                ArrayList<College> colleges = new ArrayList<>();
                Scanner scanner = new Scanner(System.in);
                int hardCap;
                int maxTuition = 90000;
                int maxAcademicProfile = 100;
                int maxEarnings = 100000;

                System.out.println("Welcome to the College Ranking System!");

                System.out.print("Do you have a hard cap for tuition? $ amount: ");
                hardCap = scanner.nextInt();

                System.out.print("How important is low tuition to you? Enter a number between 0 and 100:  ");
                int tuitionWeight = scanner.nextInt();

                System.out.print(
                                "How important is a school with high academic rigor to you? Enter a number between 0 and 100:  ");
                int academicProfileWeight = scanner.nextInt();

                System.out.print(
                                "How important is having a high income 10 years after graduation? Enter a number between 0 and 100:  ");
                int earningsWeight = scanner.nextInt();

                System.out
                                .print("How important is economic mobility (bang for your buck)? Enter a number between 0 and 100:  ");
                int economicMobilityWeight = scanner.nextInt();

                System.out.print("How important is racial diversity to you? Enter a number between 0 and 100:  ");
                int racialDiversityWeight = scanner.nextInt();

                // Add colleges
                academicProfile HarvardAP = new academicProfile(1550, 35, 3.94, 94.4, 98);
                racialDiversity HarvardRD = new racialDiversity(7110 - 1029 - 87, 826, 668, 2249, 23, 1674, 9, 545);
                College Harvard = new College("Harvard", 56500, HarvardAP.calculateAcademicScore(), 101817,
                                HarvardRD.calculateRacialDiversity());
                colleges.add(Harvard);

                academicProfile OberlinAP = new academicProfile(1440, 32, 3.69, 52, 83);
                racialDiversity OberlinRD = new racialDiversity(2950 - 280 - 37, 243, 130, 1828, 0, 156, 1, 275);
                College Oberlin = new College("Oberlin", 63700, OberlinAP.calculateAcademicScore(), 58343,
                                OberlinRD.calculateRacialDiversity());
                colleges.add(Oberlin);

                academicProfile UoVAP = new academicProfile(1360, 31, 3.83, 38, 77);
                racialDiversity UoVRD = new racialDiversity(12276 - 194 - 330, 671, 119, 10090, 7, 320, 1, 544);
                College UofVermont = new College("UofVermont", 42724, UoVAP.calculateAcademicScore(), 62472,
                                UoVRD.calculateRacialDiversity());
                colleges.add(UofVermont);

                academicProfile SonomaAP = new academicProfile(1065, 20, 3.2, 10, 69);
                racialDiversity SonomaRD = new racialDiversity(8565 - 245 - 511, 2871, 193, 3707, 36, 428, 27, 547);
                College SonomaState = new College("SonomaState", 5742, SonomaAP.calculateAcademicScore(), 65986,
                                SonomaRD.calculateRacialDiversity());
                colleges.add(SonomaState);

                // Completed and Correct
                academicProfile StanfordAP = new academicProfile(1540, 35, 3.94, 97.8, 97);
                racialDiversity StanfordRD = new racialDiversity(7904 - 39 - 965, 1501, 644, 1754, 62, 2199, 16, 724);
                College Stanford = new College("Stanford", 67731, StanfordAP.calculateAcademicScore(), 124000,
                                StanfordRD.calculateRacialDiversity());
                colleges.add(Stanford);

                // Completed and Correct
                academicProfile UofIUCAP = new academicProfile(1450, 32, 3.8, 54, 87);
                racialDiversity UofIUCRD = new racialDiversity(35564 - 5220 - 532, 4343, 1955, 13627, 10, 8000, 13,
                                1364);
                College UofIUC = new College("UIUC", 31832, UofIUCAP.calculateAcademicScore(), 81054,
                                UofIUCRD.calculateRacialDiversity());
                colleges.add(UofIUC);

                // Completed and Correct
                academicProfile PennStateAP = new academicProfile(1330, 30, 3.84, 37, 85);
                racialDiversity PennStateRD = new racialDiversity(42619 - 3989 - 1052, 3963, 1929, 26660, 50, 3224, 29,
                                1723);
                College PennState = new College("PennState", 41212, PennStateAP.calculateAcademicScore(), 63000,
                                PennStateRD.calculateRacialDiversity());
                colleges.add(PennState);

                // Completed and Correct
                academicProfile UofTexaDallasAP = new academicProfile(1280, 28, 3.71, 39.95, 70);
                racialDiversity UofTexaDallasRD = new racialDiversity(21858 - 1205 - 737, 3992, 1298, 4496, 37, 9287, 8,
                                798);
                College UofTexaDallas = new College("UofTexaDallas", 36139, UofTexaDallasAP.calculateAcademicScore(),
                                68227,
                                UofTexaDallasRD.calculateRacialDiversity());
                colleges.add(UofTexaDallas);

                rankColleges(colleges, tuitionWeight, academicProfileWeight, earningsWeight,
                                racialDiversityWeight, economicMobilityWeight,
                                maxTuition, maxAcademicProfile, maxEarnings, hardCap);
                scanner.close();
        }

        public static void rankColleges(ArrayList<College> colleges, double tuitionWeight, double academicWeight,
                        double earningsWeight,
                        double racialDiversityWeight, double economicMobilityWeight,
                        double maxTuition, double maxAcademic, double maxEarnings, double hardCap) {
                for (College college : colleges) {
                        college.calculateScore(tuitionWeight, academicWeight, earningsWeight,
                                        racialDiversityWeight, economicMobilityWeight,
                                        maxTuition, maxAcademic, maxEarnings, hardCap);
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

                double totalWeight = tuitionWeight + academicWeight + earningsWeight + racialDiversityWeight
                                + economicMobilityWeight;
                System.out.printf(
                                "Earnings: %.1f%% Academic Profile: %.1f%% Tuition: %.1f%% Racial Diversity: %.1f%% Economic Mobility: %.1f%%\n",
                                (earningsWeight / totalWeight) * 100,
                                (academicWeight / totalWeight) * 100,
                                (tuitionWeight / totalWeight) * 100,
                                (racialDiversityWeight / totalWeight) * 100,
                                (economicMobilityWeight / totalWeight) * 100);

                // Print rankings
                System.out.println("College Rankings:");
                for (int i = 0; i < colleges.size(); i++) {
                        College c = colleges.get(i);
                        System.out.printf("%d. %s (Score: %.2f)\n", i + 1, c.getName(), c.getScore());
                }
        }
}
