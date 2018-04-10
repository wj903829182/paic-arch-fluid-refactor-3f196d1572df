package com.paic.arch.jmsbroker;

import com.paic.arch.jmsbroker.jsm.JmsMessage;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by jack on 2018/4/10.
 */
public class TibcoMqBrokerSupport implements JmsMessage {

    private static final Logger LOG = getLogger(TibcoMqBrokerSupport.class);
    private String brokerUrl;

    private TibcoMqBrokerSupport(String url){
        this.brokerUrl = url;
    }


    @Override
    public JmsMessage and() {
        return null;
    }

    @Override
    public JmsMessage andThen() {
        return null;
    }

    @Override
    public JmsMessage sendTheMessage(String inputMessage) {
        return null;
    }

    @Override
    public JmsMessage to(String inputQueue) {
        return null;
    }

    @Override
    public String waitForAMessageOn(String accountingQueue) {
        return null;
    }


    public static JmsMessage bindToIbmMqBrokerAt(String aBrokerUrl) throws Exception{
        return new TibcoMqBrokerSupport(aBrokerUrl);
    }

}
