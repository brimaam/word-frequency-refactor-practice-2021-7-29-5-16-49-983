import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String BLANK_SPACE = "\\s+";
    public static final String NEW_LINE = "\n";
    public static final String ERROR_MESSAGE = "Calculate Error";

    public String generateWordFrequency(String sentence) {
        try {
            List<WordInfo> wordInfoList = calculateWordAndCount(sentence);

            sortWordInfoList(wordInfoList);

            return combineWordInfoListToAString(wordInfoList);
        } catch (Exception e) {
            return ERROR_MESSAGE;
        }
    }

    private void sortWordInfoList(List<WordInfo> wordInfos) {
        wordInfos.sort((wordOne, wordTwo) -> wordTwo.getWordCount() - wordOne.getWordCount());
    }

    private String combineWordInfoListToAString(List<WordInfo> wordInfos) {
        StringJoiner joiner = new StringJoiner(NEW_LINE);
        wordInfos.forEach(wordInfo -> joiner.add(wordInfo.getWord() + " " + wordInfo.getWordCount()));
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
