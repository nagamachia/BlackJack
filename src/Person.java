import java.util.ArrayList;
import java.util.Arrays;

public class Person {
    private String name;
    private int[] minMaxScore;
    private ArrayList<String> hands;
    private boolean busted;
    private String role;

    public Person(String name, String role) {
        this.name = name;
        this.minMaxScore = new int[]{0, 0};
        this.busted = false;
        this.hands = new ArrayList<>();
        this.role = role;
    }

    public void draw(Deck deck){
        ArrayList<String> cards = deck.getCards();
        String card = cards.remove(0);
        this.hands.add(card);
        this.minMaxScore = Rule.hands2point(this.hands);
    }

    public void showStatus(){
        System.out.println(this.name+"の手札：");
        System.out.println(Arrays.toString(this.hands.toArray()));
        if(this.role.equals("Dealer") || minMaxScore[0] == minMaxScore[1]){
            System.out.println(this.name+"の点数："+this.minMaxScore[1]+"点");
        }else {
            System.out.println(this.name+"の最小点数："+this.minMaxScore[0]+"点");
            System.out.println(this.name+"の最大点数："+this.minMaxScore[1]+"点");
        }
    }

    public String getName() {
        return name;
    }

    public int[] getMinMaxScore() {
        return minMaxScore;
    }

    public boolean isBusted() {
        return busted;
    }

    public void setBusted(boolean busted) {
        this.busted = busted;
    }

    public String getRole() {
        return role;
    }
}
