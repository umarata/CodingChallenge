package com.example.coding_challenge.domain

class InputValidatorHelper {
    fun validateSearchedText(query: String?): InputValidator {
        return if (query.isNullOrEmpty()) {
            InputValidator.EmptyInput
        } else if (query.trim().isEmpty()) {
            InputValidator.EmptyInput
        } else if (query.length >= 3) {
            InputValidator.ValidInput(sf = query)
        } else {
            InputValidator.SmallerTo3CharacterInput
        }
    }
}