package com.example.coding_challenge.domain

sealed class InputValidator {
    data class ValidInput(val sf: String) : InputValidator()
    object EmptyInput : InputValidator()
    object SmallerTo3CharacterInput : InputValidator()
}

