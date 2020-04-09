package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class IncorrectServiceImpl implements IncorrectService {

    @Autowired
    SimpleText simpleText;

    public HashMap<String, Object> printMessage() throws NullPointerException {
        return simpleText.simpleText("다음과 같은 명령어가 있습니다.\n" +
                "식단 관련 명령어\n" +
                "['아침', '점심', '저녁']\n" +
                "버스 관련 명령어\n" +
                "시작점과 도착점에 대한 정보를 공백을 두고 적어주세요\n" +
                "ex [한기대 야우리]");
    }
}
