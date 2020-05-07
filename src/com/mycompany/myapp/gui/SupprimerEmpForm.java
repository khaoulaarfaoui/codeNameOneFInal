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
import com.mycompany.myapp.services.ServiceEmp;

/**
 *
 * @author khaoula
 */
public class SupprimerEmpForm extends Form  {
     public SupprimerEmpForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("SUPPRIMER Emplloyee");
        setLayout(BoxLayout.y());
        
        TextField id = new TextField("","ID");
        Button delete= new Button ("SUPPRIMER");
        
        
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((id.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                   ServiceEmp emp= new ServiceEmp();
                   emp.SupprimerEmployee(Integer.parseInt(id.getText()));
                  Dialog.show("Alert", "Delete Done with Success!", new Command("OK"));
                }
            }
        });
        addAll(id,delete);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
 