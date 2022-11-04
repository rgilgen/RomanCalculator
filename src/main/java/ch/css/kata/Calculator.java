package ch.css.kata;

public class Calculator {

    private List<String> romanLetters =  Arrays.asList("I", "V");

    public String calculate(String romanNumber1, String romanNumber2) {
        String result = "";
        int index1 = romanLetters.indexOf(romanNumber1);
        int index2 = romanLetters.indexOf(romanNumber2);

        if (index1 > index2) {
            result = romanNumber1 + romanNumber2;
        } else {
            result = romanNumber2 + romanNumber1;
        }


        if (result.length() > 3) {
            return "IV";
        }
        return result;
    }


}
