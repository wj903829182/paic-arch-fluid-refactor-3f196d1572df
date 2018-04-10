package com.paic.arch.jmsbroker;

import com.paic.arch.jmsbroker.adapter.ActiveMqAdapter;
import com.paic.arch.jmsbroker.jsm.JmsMessage;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by jack on 2018/4/10.
 */
public class IbmMqBrokerSupport implements JmsMessage {

    private static final Logger LOG = getLogger(IbmMqBrokerSupport.class);
    private String brokerUrl;

    private IbmMqBrokerSupport(String url){
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
        return new IbmMqBrokerSupport(aBrokerUrl);
    }

}
