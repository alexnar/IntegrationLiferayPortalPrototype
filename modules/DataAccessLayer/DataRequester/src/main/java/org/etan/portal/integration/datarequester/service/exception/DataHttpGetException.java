package org.etan.portal.integration.datarequester.service.exception;

/**
 * Signals that while getting data by http protocol
 * DataHttpGetException occurred.
 */
public class DataHttpGetException extends Exception {
    public DataHttpGetException(String message) {
       super(message);
    }

    public DataHttpGetException(String message, Throwable cause) {
        super(message, cause);
    }


}
