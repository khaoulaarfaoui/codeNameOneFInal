/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;

import com.codename1.ui.TextField;

import com.mycompany.myapp.entities.Employee;

import com.mycompany.myapp.services.ServiceEmp;

import java.io.IOException;



/**
 *
 * @author khaoula
 */
public class UpdateForm extends Form{
    private Image img;
    private String imgPath;

    public UpdateForm(Form previous) throws IOException {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
       
        TextField tfId = new TextField("","ID");
        TextField tfName = new TextField("","Name");
        TextField tfLastName = new TextField("","Last Name");
        TextField tfEmail = new TextField("","Email");
        TextField tfFonction = new TextField("","Fonction");
        ImageViewer iv = new ImageViewer(Image.createImage(1000, 1000, 0xffff0000));
        Button btnGet=new Button ("get employee by id");
        Button btnupdate=new Button ("update employee");
        Button btnphoto=new Button ("upload photo");
    
    /*    iv.addPointerReleasedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        imgPath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                        img = Image.createImage(imgPath);                      
                        iv.setImage(img);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            });
    
    */
    
    
     btnphoto.addActionListener((e) -> {
      
try {
                        imgPath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                        img = Image.createImage(imgPath);     
                        String a= imgPath.substring(12);
                        System.out.println("lenaaaaaaaaaaaaaaaa"+a);
                        iv.setImage(img);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                
        });
    btnGet.addActionListener((e) -> {
            if ((tfId.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {

                    try {
                        int id=Integer.parseInt(tfId.getText());
                        System.out.println(id);
                        ServiceEmp SE=new ServiceEmp();
                       Employee emp= SE.GetEmployeeId(id);
                       System.out.println(emp.toString());
                       tfName.setText(emp.getName());
                       tfLastName.setText(emp.getLast_name());
                        tfEmail.setText(emp.getEmail());
                        tfFonction.setText(emp.getFonction());
                        
                      } 
                    catch (IOException ex) {
                  System.out.println("NOOOOOO");

                    }
                   System.out.println("YES");
                  
                }

        });

        btnupdate.addActionListener((e) -> {
            ServiceEmp ser = new ServiceEmp();
            Employee p1=new Employee(tfFonction.getText(), tfName.getText(), tfLastName.getText(),imgPath.substring(12),tfEmail.getText());
            ser.updateEmp(p1,Integer.parseInt(tfId.getText()));
             Dialog.show("Alert", "OPERATION DONE WITH SUCCESS", new Command("OK"));
            

        });
      
        addAll(tfId,tfName,tfLastName,tfEmail,tfFonction,btnGet,btnupdate,iv,btnphoto);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
