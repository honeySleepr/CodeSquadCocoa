package cocoa.masters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateGame3 {
	private final List<String[][]> encryptedMapList = new ArrayList<>();

	CreateGame3() {
		readFromFile();
		play();
	}

	private void play() {
		System.out.println("소코반 게임을 시작합니다!");
		int level = 0;
		while (level < encryptedMapList.size()) {
			Control3 control3 = showMap(level);
			if (control3.quit) {
				System.out.print("Bye~");
				return;
			}
			if (control3.turnCount == -1) {
				System.out.println("RESET");
				continue;
			}
			if (control3.turnCount == -2) {
				System.out.println("치트키 발동! 다음판으로 넘어갑니다");
				level++;
				continue;
			}
			System.out.print("Stage " + (level + 1) + " 클리어!");
			System.out.println("   턴 수 : " + control3.turnCount + "\n");
			level++;
		}
		System.out.println("축하합니다!\n모든 게임을 클리어하셨습니다!");
	}

	private Control3 showMap(int level) {

		String[][] tempMap = saveTemp(encryptedMapList.get(level));
		System.out.println("Stage " + (level + 1) + " / " + encryptedMapList.size());
		ConvertMap3.getInstance().displayMap(tempMap);
		return new Control3(tempMap);
	}

	private String[][] saveTemp(String[][] encrypt) {
		String[][] temp = new String[encrypt.length][encrypt[0].length];
		for (int i = 0; i < encrypt.length; i++) {
			System.arraycopy(encrypt[i], 0, temp[i], 0, encrypt[0].length);
		}
		return temp;
	}

	private void readFromFile() {
		List<String> readFile = new ArrayList<>();
		try (BufferedReader input = new BufferedReader(new FileReader("src/cocoa/masters/Map.txt"))) {
			StringBuilder stringBuilder = new StringBuilder();
			String line = input.readLine();

			while (line != null) {
				stringBuilder.append(line);
				stringBuilder.append("\n");
				/* System.lineSeparator() 때문에.. 개행할 때 마다 \r\n 가 추가되고 있는거였다..
				메모장, IntelliJ, 또는 Git 문제인줄 알고 몇시간을 허비했다. */
				line = input.readLine();
			}
			readFile = Arrays.asList(stringBuilder.toString().split("\\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		createMapInfo(readFile);
	}

	private void createMapInfo(List<String> readFile) {
		ConvertMap3 convertMap3 = ConvertMap3.getInstance();
		List<String> stage;
		String[][] encryptTemp;
		int startIndex = 0;
		int breakLineIndex;
		for (int i = 0; i < readFile.size(); i++) {
			if (readFile.get(i).contains("=")) {
				breakLineIndex = i;
				stage = readFile.subList(startIndex, breakLineIndex);
				encryptTemp = convertMap3.encryptMap3(stage);
				encryptedMapList.add(encryptTemp);
				startIndex = breakLineIndex + 1;
			}
			if (i == (readFile.size() - 1)) {
				stage = readFile.subList(startIndex, readFile.size());
				encryptTemp = convertMap3.encryptMap3(stage);
				encryptedMapList.add(encryptTemp);
			}
		}
	}
}
