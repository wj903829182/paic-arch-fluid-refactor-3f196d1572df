package com.paic.arch.jmsbroker.adapter;

import com.paic.arch.jmsbroker.JmsMessageBrokerSupport;
import com.paic.arch.jmsbroker.jsm.JmsMessage;

/**
 * Created by jack on 2018/4/10.
 */
public class ActiveMqAdapter extends JmsMessageBrokerSupport implements JmsMessage{
    private String message;
    /**
     * 调用父类构造函数
     * @param aBrokerUrl
     */
    private ActiveMqAdapter(String aBrokerUrl) {
        super(aBrokerUrl);
    }

    @Override
    public JmsMessage and() {
        return this;
    }

    /*@Override
    public JmsMessage andThen() {
        return this;
    }*/

    @Override
    public JmsMessage sendTheMessage(String aMessageToSend) {
        this.message = aMessageToSend;
        return this;
    }

    @Override
    public JmsMessage to(String aDestinationName) {
        return super.sendATextMessageToDestinationAt(aDestinationName, message);
    }

    @Override
    public String waitForAMessageOn(String accountingQueue) {
        return super.retrieveASingleMessageFromTheDestination(accountingQueue);
    }

    public static JmsMessage bindToActiveMqBrokerAt(String aBrokerUrl) throws Exception{
        return new ActiveMqAdapter(aBrokerUrl);
    }
}
