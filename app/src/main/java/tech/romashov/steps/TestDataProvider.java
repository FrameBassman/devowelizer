package tech.romashov.steps;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class TestDataProvider {
    public List<String> getVowels() {
        return Arrays.asList("a", "e", "i", "o", "u");
    }
    public List<String> getConsonants() {
        return Arrays.asList("b", "c", "d", "f", "g",
            "h", "j", "k", "l", "m",
            "n", "p", "q", "r", "s",
            "t", "v", "w", "x", "y", "z");
    }
    public List<String> getAlphabet() {
        return Arrays.asList("a", "e", "i", "o", "u",
            "b", "c", "d", "f", "g",
            "h", "j", "k", "l", "m",
            "n", "p", "q", "r", "s",
            "t", "v", "w", "x", "y", "z");
    }

    public List<String> getNumbers() {
        return Arrays.asList("0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9");
    }

    public List<String> getVowelsCapitalLetters() {
        return Arrays.asList("A", "E", "I", "O", "U");
    }
    public List<String> getConsonantsCapitalLetters() {
        return Arrays.asList("B", "C", "D", "F", "G",
            "H", "J", "K", "L", "M",
            "N", "P", "Q", "R", "S",
            "T", "V", "W", "X", "Y", "Z");
    }
    public List<String> getAlphabetCapitalLetters() {
        return Arrays.asList("A", "E", "I", "O", "U",
            "B", "C", "D", "F", "G",
            "H", "J", "K", "L", "M",
            "N", "P", "Q", "R", "S",
            "T", "V", "W", "X", "Y", "Z");
    }

    public List<String> getCyrillicVowels() {
        return Arrays.asList("а", "е", "ё", "и", "о",
                "у", "ы", "э", "ю", "я");
    }

    public List<String> getCyrillicConsonants() {
        return Arrays.asList("б", "в", "г", "д", "ж",
                "з", "к", "л", "м", "н",
                "п", "р", "с", "т", "ф",
                "х", "ц", "ч", "ш", "щ", "ъ", "ь");
    }

    public List<String> getCyrillicAlphabet() {
        return Arrays.asList("а", "б", "в", "г", "д",
                "е", "ё", "ж", "з", "и",
                "к", "л", "м", "н", "о",
                "п", "р", "с", "т", "у",
                "ф", "х", "ц", "ч", "ш",
                "щ", "ъ", "ы", "ь", "э", "ю", "я");
    }

    public List<String> getSpecialSymbols() {
        return Arrays.asList("~", "!", "@", "#", "$");
    }
}
