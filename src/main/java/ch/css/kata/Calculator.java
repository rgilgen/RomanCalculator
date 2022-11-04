package ch.css.kata;

import java.util.*;
import java.util.stream.Collectors;

public class Calculator {

    private final List<String> romanLetters = Arrays.asList("I", "V", "X", "L", "C", "D", "M");

    public String calculate(String romanNumber1, String romanNumber2) {

        Map<String, Integer> numberRating1 = parseNumber(romanNumber1);
        Map<String, Integer> numberRating2 = parseNumber(romanNumber2);

        Map<String, Integer> accumulateRating = accumulateRatingMaps(numberRating1, numberRating2);

        String result = ratingInterpreter(accumulateRating);

        return result;
    }

    private Map<String, Integer> parseNumber(String romanNumber) {
        Map<String, Integer> numberRating = new HashMap<>();
        for (int charPosition = 0; charPosition < romanNumber.length(); charPosition++) {
            String actualChar = romanNumber.substring(charPosition, charPosition + 1);
            int addCounter = 1;
            if (hasMajorLetterFollowing(actualChar, romanNumber.substring(charPosition + 1))) {
                addCounter = -1;
            }
            numberRating.put(actualChar, numberRating.getOrDefault(actualChar, 0) + addCounter);
        }
        return numberRating;
    }

    private boolean hasMajorLetterFollowing(String currentChar, String siblingString) {
        if (siblingString.isBlank()) {
            return false;
        }
        String siblingChar = siblingString.substring(0, 1);
        int indexOfCurrentChar = romanLetters.indexOf(currentChar);
        int indexOfSiblingChar = romanLetters.indexOf(siblingChar);

        return indexOfCurrentChar < indexOfSiblingChar;
    }

    private Map<String, Integer> accumulateRatingMaps(Map<String, Integer> numberRating1, Map<String, Integer> numberRating2) {
        Map<String, Integer> accumulatedRating = new HashMap<>();
        int uebertrag = 0;
        for (String romanLetter : romanLetters) {
            int letterCounter1 = numberRating1.getOrDefault(romanLetter, 0) + uebertrag;
            int letterCounter2 = numberRating2.getOrDefault(romanLetter, 0);
            uebertrag = 0;
            int maxRepeatableLetter = evaluateRepetition(romanLetter);
            int overflowRemoval = evaluateOverflow(maxRepeatableLetter);
            if (letterCounter1 + letterCounter2 > maxRepeatableLetter) {
                uebertrag++;
                accumulatedRating.put(romanLetter, letterCounter1 + letterCounter2 - overflowRemoval);
            } else {
                accumulatedRating.put(romanLetter, letterCounter1 + letterCounter2);
            }
        }
        return accumulatedRating;
    }

    private int evaluateOverflow(int maxRepeatableLetter) {
        return maxRepeatableLetter == 3 ? 5 : 2;
    }

    private int evaluateRepetition(String romanLetter) {
        int index = romanLetters.indexOf(romanLetter);
        return index % 2 == 0 ? 3 : 1;
    }

    private String ratingInterpreter(Map<String, Integer> numberRating) {
        List<String> reverseOrderedRomanLetters = romanLetters
                .stream()
                .collect(Collectors.toList());
        Collections.reverse(reverseOrderedRomanLetters);
        String romanNumberResult = "";

        for (String romanLetter : romanLetters) {
            int letterCounter = numberRating.getOrDefault(romanLetter, 0);
            if (letterCounter < 0) {
                romanNumberResult += romanLetter.repeat(letterCounter * -1);
            }
        }

        for (String romanLetter : reverseOrderedRomanLetters) {
            int letterCounter = numberRating.getOrDefault(romanLetter, 0);
            if (letterCounter > 0) {
                romanNumberResult += romanLetter.repeat(letterCounter);
            }
        }
        return romanNumberResult;
    }
}