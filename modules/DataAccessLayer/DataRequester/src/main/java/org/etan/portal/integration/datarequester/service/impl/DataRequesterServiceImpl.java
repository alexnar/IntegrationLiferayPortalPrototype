package org.etan.portal.integration.datarequester.service.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.http.*;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.etan.portal.integration.datarequester.service.DataRequesterService;
import org.etan.portal.integration.datarequester.service.exception.DataRequestException;
import org.osgi.service.component.annotations.Component;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
    private static final String BAD_RESPONSE = "Bad response from server";
    private static final int OK_STATUS_CODE = 200;
    private static final int NO_CONTENT_STATUS_CODE = 204;


    private static final Log logger = LogFactoryUtil.getLog(DataRequesterServiceImpl.class);


    @Override
    public StringBuilder getDataFromUrl(String url) throws DataRequestException {
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
            throw new DataRequestException(GET_DATA_ERROR);
        } catch (ClientProtocolException e) {
            logger.warn(CONNECTION_PROBLEM_MESSAGE, e);
            throw new DataRequestException(GET_DATA_ERROR);
        } catch (IOException e) {
            logger.warn(READING_PAGE_PROBLEM_MESSAGE, e);
            throw new DataRequestException(GET_DATA_ERROR);
        }
        return httpResponse;
    }

    @Override
    public StringBuilder getDataFromUrlWithAuthorization(String url, String username,
                                                         String password) throws DataRequestException {

        if (url == null) {
            return new StringBuilder();
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        Header authenticate = null;
        UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials(username, password);
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
            throw new DataRequestException(GET_DATA_ERROR, e);
        } catch (ClientProtocolException e) {
            logger.warn(CONNECTION_PROBLEM_MESSAGE, e);
            throw new DataRequestException(GET_DATA_ERROR, e);
        } catch (IOException e) {
            logger.warn(READING_PAGE_PROBLEM_MESSAGE, e);
            throw new DataRequestException(GET_DATA_ERROR, e);
        }
        return httpResponse;
    }

    @Override
    public StringBuilder postJsonToUrlWithAuthorization(String url,
                                                        String username, String password,
                                                        String jsonContent) throws DataRequestException {
        if (url == null) {
            return new StringBuilder();
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        Header authenticate;
        UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials(username, password);
        try {
            authenticate = new BasicScheme().authenticate(usernamePasswordCredentials, httpPost, null);
        } catch (AuthenticationException e) {
            logger.warn(AUTHORIZATION_ERROR, e);
            throw new DataRequestException(AUTHORIZATION_ERROR, e);
        }
        httpPost.addHeader(authenticate);
        StringEntity jsonContentEntity = new StringEntity(jsonContent, ContentType.APPLICATION_JSON);
        httpPost.setEntity(jsonContentEntity);
        StringBuilder httpResponse;
        try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
            int statusCode = getStatusCode(response);
            if (statusCode != OK_STATUS_CODE && statusCode!= NO_CONTENT_STATUS_CODE) {
                throw new DataRequestException(BAD_RESPONSE);
            }
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity == null) {
                return new StringBuilder();
            }
            httpResponse = getResponseFromHttpEntity(httpEntity);
        } catch (UnknownHostException e) {
            logger.warn(WRONG_URL_MESSAGE, e);
            throw new DataRequestException(GET_DATA_ERROR, e);
        } catch (ClientProtocolException e) {
            logger.warn(CONNECTION_PROBLEM_MESSAGE, e);
            throw new DataRequestException(GET_DATA_ERROR, e);
        } catch (IOException e) {
            logger.warn(READING_PAGE_PROBLEM_MESSAGE, e);
            throw new DataRequestException(GET_DATA_ERROR, e);
        }
        return httpResponse;
    }

    @Override
    public StringBuilder postParametersToUrlWithAuthorization(String url, String username, String password,
                                                              Map<String, String> parametersMap)
            throws DataRequestException {

        if (url == null) {
            return new StringBuilder();
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        Header authenticate;
        UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials(username, password);
        try {
            authenticate = new BasicScheme().authenticate(usernamePasswordCredentials, httpPost, null);
        } catch (AuthenticationException e) {
            logger.warn(AUTHORIZATION_ERROR, e);
            throw new DataRequestException(AUTHORIZATION_ERROR, e);
        }
        httpPost.addHeader(authenticate);
        List<NameValuePair> parameters = getParametersByMap(parametersMap);
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(parameters, Consts.UTF_8);
        httpPost.setEntity(urlEncodedFormEntity);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "text/plain");
        StringBuilder httpResponse;
        try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
            int statusCode = getStatusCode(response);
            if (statusCode != OK_STATUS_CODE && statusCode!= NO_CONTENT_STATUS_CODE) {
                throw new DataRequestException(BAD_RESPONSE);
            }
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity == null) {
                return new StringBuilder();
            }
            httpResponse = getResponseFromHttpEntity(httpEntity);
        } catch (UnknownHostException e) {
            logger.warn(WRONG_URL_MESSAGE, e);
            throw new DataRequestException(GET_DATA_ERROR, e);
        } catch (ClientProtocolException e) {
            logger.warn(CONNECTION_PROBLEM_MESSAGE, e);
            throw new DataRequestException(GET_DATA_ERROR, e);
        } catch (IOException e) {
            logger.warn(READING_PAGE_PROBLEM_MESSAGE, e);
            throw new DataRequestException(GET_DATA_ERROR, e);
        }
        return httpResponse;
    }


    /**
     * Simple post request to external service with some parameters in body.
     * If process gone wrong throws DataRequestException. Fluent facade API uses.
     * <br/>
     * <br/>
     * <a href="https://hc.apache.org/httpcomponents-client-4.5.x/tutorial/html/fluent.html">
     * little examples of HttpClient fluent API
     * </a>
     * <br/>
     * .addHeader("X-Custom-header", "stuff") такое тоже есть
     * <br/>
     * .auth(new HttpHost("somehost"), "username", "password") такое тоже есть
     *
     * @param url           specified url
     * @param parametersMap optional (may be null)
     * @return - String, response body
     * @throws DataRequestException     - if some problems with connection
     *                                  or wrong url specified
     *                                  or problems while data reading
     * @throws IllegalArgumentException if url is null
     */
    @Override
    public String post(String url, Map<String, String> parametersMap)
            throws DataRequestException {

        if (url == null) {
            throw new IllegalArgumentException("url could not be null");
        }

        Request request = Request.Post(url);

        /* only for do custom httpClient, that can visit danger sites */
        Executor executor = Executor.newInstance(getCloseableHttpClient());

        if (parametersMap != null) {
            Form form = Form.form();
            for (Map.Entry<String, String> entry : parametersMap.entrySet()) {
                form.add(entry.getKey(), entry.getValue());
            }
            request.bodyForm(form.build());
        }

        try {
            String response = executor.execute(request).returnContent().asString();
            return response;
        } catch (ClientProtocolException e) {
            logger.error(CONNECTION_PROBLEM_MESSAGE, e);
            throw new DataRequestException(CONNECTION_PROBLEM_MESSAGE, e);
        } catch (IOException e) {
            logger.error(e, e);
            throw new DataRequestException(e.getMessage(), e);
        }
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

    /**
     * Get customized HttpClient.
     *
     * @return customized HttpClient
     * @throws DataRequestException if could create customized HttpClient
     */
    private CloseableHttpClient getCloseableHttpClient() throws DataRequestException {
        try {
            return createAcceptSelfSignedCertificateClient();
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            logger.error(e, e);
            throw new DataRequestException(e.getMessage(), e);
        }
    }


    /**
     * Create HttpClient, that can visit that can visit danger sites,
     * as GitLab on local server on https :)
     *
     * @return unsecured HttpClient
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     */
    private CloseableHttpClient createAcceptSelfSignedCertificateClient()
            throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

        // use the TrustSelfSignedStrategy to allow Self Signed Certificates
        SSLContext sslContext = SSLContextBuilder
                .create()
                .loadTrustMaterial(new TrustSelfSignedStrategy())
                .build();

        // we can optionally disable hostname verification.
        // if you don't want to further weaken the security, you don't have to include this.
        HostnameVerifier allowAllHosts = new NoopHostnameVerifier();

        // create an SSL Socket Factory to use the SSLContext with the trust self signed certificate strategy
        // and allow all hosts verifier.
        SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext, allowAllHosts);

        // finally create the HttpClient using HttpClient factory methods and assign the ssl socket factory
        return HttpClients
                .custom()
                .setSSLSocketFactory(connectionFactory)
                .build();
    }

    private int getStatusCode(CloseableHttpResponse response) {
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        return statusCode;
    }
}
