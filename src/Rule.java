import java.util.ArrayList;

public class Rule {

    public static int[] hands2point(ArrayList<String> hands){
        int[] minMaxPoint = {0,0};
        boolean isDrawA = false;

        for(String card: hands){
            String[] suitNum = card.split("_");

            if(suitNum[1].equals("A")){
                if(!isDrawA){
                    minMaxPoint[0] += 1;
                    minMaxPoint[1] += 11;
                    isDrawA = true;
                }else {
                    minMaxPoint[0] += 1;
                    minMaxPoint[1] += 1;
                }
            } else if("JQK".contains(suitNum[1])){
                minMaxPoint[0] += 10;
                minMaxPoint[1] += 10;
            } else {
                minMaxPoint[0] += Integer.parseInt(suitNum[1]);
                minMaxPoint[1] += Integer.parseInt(suitNum[1]);
            }
        }
        return minMaxPoint;
    }

    public static boolean checkBusted(Person person){
        return person.getMinMaxScore()[0] > 21;
    }

    public static int checkDealerScore(Person person){
        int score = person.getMinMaxScore()[1];
        int flag = 0;
        if(score < 17){
            flag = 0;
        } else if (score <= 21){
            flag = 1;
        } else {
            flag = 2;
        }
        return flag;
    }
}
