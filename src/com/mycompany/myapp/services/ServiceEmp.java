/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import static com.codename1.ui.TextArea.URL;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Employee;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author bhk
 */
public class ServiceEmp {

    public ArrayList<Employee> Employees;
    
    public static ServiceEmp instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceEmp() {
         req = new ConnectionRequest();
    }

    public static ServiceEmp getInstance() {
        if (instance == null) {
            instance = new ServiceEmp();
        }
        return instance;
    }

    public boolean AddEmp(Employee t) {
        String url = Statics.BASE_URL + "/emp/add?" +"Fonction="+ t.getFonction()+"&Name="+ t.getName() +"&Last_name="+t.getLast_name()+"&email="+t.getEmail() ; //création de l'URL
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

    public ArrayList<Employee> parseEmployees(String jsonText){
        try {
            Employees=new ArrayList<>(); 
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> EmployeesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)EmployeesListJson.get("root");
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
         
                Employee E = new Employee();
                float id = Float.parseFloat(obj.get("id").toString());
                E.setId((int)id);
                E.setName(obj.get("Name").toString());
                E.setLast_name(obj.get("lastName").toString());
                E.setEmail(obj.get("email").toString());
                E.setFonction(obj.get("Fonction").toString());
                        
//Ajouter la tâche extraite de la réponse Json à la liste
                Employees.add(E);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return Employees;
    }

    public Employee emp;
    public int j=0;
    
    public Employee GetEmployeeId(int i) throws IOException{
     String url = Statics.BASE_URL+"/employee/find/"+i;
        req.setUrl(url);
        req.setPost(false);
        Employees=getAllEmployees();
        while(j<Employees.size())
        {
        if (Employees.get(j).getId()== i){
        return emp = Employees.get(j);
        }
        }
      
        NetworkManager.getInstance().addToQueueAndWait(req);
     return emp;
    }
    
    public ArrayList<Employee> getAllEmployees(){
        String url = Statics.BASE_URL+"/employee/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Employees = parseEmployees(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Employees;
    }
    
    public void SupprimerEmployee(int id) {
       String url = Statics.BASE_URL+"/emp/delete/"+id;
        req.setUrl(url);
        req.setPost(false);
       NetworkManager.getInstance().addToQueueAndWait(req);
       
    }
    public void updateEmp(Employee t,int x) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = Statics.BASE_URL+"/emp/update/"+x +"?Name="+ t.getName() +"&Fonction="+t.getFonction()+"&Last_name="+t.getLast_name()+"&image="+t.getImage()+"&email="+t.getEmail();
        con.setUrl(Url);

         con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueue(con);
        System.out.println("yes!");
    }

}
