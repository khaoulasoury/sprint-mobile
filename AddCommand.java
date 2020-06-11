/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.notifications.LocalNotification;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.util.regex.RE;
import com.mycompany.myapp.entities.Association;

import com.mycompany.myapp.entities.Command;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.services.SMSAPI;
import com.mycompany.myapp.services.ServiceAssociation;

import com.mycompany.myapp.services.ServiceCommand;
import com.mycompany.myapp.services.ServiceProduct;


import java.io.IOException;
import java.util.ArrayList;






/**
 *
 * @author Salma
 */
public class AddCommand  extends BaseForm  {
     Form f;

    com.codename1.io.File file;
    String fileName;
    Image videImg;
//    public static int idUser = User.user.getId();

    TextField tf_nom;
    TextField tf_desc;
    TextField tf_effectif;
    TextField tf_domaine;
   // Label lblemailtest;
    Label testVide;
      
         LocalNotification n;
    public AddCommand() throws IOException {

       // f = new Form("ADD to cart.", new BoxLayout(BoxLayout.Y_AXIS));
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
               
AffichageProduct afp=new AffichageProduct();
 System.out.println("jskxsjkjsjs"+afp.sproduct.prod.getId_Product());
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
        ServiceCommand es = new ServiceCommand();
      
        Resources theme = UIManager.initFirstTheme("/theme");
        
        Image back;
       
              // back = Image.createImage("/back-command.png");
              //back.scaled(150, 150);
             // getToolbar().addCommandToLeftBar(" ", back, (ev) -> {
            //try {
               // AffichageProduct myp = new AffichageProduct();
              //  myp.showBack();
           // } catch (IOException ex) {
              
          //  }
      //  });
        
               
         getToolbar().addCommandToOverflowMenu("Display Command",videImg, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                try {
                    AffichageCommand add=new AffichageCommand();
                    add.show();
                } catch (IOException ex) {
                    
                }
            }
        });
        
       
      
        


       
        ServiceCommand prodServ = new ServiceCommand();

        //-------------------------------Components-------------------------------------
        tf_nom = new TextField("", "quantity", 20, TextArea.ANY);
        tf_desc = new TextField("", "paid", 20, TextArea.ANY);
       
       // tf_effectif=new TextField("", "date", 20, TextArea.ANY);
        
         
       tf_nom.getAllStyles().setFgColor(0x000000);
          tf_desc.getAllStyles().setFgColor(0x000000);

        
        add(tf_nom);
        add(tf_desc);
     
        Button add = new Button("Add");
        Button nonparticiper = new Button("Remove Command");
             add(nonparticiper);
            nonparticiper.setVisible(false);


        add.addActionListener(new ActionListener() {
            private String textAttachmentUri;
            private String imageAttachmentUri;
            
            @Override
            public void actionPerformed(ActionEvent evt) {
               
                          
                try {  
                Command e = new Command();
             if (Integer.parseInt(tf_nom.getText())<0  ||Integer.parseInt(tf_desc.getText())<0){   
                     Dialog.show("ERROR", "Please write something positive", "OK", null);
}else{
                e.setQuantity_Product(Integer.parseInt(tf_nom.getText()));
                
                e.setPaid(Integer.parseInt(tf_desc.getText()));
               
                 e.setId_Product(afp.sproduct.prod.getId_Product()+950);
                   prodServ.ParticipEvent(e);
                   nonparticiper.setVisible(true);
                     add.setVisible(false);
                       SMSAPI sms = new SMSAPI("hello  "+tf_nom.getText()+"  successful purchase  ", "+21628183067");

            
}
            
                 
                            
             
             } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Status must be a number", "OK", null);
                }


           }
            
        });

        
        add(add);
           
             ServiceCommand sc=new ServiceCommand();
                

        ArrayList<Command> comms = sc.getListEvenements();
        
            for(int k= comms.size() - 1; k >= 0; k--){
                
                Container commz = new Container(BoxLayout.x());
                Command currcomm = comms.get(k);
              System.out.println(currcomm);

      nonparticiper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                                if(Dialog.show("delete", "Are you sure?", "OK", "Cancel")){
                                    //System.out.println(currcomm.getId_Command()); 
                                    int kh=currcomm.getId_Command()+1;
                                     System.out.println(kh);
                    ConnectionRequest con = new ConnectionRequest();
             con.setUrl("http://localhost/donationWEB/web/app_dev.php/donationm/products/DeleteCommandeMobile/"+kh);
                            System.out.println("The order has been canceled.");
                            ToastBar.showMessage("The order has been canceled",FontImage.MATERIAL_DONE);

                        
                    
                    
                    con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                    nonparticiper.setVisible(false);
                    add.setVisible(true);
 
                                }
                                
                            }
                            });
          break;
                        }
                        
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


   /* public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }*/
    
}

