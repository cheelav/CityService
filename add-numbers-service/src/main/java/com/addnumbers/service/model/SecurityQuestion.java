package com.addnumbers.service.model;

import java.util.List;

public class SecurityQuestion {
    private final String questionText;

    private final List<Integer> numbers;

    private final int answer;

	public SecurityQuestion(String questionText, List<Integer> numbers, int answer) {
		super();
		this.questionText = questionText;
		this.numbers = numbers;
		this.answer = answer;
	}

	public String getQuestionText() {
		return questionText;
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	public int getAnswer() {
		return answer;
	}
    
    
}
