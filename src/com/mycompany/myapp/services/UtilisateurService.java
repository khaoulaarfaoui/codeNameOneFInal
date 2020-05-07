/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Employee;
import com.mycompany.myapp.entities.FosUser;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class UtilisateurService {
    
public ArrayList<FosUser> FosUsers;
public static UtilisateurService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public UtilisateurService() {
         req = new ConnectionRequest();
    }

    public static UtilisateurService getInstance() {
        if (instance == null) {
            instance = new UtilisateurService();
        }
        return instance;
    }

   public ArrayList<FosUser> parseEmployees(String jsonText){
        try {
            FosUsers=new ArrayList<>(); 
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> EmployeesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)EmployeesListJson.get("root");
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
         
                FosUser E = new FosUser();
               // float id = Float.parseFloat(obj.get("id").toString());
               
               E.setUsername(obj.get("username").toString());
               E.setFirstName(obj.get("firstName").toString());
               E.setLastName(obj.get("lastName").toString());           
               E.setPassword(obj.get("password").toString());
                E.setEmail(obj.get("email").toString());
               
                        
//Ajouter la tâche extraite de la réponse Json à la liste
                FosUsers.add(E);
            }
            
            
        }
        catch (IOException ex) {
            
        }  
return FosUsers;
   }
    public ArrayList<FosUser> getAllUsers(){
        String url = Statics.BASE_URL+"/allusers";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                FosUsers = parseEmployees(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return FosUsers;
    }
    public boolean AddUser(FosUser t) {
        String url = Statics.BASE_URL + "/user/add?" +"username="+ t.getUsername()+"&firstName="+ t.getFirstName() +"&lastName="+t.getLastName()+"&password="+t.getPassword()+"&email="+t.getEmail() ; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }


}