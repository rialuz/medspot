package io.medspot.api.exceptions;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import io.medspot.api.constants.StringConstants;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * A controller advice allows you to use exactly the same exception handling techniques but apply
 * them across the whole application, not just to an individual controller. You can think of them as
 * an annotation driven interceptor. More info: https://www.baeldung.com/exception-handling-for-rest-with-spring
 *
 * <p>Handles exception responses for HTTP codes 400 (Bad Request), 404(Not Found), 409 (Conflict),
 * and 500(Server Error).
 */
@ControllerAdvice
public class ExceptionController {

  /** Logger object */
  private final Logger logger = LogManager.getLogger();

  /**
   * @param exception response thrown
   * @return string NOT_FOUND, date, and exception message
   */
  @ExceptionHandler(ResourceNotFound.class)
  protected ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFound exception) {
    ExceptionResponse response =
        new ExceptionResponse(StringConstants.NOT_FOUND, new Date(), exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  /**
   * @param exception response thrown
   * @return string BAD_REQUEST, date, and exception message
   */
  @ExceptionHandler(BadRequest.class)
  protected ResponseEntity<ExceptionResponse> badRequest(BadRequest exception) {
    ExceptionResponse response =
        new ExceptionResponse(StringConstants.BAD_REQUEST, new Date(), exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  /**
   * @param exception response thrown
   * @return string constant SERVER_ERROR, date, and exception message
   */
  @ExceptionHandler(ServerError.class)
  protected ResponseEntity<ExceptionResponse> serverError(ServerError exception) {
    ExceptionResponse response =
        new ExceptionResponse(StringConstants.SERVER_ERROR, new Date(), exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * @param exception response thrown
   * @return string constant UNPROCESSABLE_ENTITY, date, and exception message
   */
  @ExceptionHandler(UnprocessableEntity.class)
  protected ResponseEntity<ExceptionResponse> unprocessableEntity(UnprocessableEntity exception) {
    ExceptionResponse response =
        new ExceptionResponse(StringConstants.UNPROCESSABLE_ENTITY, new Date(), exception.getMessage());

    return new ResponseEntity<>(response, UNPROCESSABLE_ENTITY);
  }

  /**
   *@param exception response thrown
   * @return string SERVICE_UNAVAILABLE, date, and exception message
   */
  @ExceptionHandler(ServiceUnavailable.class)
  protected ResponseEntity<ExceptionResponse> ServiceUnavailable(ServiceUnavailable exception) {
    ExceptionResponse response =
        new ExceptionResponse(StringConstants.SERVICE_UNAVAILABLE, new Date(), exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
  }

  /**
   *@param exception response thrown
   * @return string CONFLICT, date, and exception message
   */
  @ExceptionHandler(Conflict.class)
  protected ResponseEntity<ExceptionResponse> Conflict(Conflict exception) {
    ExceptionResponse response =
        new ExceptionResponse(StringConstants.CONFLICT, new Date(), exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }

  /**
   * @param ex exception response.
   * @return the fields that caused the response as a string.
   */
  private String parseMessage(MethodArgumentNotValidException ex) {
    List<FieldError> errors = ex.getBindingResult().getFieldErrors();
    StringBuilder message = new StringBuilder();
    for (FieldError err : errors) {
      message.append(err.getDefaultMessage());
      message.append(" ");
    }
    return message.toString().trim();
  }
}
