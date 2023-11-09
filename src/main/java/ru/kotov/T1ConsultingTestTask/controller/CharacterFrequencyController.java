package ru.kotov.T1ConsultingTestTask.controller;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(examples = @ExampleObject(
                            value = "[{\"character\":\"a\",\"frequency\":4}," +
                                    "{\"character\":\"b\",\"frequency\":3}]"))),
            @ApiResponse(responseCode = "400", description = """
            All types of responses with a status of 400:
            
                The ignoreCase parameter must be true or false.
               
                The input string must not be empty.
            
                The length of the input string must be less than 5000 characters.""",
            content = @Content(examples = @ExampleObject("The ignoreCase parameter must be true or false")))
    })
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
