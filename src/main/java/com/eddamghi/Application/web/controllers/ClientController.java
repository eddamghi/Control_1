package com.eddamghi.Application.web.controllers;

import com.eddamghi.Application.DAO.entities.Client;
import com.eddamghi.Application.DAO.repositories.ClientRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/admin/clients/index")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String kw
    ){
        Page<Client> pageClients = clientRepository.findByNameContains(kw, PageRequest.of(page,size));
        model.addAttribute("listClients",pageClients.getContent());
        model.addAttribute("pages",new int[pageClients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",kw);
        return "clients";
    }
    @GetMapping("/admin/clients/deleteClient")
    public String deleteClient(@RequestParam(name = "id") Long id, String keyword, int page){
        clientRepository.deleteById(id);
        return "redirect:/admin/clients/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/clients/formClient")
    public String formClient(Model model ){
        model.addAttribute("client",new Client());
        return "formClient";
    }
    @PostMapping("/admin/clients/saveClient")
    public String saveClient(@Valid Client client, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "formClient";
        clientRepository.save(client);
        return "formClient";
    }
    @GetMapping("/admin/clients/editClient")
    public String editClient(@RequestParam(name = "id") Long id, Model model){
        Client client = clientRepository.findById(id).get();
        model.addAttribute("client",client);
        return "editClient";
    }
    @GetMapping("/")
    public String home(){
        return "redirect:/admin/clients/index";
    }



}

