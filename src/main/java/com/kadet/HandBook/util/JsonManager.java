package com.kadet.HandBook.util;

import com.kadet.HandBook.entity.User;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 03.11.13
 * Time: 22:32
 * To change this template use File | Settings | File Templates.
 */
public class JsonManager {

    private static ObjectMapper mapper = new ObjectMapper();

    //{"id":"100001188185493","name":"\u041b\u0435\u0445\u0430 \u0421\u043e\u0440\u043e\u043a\u0430","first_name":"\u041b\u0435\u0445\u0430","last_name":"\u0421\u043e\u0440\u043e\u043a\u0430","link":"https:\/\/www.facebook.com\/leha.soroka","username":"leha.soroka","birthday":"09\/15\/1994","hometown":{"id":"108570489168311","name":"Slutsk"},"location":{"id":"107677462599905","name":"Minsk, Belarus"},"quotes":"\u041d\u0438\u043a\u043e\u0433\u0434\u0430 \u043d\u0435 \u0441\u0434\u0430\u0432\u0430\u0439\u0441\u044f","favorite_athletes":[{"id":"8529799634","name":"Didier Drogba Fan Page"}],"gender":"male","timezone":2,"locale":"ru_RU","languages":[{"id":"106059522759137","name":"English"},{"id":"112624162082677","name":"Russian"}],"verified":true,"updated_time":"2012-01-28T07:56:50+0000"}
    public static User userFromUrl (String url) throws IOException {
        User user = mapper.readValue(new URL(url).openStream(), User.class);
        return user;
    }



}
