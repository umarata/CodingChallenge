package com.example.coding_challenge.presentation

import com.example.coding_challenge.domain.InputValidator
import com.example.coding_challenge.domain.InputValidatorHelper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ValidateSearchedTextTest {

    private lateinit var inputValidatorHelper: InputValidatorHelper

    @Before
    fun setUp() {
        inputValidatorHelper = InputValidatorHelper()
    }

    @Test
    fun validateSearchedText_nullInput_EmptyInput() {
        val result = inputValidatorHelper.validateSearchedText(null)
        assertEquals(result, InputValidator.EmptyInput)
    }

    @Test
    fun validateSearchedText_emptyString_EmptyInput() {
        val result = inputValidatorHelper.validateSearchedText("")
        assertEquals(result, InputValidator.EmptyInput)
    }

    @Test
    fun validateSearchedText_whitespace_EmptyInput() {
        val result = inputValidatorHelper.validateSearchedText(" ")
        assertEquals(result, InputValidator.EmptyInput)
    }

    @Test
    fun validateSearchedText_3Characters_ValidInput() {
        val result = inputValidatorHelper.validateSearchedText("hmm")
        assertEquals(result, InputValidator.ValidInput("hmm"))
    }

    @Test
    fun validateSearchedText_SmallerTo3CharacterInput_SmallerTo3CharacterInput() {
        val result = inputValidatorHelper.validateSearchedText("hm")
        assertEquals(result, InputValidator.SmallerTo3CharacterInput)
    }


}