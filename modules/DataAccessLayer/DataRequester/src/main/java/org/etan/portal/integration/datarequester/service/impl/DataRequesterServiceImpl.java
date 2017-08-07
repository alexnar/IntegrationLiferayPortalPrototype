package org.etan.portal.integration.datarequester.service.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.etan.portal.integration.datarequester.service.DataRequesterService;
import org.etan.portal.integration.datarequester.service.exception.DataHttpGetException;
import org.osgi.service.component.annotations.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

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


    private static final Log logger = LogFactoryUtil.getLog(DataRequesterServiceImpl.class);


    @Override
    public StringBuilder getDataFromUrl(String url) throws DataHttpGetException {
        if (url == null) {
            return new StringBuilder();
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        StringBuilder htmlResponse = new StringBuilder();

        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            HttpEntity httpEntity = response.getEntity();
            InputStream connectInputStream = httpEntity.getContent();
            InputStreamReader connectInputStreamReader = new InputStreamReader(connectInputStream);
            BufferedReader bufferedReader = new BufferedReader(connectInputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                htmlResponse.append(line);
            }
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
        return htmlResponse;
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
        StringBuilder htmlResponse = new StringBuilder();

        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            HttpEntity httpEntity = response.getEntity();
            InputStream connectInputStream = httpEntity.getContent();
            InputStreamReader connectInputStreamReader = new InputStreamReader(connectInputStream);
            BufferedReader bufferedReader = new BufferedReader(connectInputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                htmlResponse.append(line);
            }
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
        return htmlResponse;
    }
}
