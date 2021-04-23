package com.deloitte.todo.controllers;

import com.deloitte.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.deloitte.todo.domain.Todo;

@RequiredArgsConstructor
@Controller
public class TodoController {

	private final TodoService todoService;

	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping
	public String index() {
		return "index";
	}

	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/api/todos")
	public String todos(Model model) {
		model.addAttribute("todos", todoService.findAll());
		return "todos";
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/api/todoNew")
	public String add(@RequestParam String todoItem, @RequestParam String status, Model model) {
		Todo todo = new Todo();
		todo.setTodoItem(todoItem);
		todo.setCompleted(status);
		todoService.save(todo);
		model.addAttribute("todos", todoService.findAll());
		return "redirect:/api/todos";
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/api/todoDelete/{id}")
	public String delete(@PathVariable long id, Model model) {
		todoService.deleteById(id);
		model.addAttribute("todos", todoService.findAll());
		return "redirect:/api/todos";
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/api/todoUpdate/{id}")
	public String update(@PathVariable long id, Model model) {
		Todo todo = todoService.findById(id);
		if("Yes".equals(todo.getCompleted())) {
			todo.setCompleted("No");
		}
		else {
			todo.setCompleted("Yes");
		}
		todoService.save(todo);
		model.addAttribute("todos", todoService.findAll());
		return "redirect:/api/todos";
	}
}
