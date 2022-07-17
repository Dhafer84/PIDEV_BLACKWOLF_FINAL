/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class sendSMS {
    public static final String ACCOUNT_SID = System.getenv("ACcc95a879c3c1ae23fd17706c2c7c2bfb");
    public static final String AUTH_TOKEN = System.getenv("5569ace6d1795191979ec23e8903f346");
   

    public static void sendSMS() {
        Twilio.init("ACcc95a879c3c1ae23fd17706c2c7c2bfb", "5569ace6d1795191979ec23e8903f346");
        Message message = Message.creator(new PhoneNumber("+21652854245"),
                new PhoneNumber("+18584633984"),
                "events valide").create();
        System.out.println(message.getSid());
       
    }
}
