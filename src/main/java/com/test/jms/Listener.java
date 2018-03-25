package com.test.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Listener {

	@JmsListener(destination = "testqueue")
	@SendTo("Send2Recv")
	public String receiveMessage(final Message jsonMessage) throws JMSException {
		String messageData = null;
		System.out.println("Received message " + jsonMessage);
		String response = null;
		if(jsonMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage)jsonMessage;
			messageData = textMessage.getText();
			// xml to json
			/*JSONObject soapDatainJsonObject = XML.toJSONObject(messageData);
			System.out.println(soapDatainJsonObject);*/
			
			// json to xml
			JSONObject json = new JSONObject(messageData);
			String xml = XML.toString(json);
			System.out.println("xml :: "+xml);
//            Map<String, String> map = new Gson().fromJson(messageData, Map.class);
            response  = "Hello " + messageData;
		}
		return response;
	}

}
