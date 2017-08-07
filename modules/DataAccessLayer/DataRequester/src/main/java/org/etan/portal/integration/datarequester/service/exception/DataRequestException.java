package org.etan.portal.integration.datarequester.service.exception;

/**
 * Signals that while getting data by http protocol
 * DataRequestException occurred.
 */
public class DataRequestException extends Exception {
    public DataRequestException(String message) {
       super(message);
    }

    public DataRequestException(String message, Throwable cause) {
        super(message, cause);
    }


}
