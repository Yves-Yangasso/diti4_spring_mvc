package diti.REST;


import diti.dto.ProduitDTO;
import diti.mapper.ProduitMapper;
import diti.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/produit")
public class ProduitRestController {


    @Autowired
    private ProductService productService;

    @Autowired
    private ProduitMapper produitMapper;


    @GetMapping
    public List<ProduitDTO>  getList(){
        List<ProduitDTO> dtos = new ArrayList<>();
        productService.findAll().forEach(produit -> dtos.add(produitMapper.toDto(produit)));
        return dtos;
    }

    @PostMapping
    public String save(@Valid @RequestBody ProduitDTO produitDTO){
        productService.save(produitMapper.toEntity(produitDTO));
        return "produit ajoute avec succes";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        productService.delete(id);
        return "produit supprime avec succes";
    }


    @GetMapping("/{id}")
    public ProduitDTO getById(@PathVariable Long id){
        return  produitMapper.toDto(productService.findById(id));
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        ProduitDTO produitDTO =  produitMapper.toDto(productService.findById(id));
        model.addAttribute("produit", produitDTO);
        return "form-product";
    }


}

