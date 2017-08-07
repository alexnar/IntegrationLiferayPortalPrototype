package org.etan.portal.integration.datarequester.service;


import org.apache.http.auth.UsernamePasswordCredentials;
import org.etan.portal.integration.datarequester.service.exception.DataHttpGetException;

import java.util.Map;

/**
 * DataRequesterService interface provides methods
 * for getting data, from different sources
 */
public interface DataRequesterService {
    /**
     * Get data from specified url. If process
     * gone wrong throws DataHttpGetException.
     *
     * @param url specified url
     * @return - StringBuilder, contains data that
     * was received by url
     * @throws DataHttpGetException - if some problems with connection
     *                              or wrong url specified
     *                              or problems while data reading
     */
    StringBuilder getDataFromUrl(String url) throws DataHttpGetException;

    /**
     * Get data from specified url with authorization.
     * If process gone wrong throws DataHttpGetException.
     *
     * @param url specified url
     * @return - StringBuilder, contains data that
     * was received by url
     * @throws DataHttpGetException - if some problems with connection
     *                              or wrong url specified
     *                              or problems while data reading
     */
    StringBuilder getDataFromUrlWithAuthorization(
            String url, UsernamePasswordCredentials usernamePasswordCredentials) throws DataHttpGetException;

    /**
     * Post json data to specified url with authorization.
     * If process gone wrong throws DataHttpGetException.
     *
     * @param url specified url
     * @return - StringBuilder, response
     * @throws DataHttpGetException - if some problems with connection
     *                              or wrong url specified
     *                              or problems while data reading
     */
    StringBuilder postJsonToUrlWithAuthorization(String url, UsernamePasswordCredentials usernamePasswordCredentials,
                                                 String jsonContent) throws DataHttpGetException;

    /**
     * Post json data to specified url with authorization.
     * If process gone wrong throws DataHttpGetException.
     *
     * @param url specified url
     * @return - StringBuilder, response
     * @throws DataHttpGetException - if some problems with connection
     *                              or wrong url specified
     *                              or problems while data reading
     */
    StringBuilder postParametersToUrlWithAuthorization(String url,
                                                       UsernamePasswordCredentials usernamePasswordCredentials,
                                                       Map<String, String> parametersMa) throws DataHttpGetException;
}
