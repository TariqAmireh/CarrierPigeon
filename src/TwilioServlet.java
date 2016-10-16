package com.twilio;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.twilio.twiml.Body;
import com.twilio.twiml.Message;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.TwiMLExcetpion;

public class TwilioServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletRespones response) throws IOException {
        Message message = new Message.Builder()
                .body(new Body("Just anything"))
                .build();
        MessagingResponse twiml = new MessagingResponse.Builder()
                .message(message)
                .build();
        response.setContentType("application/xml");

        try {
            response.getWriter().print(twiml.toXML());
        } catch (TwiMlException e) {
            e.printStackTrace();
        }
    }
}