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
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
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
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Association;
import com.mycompany.myapp.entities.Command;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.services.ServiceAssociation;
import com.mycompany.myapp.services.ServiceCommand;
import com.mycompany.myapp.services.ServiceProduct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.Map;



/**
 *
 * @author ASUS
 */
public class AffichageCommand  extends BaseForm{
    
    Form f;
    ImageViewer ip;
    List<Command> lse = new ArrayList();
        ArrayList<Command> form;
   
    
 Image videImg;

    public AffichageCommand() throws IOException {

      //  f = new Form("Command list", BoxLayout.y());
          
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
    
        Slider slider = new Slider();
        slider.setPreferredSize(new Dimension(256, 2));
        add(slider);
     
                
        //Toolbar tb = getToolbar();
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
        Image back;
       
               back = Image.createImage("/back-command.png");
              
               getToolbar().addCommandToLeftBar(" ", back, (ev) -> {
            try {
                    AffichageProduct myp = new AffichageProduct();
                myp.showBack();
            } catch (IOException ex) {
              
            }
        });
             
        lse = new ServiceCommand().getListEvenements();
        for (int i = 0; i < lse.size(); i++) {
        
            addItem(lse.get(i));
         
        
              Button participer = new Button("Détails");
     
             add(participer);
            
            
                   
                
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
   

    

    public void addItem(Command e) {

        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
          ServiceProduct sp=new ServiceProduct();
                ServiceCommand sc=new ServiceCommand();
        ArrayList<Product> Products = sp.getListEvenements();
        ArrayList<Command> v =sc.getListEvenements();
            System.out.println("display command");
          Label nomproduct = new Label();
           for(int j=Products.size()-1;j>=0;j--){
                
                 //System.out.println(i);
              System.out.println(e.getId_Product());
                System.out.println(Products.get(j).getId_Product());
                System.out.println("");
                if (e.getId_Product()==Products.get(j).getId_Product()){
                    
                        nomproduct.setText("Product'name: "+Products.get(j).getName_Product());
                
                }
    }
           Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l2 = new Label("paid:"+e.getPaid());
        Label l = new Label("date:" +e.getDate_Command());
        Label lq=new Label("Quantity: "+e.getQuantity_Product()+"items");
        l.getAllStyles().setFgColor(0x000000);
        l2.getAllStyles().setFgColor(0x000000);
        lq.getAllStyles().setFgColor(0x000000);
       nomproduct.getAllStyles().setFgColor(0x000000);
        c2.add(l);
   
        c2.add(l2);
       c2.add(lq);
       c2.add(nomproduct);
        c1.add(c2);
       
    
        refreshTheme();


     

     add(c1);
        
    
    
    

   /* public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }*/

    
}}
