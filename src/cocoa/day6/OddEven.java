package cocoa.day6;

import java.util.Random;
import java.util.Scanner;

class GamePlay {
    int myMoney = 100;
    int botMoney = 120;
    int numStage = 1;
    int turn = 0;
    String player1;

    public void RegisterPlayer() {

        System.out.println("플레이어 이름을 입력하세요");
        Scanner sc = new Scanner(System.in);
        player1 = sc.nextLine();
    }

    public void Bot() {
        botMoney = (int) (myMoney * Math.pow(1.2, numStage));
        System.out.println(botMoney);
    }

    public int randomNumber() {
        Random random = new Random();
        return random.nextInt(20);
//        System.out.println(number);  // 임시 확인용. 실제 게임 중에는 보여지지 않음
    }

    public void pick() {

//       Todo: 선택

        System.out.println("[홀: 1] , [짝: 2] 입력");
        Scanner sc = new Scanner(System.in);
        int pick = sc.nextInt();
        while (pick != 1 && pick != 2) {
            System.out.println(" [홀: 1] , [짝: 2] 중에 하나를 입력해주세요");
            pick = sc.nextInt();
        }

//        Todo: 베팅

        System.out.println("얼마를 베팅하시겠습니까? 최대 베팅 가능 금액:" + Math.min(myMoney, botMoney) + " 원");
        Scanner sc1 = new Scanner(System.in);
        int bet = sc1.nextInt();
        while (bet > Math.min(myMoney, botMoney)) {
            System.out.println("최대 베팅 가능 금액은 " + Math.min(myMoney, botMoney) + "원 입니다. 얼마를 베팅하시겠습니까?");
            bet = sc1.nextInt();

        }

//      Todo : 판별

        int number = randomNumber();
        if (pick == 1) {
            System.out.println("홀을 선택하셨습니다");
            System.out.println("홀! 정답입니다!");  //테스트용
            win(bet);                           //테스트용
        }
//            if (number % 2 == 1) {
//                System.out.println("홀! 정답입니다!");
//                win(bet);
//            }
//            if (number % 2 == 0) {
//                System.out.println("짝! 틀렸습니다!");
//                lose(bet);
//            }

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
        System.out.println(player1 + " 돈: " + myMoney + " 원");
        System.out.println("상대방 돈: " + botMoney + " 원");

    }

//      Todo: 점수 계산

    public void win(int bet) {
        botMoney -= bet;
        myMoney += bet;
    }

    public void lose(int bet) {
        botMoney += bet;
        myMoney -= bet;
    }

    public void repeat() {
        while (myMoney != 0 && botMoney != 0) {
            pick();
        }
        judge();
    }

    public void judge() {
        if (numStage < 8) {
            if (myMoney == 0) {
                System.out.println(player1 + " 패배!!");
                System.out.println("Level : " + numStage + "   " + "턴 수 : " + turn);
            }

            if (myMoney != 0) {
                System.out.println(player1 + " 승리!!");
                System.out.println("Level : " + numStage + "   " + "턴 수 : " + turn);
                System.out.println("-------------------------");
                System.out.println("HERE COMES A NEW CHALLENGER");
                turn = 0;
                numStage++;
                Bot();
            }
        }
        if (numStage == 8) {
            System.out.println(player1 + " 최종 우승!!");
            System.out.println("Level : " + numStage + "   " + "턴 수 : " + turn);
            numStage++;
        }
    }
}

public class OddEven {
    public static void main(String[] args) {
        GamePlay game = new GamePlay();
        game.RegisterPlayer();
        game.numStage = 8; // 테스트용
        while (game.myMoney != 0 && game.numStage < 9) {
            game.repeat();
        }

    }
}
// todo : 플레이어 홀짝 입력 후 배팅금 입력