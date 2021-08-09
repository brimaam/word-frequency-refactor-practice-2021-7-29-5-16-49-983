import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String BLANK_SPACE = "\\s+";
    public static final int ONE_WORD = 1;

    public String getResult(String sentence) {

        if (sentence.split(BLANK_SPACE).length == ONE_WORD) {
            return sentence + " " + ONE_WORD;
        }

        try {
            List<WordInfo> wordInfoList = calculateWordAndCount(sentence);

            sortWordInfoList(wordInfoList);

            return combineWordInfoListToAString(wordInfoList);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private void sortWordInfoList(List<WordInfo> wordInfoList) {
       wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
    }

    private String combineWordInfoListToAString(List<WordInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner("\n");
        wordInfoList.forEach(wordInfo -> {
            joiner.add(wordInfo.getWord() + " " + wordInfo.getWordCount());
        });
        return joiner.toString();
    }

    private List<WordInfo> calculateWordAndCount(String sentence) {
        List<WordInfo> wordInfos = new ArrayList<>();
        List<String> words = Arrays.asList(sentence.split(BLANK_SPACE));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());

        distinctWords.forEach(distinctWord -> {
            int count = (int) words.stream().filter(word -> word.equals(distinctWord)).count();
            wordInfos.add(new WordInfo(distinctWord, count));
        });

        return wordInfos;
    }
}
