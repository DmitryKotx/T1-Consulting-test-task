package ru.kotov.T1ConsultingTestTask.service;

import org.springframework.stereotype.Service;
import ru.kotov.T1ConsultingTestTask.model.CharacterFrequency;
import ru.kotov.T1ConsultingTestTask.exception.InvalidInputStringFormatException;
import ru.kotov.T1ConsultingTestTask.exception.InvalidParameterFormatException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CharacterFrequencyService {
    public List<CharacterFrequency> getCharacterFrequencies(String inputString, String ignoreCase)
            throws InvalidParameterFormatException, InvalidInputStringFormatException {
        validate(inputString);
        if(ignoreCase.equals("false")) {
            return getCharacterFrequencies(inputString);
        } else if (ignoreCase.equals("true")) {
            return getCharacterFrequenciesIgnoreCase(inputString);
        } else {
            throw new InvalidParameterFormatException("The ignoreCase parameter must be true or false");
        }
    }

    public List<CharacterFrequency> getCharacterFrequenciesIgnoreCase(String inputString) {
        char[] chars = inputString.toCharArray();
        Map<Character,Integer> characterFrequency = new HashMap<>();
        for (char c : chars) {
            char lowerChar = Character.toLowerCase(c);
            if(characterFrequency.containsKey(lowerChar)) {
                characterFrequency.put(lowerChar, characterFrequency.get(lowerChar) + 1);
            } else  {
                characterFrequency.put(lowerChar, 1);
            }
        }
        return mapToList(characterFrequency);
    }
    public List<CharacterFrequency> getCharacterFrequencies(String inputString) {
        char[] chars = inputString.toCharArray();
        Map<Character,Integer> characterFrequency = new HashMap<>();
        for (char c : chars) {
            characterFrequency.put(c, characterFrequency.getOrDefault(c,0) + 1);
        }
        return mapToList(characterFrequency);
    }

    public List<CharacterFrequency> mapToList(Map<Character,Integer> characterFrequency) {
        return characterFrequency.entrySet().stream()
                .map(e -> new CharacterFrequency(e.getKey(), e.getValue()))
                .sorted((o1, o2) -> (o1.getFrequency()-o2.getFrequency())*-1)
                .toList();
    }

    public void validate (String inputString) throws InvalidInputStringFormatException {
        if(inputString.isEmpty()) {
            throw new InvalidInputStringFormatException("The input string must not be empty" +
                    " and must not contain the following characters: [, ], {, }, ^, |");
        }
        if(inputString.length() > 10000) {
            throw new InvalidInputStringFormatException("The length of the input string should be" +
                    " no more than 10000 characters");
        }
    }
}
