import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BlackJack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Person> people = new ArrayList<>();
        Deck deck = new Deck();

        //名前入力
        System.out.print("ディーラーの名前：");
        Person dealer = new Person(scanner.next(),"Dealer");
        people.add(dealer);
        //1人プレイヤーの場合
        System.out.print("プレイヤーの名前：");
        Person player = new Person(scanner.next(),"Player");
        people.add(player);

        //複数プレイヤーの場合
//        System.out.print("プレイヤーの人数：");
//        for(int i=0; i<scanner.nextInt(); i++){
//            System.out.print("プレイヤーの名前：");
//            people.add(new Player(scanner.next()));
//        }

        //ランダムに順番を決める
        Collections.shuffle(people);

        dealer.draw(deck);
        dealer.showStatus();
        player.draw(deck);
        player.showStatus();

        boolean endFlag = false;
        String drawSelect;
        Person person;
        int i;
        while(!endFlag || people.size()==0){
            for(i = 0; i < people.size(); i++){
                person = people.get(i);
                System.out.println(person.getName()+"の番です。");
                if(person.getRole().equals("Player")) {
                    System.out.println("ドローしますか？(y/n)");
                    drawSelect = scanner.next();
                    if (drawSelect.equals("y")) {
                        person.draw(deck);
                    } else if (drawSelect.equals("n")) {
                        people.remove(i);
                    }
                } else if(person.getRole().equals("Dealer")){
                    int flag = Rule.checkDealerScore(people.get(i));
                    if(flag == 0){
                        person.draw(deck);
                    } else if(flag == 1){
                        people.remove(i);
                    }
                }
                person.showStatus();
                if(Rule.checkBusted(person)){
                    person.setBusted(true);
                    endFlag = true;
                }
                System.out.println();
            }
        }

        //ゲーム終了処理
        System.out.println("ゲームは終了です。");
        dealer.showStatus();
        player.showStatus();

        String[] messages = {"プレイヤーの勝ちです。おめでとうございます。","引き分けです。","プレイヤーの負けです。"};
        if(player.isBusted()){
            System.out.println("プレイヤーはバーストしました。");
            System.out.println(messages[2]);
        } else if(dealer.isBusted()){
            System.out.println("ディーラーはバーストしました。");
            System.out.println(messages[0]);
        } else{
            int playerScore;
            if(player.getMinMaxScore()[1] > 21){
                playerScore = player.getMinMaxScore()[0];
            }else{
                playerScore = player.getMinMaxScore()[1];
            }
            int dealerScore = dealer.getMinMaxScore()[1];
            if(playerScore==dealerScore){
                System.out.println(messages[1]);
            } else if(playerScore>dealerScore){
                System.out.println(messages[0]);
            } else if(playerScore<dealerScore){
                System.out.println(messages[2]);
            }
        }
    }
}
