/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Employee;
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.services.ServiceEmp;
import com.mycompany.myapp.services.ServiceTask;

/**
 *
 * @author bhk
 */
public class AddEmpForm extends Form{

    public AddEmpForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Add a new Emplloyee");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","Name");
        TextField tfLastName = new TextField("","Last Name");
        TextField tfEmail = new TextField("","Email");
        TextField tfFonction = new TextField("","Fonction");
        Button btnValider = new Button("Add Employee");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfLastName.getText().length()==0)||(tfEmail.getText().length()==0)||(tfFonction.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Employee E = new Employee(tfFonction.getText(), tfName.getText(), tfLastName.getText(),tfFonction.getText());
                        if( ServiceEmp.getInstance().AddEmp(E))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfName,tfLastName,tfEmail,tfFonction,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}