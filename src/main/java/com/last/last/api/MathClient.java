package com.last.last.api;

import com.medium.article.Add;
import com.medium.article.AddResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.w3c.dom.CDATASection;

public class MathClient extends WebServiceGatewaySupport {

    public AddResponse sum(int num1, int num2){
        Add add = new Add();
        add.setIntA(num1);
        add.setIntB(num2);
        add.setCd("Makamu Evans");
        return (AddResponse) getWebServiceTemplate().marshalSendAndReceive(add, new SoapActionCallback("http://tempuri.org/Add"));
    }
}
