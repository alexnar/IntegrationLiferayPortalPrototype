package org.etan.portal.integration.datarequester.service.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.http.*;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.etan.portal.integration.datarequester.service.DataRequesterService;
import org.etan.portal.integration.datarequester.service.exception.DataHttpGetException;
import org.osgi.service.component.annotations.Component;

import java.io.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DataRequesterService implementation.
 */
@Component(
        name = "DataRequesterServiceImpl",
        immediate = true,
        service = DataRequesterService.class
)
public class DataRequesterServiceImpl implements DataRequesterService {

    private static final String WRONG_URL_MESSAGE = "Probably you specify wrong URL";
    private static final String CONNECTION_PROBLEM_MESSAGE = "Probably you have problems with your connection";
    private static final String READING_PAGE_PROBLEM_MESSAGE = "Problems while reading page html";
    private static final String GET_DATA_ERROR = "Error getting page html";
    private static final String AUTHORIZATION_ERROR = "Authorization failed";
    private static final String CANNOT_ENCODE_PARAMETERS_ERROR = "Cannot encode parameters";


    private static final Log logger = LogFactoryUtil.getLog(DataRequesterServiceImpl.class);



    @Override
    public StringBuilder getDataFromUrl(String url) throws DataHttpGetException {
        if (url == null) {
            return new StringBuilder();
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        StringBuilder httpResponse;

        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            HttpEntity httpEntity = response.getEntity();
            httpResponse = getResponseFromHttpEntity(httpEntity);
        } catch (UnknownHostException e) {
            logger.warn(WRONG_URL_MESSAGE, e);
            throw new DataHttpGetException(GET_DATA_ERROR);
        } catch (ClientProtocolException e) {
            logger.warn(CONNECTION_PROBLEM_MESSAGE, e);
            throw new DataHttpGetException(GET_DATA_ERROR);
        } catch (IOException e) {
            logger.warn(READING_PAGE_PROBLEM_MESSAGE, e);
            throw new DataHttpGetException(GET_DATA_ERROR);
        }
        return httpResponse;
    }

    @Override
    public StringBuilder getDataFromUrlWithAuthorization(
            String url, UsernamePasswordCredentials usernamePasswordCredentials) throws DataHttpGetException {

        if (url == null) {
            return new StringBuilder();
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        Header authenticate = null;
        try {
            authenticate = new BasicScheme().authenticate(usernamePasswordCredentials, httpGet, null);
        } catch (AuthenticationException e) {
            logger.warn(AUTHORIZATION_ERROR, e);
        }
        if (authenticate != null) {
            httpGet.addHeader(authenticate);
        }
        StringBuilder httpResponse;

        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity == null) {
                return new StringBuilder();
            }
            httpResponse = getResponseFromHttpEntity(httpEntity);
        } catch (UnknownHostException e) {
            logger.warn(WRONG_URL_MESSAGE, e);
            throw new DataHttpGetException(GET_DATA_ERROR, e);
        } catch (ClientProtocolException e) {
            logger.warn(CONNECTION_PROBLEM_MESSAGE, e);
            throw new DataHttpGetException(GET_DATA_ERROR, e);
        } catch (IOException e) {
            logger.warn(READING_PAGE_PROBLEM_MESSAGE, e);
            throw new DataHttpGetException(GET_DATA_ERROR, e);
        }
        return httpResponse;
    }

    @Override
    public StringBuilder postJsonToUrlWithAuthorization(String url,
                                                        UsernamePasswordCredentials usernamePasswordCredentials,
                                                        String jsonContent) throws DataHttpGetException {
        if (url == null) {
            return new StringBuilder();
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        Header authenticate;
        try {
            authenticate = new BasicScheme().authenticate(usernamePasswordCredentials, httpPost, null);
        } catch (AuthenticationException e) {
            logger.warn(AUTHORIZATION_ERROR, e);
            throw new DataHttpGetException(AUTHORIZATION_ERROR, e);
        }
        httpPost.addHeader(authenticate);
        StringEntity jsonContentEntity = new StringEntity(jsonContent, ContentType.APPLICATION_JSON);
        httpPost.setEntity(jsonContentEntity);
        StringBuilder httpResponse;
        try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity == null) {
                return new StringBuilder();
            }
            httpResponse = getResponseFromHttpEntity(httpEntity);
        } catch (UnknownHostException e) {
            logger.warn(WRONG_URL_MESSAGE, e);
            throw new DataHttpGetException(GET_DATA_ERROR, e);
        } catch (ClientProtocolException e) {
            logger.warn(CONNECTION_PROBLEM_MESSAGE, e);
            throw new DataHttpGetException(GET_DATA_ERROR, e);
        } catch (IOException e) {
            logger.warn(READING_PAGE_PROBLEM_MESSAGE, e);
            throw new DataHttpGetException(GET_DATA_ERROR, e);
        }
        return httpResponse;
    }

    @Override
    public StringBuilder postParametersToUrlWithAuthorization(
            String url, UsernamePasswordCredentials usernamePasswordCredentials,
            Map<String, String> parametersMap) throws DataHttpGetException {

        if (url == null) {
            return new StringBuilder();
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        Header authenticate;
        try {
            authenticate = new BasicScheme().authenticate(usernamePasswordCredentials, httpPost, null);
        } catch (AuthenticationException e) {
            logger.warn(AUTHORIZATION_ERROR, e);
            throw new DataHttpGetException(AUTHORIZATION_ERROR, e);
        }
        httpPost.addHeader(authenticate);
        List<NameValuePair> parameters = getParametersByMap(parametersMap);
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(parameters, Consts.UTF_8);
        httpPost.setEntity(urlEncodedFormEntity);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "text/plain");
        StringBuilder httpResponse;
        try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity == null) {
                return new StringBuilder();
            }
            httpResponse = getResponseFromHttpEntity(httpEntity);
        } catch (UnknownHostException e) {
            logger.warn(WRONG_URL_MESSAGE, e);
            throw new DataHttpGetException(GET_DATA_ERROR, e);
        } catch (ClientProtocolException e) {
            logger.warn(CONNECTION_PROBLEM_MESSAGE, e);
            throw new DataHttpGetException(GET_DATA_ERROR, e);
        } catch (IOException e) {
            logger.warn(READING_PAGE_PROBLEM_MESSAGE, e);
            throw new DataHttpGetException(GET_DATA_ERROR, e);
        }
        return httpResponse;
    }

    private StringBuilder getResponseFromHttpEntity(HttpEntity httpEntity) throws IOException {
        StringBuilder httpResponse = new StringBuilder();
        InputStream connectInputStream = httpEntity.getContent();
        InputStreamReader connectInputStreamReader = new InputStreamReader(connectInputStream);
        BufferedReader bufferedReader = new BufferedReader(connectInputStreamReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            httpResponse.append(line);
        }
        return httpResponse;
    }

    private List<NameValuePair> getParametersByMap(Map<String, String> parametersMap) {
        List<NameValuePair> parametersList = new ArrayList<>();
        for (Map.Entry<String, String> parameterEntry : parametersMap.entrySet()) {
            String parameterEntryKey = parameterEntry.getKey();
            String parameterEntryValue = parameterEntry.getValue();
            BasicNameValuePair basicNameValuePair = new BasicNameValuePair(parameterEntryKey, parameterEntryValue);
            parametersList.add(basicNameValuePair);
        }
        return parametersList;
    }

}
