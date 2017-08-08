package org.etan.portal.integration.datarequester.service;


import org.etan.portal.integration.datarequester.service.exception.DataRequestException;

import java.util.Map;

/**
 * DataRequesterService interface provides methods
 * for getting data, from different sources
 */
public interface DataRequesterService {
    /**
     * Get data from specified url. If process
     * gone wrong throws DataRequestException.
     *
     * @param url specified url
     * @return - StringBuilder, contains data that
     * was received by url
     * @throws DataRequestException - if some problems with connection
     *                              or wrong url specified
     *                              or problems while data reading
     */
    StringBuilder getDataFromUrl(String url) throws DataRequestException;

    /**
     * Get data from specified url with authorization.
     * If process gone wrong throws DataRequestException.
     *
     * @param url specified url
     * @return - StringBuilder, contains data that
     * was received by url
     * @throws DataRequestException - if some problems with connection
     *                              or wrong url specified
     *                              or problems while data reading
     */
    StringBuilder getDataFromUrlWithAuthorization(
            String url, String username, String password) throws DataRequestException;

    /**
     * Post json data to specified url with authorization.
     * If process gone wrong throws DataRequestException.
     *
     * @param url specified url
     * @return - StringBuilder, response
     * @throws DataRequestException - if some problems with connection
     *                              or wrong url specified
     *                              or problems while data reading
     */
    StringBuilder postJsonToUrlWithAuthorization(String url, String username, String password,
                                                 String jsonContent) throws DataRequestException;

    /**
     * Post json data to specified url with authorization.
     * If process gone wrong throws DataRequestException.
     *
     * @param url specified url
     * @return - StringBuilder, response
     * @throws DataRequestException - if some problems with connection
     *                              or wrong url specified
     *                              or problems while data reading
     */
    StringBuilder postParametersToUrlWithAuthorization(String url,
                                                       String username, String password,
                                                       Map<String, String> parametersMap) throws DataRequestException;


    /**
     * Simple post request to external service with some parameters in body.
     * If process gone wrong throws DataRequestException.
     *
     * @param url           specified url
     * @param parametersMap optional (may be null)
     * @return - String, response body
     * @throws DataRequestException     - if some problems with connection
     *                                  or wrong url specified
     *                                  or problems while data reading
     * @throws IllegalArgumentException if url is null
     */
    String post(String url,
                Map<String, String> parametersMap) throws DataRequestException;
}
