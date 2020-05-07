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
import java.io.IOException;



/**
 *
 * @author khaoula
 */
public class ListOneEmp extends Form{
    public ListOneEmp(Form previous) {
       setTitle("List EMP");
        setLayout(BoxLayout.y());
        
        TextField tfid = new TextField("","id");
        TextField sp = new TextField("");
         Button btnValider = new Button("Chercher Employee");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfid.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                   int id=Integer.parseInt(tfid.getText());
                   
                    try {
                        ServiceEmp.getInstance().GetEmployeeId(id);
                        System.out.println("YES");
                    } catch (IOException ex) {
                    }
                  
                    
                }
                
                
            }
        });
        

        add(tfid,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
}
}