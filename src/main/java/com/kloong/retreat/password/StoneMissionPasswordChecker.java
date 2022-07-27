package com.kloong.retreat.password;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class StoneMissionPasswordChecker {

    private final static String PASSWORD_DIR_PATH = "data/passwords";

    private final Map<String, String> missionPasswordMap;

    public StoneMissionPasswordChecker() {
        missionPasswordMap = new HashMap<>();
        initMissionPasswordMap();
    }

    public boolean checkPassword(String stone, int missionNumber, String password) {
        String mission = stone + missionNumber;

        if (!missionPasswordMap.containsKey(mission)) {
            System.out.println("Error! 존재하지 않는 미션입니다 - " + mission);
            return false;
        }
        return missionPasswordMap.get(mission).equals(password);
    }

    /*
    * 디렉토리 내의 모든 파일의 내용을 읽어서 map으로 변환한다.
    *
    * 파일 포맷
    * {stone}{number};{password}\n{stone}{number};{password}...
    *
    * ex)
    *   disciple1;1q2w
    *   disciple2;abcd123
    *   ...
    *
    * ";" 왼쪽이 key, ";" 오른쪽이 value 가 된다.
    */
    private void initMissionPasswordMap() {
        File passwordDir = new File(PASSWORD_DIR_PATH);

        File[] passwordFiles = passwordDir.listFiles();
        if (passwordFiles == null) {
            throw new RuntimeException("비밀번호 파일이 존재하지 않습니다. - " + passwordDir.getAbsolutePath());
        }

        for (File passwordFile : passwordFiles) {
            if (!passwordFile.getName().endsWith(".txt") || !passwordFile.canRead()) {
                continue;
            }

            try (FileReader fr = new FileReader(passwordFile);
                 BufferedReader br = new BufferedReader(fr)) {

                String line;
                while ((line = br.readLine()) != null) {
                    String[] missionAndPassword = line.split(";");
                    missionPasswordMap.put(missionAndPassword[0], missionAndPassword[1]);
                }
            } catch (IOException e) {
                throw new RuntimeException("잘못된 비밀번호 파일입니다. - " + passwordFile.getAbsolutePath());
            }
        }
    }
}
