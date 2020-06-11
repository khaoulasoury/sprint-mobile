/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Association;
import com.mycompany.myapp.entities.Command;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.services.ServiceAssociation;
import com.mycompany.myapp.services.ServiceCommand;
import com.mycompany.myapp.services.ServiceProduct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author DELL
 */
public class Tribyprix extends BaseForm{

  Form f;
    ImageViewer ip;
    List<Product> lse = new ArrayList();
        ArrayList<Product> form;
   
    


    public Tribyprix() throws IOException {

        //f = new Form("Event List", BoxLayout.y());
          //-----------------------------RECHERCHES-----------------------------------------    
         super("Newsfeed", BoxLayout.y());
        Resources res = UIManager.initFirstTheme("/theme");
          
         // Toolbar tb = new Toolbar(true);
           Toolbar tb = getToolbar();
        setToolbar(tb);
        getTitleArea().setUIID("Container");
       setTitle("Newsfeed");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
          
        
        Tabs swipe = new Tabs();
 
        Label spacer1 = new Label();
        Label spacer2 = new Label();
     
         addTab(swipe, res.getImage("news-item.jpg"), spacer1, "15 Likes  ", "85 Comments", "Integer ut placerat purued non dignissim neque. ");
        //addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
//        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("All", barGroup);
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("Featured", barGroup);
        featured.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("Popular", barGroup);
        popular.setUIID("SelectBar");
        RadioButton myFavorite = RadioButton.createToggle("My Favorites", barGroup);
        myFavorite.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, all, featured, popular, myFavorite),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        
         
        ComboBox cb = new ComboBox("Quantity Remaining","NameProduct", "Price Product");
         add(cb);
       

          
      cb.addActionListener(new ActionListener() {

          
             @Override
            public void actionPerformed(ActionEvent evt) {
                Form f2 = new Form("khawla",new BorderLayout());
               if(cb.getSelectedItem() == "NameProduct") {
                   ConnectionRequest con = new ConnectionRequest();
                    con.setUrl("http://localhost/donationWEB/web/app_dev.php/donationm/products/triMobile");
                     con.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                             Tri detail_form1;
                        try {
                            detail_form1 = new Tri();
                              detail_form1.show();
                        } catch (IOException ex) {
                        }
                            System.out.println("Le produit  a été trié par succées.");
                            ToastBar.showMessage("product list has been sorted successfully",FontImage.MATERIAL_DONE);

                        }
                      
                    });
                     
                       f2.show();
                        con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                  
                }
          if(cb.getSelectedItem() == "Quantity Remaining") {
                   ConnectionRequest con = new ConnectionRequest();
                    con.setUrl("http://localhost/donationWEB/web/app_dev.php/donationm/products/tribyqteMobile");
                     con.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                             Tribyqte detail_form2;
                        try {
                            detail_form2 = new Tribyqte();
                              detail_form2.show();
                        } catch (IOException ex) {
                        }
                            System.out.println("Le produit  a été trié par succées.");
                            ToastBar.showMessage("product list has been sorted successfully",FontImage.MATERIAL_DONE);

                        }
                      
                    });
                     
                       f2.show();
                        con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                  
                }
              
            if(cb.getSelectedItem() == "Price Product") {
                   ConnectionRequest con = new ConnectionRequest();
                    con.setUrl("http://localhost/donationWEB/web/app_dev.php/donationm/products/tribyprixMobile");
                     con.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                             Tribyprix detail_form3;
                        try {
                            detail_form3 = new Tribyprix();
                              detail_form3.show();
                        } catch (IOException ex) {
                        }
                            System.out.println("Le produit  a été trié par succées.");
                            ToastBar.showMessage("product list has been sorted successfully",FontImage.MATERIAL_DONE);

                        }
                      
                    });
                     
                       f2.show();
                        con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                  
                }
            }
            
        });
    
      

        //===================================================================================================
        Slider slider = new Slider();
        slider.setPreferredSize(new Dimension(256, 2));
        add(slider);
        //--------------------------------ToolBar---------------
       
      //  Toolbar tb = f.getToolbar();
        
        tb.addCommandToLeftBar("<back", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             //   HomeForm a =new HomeForm();
               // a.getFjdida().show();
            }
        });
        
        lse = new ServiceProduct().getListProducttriebyprix();
        for (int i = 0; i < lse.size(); i++) {
            int id = lse.get(i).getId_Product();
            addItem(lse.get(i));
         
             //String dateevent = lse.get(i).getDateEvent().toString();
          //  String description = lse.get(i).getDescription_Product().toString();
            Integer quantity = lse.get(i).getQuantity_Total();
     
           Button participer = new Button("acheter");
              Button rech = new Button("recherche");
             Button nonparticiper = new Button("annuler participation");
             add(nonparticiper);
            nonparticiper.setVisible(false);
            add(participer);
            add(rech);
              rech.addActionListener(new ActionListener() {
                private String textAttachmentUri;
                private String imageAttachmentUri;
                @Override
                public void actionPerformed(ActionEvent evt) {
                       TriForm detail_form2;
                    try {
                        detail_form2 = new TriForm();
                        detail_form2.show();
                    } catch (IOException ex) {
                  
                    }
                       
                    }
            });
            
            participer.addActionListener(new ActionListener() {
                private String textAttachmentUri;
                private String imageAttachmentUri;
                @Override
                public void actionPerformed(ActionEvent evt) {
                      AddCommand detail_form;
                        try {
                            detail_form = new AddCommand();
                              detail_form.show();
                        } catch (IOException ex) {
                        }

                    //User p = new User(1,"Hnana", "Alaeddine", 12345678);
                   // ParticipationService es = new ParticipationService();
                   // float id = Float.parseFloat(lse.get(i).getIdevent());
                    
                   //es.ajouterPartEvenement(id, Authentification.PERSONNECONNECTEE.getId());
                   
                    //User p = new User(1,"Hnana", "Alaeddine", 12345678);
                  ServiceCommand es = new ServiceCommand();
                  
             
                           Command pa = new Command(1,1);
                 
                      //  pa.setIdevent(lse.get(i));
                       // pa.setIdUser(User.user);
               //         es.ParticipEvent(pa);
                        
                      
                  // es.ajouterPartEvenement(2,5);
                   
                   
                                   
                           
                       
//  
                                 //nonparticiper.setVisible(true);
                                 //participer.setVisible(false);
                            
                    
                }
            });
                   
                
        }
        
        /*tb.addCommandToLeftBar("< Retour", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                precedent.show();
            }
        });*/
    }

   

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    

   public void addItem(Product e) { 
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
     ServiceAssociation sa=new ServiceAssociation();
                ServiceProduct sp=new ServiceProduct();

        ArrayList<Association> Associations = sa.getListEvenements();
        ArrayList<Product> v =sp.getListEvenements();
            System.out.println("bjah rabi emchi");
       
            //System.out.println(Associations.get(0).getId_Association());
                                Label nomassociation = new Label();

            for(int j=Associations.size()-1;j>=0;j--){
                
                 //System.out.println(i);
              System.out.println(e.getId_Association());
                System.out.println(Associations.get(j).getId_Association());
                System.out.println("");
                if (e.getId_Association()==Associations.get(j).getId_Association()){
                    
                        nomassociation.setText("Association'name: "+Associations.get(j).getNom_Association());
                
                }
    }
        
            Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Label l1 = new Label("Name Product:"+e.getName_Product());
                    Label l2 = new Label("Description:"+e.getDescription_Product());
                    Label l = new Label("Quantity:" +e.getQuantity_Total()+" items ");
                   Label Qr = new Label("QuantityRemaining:" +e.getQuantity_Remaining()+" items ");
                    Label PR = new Label("Price:" +e.getPrice_Product()+" dt ");
                    l1.getAllStyles().setFgColor(0x000000);
                    l2.getAllStyles().setFgColor(0x000000);
                    l.getAllStyles().setFgColor(0x000000);
                    Qr.getAllStyles().setFgColor(0x000000);
                    PR.getAllStyles().setFgColor(0x000000);
nomassociation.getAllStyles().setFgColor(0x000000);
                   
                    c2.add(l1);
                    c2.add(l2);
                     c2.add(l);
                     c2.add(Qr);
                    c2.addAll(PR,nomassociation);
                    
                    c1.add(c2);

                    c1.setLeadComponent(l1);
        
        

    /*public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }*/

     add(c1);
}}

