import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    public static final String BLANK_SPACE = "\\s+";

    public String getResult(String sentence) {


        if (sentence.split(BLANK_SPACE).length == 1) {
            return sentence + " 1";
        } else {

            try {
                List<WordInfo> wordInfoList = getWordAndCount(sentence);

                wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo wordInfo : wordInfoList) {
                    String s = wordInfo.getWord() + " " + wordInfo.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private List<WordInfo> getWordAndCount(String sentence) {
        //split the input string with 1 to n pieces of spaces
        String[] arr = sentence.split(BLANK_SPACE);

        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String s : arr) {
            WordInfo wordInfo = new WordInfo(s, 1);
            wordInfoList.add(wordInfo);
        }

        //get the map for the next step of sizing the same word
        Map<String, List<WordInfo>> map = getListMap(wordInfoList);

        List<WordInfo> list = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()) {
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            list.add(wordInfo);
        }
        return list;
    }


    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordInfo.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getWord(), arr);
            } else {
                map.get(wordInfo.getWord()).add(wordInfo);
            }
        }


        return map;
    }


}
