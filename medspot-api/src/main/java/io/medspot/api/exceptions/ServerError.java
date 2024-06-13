package io.medspot.api.exceptions;

/**
 * A custom exception for internal service errors.
 */
public class ServerError extends RuntimeException {

  /**
   * Constructor for the custom 500 exception.
   */
  public ServerError() {
  }
  /**
   * Throws a customized 500 error with the provided message.
   * @param message
   */
  public ServerError(String message) {
    super(message);
  }
}
