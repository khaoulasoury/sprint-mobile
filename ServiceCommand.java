/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;



import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Command;
import com.mycompany.myapp.entities.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import com.codename1.sendgrid.SendGrid;
import com.codename1.util.StringUtil;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author ASUS
 */
public class ServiceCommand {
     public boolean resultOK;
    private ConnectionRequest req;
    
    public ArrayList<Command> parseListEvenementsJson(String json) {
        
        ArrayList<Command> listEvenements = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> evenements = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) evenements.get("root");
            for (Map<String, Object> obj : list) {
                Command e = new Command();
               
                      float idCommand = Float.parseFloat(obj.get("idCommand").toString());
                     e.setId_Command((int) idCommand);
                    float qte=Float.parseFloat(obj.get("quantityProduct").toString());
                    e.setQuantity_Product((int)qte);
                      float qteTotal = Float.parseFloat(obj.get("paid").toString());
                     e.setPaid((int) qteTotal);
                      e.setDate_Command(new Date(((Double) ((Map<String, Object>) obj.get("dateCommand")).get("timestamp")).longValue()* 1000));
                     String unholyText = obj.get("idProduct").toString();
                String demiHolyText=""; 
                for(int i = 11; i<17 ; i++){
                    demiHolyText = demiHolyText + unholyText.charAt(i);
                }
                String holyText = StringUtil.replaceAll(demiHolyText, ",", "");
                e.setId_Product(((int)Float.parseFloat(holyText)));
                     
                listEvenements.add(e);
            }
        } catch (IOException ex) {
        }
        return listEvenements;
    
    }
    
    

    ArrayList<Command> listEvenements = new ArrayList();
  

    
    public ArrayList<Command> getListEvenements() {
        ConnectionRequest cnx = new ConnectionRequest();
        cnx.setUrl("http://localhost/donationWEB/web/app_dev.php/donationm/products/AffichageCommande");
        cnx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceCommand ser = new ServiceCommand();
                listEvenements = ser.parseListEvenementsJson(new String(cnx.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cnx);
        return listEvenements;
    }
    
  public boolean addTask(Command t) {
       ConnectionRequest cnx = new ConnectionRequest();
        cnx.setUrl("http://localhost/donationWEB/web/app_dev.php/donationm/products/CommandeDbMobile?paid="
          +t.getPaid());
        cnx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = cnx.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
   public boolean deleteCommend(int idcommand) {
 ConnectionRequest cnx = new ConnectionRequest();
        cnx.setUrl("http://localhost/donationWEB/web/app_dev.php/donationm/products/DeleteCommandeMobile/"+idcommand);
        cnx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = cnx.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public void ParticipEvent(Command p) {
        //System.out.println(p);
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = ("http://localhost/donationWEB/web/app_dev.php/donationm/products/CommandeDbMobile?idProduct="+p.getId_Product()+"&qteproduct="+p.getQuantity_Product()+
              "&paid="+p.getPaid());
         
        System.out.println(Url);
   
// création de l'URLgetIdUser
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
          //  System.out.println(str);//Affichage de la réponse serveur sur la console
            ToastBar.showMessage("successful purchase",FontImage.MATERIAL_DONE);
             SendGrid s = SendGrid.create("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");
//        SendGrid s = SendGrid.create("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");
        s.sendSync("khaoula.soury@esprit.tn"
                , "khaoulasoury@gmail.com"
                , "Command  "
                , "order was successfully completed"       );
        //SMSAPI sms = new SMSAPI("marekkk  "+tfNom_Demande.getText()+"  successful purchase  ", "+21628183067");
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }  

}