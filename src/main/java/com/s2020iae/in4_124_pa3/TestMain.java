/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s2020iae.in4_124_pa3;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author zhiyi
 */
public class TestMain {
    public static void main(String[] args){
        try {
            Client client = ClientBuilder.newClient();
        WebTarget target = client.target(getBaseURI());
        WebTarget jsonResponse=target.path("rest").path("item").path("abc");
        System.out.println(jsonResponse);
        String i = jsonResponse.request().accept(MediaType.TEXT_PLAIN).get(String.class);
        System.out.println(i);//  usse the get method and return the response as a string
        //ObjectMapper objectMapper = new ObjectMapper();
        //Items item = objectMapper.readValue(i, Items.class);
        // System.out.println(item);
        //} catch (IOException ex) {
            //Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        //    ex.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/in4_124_pa3").build();
    }
    
    
}
