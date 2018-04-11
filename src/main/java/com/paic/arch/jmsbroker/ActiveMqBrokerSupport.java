package com.paic.arch.jmsbroker;

import com.paic.arch.jmsbroker.jsm.JmsMessage;

import static com.paic.arch.jmsbroker.JmsMessageBrokerSupport.bindToBrokerAtUrl;

/**
 * Created by jack on 2018/4/10.
 */
public class ActiveMqBrokerSupport implements JmsMessage{

    private JmsMessageBrokerSupport jmsMessageBrokerSupport;

    private String message;
    private String brokerUrl;
    /**
     * 调用父类构造函数
     * @param aBrokerUrl
     */
    private ActiveMqBrokerSupport(String aBrokerUrl) {
        this.brokerUrl = aBrokerUrl;
    }

    @Override
    public JmsMessage and() {
        return this;
    }

    @Override
    public JmsMessage andThen() {
        return this;
    }


    @Override
    public JmsMessage sendTheMessage(String aMessageToSend) {
        this.message = aMessageToSend;
        return this;
    }

    @Override
    public JmsMessage to(String aDestinationName) {
        jmsMessageBrokerSupport.sendATextMessageToDestinationAt(aDestinationName, message);
        return this;
    }

    @Override
    public String waitForAMessageOn(String accountingQueue) {
        return jmsMessageBrokerSupport.retrieveASingleMessageFromTheDestination(accountingQueue);
    }

    public static JmsMessage bindToActiveMqBrokerAt(String aBrokerUrl) throws Exception{
        ActiveMqBrokerSupport activeMqAdapter = new ActiveMqBrokerSupport(aBrokerUrl);
        activeMqAdapter.setJmsMessageBrokerSupport(bindToBrokerAtUrl(aBrokerUrl));
        return activeMqAdapter;
    }

    public JmsMessageBrokerSupport getJmsMessageBrokerSupport() {
        return jmsMessageBrokerSupport;
    }

    public void setJmsMessageBrokerSupport(JmsMessageBrokerSupport jmsMessageBrokerSupport) {
        this.jmsMessageBrokerSupport = jmsMessageBrokerSupport;
    }
}
