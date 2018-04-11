package com.paic.arch.jmsbroker;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.paic.arch.jmsbroker.JmsMessageBrokerSupport.bindToBrokerAtUrl;
import static com.paic.arch.jmsbroker.ActiveMqBrokerSupport.bindToActiveMqBrokerAt;
import static org.assertj.core.api.Assertions.assertThat;

public class JmsMessageBrokerSupportTest {

    public static final String TEST_QUEUE = "MY_TEST_QUEUE";
    public static final String MESSAGE_CONTENT = "Lorem blah blah";
    private static JmsMessageBrokerSupport JMS_SUPPORT;
    private static String REMOTE_BROKER_URL;

    @BeforeClass
    public static void setup() throws Exception {
        JMS_SUPPORT = JmsMessageBrokerSupport.createARunningEmbeddedBrokerOnAvailablePort();
        REMOTE_BROKER_URL = JMS_SUPPORT.getBrokerUrl();
    }

    @AfterClass
    public static void teardown() throws Exception {
        JMS_SUPPORT.stopTheRunningBroker();
    }

    @Test
    public void sendsMessagesToTheRunningBroker() throws Exception {
        bindToBrokerAtUrl(REMOTE_BROKER_URL)
                .andThen().sendATextMessageToDestinationAt(TEST_QUEUE, MESSAGE_CONTENT);
        long messageCount = JMS_SUPPORT.getEnqueuedMessageCountAt(TEST_QUEUE);
        assertThat(messageCount).isEqualTo(1);
    }

    @Test
    public void readsMessagesPreviouslyWrittenToAQueue() throws Exception {
        String receivedMessage = bindToBrokerAtUrl(REMOTE_BROKER_URL)
                .sendATextMessageToDestinationAt(TEST_QUEUE, MESSAGE_CONTENT)
                .andThen().retrieveASingleMessageFromTheDestination(TEST_QUEUE);
        assertThat(receivedMessage).isEqualTo(MESSAGE_CONTENT);
    }

    @Test(expected = JmsMessageBrokerSupport.NoMessageReceivedException.class)
    public void throwsExceptionWhenNoMessagesReceivedInTimeout() throws Exception {
        bindToBrokerAtUrl(REMOTE_BROKER_URL).retrieveASingleMessageFromTheDestination(TEST_QUEUE, 1);
    }

    @Test
    public void systemProcessesTradesAndCreatesAccountRequests()throws Exception{

        String accountingMessage = bindToBrokerAtUrl(REMOTE_BROKER_URL)
                .andThen().sendATextMessageToDestinationAt(TEST_QUEUE, MESSAGE_CONTENT)
                .andThen().retrieveASingleMessageFromTheDestination(TEST_QUEUE);
        assertThat(accountingMessage).isEqualTo(MESSAGE_CONTENT);
    }

    @Test
    public void blah() throws Exception{
        String receivedMessage = bindToActiveMqBrokerAt(REMOTE_BROKER_URL)
                .and().sendTheMessage(MESSAGE_CONTENT).to(TEST_QUEUE)
                .andThen().waitForAMessageOn(TEST_QUEUE);
        assertThat(receivedMessage).isEqualTo(MESSAGE_CONTENT);


       /* bindToIbmMqBrokerAt(url)
                .and().sendTheMessage(inputMessage).to(inputQueue)
                .andThen().waitForAMessageOn(accountingQueue);

        bindToTibcoMqBrokerAt(url)
                .and().sendTheMessage(inputMessage).to(inputQueue)
                .andThen().waitForAMessageOn(accountingQueue);*/

    }


}