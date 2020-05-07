/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;


/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public HomeForm() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddProduct = new Button("Find Emp By ID");
        Button btnListProduct = new Button("Find Emp By ID");
        Button btnListEmp = new Button("Find All Employees");
      //  Button btnchercher = new Button("Find Emp By ID");
        Button AjouterEmp = new Button("Ajouter Employee");
        Button Supprimer = new Button("Delete Employee");
        Button update = new Button("update Employee");
       Button register = new Button("REGISTER");
       Button login = new Button("Login");
        
        btnAddProduct.addActionListener(e -> new EmpByIdForm(current).show());
       btnListEmp.addActionListener(e -> new ListEmpsForm(current).show());
        //btnListProduct.addActionListener(e -> new ListOneEmp(current).show());
        Supprimer.addActionListener(e -> new SupprimerEmpForm(current).show());
        register.addActionListener(e -> new Register(current).show());
        login.addActionListener(e -> new Login1(current).show());
       /* update.addActionListener(e -> {
            try {
                new UpdateForm(current).show();
            } catch (IOException ex) {
                Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
*/
        //login.addActionListener(e -> new LoginForm(current).show());
        addAll(btnAddProduct,btnListEmp, AjouterEmp,Supprimer, update,register,login);

    }

}
