package com.github.korenevaS.lesson5.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilTest {
    @DisplayName("Checking the correct name.")
    @Test
    void isNameCorrect() {
        String nameForComparison = "Эмма";
        assertTrue(ValidationUtil.isNameCorrect(nameForComparison));
    }

    @DisplayName("Checking a name with space.")
    @Test
    void isNameWithSpaceNotCorrect() {
        String nameForComparison = "Эмм а";
        assertFalse(ValidationUtil.isNameCorrect(nameForComparison));
    }

    @DisplayName("Checking a name with number.")
    @Test
    void isNameWithNumberNotCorrect() {
        String nameForComparison = "Эмма1";
        assertFalse(ValidationUtil.isNameCorrect(nameForComparison));
    }

    @DisplayName("Checking a name with symbol.")
    @Test
    void isNameWithSymbolNotCorrect() {
        String nameForComparison = "Эмма.";
        assertFalse(ValidationUtil.isNameCorrect(nameForComparison));
    }

    @DisplayName("Checking the correct number.")
    @Test
    void isPhoneCorrect() {
        String phoneForComparison = "+79991234567";
        assertTrue(ValidationUtil.isPhoneCorrect(phoneForComparison));
    }

    @DisplayName("Checking a number with the wrong number of digits.")
    @Test
    void checkingWrongNumber() {
        String phoneForComparison1 = "+7999123456";
        String phoneForComparison2 = "+799912345678";
        assertFalse(ValidationUtil.isPhoneCorrect(phoneForComparison1));
        assertFalse(ValidationUtil.isPhoneCorrect(phoneForComparison2));
    }

    @DisplayName("Checking number with letters.")
    @Test
    void checkingPhoneWithLetters() {
        String phoneForComparison = "+7999123f4567";
        assertFalse(ValidationUtil.isPhoneCorrect(phoneForComparison));
    }

    @DisplayName("Checking number without plus.")
    @Test
    void checkingPhoneWithoutPlus() {
        String phoneForComparison = "79991234567";
        assertFalse(ValidationUtil.isPhoneCorrect(phoneForComparison));
    }

}