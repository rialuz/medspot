package io.medspot.api.exceptions;

/**
 * A custom exception format for 404 Resource Not Found errors.
 */
public class ResourceNotFound extends RuntimeException {

  /**
   * Constructor for the custom 404 exception.
   */
  public ResourceNotFound() {
  }

  /**
   * Throws a customized 404 error with the provided message.
   */
  public ResourceNotFound(String message) {
    super(message);
  }
}
