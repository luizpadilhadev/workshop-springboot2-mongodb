package com.nelioalves.workshopmongo.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.resources.util.URL;
import com.nelioalves.workshopmongo.services.PostService;
import com.nelioalves.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id) {
	Post obj = service.findById(id);
	return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(value ="/titleserch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findBytitle(@RequestParam(value = "text", defaultValue = "") String text) {
	text = URL.decodeParam(text);
	List<Post> list = service.findByTitle(text);
	return ResponseEntity.ok().body(list);
    }

}