package com.lcwd.todo.todocontroller;

import com.lcwd.todo.model.Todo;
import com.lcwd.todo.services.Todoservices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
            private Todoservices todoservices;
Random random=new Random();
    Logger logger= LoggerFactory.getLogger(TodoController.class);
@PostMapping
    public ResponseEntity<Todo> createtodohandler(@RequestBody Todo todo) {
    int id = random.nextInt(99999);
    todo.setId(id);
    Date currdate=new Date();
    logger.info("current date:{}",currdate);
    logger.info("tododate {}",todo.getToDate());
    todo.setAddedDate(currdate);
    logger.info("Create Todo");
    Todo todo1 = Todoservices.createtodo(todo);
    return new ResponseEntity<>(todo1, HttpStatus.CREATED);
}
@GetMapping
 public ResponseEntity<List<Todo>> getalltodohandler(){
 List<Todo>allTodos=Todoservices.getAllTodos();
 return new ResponseEntity<>(allTodos, HttpStatus.OK);
 }
 @GetMapping("/{todoId}")
    public ResponseEntity <Todo>getsingletodohandler(@PathVariable int todoId){
Todo todo=Todoservices.getTodo(todoId);
return ResponseEntity.ok(todo);
 }

 // updade todo
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo newTodo,@PathVariable int todoId){

 Todo todo= todoservices.updateTodo(todoId,newTodo);
return ResponseEntity.ok(todo);
    }
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deletetodo(@PathVariable int todoId){
todoservices.deleteTodo(todoId);
return ResponseEntity.ok("todo successfully created");
    }
    }

