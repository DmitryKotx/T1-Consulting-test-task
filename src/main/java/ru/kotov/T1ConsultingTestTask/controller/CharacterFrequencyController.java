package ru.kotov.T1ConsultingTestTask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotov.T1ConsultingTestTask.model.CharacterFrequency;
import ru.kotov.T1ConsultingTestTask.exception.InvalidInputStringFormatException;
import ru.kotov.T1ConsultingTestTask.exception.InvalidParameterFormatException;
import ru.kotov.T1ConsultingTestTask.service.CharacterFrequencyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class CharacterFrequencyController {

    private final CharacterFrequencyService service;
    @GetMapping("/character-frequencies")
    public ResponseEntity<List<CharacterFrequency>> getCharacterFrequencies(
            @RequestParam("inputString") String inputString,
            @RequestParam(name = "ignoreCase", required = false, defaultValue = "false") String ignoreCase)
            throws InvalidParameterFormatException, InvalidInputStringFormatException {

        List<CharacterFrequency> characterFrequencies =
                service.getCharacterFrequencies(inputString, ignoreCase);
        return ResponseEntity.ok(characterFrequencies);
    }

    @ExceptionHandler({InvalidParameterFormatException.class, InvalidInputStringFormatException.class})
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
