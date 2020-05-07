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
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Employee;
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.services.ServiceEmp;
import com.mycompany.myapp.services.ServiceTask;
import java.io.IOException;


/**
 *
 * @author bhk
 */
public class EmpByIdForm extends Form{

    public EmpByIdForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
       
        setTitle("Add a new task");
        setLayout(BoxLayout.y());
        
        TextField Empid = new TextField("","TaskName");
        Label test= new Label("aaaaaaaaaaaaaaaaaaa");
        Button btnValider = new Button("Add task");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    
                if ((Empid.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {

                    try {
                        int id=Integer.parseInt(Empid.getText());
                        System.out.println(id);
                        ServiceEmp SE=new ServiceEmp();
                       Employee emp= SE.GetEmployeeId(id);
                       System.out.println(emp.toString());
                       test.setText(emp.toString());
                    } catch (IOException ex) {
                  System.out.println("NOOOOOO");

                    }
                   System.out.println("YES");
                  
   
            }}
        });
        
        addAll(Empid,btnValider,test);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
