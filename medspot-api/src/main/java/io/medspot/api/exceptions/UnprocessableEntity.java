package io.medspot.api.exceptions;

/**
 * A custom exception format for 422 Bad Request errors.
 */
public class UnprocessableEntity extends RuntimeException {

  /**
   * Constructor for the custom 422 exception.
   */
  public UnprocessableEntity() {
  }

  /**
   * Throws a customized 422 error with the provided message.
   */
  public UnprocessableEntity(String message) {
    super(message);
  }

}
