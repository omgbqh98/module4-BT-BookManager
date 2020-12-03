package controller;

import model.Book;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.BookService;
import service.CategoryService;

import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categorys")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping("/book")
    public ModelAndView listBook(@RequestParam("s") Optional<String> s) {
        Iterable<Book> books;
        if (s.isPresent()) {
            books = bookService.findAllByNameContaining(s.get());
        } else {
            books = bookService.findAll();
        }
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Validated @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/create");
            return modelAndView;
        }
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("book", new Book());
        modelAndView.addObject("message", "create success");
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Book book = bookService.finById(id);
        if(book != null) {
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("book", book);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateCustomer(@ModelAttribute("book") Book book){
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("book", book);
        modelAndView.addObject("message", "book updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Book book = bookService.finById(id);
        if(book != null) {
            ModelAndView modelAndView = new ModelAndView("/delete");
            modelAndView.addObject("book", book);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String deleteCustomer(@ModelAttribute("book") Book book, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success", "delete success");
        bookService.remove(book.getId());
        return "redirect:/book";
    }

}
