package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.restfulwebservices.todo.repository.TodoRespository;

@RestController
public class TodoJpaResource 
{
	private TodoService todoService;
	private TodoRespository todoRespository;
	
	public TodoJpaResource(TodoService todoService, TodoRespository todoRespository) {
		this.todoService = todoService;
		this.todoRespository = todoRespository;
	}
	
	@GetMapping("/users/{username}/todos")
	public List<Todo> retrieveTodos(@PathVariable String username) 
	{
//		return todoService.findByUsername(username);
		return todoRespository.findByUsername(username);
		
	}
	@GetMapping("/users/{username}/todos/{id}")
	public Todo retrieveTodo(@PathVariable String username, @PathVariable int id) 
	{
//		return todoService.findById(id);
		return todoRespository.findById(id).get();
		
	}
	
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id) 
	{
//		todoService.deleteById(id);
		todoRespository.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping("/users/{username}/todos/{id}")
	public Todo updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo) 
	{
//		todoService.updateTodo(todo);
		todoRespository.save(todo);
		return todo;
		
	}
	
	@PostMapping("/users/{username}/todos")
	public Todo createTodo(@PathVariable String username, @RequestBody Todo todo) 
	{
		todo.setUsername(username);
		todo.setId(null);
		return todoRespository.save(todo);
//		Todo createdTodo = todoService.addTodo(username,todo.getDescription(),todo.getTargetDate(),todo.isDone());
//		return createdTodo;
		
	}

}
