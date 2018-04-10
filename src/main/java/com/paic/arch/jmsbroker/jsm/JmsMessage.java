package com.paic.arch.jmsbroker.jsm;

/**
 * Created by jack on 2018/4/10.
 */
public interface JmsMessage {
    JmsMessage and();
    JmsMessage andThen();
    JmsMessage sendTheMessage(final String inputMessage);
    JmsMessage to(String inputQueue);
    String waitForAMessageOn(String accountingQueue);
}
