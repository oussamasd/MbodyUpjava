/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;




import com.twilio.Twilio; 
import com.twilio.converter.Promoter; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.type.PhoneNumber; 
import java.io.IOException;
import java.io.UnsupportedEncodingException;
 
import java.net.URI; 
import java.math.BigDecimal; 
import java.net.MalformedURLException;

/**
 *
 * @author SeifBS
 */
public class SendSms  {
         public static final String ACCOUNT_SID = "AC30aaa6f0b68bd14e3331b3519fdd2fd4"; 
    public static final String AUTH_TOKEN = "fe3fd9b6dcfe7799357d2de6c712d43c"; 

    public static void sendSms (String message_, String number) throws UnsupportedEncodingException, MalformedURLException, IOException {

    Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
        Message message = Message.creator( 
                new com.twilio.type.PhoneNumber("+21620550409"),  
               "MGb586c8cb48d4f00c09340f0c7f222a72", 
                message_)      
            .create(); 
 
        System.out.println(message.getSid()); 
    
}

    
}



