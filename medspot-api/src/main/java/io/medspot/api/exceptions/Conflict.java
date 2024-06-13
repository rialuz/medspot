package io.medspot.api.exceptions;
/**
 * A custom exception for conflict errors.
 */
public class Conflict extends RuntimeException {

    /**
     * Constructor for the custom 409 exception.
     */
    public Conflict() {
    }
    /**
     * Throws a customized 409 error with the provided message.
     * @param message
     */
    public Conflict(String message) {
      super(message);
    }
  }
