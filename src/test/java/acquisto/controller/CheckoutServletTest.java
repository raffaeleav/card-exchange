package acquisto.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CheckoutServletTest {
    @Mock
    private Matcher matcher;
    private CheckoutServlet checkoutServlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        checkoutServlet = new CheckoutServlet();
    }

    @Test
    public void testIsAlphabet_ShouldReturnTrue_WhenStringContainsOnlyAlphabets() {
        // Given
        String str = "Hello World";
        when(matcher.matches()).thenReturn(true);

        // When
        boolean result = checkoutServlet.isAlphabet(str);

        // Then
        assertTrue(result);
    }

    @Test
    public void testIsAlphabet_ShouldReturnFalse_WhenStringContainsNonAlphabets() {
        // Given
        String str = "Hello World 123";
        when(matcher.matches()).thenReturn(false);

        // When
        boolean result = checkoutServlet.isAlphabet(str);

        // Then
        assertFalse(result);
    }

    @Test
    public void testIsAlphabet_ShouldReturnFalse_WhenStringContainsNonAlphabetsAndSymbols() {
        // Given
        String str = "Hello World 123!@#";
        when(matcher.matches()).thenReturn(false);

        // When
        boolean result = checkoutServlet.isAlphabet(str);

        // Then
        assertFalse(result);
    }

    @Test
    public void testIsAlphabet_ShouldReturnFalse_WhenStringIsEmpty() {
        // Given
        String str = "";
        when(matcher.matches()).thenReturn(false);

        // When
        boolean result = checkoutServlet.isAlphabet(str);

        // Then
        assertFalse(result);
    }

    @Test
    public void testIsAlphabet_ShouldReturnFalse_WhenStringContainsOnlySpaces() {
        // Given
        String str = " ";
        when(matcher.matches()).thenReturn(false);

        // When
        boolean result = checkoutServlet.isAlphabet(str);

        // Then
        assertFalse(result);
    }

}