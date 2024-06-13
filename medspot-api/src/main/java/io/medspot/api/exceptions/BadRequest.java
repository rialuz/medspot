package io.medspot.api.exceptions;

/**
 * A custom exception format for 400 Bad Request errors.
 */
public class BadRequest extends RuntimeException {

  /**
   * Constructor for the custom 400 exception.
   */
  public BadRequest() {
  }

  /**
   * Throws a customized 400 error with the provided message.
   */
  public BadRequest(String message) {
    super(message);
  }

}
