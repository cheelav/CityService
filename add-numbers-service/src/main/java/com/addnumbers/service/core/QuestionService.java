package com.addnumbers.service.core;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.context.annotation.Bean;

import com.addnumbers.service.model.SecurityQuestion;


public class QuestionService {

    private static final String QUESTION_TEXT = "Please add the numbers: ";
    private static final int MIN_NUMBERS_TO_SUM = 2;
    private static final int MAX_NUMBERS_TO_SUM = 3;

    public SecurityQuestion createQuestion() {
        StringBuilder text = new StringBuilder(QUESTION_TEXT);

        int numValues = getRandomIntegerInRange(MIN_NUMBERS_TO_SUM, MAX_NUMBERS_TO_SUM);
        List<Integer> values = new ArrayList<>(numValues);
        for (int i = 0; i < numValues; i++) {
            int n = getRandomIntegerInRange(1, 19);
            values.add(n);
            text.append(i > 0 ? "," : "");
            text.append(n);
        }

        return new SecurityQuestion(text.toString(), values, 0);
    }

    public boolean validateAnswer(SecurityQuestion question) {
        return question.getNumbers().stream()
                .mapToInt(Integer::intValue)
                .sum()
                == question.getAnswer();
    }

    private int getRandomIntegerInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
