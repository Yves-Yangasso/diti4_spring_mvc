package diti.controller;


import diti.dto.ProduitDTO;
import diti.entity.Produit;
import diti.mapper.ProduitMapper;
import diti.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/produit")
public class ProduitController {


    @Autowired
    private ProductService productService;

    @Autowired
    private ProduitMapper produitMapper;


    @GetMapping
    public String getList(Model model){
        List<Produit>  produits =  productService.findAll();
        model.addAttribute("produits",produits);
        return "produit";
    }


    @GetMapping("/new")
    public String form(Model model){
        model.addAttribute("produit", new ProduitDTO());
        return "form-product";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("produit") ProduitDTO produitDTO,
                       BindingResult result){
        if (result.hasErrors()) {
            return "form-product";
        }
        productService.save(produitMapper.toEntity(produitDTO));
        return "redirect:/produit";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        productService.delete(id);
        return "redirect:/produit";
    }



    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("produit", produitMapper.toDto(productService.findById(id)));
        return "form-product";
    }


}

