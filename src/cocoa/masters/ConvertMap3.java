package cocoa.masters;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ConvertMap3 {
    private Map<String, String> encryptHash;
    private Map<String, String> decryptHash;
    private static ConvertMap3 convertMap3 = null;

    /* Singleton 활용해서 setHash 한번 실행하고 ConvertMap3 객체 하나만 계속 돌려 쓰기*/
    private ConvertMap3() {
        setHash();
    }

    public static ConvertMap3 getInstance() {
        if (convertMap3 == null) {
            convertMap3 = new ConvertMap3();
        }
        return convertMap3;
    }

    public void displayMap(String[][] encrypt) {
        DisplayContent display = new DisplayContent();
        display.printMap(decryptMap3(encrypt));
    }

    public void setHash() {
        encryptHash = Map.of(
            "#", "0",
            "O", "1",
            "o", "2",
            "P", "3",
            "=", "4",
            "0", "6",
            " ", " "

        );
        decryptHash = Map.of(
            "0", "#",
            "1", "O",
            "2", "o",
            "3", "P",
            "4", "=",
            "5", "P",
            "6", "0",
            " ", " "
        );
    }

    public String[][] encryptMap3(List<String> stage) {

        String[][] encryptTemp = make2DArray3(stage);
        for (int i = 0; i < stage.size() - 1; i++) {    // encrypt 에는 "Stage X" 가 포함 안되기 때문에 size()-1
            String[] rows = stage.get(i + 1).split("");
            for (int j = 0; j < rows.length; j++) {
                /*HashMap 으로 해보자*/
                encryptTemp[i][j] = encryptHash.get(rows[j]);
            }
        }
        return encryptTemp;
    }

    public String[][] decryptMap3(String[][] encrypt) {
        String[][] decryptedMap = new String[encrypt.length][encrypt[0].length];
        for (String[] s : decryptedMap) {
            Arrays.fill(s, " ");
        }
        for (int i = 0; i < encrypt.length; i++) {
            for (int j = 0; j < encrypt[0].length; j++) {
                decryptedMap[i][j] = decryptHash.get(encrypt[i][j]);
            }
        }
        return decryptedMap;
    }

    private String[][] make2DArray3(List<String> stage) {

        int maxWidth = 0;
        int maxHeight = stage.size() - 1;
        for (int i = 1; i < stage.size(); i++) {
            if (maxWidth < stage.get(i).length()) {
                maxWidth = stage.get(i).length();
            }
        }
        String[][] encrypt = new String[maxHeight][maxWidth];

        for (String[] s : encrypt) {
            Arrays.fill(s, " ");
        }
        return encrypt;
    }
}
