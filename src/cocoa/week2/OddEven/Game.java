package cocoa.week2.OddEven;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Game {
    int myMoney; //대입은 생성할 때 하자
    int botMoney;
    int numStage;        //todo : 테스트용
    int turn;
    int totalTurn;
    //    String player1;
    List<Integer> money;
    List<String> record;

    public Game() {           // 생성자!
        this.myMoney = 100;
        this.botMoney = 120;
        this.numStage = 1;
        this.turn = 0;
        this.totalTurn = 0;
        money = new ArrayList<>();
        record = new ArrayList<>();
        Player player = new Player();
        player.registerPlayer();
// todo : Player.java 로 부터 플레이어 이름 가져오기 성공!
    }

//    public void registerPlayer() {
//
//        System.out.print("플레이어 이름을 입력하세요 : ");
//        Scanner sc = new Scanner(System.in);
//        Player.player1 = sc.nextLine();
//
//    }

    public void bot() {
        botMoney = (int) (getMyMoney() * Math.pow(1.2, numStage));
        System.out.println(">>> 상대방 소지금 : " + getBotMoney() + " 원");
        play();
    }

    public int getMyMoney() {
        return myMoney;
    }

    public int getBotMoney() {
        return botMoney;
    }


    public int randomNumber() {
        Random random = new Random();
        return random.nextInt(20) + 1;
    }

    public void play() {
        while (myMoney != 0 && botMoney != 0) {
            pick();
        }
        finalResult();
    }

    public void pick() {
        int number = randomNumber();
        System.out.println("blackSheepWall : " + number);           // todo: 테스트용
        System.out.print("[홀: 1] [짝: 2] 입력 :  ");
        Scanner sc = new Scanner(System.in);
        int pick = sc.nextInt();

        while (pick != 1 && pick != 2) {
            System.out.println(" [홀: 1] , [짝: 2] 중에 하나를 입력해주세요");
            pick = sc.nextInt();
        }

        System.out.println("얼마를 베팅하시겠습니까? 최대 베팅 가능 금액 : " + Math.min(myMoney, botMoney) + " 원");
        int bet = sc.nextInt();
        while (bet < 0) {
            System.out.println("양수를 입력해주세요. 최대 베팅 가능 금액 : " + Math.min(myMoney, botMoney) + " 원");
            bet = sc.nextInt();
        }
//        sc.close();
        if (bet > Math.min(myMoney, botMoney)) {
            System.out.println("최대 금액으로 베팅 : " + Math.min(myMoney, botMoney) + " 원");
            bet = Math.min(myMoney, botMoney);

        }
//        sc.close();   ?
        result(number, pick, bet);
//        sc.close();   ?
    }

    public void result(int number, int pick, int bet) {
        if (pick == 1) {
            System.out.println("홀을 선택하셨습니다");
            if (number % 2 == 1) {
                System.out.println("홀! 정답입니다!");
                win(bet);
            }
            if (number % 2 == 0) {
                System.out.println("짝! 틀렸습니다!");
                lose(bet);
            }
        }
        if (pick == 2) {
            System.out.println("짝을 선택하셨습니다");
            if (number % 2 == 1) {
                System.out.println("홀! 틀렸습니다!");
                lose(bet);
            }
            if (number % 2 == 0) {
                System.out.println("짝! 정답입니다!");
                win(bet);
            }
        }
        turn++;
        totalTurn += turn;
        money.add(myMoney);
        System.out.println("<소지금>  " + Player.player1 + " : " + myMoney + " 원" + "   상대방 : " + botMoney + " 원");
//        System.out.println(money);

    }

    public void win(int bet) {
        botMoney -= bet;
        myMoney += bet;
    }

    public void lose(int bet) {
        botMoney += bet;
        myMoney -= bet;
    }


    public void finalResult() {
        if (numStage < 8) {
            if (myMoney == 0) {
                System.out.print(Player.player1 + " 패배!!");
                System.out.println("  Level : " + numStage + "   " + "턴 수 : " + turn);
                ranking();
            }

            if (myMoney != 0) {
                System.out.print(Player.player1 + " 승리!!");
                System.out.println("  Level : " + numStage + "   " + "턴 수 : " + turn);
                System.out.println("----------------------------");
                System.out.println("HERE COMES A NEW CHALLENGER");
                System.out.println("----------------------------");
                turn = 0;
                numStage++;
                bot();
            }
        }
        if (numStage == 8) {
            System.out.println("-------------------");
            System.out.println(Player.player1 + " 최종 우승!!");
            System.out.println("-------------------");
            System.out.println("Level : " + numStage + "   " + "턴 수 : " + turn);
            numStage++;
            ranking();
        }

    }

    public void ranking() {
        writeFile();
        System.out.println(" 이름      최고금액        턴수");
        System.out.println(Player.player1 + "       " +
                Collections.max(money) + "          " + totalTurn);

    }


    public void writeFile() {

        // todo : 파일에 [이름, 최대소지금, 턴수] 형태로 저장 하는 것 까진 됐는데,
        //  기존의 값이 남지 않고 매번 새로 덮어씌워지는걸 해결해야한다. 그다음에는 읽어오기가 돼야 하고..

        record.add(Player.player1 + "," + Collections.max(money).toString() + "," + totalTurn);
        try {
            FileWriter fw = new FileWriter("record.csv");
            fw.write(String.valueOf(record));
            fw.close();
        } catch (IOException e) {
            System.out.println("error");
        }
    }
}