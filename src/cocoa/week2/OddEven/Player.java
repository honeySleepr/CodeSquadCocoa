package cocoa.week2.OddEven;

import java.util.Scanner;

public class Player {
    static String player1;
    // public? private? 빈칸?
    public Player() {
    }
    public void registerPlayer() {

        System.out.print("플레이어 이름을 입력하세요 : ");
        Scanner sc = new Scanner(System.in);
        player1 = sc.nextLine();
//
    }

}
