/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.controller.web;

/**
 *
 * @author cuartz
 */
import org.springframework.web.util.WebUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import com.sivalabs.app.entities.Todo;
//import com.sivalabs.app.repos.TodoRepository;
import java.util.Date;
 
@RestController
@RequestMapping("/todos")
public class TodoController {
 
//    @Autowired
//    private TodoRepository todoRepository;
    
    private static List<Todo> todos=new ArrayList<>();
    
    static{
		try {			
			Todo t1 = new Todo(1, "Task 1", new Date());
			Todo t2 = new Todo(2, "Task 2", new Date());
			Todo t3 = new Todo(3, "Task 3", new Date());
			todos.add(t1);
                        todos.add(t2);
                        todos.add(t3);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
 
    @RequestMapping(value="", method=RequestMethod.GET)
    public List<Todo> persons() {
        return todos;
    }    
    @RequestMapping(value="", method=RequestMethod.POST)
    public Todo create(@RequestBody Todo todo) {
        todos.add(todo);
		return todo;
	}
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        todos.remove(new Todo(id));
	}
    
}