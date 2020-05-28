/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s2020iae.in4_124_pa3;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author zhiyi
 */
public class ItemsService {
    private static final WebTarget resource = ClientBuilder.newClient().target("http://localhost:8080/in4_124_pa3");
    public static Items getItemById(int id) throws IOException{
        //ClientConfig config = new ClientConfig();

        //Client client = ClientBuilder.newClient(config);

        //WebTarget target = client.target(getBaseURI());
        //resource
        //Items item = resource.queryParam(name, values); 
        String jsonResponse = resource.path("TestServlet").path("item").path(""+id).request().accept(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Items item = objectMapper.readValue(jsonResponse, Items.class);
        return item;
        //ObjectMapper objectMapper = new ObjectMapper();
    }
    
}
