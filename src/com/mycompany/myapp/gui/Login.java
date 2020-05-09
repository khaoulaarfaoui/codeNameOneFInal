/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.FosUser;
import com.mycompany.myapp.services.UtilisateurService;
import java.util.ArrayList;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author khaoula
 */
public class Login1 extends Form{
    int j=0;
    boolean find= true;
    UtilisateurService service= new UtilisateurService();

    ArrayList<FosUser> users=service.getInstance().getAllUsers(); 
    public Login1(Form previous) {
        
        setTitle("Login");
        setLayout(BoxLayout.y());
         TextField tfUsrnm = new TextField();
        tfUsrnm.setHint("username");
        TextField tfPassword = new TextField();
        tfPassword.setHint("password");
        Button lgnBtn = new Button("Login");
        lgnBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
                    int taille=users.size();
                    while((j<taille) &&(find==true))
                    {
                    if (tfUsrnm.getText().equals(users.get(j).getUsername()))
                    {  
                    find=false;
                    System.out.println("aaaaaaaaaaaaaaaaaaaa"+j);
                    String hashedPassword = users.get(j).getPassword();
                    String  hashedpassword = hashedPassword.substring(0, 2) + 'a' + hashedPassword.substring(3);
                    boolean ham= BCrypt.checkpw(tfPassword.getText(), hashedpassword); 
                    if  (ham) 
                    {
                        System.out.println("Succes");
                       
                        Dialog.show("Connecté", "Connecté en tant que  " + tfUsrnm.getText(), "Ok", "Cancel");
                        new HomeForm().show();
                    } 
                    
                    
                    else 
                    {
                        Dialog.show("Authentification échoué", "Login ou mot de passe incorrect", "Ok", "Cancel");
                            System.out.println("wrong password ");
                    }  
                    
                    }
                    j++;
                    }

                if (j==users.size()){
                    Dialog.show("Authentification échoué", "Login ou mot de passe incorrect", "Ok", "Cancel");
                }
                    
                });
 
       
      
        add(tfUsrnm);
        add(tfPassword);
        add(lgnBtn);
        setBackCommand(new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                showBack();
            }
        });

    }
    }

