/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Employee;
import com.mycompany.myapp.entities.FosUser;
import com.mycompany.myapp.services.ServiceEmp;
import com.mycompany.myapp.services.UtilisateurService;
import java.io.IOException;
import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author khaoula
 */
public class Register extends Form{
 Form current; 
 

    public Register(Form previous) {
        current = this; 
        setTitle("Login");
        setLayout(BoxLayout.y());
        TextField tfUsrname = new TextField();
        tfUsrname.setHint("username");
        TextField tfFirstName = new TextField();
        tfFirstName.setHint("FirstName");
        TextField tfLastName = new TextField();
        tfLastName.setHint("LastName");
        TextField tfEmail = new TextField();
        tfEmail.setHint("email");
        TextField tfPassword = new TextField();
         tfPassword.setHint("password");
        TextField tfConfirmPswd = new TextField();
        tfConfirmPswd.setHint("password2");
        Button registerBtn = new Button("Register");
        
        
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               {
                            UtilisateurService service= new UtilisateurService();
                            FosUser userDAO=new FosUser();
                          ArrayList<FosUser> users=service.getInstance().getAllUsers();

                            //regestration conditions
                            if ((tfUsrname.getText().equals("")) || (tfEmail.getText().equals("")) || (tfPassword.getText().equals("")) || (tfConfirmPswd.getText().equals(""))) {
                                Dialog.show("Erreur", "Please fill all fields", "Ok", null);
                            }
                            if (!(tfPassword.getText().length() > 7)) {
                                Dialog.show("Erreur", "Password should contain at least 8 characters", "Ok", "Cancel");
                                tfPassword.setText("");
                            } else {
                                if (//(userDAO.getUsersList(new String(con2.getResponseData())).size() == 0)
                                       // && (userDAO.getUsersList(new String(con.getResponseData())).size()) == 0
                                         (tfPassword.getText().equals(tfConfirmPswd.getText()))
                                        && (tfPassword.getText().length() > 7)
                                        && (tfEmail.getText().endsWith("@esprit.tn") || tfEmail.getText().endsWith("@gmail.com") || tfEmail.getText().endsWith("@hotmail.com") || tfEmail.getText().endsWith("@yahoo.com") || tfEmail.getText().endsWith("@yahoo.fr") || tfEmail.getText().endsWith("@hotmail.fr"))
                                        && (!tfUsrname.getText().equals(""))
                                         && (!tfEmail.getText().equals(""))) {
                                    String originalPassword = tfPassword.getText();
                                    String salt = org.springframework.security.crypto.bcrypt.BCrypt.gensalt(13);
                                    String thashed_password = org.springframework.security.crypto.bcrypt.BCrypt.hashpw(originalPassword, salt);
                                    String hashed_password = thashed_password.substring(0, 2) + 'y' + thashed_password.substring(3);
       

                                    FosUser u= new FosUser(tfUsrname.getText(), tfFirstName.getText(),tfLastName.getText(),hashed_password,tfEmail.getText());
                                    service.AddUser(u);
                                     Dialog.show("GOOD", "DONE", "Ok", null);
                                   // userDAO.registration(tfUsrname.getText(), tfEmail.getText(), generatedSecuredPasswordHash);
                                  //  new Login1().show();
                                }  //
                             /*   if (
                                     (userDAO.getUsersList(new String(con2.getResponseData())).size() > 0)) {
                                    Dialog.show("Erreur", "Mail already exists", "Ok", null);
                                    tfEmail.setText("");
                                }
                                /*if ((userDAO.getUsersList(new String(con.getResponseData())).size() > 0)) {
                                Dialog.show("Erreur", "Username already in use", "Ok", null);
                                tfUsrname.setText("");
                            }*/
                                if (!(tfPassword.getText().equals(tfConfirmPswd.getText()))) {
                                    Dialog.show("Erreur", "Passwords not matching", "Ok", null);
                                    tfPassword.setText("");
                                    tfConfirmPswd.setText("");
                                }
                                if (!(tfEmail.getText().endsWith("@esprit.tn") || tfEmail.getText().endsWith("@gtfEmail.com") || tfEmail.getText().endsWith("@hottfEmail.com") || tfEmail.getText().endsWith("@yahoo.com") || tfEmail.getText().endsWith("@yahoo.fr") || tfEmail.getText().endsWith("@hottfEmail.fr"))) {
                                    Dialog.show("Erreur", "Please correct email address", "Ok", null);
                                    tfEmail.setText("");
                                }

                            }

                        }}});

                   
    
          
        Label toLoginLbl = new Label("Already a member?");
        toLoginLbl.getUnselectedStyle().setAlignment(Component.CENTER);
        Font fnt = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
        toLoginLbl.getUnselectedStyle().setFont(fnt);
   
 
        toLoginLbl.addPointerReleasedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) { 
                     new Login1(current).show(); }
        });
      
        addAll(tfUsrname,tfFirstName, tfLastName, tfEmail,tfPassword,tfConfirmPswd,registerBtn );
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    
    }}
    

