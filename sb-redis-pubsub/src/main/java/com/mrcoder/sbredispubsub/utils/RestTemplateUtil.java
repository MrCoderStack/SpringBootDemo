package com.mrcoder.sbredispubsub.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description: RestTemplate请求工具类
 */
public class RestTemplateUtil {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateUtil.class);

    @Autowired(required = false)
    private RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        logger.info("RestTemplateUtil init ===>> RestTemplate、AsyncRestTemplate......");
    }

    /**
     * 同步GET请求
     *
     * @param requestUrl   请求地址，请求地址中参数采用{}占位符的形式。eg:
     *                     http://example.com/hotels/{hotel}/bookings/{booking}
     * @param responseType 返回值类型
     * @param headerMap    请求头Map参数
     * @param uriVariables 请求参数
     * @return
     */
    public <T> T syncGet(String requestUrl, Class<T> responseType, Map<String, String> headerMap, Map<String, String> cookieMap, Object... uriVariables) {
        try {
            logger.info("method: {}; requestUrl: {}; headerMap: {}; cookieMap: {}; uriVariables: {}", HttpMethod.GET.name(), requestUrl, headerMap, cookieMap, uriVariables);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            // 1、设置header
            if (headerMap != null && headerMap.size() > 0) {
                for (Entry<String, String> headerEntry : headerMap.entrySet()) {
                    headers.add(headerEntry.getKey(), headerEntry.getValue());
                }
            }
            // 2、设置cookie
            if (cookieMap != null && cookieMap.size() > 0) {
                List<String> cookies = new ArrayList<String>();
                for (Entry<String, String> cookieEntry : cookieMap.entrySet()) {
                    cookies.add(cookieEntry.getKey() + "=" + cookieEntry.getValue());
                }
                headers.put(HttpHeaders.COOKIE, cookies); // 将cookie放入header
            }
            ResponseEntity<T> exchange = this.restTemplate.exchange(requestUrl, HttpMethod.GET, new HttpEntity<String>(null, headers), responseType, uriVariables);
            logger.info("responseCode: {}", exchange.getStatusCodeValue());
            return exchange.getBody();
        } catch (Exception e) {
            logger.error("同步GET请求异常, requestUrl: {}; headerMap: {}; cookieMap: {}; uriVariables: {}", requestUrl, headerMap, cookieMap, uriVariables);
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 同步get请求【参数为占位符】
     *
     * @param requestUrl
     * @param responseType
     * @param uriVariables
     * @return
     */
    public <T> T syncGet(String requestUrl, Class<T> responseType, Object... uriVariables) {
        return syncGet(requestUrl, responseType, null, null, uriVariables);
    }

    /**
     * 同步get请求【参数直接待在URL里】
     *
     * @param requestUrl
     * @param responseType
     * @param uriVariables
     * @return
     */
    public <T> T syncGet(String requestUrl, Class<T> responseType) {
        return syncGet(requestUrl, responseType, null, null);
    }

    /**
     * 同步GET请求，请求地址直接跟在RRL里面
     *
     * @param requestUrl   请求地址
     * @param responseType 返回值类型
     * @param headerMap    请求头Map参数
     * @return
     */
    public <T> T syncGet(String requestUrl, Class<T> responseType, Map<String, String> headerMap, Map<String, String> cookieMap) {
        try {
            logger.info("method: {}; requestUrl: {}; headerMap: {}; cookieMap: {}", HttpMethod.GET.name(), requestUrl, headerMap, cookieMap);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            // 1、设置header
            if (headerMap != null && headerMap.size() > 0) {
                for (Entry<String, String> headerEntry : headerMap.entrySet()) {
                    headers.add(headerEntry.getKey(), headerEntry.getValue());
                }
            }
            // 2、设置cookie
            if (cookieMap != null && cookieMap.size() > 0) {
                List<String> cookies = new ArrayList<String>();
                for (Entry<String, String> cookieEntry : cookieMap.entrySet()) {
                    cookies.add(cookieEntry.getKey() + "=" + cookieEntry.getValue());
                }
                headers.put(HttpHeaders.COOKIE, cookies); // 将cookie放入header
            }
            ResponseEntity<T> exchange = this.restTemplate.exchange(requestUrl, HttpMethod.GET, new HttpEntity<String>(null, headers), responseType);
            logger.info("responseCode: {}", exchange.getStatusCodeValue());
            return exchange.getBody();
        } catch (Exception e) {
            logger.error("同步GET请求异常, requestUrl: {}; headerMap: {}; cookieMap: {}", requestUrl, headerMap, cookieMap);
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 同步POST请求(JSON，带header，带cookie)
     *
     * @param requestUrl   请求地址
     * @param param        请求参数
     * @param responseType 返回类型
     * @param headerMap
     * @param cookieMap
     * @return
     */
    public <T> T syncPostJson(String requestUrl, Object param, Class<T> responseType, Map<String, String> headerMap, Map<String, String> cookieMap) {
        String jsonParam = null;
        try {
            if (param instanceof String) {
                jsonParam = (String) param;
            } else {
                jsonParam = JSONObject.toJSONString(param);
            }
            logger.info("method: {}; requestUrl: {}; requestParm: {}; headerMap: {}; cookieMap: {}", HttpMethod.POST.name(), requestUrl, jsonParam, headerMap, cookieMap);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            // 1、设置header
            if (headerMap != null && headerMap.size() > 0) {
                for (Entry<String, String> headerEntry : headerMap.entrySet()) {
                    headers.add(headerEntry.getKey(), headerEntry.getValue());
                }
            }
            // 2、设置cookie
            if (cookieMap != null && cookieMap.size() > 0) {
                List<String> cookies = new ArrayList<String>();
                for (Entry<String, String> cookieEntry : cookieMap.entrySet()) {
                    cookies.add(cookieEntry.getKey() + "=" + cookieEntry.getValue());
                }
                headers.put(HttpHeaders.COOKIE, cookies); // 将cookie放入header
            }
            HttpEntity<String> httpEntity = new HttpEntity<String>(jsonParam, headers);
            ResponseEntity<T> exchange = this.restTemplate.exchange(requestUrl, HttpMethod.POST, httpEntity, responseType);
            logger.info("responseCode: {}", exchange.getStatusCodeValue());
            return exchange.getBody();
        } catch (Exception e) {
            logger.error("同步POST请求异常, requestUrl: {}; requestParm: {}; headerMap: {}; cookieMap: {}", requestUrl, jsonParam, headerMap, cookieMap);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 同步POST请求(JSON，不带cookie，不带header)
     *
     * @param requestUrl
     * @param param
     * @param responseType
     * @return
     */
    public <T> T syncPostJson(String requestUrl, Object param, Class<T> responseType) {
        return syncPostJson(requestUrl, param, responseType, null, null);
    }

    /**
     * 同步POST请求(JSON，带cookie，不带header)
     *
     * @param requestUrl
     * @param param
     * @param responseType
     * @param cookieMap
     * @return
     */
    public <T> T syncPostJson(String requestUrl, Object param, Class<T> responseType, Map<String, String> cookieMap) {
        return syncPostJson(requestUrl, param, responseType, null, cookieMap);
    }

    /**
     * 同步PUT请求
     *
     * @param requestUrl
     * @param param
     * @param responseType
     * @param headerMap
     * @param cookieMap
     * @return
     */
    public <T> T syncPutJson(String requestUrl, Object param, Class<T> responseType, Map<String, String> headerMap, Map<String, String> cookieMap) {
        String jsonParam = null;
        try {
            if (param instanceof String) {
                jsonParam = (String) param;
            } else {
                jsonParam = JSONObject.toJSONString(param);
            }
            logger.info("method: {}; requestUrl: {}; requestParm: {}; headerMap: {}; cookieMap: {}", HttpMethod.PUT.name(), requestUrl, jsonParam, headerMap, cookieMap);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            // 1、设置header
            if (headerMap != null && headerMap.size() > 0) {
                for (Entry<String, String> headerEntry : headerMap.entrySet()) {
                    headers.add(headerEntry.getKey(), headerEntry.getValue());
                }
            }
            // 2、设置cookie
            if (cookieMap != null && cookieMap.size() > 0) {
                List<String> cookies = new ArrayList<String>();
                for (Entry<String, String> cookieEntry : cookieMap.entrySet()) {
                    cookies.add(cookieEntry.getKey() + "=" + cookieEntry.getValue());
                }
                headers.put(HttpHeaders.COOKIE, cookies); // 将cookie放入header
            }
            HttpEntity<String> httpEntity = new HttpEntity<String>(jsonParam, headers);
            ResponseEntity<T> exchange = this.restTemplate.exchange(requestUrl, HttpMethod.PUT, httpEntity, responseType);
            logger.info("responseCode: {}", exchange.getStatusCodeValue());
            return exchange.getBody();
        } catch (Exception e) {
            logger.error("同步PUT请求异常, requestUrl: {}; requestParm: {}; headerMap: {}; cookieMap: {}", requestUrl, jsonParam, headerMap, cookieMap);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 同步PUT请求(JSON，带cookie，不带header)
     *
     * @param requestUrl
     * @param param
     * @param responseType
     * @param cookieMap
     * @return
     */
    public <T> T syncPutJson(String requestUrl, Object param, Class<T> responseType, Map<String, String> cookieMap) {
        return syncPutJson(requestUrl, param, responseType, null, cookieMap);
    }

    /**
     * 同步DELETE请求(JSON，带cookie，不带header)
     *
     * @param requestUrl
     * @param param
     * @param responseType
     * @param cookieMap
     * @return
     */
    public <T> T syncDeleteJson(String requestUrl, Object param, Class<T> responseType, Map<String, String> cookieMap) {
        return syncDeleteJson(requestUrl, param, responseType, null, cookieMap);
    }

    /**
     * 同步DELETE请求(JSON，带header，带cookie)
     *
     * @param requestUrl   请求地址
     * @param param        请求参数
     * @param responseType 返回类型
     * @param headerMap
     * @param cookieMap
     * @return
     */
    public <T> T syncDeleteJson(String requestUrl, Object param, Class<T> responseType, Map<String, String> headerMap, Map<String, String> cookieMap) {
        String jsonParam = null;
        try {
            if (param instanceof String) {
                jsonParam = (String) param;
            } else {
                jsonParam = JSONObject.toJSONString(param);
            }
            logger.info("method: {}; requestUrl: {}; requestParm: {}; headerMap: {}; cookieMap: {}", HttpMethod.DELETE.name(), requestUrl, jsonParam, headerMap, cookieMap);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            // 1、设置header
            if (headerMap != null && headerMap.size() > 0) {
                for (Entry<String, String> headerEntry : headerMap.entrySet()) {
                    headers.add(headerEntry.getKey(), headerEntry.getValue());
                }
            }
            // 2、设置cookie
            if (cookieMap != null && cookieMap.size() > 0) {
                List<String> cookies = new ArrayList<String>();
                for (Entry<String, String> cookieEntry : cookieMap.entrySet()) {
                    cookies.add(cookieEntry.getKey() + "=" + cookieEntry.getValue());
                }
                headers.put(HttpHeaders.COOKIE, cookies); // 将cookie放入header
            }
            HttpEntity<String> httpEntity = new HttpEntity<String>(jsonParam, headers);
            ResponseEntity<T> exchange = this.restTemplate.exchange(requestUrl, HttpMethod.DELETE, httpEntity, responseType);
            logger.info("responseCode: {}", exchange.getStatusCodeValue());
            return exchange.getBody();
        } catch (Exception e) {
            logger.error("同步DELETE请求异常, requestUrl: {}; requestParm: {}; headerMap: {}; cookieMap: {}", requestUrl, jsonParam, headerMap, cookieMap);
            e.printStackTrace();
            return null;
        }
    }
}