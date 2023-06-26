package cl.com.tenpo.domain.configuration.client;

import feign.RetryableException;
import feign.Retryer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomRetryer implements Retryer {

    private Logger logger= LoggerFactory.getLogger(CustomRetryer.class);

    private final int maxAttempts;
    private final long backoff;
    int attempt;

    public CustomRetryer() {
        this(2000, 3);
    }

    public CustomRetryer(long backoff, int maxAttempts) {
        this.backoff = backoff;
        this.maxAttempts = maxAttempts;
        this.attempt = 1;
    }

    public void continueOrPropagate(RetryableException e) {
        if (attempt++ >= maxAttempts)
            throw e;

        try {
            logger.warn("We are getting problems to get connect with end api, We can try "+maxAttempts+" times max"+ e.request());
            Thread.sleep(backoff);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public Retryer clone() {
        return new CustomRetryer(backoff, maxAttempts);
    }
}
