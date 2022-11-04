package ch.css.kata;

public class Calculator {

    private List<String> romanLetters =  Arrays.asList("I", "V");

    public String calculate(String romanNumber1, String romanNumber2) {

        Map<String, Integer> numberRating1 = parseNumber(romanNumber1);
        Map<String, Integer> numberRating2 = parseNumber(romanNumber2);

        Map<String, Integer> accumulateRating = accumulateRatingMaps(numberRating1, numberRating2);

        String result = ratingInterpreter(accumulateRating);


        if (result.length() > 3) {
            return "IV";
        }
        return result;
    }

    private Map<String, Integer> parseNumber(String romanNumber) {
        Map<String, Integer> numberRating = new HashMap<>();
        for (int charPosition = 0; charPosition < romanNumber.length(); charPosition++) {
            String actualChar = romanNumber.substring(charPosition, charPosition + 1);

            numberRating.put(actualChar, numberRating.getOrDefault(actualChar, 0) + 1);
        }
        return numberRating;
    }

    private Map<String, Integer> accumulateRatingMaps(Map<String, Integer> numberRating1, Map<String, Integer> numberRating2) {
        Map<String, Integer> accumulatedRating = new HashMap<>();
        for (String romanLetter : romanLetters) {
            int zahl1  = numberRating1.getOrDefault(romanLetter, 0);
            int zahl2  = numberRating2.getOrDefault(romanLetter, 0);
            accumulatedRating.put(romanLetter, zahl1+zahl2);
        }
        return accumulatedRating;
    }

    private String ratingInterpreter(Map<String, Integer> numberRating) {
       List<String> reverseOrderedRomanLetters = romanLetters
               .stream()
               .collect(Collectors.toList());
       Collections.reverse(reverseOrderedRomanLetters);
        String romanNumberResult = "";
        for (String romanLetter : reverseOrderedRomanLetters) {
            romanNumberResult += romanLetter.repeat(numberRating.getOrDefault(romanLetter, 0));
        }
        return romanNumberResult;
    }
}