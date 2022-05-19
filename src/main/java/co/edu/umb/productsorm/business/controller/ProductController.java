package co.edu.umb.productsorm.business.controller;

import co.edu.umb.productsorm.business.service.ProductService;
import co.edu.umb.productsorm.domain.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public record ProductController(ProductService productService) {

  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("products", productService.getProducts());
    return "index";
  }

  @PostMapping("/save")
  public String save(Product product, Model model) {
    StringBuilder message = new StringBuilder();
    message.append("Product edited");
    if (product.getId() != null) {
      productService.create(product);
      message.delete(0, message.length())
        .append("Product created");
    }
    productService.edit(product);
    model.addAttribute("message", message.toString());
    return "redirect:/";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") Integer id, Model model){
    model.addAttribute("product", productService.findById(id));
    return "redirect:/edit";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable("id") Integer id, Model model){
    model.addAttribute("product", productService.findById(id));
    return "redirect:/confirm";
  }

}
