package io.medspot.api.validation;

import java.util.regex.Pattern;

/**
 * Provides methods for Regex pattern matching
 */
public class RegexMatcher {

  /**
   * Tests if the regular expression exactly matches the input text. Equivalent to adding ^ and $
   * for specifying matching at the start and end of the input.
   *
   * @param textToCheck - The String text to check against the regular expression.
   * @param pattern     - The pattern of the regular expression to test with, in String format.
   * @return - A boolean value of whether the pattern exactly matches the input.
   */
  public boolean exactMatch(String textToCheck, String pattern) {
    return Pattern.compile(pattern)
        .matcher(textToCheck)
        .matches();
  }

  /**
   * Tests if the regular expression pattern is found anywhere inside the input text.
   *
   * @param textToCheck - The String text to check against the regular expression.
   * @param pattern     - The pattern of the regular expression to test with, in String format.
   * @return - A boolean value of whether the pattern is found anywhere inside the input.
   */
  public boolean containsMatch(String textToCheck, String pattern) {
    return Pattern.compile(pattern)
        .matcher(textToCheck)
        .find();
  }
}
