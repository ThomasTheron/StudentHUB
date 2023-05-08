package myappj.example.james.stub;

import android.app.Application;

public class varStore extends Application {

    private String studentNumber;
    private String achivmentsText;
    private String gradebookText;
    private String tutorialMain;

    private boolean GivenConsent;

    private int[][] hub1019fMarks;//[0][x] is got marks  //[1][y] is future marks
    private int[][] hub1020sMarks;
    private int[][] HUB1006fMarks;
    private int[][] ibs1007sMarks;

    //exam/test//assinments
    private double[] hub1000CourseWaiting = {0.55, 0.40, 0.5};//todo remove this line
    //written exam/test//assinments//prac exam
    private double[] hub1019fWaiting = {0.45, 0.40, 0.05, 0.1};
    private double[] hub1020sWaiting = {0.45, 0.40, 0.05, 0.1};
    private double[] HUB1006fWaiting = {0.50, 0.30, 0.1, 0.1};//special cause test 1 is 10 and test 2 is 20
    private double[] ibs1007sWaiting = {0.50, 0.30, 0.1, 0.1};
    //todo add other courses waiting


    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setAchivmentsText(String achivmentsText) {
        this.achivmentsText = achivmentsText;
    }

    public String getAchivmentsText() {
        return achivmentsText;
    }

    public int[] getAchivmentValues() {

        String[] AchivmentsSplit = achivmentsText.split(",");
        int[] AValues = new int[AchivmentsSplit.length - 1];

        for (int i = 1; i < AchivmentsSplit.length; i++) {
            AValues[i - 1] = Integer.parseInt(AchivmentsSplit[i]);
        }

        return AValues;
    }

    public void sethub1019fMarks(int[][] hub1019fMarks) {
        this.hub1019fMarks = hub1019fMarks;
    }

    public int[][] gethub1019fMarks() {
        return hub1019fMarks;
    }

    public void sethub1020sMarks(int[][] hub1020sMarks) {
        this.hub1020sMarks = hub1020sMarks;
    }

    public int[][] gethub1020sMarks() {
        return hub1020sMarks;
    }

    public void setHUB1006fMarks(int[][] HUB1006fMarks) {
        this.HUB1006fMarks = HUB1006fMarks;
    }

    public int[][] getHUB1006fMarks() {
        return HUB1006fMarks;
    }

    public void setibs1007sMarks(int[][] ibs1007sMarks) {
        this.ibs1007sMarks = ibs1007sMarks;
    }

    public int[][] ibs1007sMarks() {
        return ibs1007sMarks;
    }


    public double[] gethub1019fWaiting() {
        return hub1019fWaiting;
    }

    public double[] gethub1020sWaiting() {
        return hub1020sWaiting;
    }

    public double[] gethub1006fWaiting() {
        return HUB1006fWaiting;
    }

    public double[] getibs1007sWaiting() {
        return ibs1007sWaiting;
    }

    //sets all course marks and handles sting mitipulation.
    public void setgradebookText(String gradebookText) {
        //todo set other grades by miniping this stirng

        System.out.println(gradebookText);
        System.out.println();
        this.gradebookText = gradebookText;
        System.out.println(gradebookText);


        String[] courseGrades = new String[4];
        courseGrades = gradebookText.split("#");
        String[][] seperatedCourseGrades = new String[4][12];
        int[][] seperatedCourseGradesINTEGER = new int[4][12];
        // courseCode/Studentnumber/Test1/test1/	Test2/test2/ Assignments/Assignments/Written Exams/Written Exams/Practical Exams/Practical Exams
        if (courseGrades[0].length() > 5) {
            seperatedCourseGrades[0] = courseGrades[0].split("/");
        } else {
            seperatedCourseGrades[0] = null;
        }
        if (courseGrades[1].length() > 5) {
            seperatedCourseGrades[1] = courseGrades[1].split("/");
        } else {
            seperatedCourseGrades[1] = null;
        }
        if (courseGrades[2].length() > 5) {
            seperatedCourseGrades[2] = courseGrades[2].split("/");
        } else {
            seperatedCourseGrades[2] = null;
        }
        if (courseGrades[3].length() > 5) {
            seperatedCourseGrades[3] = courseGrades[3].split("/");
        } else {
            seperatedCourseGrades[3] = null;
        }

        if (seperatedCourseGrades != null) {


            for (int i = 0; i < 4; i++) {
                if (seperatedCourseGrades[i] != null) {

                    if (seperatedCourseGrades[i].length > 3) {

                        for (int j = 1; j < seperatedCourseGrades[i].length; j++) {//start at one to slkip username

                            if (!seperatedCourseGrades[i][j].equals("null")) {

                                seperatedCourseGradesINTEGER[i][j] = Integer.parseInt(seperatedCourseGrades[i][j]);

                            } else {//if it is null
                                seperatedCourseGradesINTEGER[i][j] = -1;

                            }

                        }

                    }
                }
            }
        }

        int[][] hub1019fMarksMaker = new int[2][3];//[0][x] is got marks  //[1][y] is future marks
        int[][] hub1020sMarksMaker = new int[2][3];//test/assinment//exam
        int[][] hub1006fMarksMaker = new int[2][3];
        int[][] ibs1007sMarksMaker = new int[2][3];
        for (int i = 0; i < hub1019fMarksMaker.length; i++) {
            for (int j = 0; j < hub1019fMarksMaker[i].length; j++) {
                hub1019fMarksMaker[i][j] = 0;
                hub1020sMarksMaker[i][j] = 0;
                hub1006fMarksMaker[i][j] = 0;
                ibs1007sMarksMaker[i][j] = 0;
            }
        }

        if (seperatedCourseGradesINTEGER[0] != null) {


            // hub1019f tests

            if (seperatedCourseGradesINTEGER[0][1] != -1) {
                hub1019fMarksMaker[0][0] = hub1019fMarksMaker[0][0] + ((int) (seperatedCourseGradesINTEGER[0][1] / 2));
            } else {
                hub1019fMarksMaker[1][0] = hub1019fMarksMaker[1][0] + 50;

            }
            if (seperatedCourseGradesINTEGER[0][3] != -1) {
                hub1019fMarksMaker[0][0] = +hub1019fMarksMaker[0][0] + (int) (seperatedCourseGradesINTEGER[0][3] / 2);
            } else {
                hub1019fMarksMaker[1][0] = hub1019fMarksMaker[1][0] + 50;

            }
            // hub1019f tests

            // hub1019f assinments
            if (seperatedCourseGradesINTEGER[0][5] != -1 && seperatedCourseGradesINTEGER[0][7] != -1) {
                hub1019fMarksMaker[0][1] = hub1019fMarksMaker[0][1] + seperatedCourseGradesINTEGER[0][5];
                hub1019fMarksMaker[1][1] = hub1019fMarksMaker[1][1] + (int) ((8 - seperatedCourseGradesINTEGER[0][7]) / 0.08);//* assinments not done

            } else if (seperatedCourseGradesINTEGER[0][5] != -1) {
                hub1019fMarksMaker[0][1] = hub1019fMarksMaker[0][1] + seperatedCourseGradesINTEGER[0][5];
            } else {
                hub1019fMarksMaker[1][1] = hub1019fMarksMaker[1][1] + 100;//todo fix for multiple assinments
            }
            // hub1019f assinments


            // hub1019f exams
            if (seperatedCourseGradesINTEGER[0][8] != -1) {
                hub1019fMarksMaker[0][2] = hub1019fMarksMaker[0][2] + ((int) (seperatedCourseGradesINTEGER[0][8] * 0.82)); //  45/55=0.81
            } else {
                hub1019fMarksMaker[1][2] = hub1019fMarksMaker[1][2] + ((int) (82)); //  45/55=0.81
            }
            if (seperatedCourseGradesINTEGER[0][10] != -1) {
                hub1019fMarksMaker[0][2] = hub1019fMarksMaker[0][2] + ((int) (seperatedCourseGradesINTEGER[0][10] * 0.18));//  10/55=0.18
            } else {
                hub1019fMarksMaker[1][2] = hub1019fMarksMaker[1][2] + ((int) (18));//  10/55=0.18
            }
            // hub1019f exams
            sethub1019fMarks(hub1019fMarksMaker);
        }
//'----------------------------------------------------------------------------
        if (seperatedCourseGradesINTEGER[1] != null) {

            // hub1020s tests
            if (seperatedCourseGradesINTEGER[1][1] != -1) {
                hub1020sMarksMaker[0][0] = hub1020sMarksMaker[0][0] + ((int) (seperatedCourseGradesINTEGER[1][1] / 2));
            } else {
                hub1020sMarksMaker[1][0] = hub1020sMarksMaker[1][0] + 50;
            }
            if (seperatedCourseGradesINTEGER[1][3] != -1) {
                hub1020sMarksMaker[0][0] = +hub1020sMarksMaker[0][0] + (int) (seperatedCourseGradesINTEGER[1][3] / 2);
            } else {
                hub1020sMarksMaker[1][0] = hub1020sMarksMaker[1][0] + 50;
            }
            // hub1020s tests

            // hub1020s assinments

            if (seperatedCourseGradesINTEGER[1][5] != -1 && seperatedCourseGradesINTEGER[1][7] != -1) {

                hub1020sMarksMaker[0][1] = hub1020sMarksMaker[0][1] + seperatedCourseGradesINTEGER[1][5];
                hub1020sMarksMaker[1][1] = hub1020sMarksMaker[1][1] + (int) ((8 - seperatedCourseGradesINTEGER[1][7]) / 0.08);
            } else if (seperatedCourseGradesINTEGER[1][5] != -1) {
                hub1020sMarksMaker[0][1] = hub1020sMarksMaker[0][1] + seperatedCourseGradesINTEGER[1][5];
            } else {

                hub1020sMarksMaker[1][1] = hub1020sMarksMaker[1][1] + 100;
            }
            // hub1020s assinments


            // hub1020s exams
            if (seperatedCourseGradesINTEGER[1][8] != -1) {
                hub1020sMarksMaker[0][2] = hub1020sMarksMaker[0][2] + ((int) (seperatedCourseGradesINTEGER[1][8] * 0.82)); //  45/55=0.81
            } else {
                hub1020sMarksMaker[1][2] = hub1020sMarksMaker[1][2] + ((int) (82)); //  45/55=0.81
            }
            if (seperatedCourseGradesINTEGER[1][10] != -1) {
                hub1020sMarksMaker[0][2] = hub1020sMarksMaker[0][2] + ((int) (seperatedCourseGradesINTEGER[1][10] * 0.18));//  10/55=0.18
            } else {
                hub1020sMarksMaker[1][2] = hub1020sMarksMaker[1][2] + ((int) (18));//  10/55=0.18
            }
            // hub1020s exams

            sethub1020sMarks(hub1020sMarksMaker);
        }
//'----------------------------------------------------------------------------
        if (seperatedCourseGradesINTEGER[2] != null) {
            // hub1006f tests
            if (seperatedCourseGradesINTEGER[2][1] != -1) {
                hub1006fMarksMaker[0][0] = hub1006fMarksMaker[0][0] + ((int) (seperatedCourseGradesINTEGER[2][1] * 0.34));
            } else {
                hub1006fMarksMaker[1][0] = hub1006fMarksMaker[1][0] + 34;
            }
            if (seperatedCourseGradesINTEGER[2][3] != -1) {
                hub1006fMarksMaker[0][0] = +hub1006fMarksMaker[0][0] + (int) (seperatedCourseGradesINTEGER[2][3] * 0.66);
            } else {
                hub1006fMarksMaker[1][0] = hub1006fMarksMaker[1][0] + 66;
            }
            // hub1006f tests

            // hub1006f assinments
            if (seperatedCourseGradesINTEGER[2][5] != -1) {
                hub1006fMarksMaker[0][1] = hub1006fMarksMaker[0][1] + seperatedCourseGradesINTEGER[2][5];
            } else {
                hub1006fMarksMaker[1][1] = hub1006fMarksMaker[1][1] + 100;//todo fix for multiple assinments
            }
            // hub1006f assinments


            // hub1006f exams
            if (seperatedCourseGradesINTEGER[2][7] != -1) {
                hub1006fMarksMaker[0][2] = hub1006fMarksMaker[0][2] + ((int) (seperatedCourseGradesINTEGER[2][7] * 0.82)); //  45/55=0.81
            } else {
                hub1006fMarksMaker[1][2] = hub1006fMarksMaker[1][2] + ((int) (82)); //  45/55=0.81
            }
            if (seperatedCourseGradesINTEGER[2][9] != -1) {
                hub1006fMarksMaker[0][2] = hub1006fMarksMaker[0][2] + ((int) (seperatedCourseGradesINTEGER[2][9] * 0.18));//  10/55=0.18
            } else {
                hub1006fMarksMaker[1][2] = hub1006fMarksMaker[1][2] + ((int) (18));//  10/55=0.18
            }
            // hub1006f exams

            setHUB1006fMarks(hub1006fMarksMaker);
        }

//----------------------------------------------------------------------

        if (seperatedCourseGradesINTEGER[3] != null) {
            // ibs1007s tests
            if (seperatedCourseGradesINTEGER[3][1] != -1) {
                ibs1007sMarksMaker[0][0] = ibs1007sMarksMaker[0][0] + ((int) (seperatedCourseGradesINTEGER[3][1] * 0.5));
            } else {
                ibs1007sMarksMaker[1][0] = ibs1007sMarksMaker[1][0] + 50;
            }
            if (seperatedCourseGradesINTEGER[3][3] != -1) {
                ibs1007sMarksMaker[0][0] = +ibs1007sMarksMaker[0][0] + (int) (seperatedCourseGradesINTEGER[3][3] * 0.5);
            } else {
                ibs1007sMarksMaker[1][0] = ibs1007sMarksMaker[1][0] + 50;
            }
            // ibs1007s tests

            // ibs1007s assinments
            if (seperatedCourseGradesINTEGER[3][5] != -1) {
                ibs1007sMarksMaker[0][1] = ibs1007sMarksMaker[0][1] + seperatedCourseGradesINTEGER[3][5];
            } else {
                ibs1007sMarksMaker[1][1] = ibs1007sMarksMaker[1][1] + 100;
            }
            // ibs1007s assinments


            // ibs1007s exams
            if (seperatedCourseGradesINTEGER[3][7] != -1) {
                ibs1007sMarksMaker[0][2] = ibs1007sMarksMaker[0][2] + ((int) (seperatedCourseGradesINTEGER[3][7] * 0.84)); // 50/60=0.84
            } else {
                ibs1007sMarksMaker[1][2] = ibs1007sMarksMaker[1][2] + ((int) (84)); //  50/60=0.84
            }
            if (seperatedCourseGradesINTEGER[3][9] != -1) {
                ibs1007sMarksMaker[0][2] = ibs1007sMarksMaker[0][2] + ((int) (seperatedCourseGradesINTEGER[3][9] * 0.16));// 10/60=0.16
            } else {
                ibs1007sMarksMaker[1][2] = ibs1007sMarksMaker[1][2] + ((int) (16));//  10/60=0.16
            }
            // ibs1007s exams

            setibs1007sMarks(ibs1007sMarksMaker);
        }


        if (courseGrades[0].equals("-")) {
            sethub1019fMarks(null);
        }
        if (courseGrades[1].equals("-")) {
            sethub1020sMarks(null);
        }
        if (courseGrades[2].equals("-")) {
            setHUB1006fMarks(null);
        }
        if (courseGrades[3].equals("-")) {
            setibs1007sMarks(null);
        }


        //course order//hub1019f//hub1020s//hub1006f/ibs1007s
    }

    public String getgradebookText() {
        return gradebookText;
    }


    public void settutorialMain(String tutorialText) {
        this.tutorialMain = tutorialText;
    }

    public String gettutorialMain() {
        return tutorialMain;
    }

    public void setGivenConsent(boolean INGivenConsent) {
        this.GivenConsent = INGivenConsent;
    }

    public boolean getGivenConsent() {
        return GivenConsent;
    }
}
