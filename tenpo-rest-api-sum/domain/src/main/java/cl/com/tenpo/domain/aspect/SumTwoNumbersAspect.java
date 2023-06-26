package cl.com.tenpo.domain.aspect;


import cl.com.tenpo.domain.dto.CallsHistoryDTO;
import cl.com.tenpo.domain.mapper.ICallsHistoryMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class SumTwoNumbersAspect {

    private final Logger logger = LoggerFactory.logger(SumTwoNumbersAspect.class);

    @Autowired
    private cl.com.tenpo.domain.repository.ICallsHistoryRepository iCallsHistoryRepository;

    @Autowired
    private ICallsHistoryMapper iCallsHistoryMapper;

    private final ObjectMapper objectMapper;

    @Pointcut("execution(* cl.com.tenpo.sum.infrastructure.adapter.in.SumTwoNumbersAdapterIn.*(..)) ")
    private void anySumTwoNumbers() {}

    SumTwoNumbersAspect() {
        objectMapper = new ObjectMapper();
    }

    @AfterReturning(value = "anySumTwoNumbers() && args(request, numberOne, numberTwo)", returning = "returning")
    public void anySumTwoNumbers(JoinPoint joinPoint, HttpServletRequest request, Double numberOne, Double numberTwo, ResponseEntity returning) {

        // We validate successful request with target of save response
        if (returning.getStatusCode().is2xxSuccessful()) {
            try {
                // We build object to store history api calls
                CallsHistoryDTO audit = CallsHistoryDTO.builder().request(getRequestInJson(request)).response(objectMapper.writeValueAsString(returning)).build();

                // We send to store history api calls
                iCallsHistoryRepository.save(iCallsHistoryMapper.DTOInEntity(audit));
            } catch (Exception ex) {
                // we can't stop main api call
                logger.error("We've problems to stored history but we can't stop main api call");
            }
        }
    }

    public String getRequestInJson(HttpServletRequest request) throws Exception {

        // We build json object where will return request
        JSONObject headers = new JSONObject();

        // We get request url from request object
        headers.put("url", request.getRequestURI());

        // We get http method from request object
        headers.put("method", request.getMethod());

        // We get content type from request object
        headers.put("content-type", request.getContentType());

        // We get character encoding from request object
        headers.put("character-encoding", request.getCharacterEncoding());

        // We get auth type from request object
        headers.put("auth-type", request.getAuthType());

        // We get protocol from request object
        headers.put("protocol", request.getProtocol());

        // We get remote host from request object
        headers.put("remote-host", request.getRemoteHost());

        // We get remote port from request object
        headers.put("remote-port", request.getRemotePort());

        // We get headers from request object
        headers.put("headers", objectMapper.writeValueAsString(getRequestHeaders(request)));

        // We get parameters map from request object
        headers.put("parameters", objectMapper.writeValueAsString(request.getParameterMap()));

        // We return request converted to json
        return headers.toJSONString();
    }

    public Map<String, String> getRequestHeaders(HttpServletRequest request) throws Exception {

        // We initialize parameters map
        Map<String, String> headers = new HashMap<>();

        // We get parameters map from request object
        Enumeration<String> headerNames = request.getHeaderNames();

        // We validate that exist header in request object
        if (headerNames != null) {

            // We are iterating headers
            while (headerNames.hasMoreElements()) {
                String header = headerNames.nextElement();
                headers.put(header, request.getHeader(header));
            }
        }

        // We return headers map
        return headers;
    }
}