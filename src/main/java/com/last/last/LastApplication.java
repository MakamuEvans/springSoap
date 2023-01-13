package com.last.last;

import com.last.last.api.ArticleClient;
import com.last.last.api.MathClient;
import com.last.last.config.SoapClient;
import com.last.last.pojo.SomeDt;
import com.medium.article.Add;
import org.eclipse.persistence.jaxb.JAXBContext;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.soap.SoapElement;
import org.springframework.ws.soap.SoapEnvelope;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.w3c.dom.CDATASection;

import javax.xml.bind.JAXB;
import javax.xml.soap.*;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;

@SpringBootApplication
public class LastApplication {

    public static void main(String[] args) throws SOAPException, IOException {
        SpringApplication.run(LastApplication.class, args);

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage =  messageFactory.createMessage();
        SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
        soapEnvelope.addNamespaceDeclaration("soap", "http://schemas.xmlsoap.org/soap/envelope/");
        soapEnvelope.setPrefix("sopaenv");

        soapEnvelope.setAttribute("xmlns:tem", "http://tempuri.org/");

        SOAPBody body = soapEnvelope.getBody();
        body.addNamespaceDeclaration("soap", "http://schemas.xmlsoap.org/soap/envelope/");
        body.setPrefix("soapenv");

        MimeHeaders mimeHeader = soapMessage.getMimeHeaders();
        mimeHeader.addHeader("SOAPAction", "http://tempuri.org/Add");

        SOAPElement addData = body.addChildElement("Add", "tem");

        SOAPElement intA = addData.addChildElement("intA", "tem");
        intA.addTextNode("3");
        //intA.setPrefix("tem");
        SOAPElement intB = addData.addChildElement("intB", "tem");
        intB.addTextNode("5");
        //intB.setPrefix("tem");

        SomeDt someDt = new SomeDt();
        someDt.setName("Makamu");
        someDt.setEmail("makamuevans@gmail.com");

        StringWriter sw = new StringWriter();
        JAXB.marshal(someDt, sw);
        String xmlString = sw.toString();
        //System.out.println(xmlString);

        SOAPElement cData = addData.addChildElement("others", "tem");
        CDATASection section = cData.getOwnerDocument().createCDATASection(xmlString);
        cData.appendChild(section);

        soapMessage.writeTo(System.out);

        SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
        SOAPConnection connection = sfc.createConnection();
        URL requestURL = new URL("https://cdata.free.beeceptor.com");
        SOAPMessage response = connection.call(soapMessage, requestURL);

        System.out.println("");
        System.out.println("***************");
        response.writeTo(System.out);

        SOAPBody responseBody = response.getSOAPBody();
        //responseBody.getChildElements().

        /*AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SoapClient.class);
        MathClient mathClient = annotationConfigApplicationContext.getBean(MathClient.class);
        System.out.println("runnigg....");
        System.out.println("The sum is "+ mathClient.sum(9,5).getAddResult());*/
    }

}
