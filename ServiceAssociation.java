/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.StringUtil;
import com.mycompany.myapp.entities.Association;
import com.mycompany.myapp.entities.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class ServiceAssociation {
    
   
    public ArrayList<Association> parseListEvenementsJson(String json) {
        
        ArrayList<Association> listassociation = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> evenements = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) evenements.get("root");
            for (Map<String, Object> obj : list) {
                Association e = new Association();
                float idAssociation = Float.parseFloat(obj.get("idAssociation").toString());
                     e.setId_Association((int) idAssociation);
              e.setNom_Association(obj.get("nomAssociation").toString());
                
                   
               
                listassociation.add(e);
            }
        } catch (IOException ex) {
        }
        return listassociation;
    
    }
    
    

    ArrayList<Association> listassociation = new ArrayList();
  

    
    public ArrayList<Association> getListEvenements() {
        ConnectionRequest cnx = new ConnectionRequest();
        cnx.setUrl("http://localhost/donationWEB/web/app_dev.php/donationm/products/ListeAssociationall");
        cnx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAssociation ser = new ServiceAssociation();
                listassociation = ser.parseListEvenementsJson(new String(cnx.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cnx);
        return listassociation;
    }}