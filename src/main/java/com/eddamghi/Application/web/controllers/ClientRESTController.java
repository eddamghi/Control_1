package com.eddamghi.Application.web.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.eddamghi.Application.DAO.repositories.ClientRepository;
import com.eddamghi.Application.DAO.entities.Client;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClientRESTController {
//    private ClientRepository clientRepository;
//
//    @GetMapping("/admin/clients/index")
//    public List<Client> getAllClients(){
//        return clientRepository.findAll();
//    }
//    @GetMapping("/admin/clients/{id}")
//    public Client getClient(@PathVariable Long id){
//        return clientRepository.findById(id)
//                .orElseThrow(()->new RuntimeException(String.format("Client %s not found", id)));
//    }
//    @PostMapping("/admin/clients/index")
//    public Client saveClient(@RequestBody Client client){
//        return clientRepository.save(client);
//    }
//    @DeleteMapping("/admin/clients/{id}")
//    public void deleteClient(@PathVariable Long id){
//        clientRepository.deleteById(id);
//    }
}
