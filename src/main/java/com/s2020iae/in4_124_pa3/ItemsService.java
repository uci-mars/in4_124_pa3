/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s2020iae.in4_124_pa3;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
//import org.glassfish.jersey.client.ClientConfig;

/**
 *
 * @author zhiyi
 */
public class ItemsService { 
    public static Items getItemById(int id) throws IOException{
        /*WebTarget resource = ClientBuilder.newClient().target("http://localhost:8080/");
        WebTarget newresource = resource.path("RESTapi").path("item").path(""+id);
        Invocation.Builder jsonResponse = newresource.request().accept(MediaType.APPLICATION_JSON);
        String s =         jsonResponse.get(String.class);
        return s;
        */
        /*ObjectMapper objectMapper = new ObjectMapper();
        Items item = objectMapper.readValue(jsonResponse, Items.class);
        return item;
        *///ObjectMapper objectMapper = new ObjectMapper();
        
        //ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/in4_124_pa3");
        String jsonResponse =target.path("rest").path("item").path("1").request(). //send a request
                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                        get(String.class); //  usse the get method and return the response as a string
        ObjectMapper objectMapper = new ObjectMapper();
        Items item = objectMapper.readValue(jsonResponse, Items.class);
        return item;
    }
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/in4_124_pa3").build();
    }
    
}
