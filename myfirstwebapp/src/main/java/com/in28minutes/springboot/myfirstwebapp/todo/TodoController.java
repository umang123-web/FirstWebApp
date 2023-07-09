  package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;




//@Controller
@SessionAttributes("name")
public class TodoController {
	
	private TodoService todoService;
	
	
	
	
public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
		
	}




	@RequestMapping("list-todos")
   public String listAllTodos(ModelMap model) {
		String username=getLoggedinUsername(model);
	
      List<Todo>todos = todoService.findByUsername(username);
	     model.addAttribute("todos", todos);
	   return "Todo";
	   
	  
	}
	
	
	@RequestMapping(value="add-todo" , method=RequestMethod.GET)
	public String ShowAlltodo(ModelMap model) {
		String username=getLoggedinUsername(model);
		Todo todo=new Todo(0 , username , "" , LocalDate.now().plusYears(1) , false);
		model.put("todo" , todo);
		return "addingtodo";
	}
	
	
	@RequestMapping(value="add-todo" , method=RequestMethod.POST)
	public String Addtodo(ModelMap model , @Valid Todo todo , BindingResult result) {
		
		if(result.hasErrors()) {
			 return "addingtodo";
		}
		String username=getLoggedinUsername(model);
		 todo.setUsername(username);
		
		todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
		return "redirect:list-todos";
				
	}
	
	//deletethetodo
	@RequestMapping("delete-todo")
	public String DeleteTodo(@RequestParam int id) {
	 
		todoService.deletebyid(id);
		return "redirect:list-todos";
	}
	
	
	@RequestMapping("update-todo")
	public String ShowupdateTodo(@RequestParam int id , ModelMap model) {
		Todo todo=todoService.findById(id);
		model.addAttribute("todo", todo);
		return "addingtodo";

		
				
	}
	
	
	@RequestMapping(value="update-todo" , method=RequestMethod.GET)
	public String ShowUpdatetodo(ModelMap model, int id) {
		
		
		Todo todo=todoService.findById(id);
		model.addAttribute("todo", todo);
		
		return "addingtodo";
	}
	
	
	@RequestMapping(value="update-todo" , method=RequestMethod.POST)
	public String updatetodo(ModelMap model , @Valid Todo todo , BindingResult result) {
		
		if(result.hasErrors()) {
		   
			return "addingtodo";
			
		}
		
		String username= getLoggedinUsername(model);
          todo.setUsername(username);
		 todoService.updateTodo(todo);
		return "redirect:list-todos";
	}
	
	private String getLoggedinUsername(ModelMap model) {
		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();

				return authentication.getName();
	}
	
}
