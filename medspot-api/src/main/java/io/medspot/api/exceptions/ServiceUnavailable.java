package io.medspot.api.exceptions;

/**
 * A custom exception for 503 service unavailable errors.
 */
public class ServiceUnavailable extends RuntimeException{

  /**
   * Constructor for the custom 503 exception.
   */
  public ServiceUnavailable() {
  }

  /**
   * Throws a customized 503 error with the provided message.
   * @param message
   */
  public ServiceUnavailable(String message) {
    super(message);
  }
}
