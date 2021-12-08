package cocoa.masters;

import java.io.File;
import java.util.Scanner;

public class Sokoban {
    public static void main(String[] args) {

        System.out.print("[1,2 단계는  1 입력] \n[3 단계는  3 입력] \n단계 선택> ");
        Scanner sc = new Scanner(System.in);
        String level = sc.nextLine();
        while (!(level.equals("1") ||level.equals("3"))) {
            System.out.println("1 또는 3 입력 \n단계 선택>");
            level = sc.nextLine();
        }
        if (level.equals("1")) {
            new CreateGame();
        }
        if (level.equals("3")) {
            new CreateGame3();
        }
    }
}
