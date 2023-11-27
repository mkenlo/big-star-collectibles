package com.example.collectibles.controllers;

import com.example.collectibles.beans.Product;
import com.example.collectibles.dao.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

@Controller
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductRepository productRepository;
    private Executor asyncExecutor;

    public ProductController(ProductRepository productRepository, Executor asyncExecutor) {
        this.productRepository = productRepository;
        this.asyncExecutor = asyncExecutor;
    }

    @PostMapping("/search")
    public String search(@RequestParam("searchString") String keyword, Model model){
        List<Product> products = productRepository.searchByName(keyword);
        model.addAttribute("products", products);
        model.addAttribute("searchedFor", keyword);
        return "search-results";
    }

    @GetMapping("/getAllProducts")
    public DeferredResult<String> getAllProducts(Model model){
        DeferredResult<String> deferredResult = new DeferredResult<>();
        asyncExecutor.execute(()->{
            model.addAttribute("products", getProducts());
            deferredResult.setResult("product-list");
        });
        return deferredResult;
    }

    private Iterable<Product> getProducts(){
        logger.info("getting all products. We are on the Spring Executor thread");
        return productRepository.findAll();
    }

    @GetMapping("/getProductDetails")
    public String getProductDetails(Model model, @RequestParam("id") String productId){
        model.addAttribute("product", productRepository.searchById(productId));
        return "product-details";
    }

    @PostMapping("/addToCart")
    public String addToCart(Model model, @SessionAttribute("cart") Map<String, Integer> cart,
                            @RequestParam("productId") String productId, @RequestParam("quantity") Integer quantity){
        logger.info("Cart {}", cart);
        if(!cart.containsKey(productId)){
            cart.put(productId, 0);
        }
        cart.put(productId, cart.get(productId)+quantity);
        logger.info("After adding to Cart {}", cart);
        return "redirect:/getProductDetails?id="+productId;
    }
}
