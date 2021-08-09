import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String BLANK_SPACE = "\\s+";

    public String getResult(String sentence) {


        if (sentence.split(BLANK_SPACE).length == 1) {
            return sentence + " 1";
        } else {

            try {
                List<WordInfo> wordInfoList = calculateWordAndCount(sentence);

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

    private List<WordInfo> calculateWordAndCount(String sentence){
        List<WordInfo> wordInfos  = new ArrayList<>();
        List<String> words = Arrays.asList(sentence.split(BLANK_SPACE));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());

        distinctWords.forEach(distinctWord -> {
            int count = (int) words.stream().filter(word -> word.equals(distinctWord)).count();
            wordInfos.add(new WordInfo(distinctWord, count));
        });

        return wordInfos;
    }
}
