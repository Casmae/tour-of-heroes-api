package com.example.tourofheroesapi.controller;


import com.example.tourofheroesapi.model.Hero;
import com.example.tourofheroesapi.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class HeroController {
    @Autowired
    HeroRepository heroRepository;

    //get all heroes
    @GetMapping("/heroes")
    public ResponseEntity<List<Hero>> getAllHeroes() {
        try {
            List<Hero> heroes = new ArrayList<Hero>();

            heroRepository.findAll().forEach(heroes::add);

            if (heroes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(heroes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    ///create a new hero in DB
    @PostMapping("/heroes")
    public ResponseEntity<Hero> createHero(@RequestBody Hero hero){
        try{
            Hero newHero = heroRepository.save(new Hero(hero.getName(), hero.getAlterEgo(), hero.getPower(), hero.getPicture()));

            return new ResponseEntity<>(newHero, HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/heroes/{id}")
    public ResponseEntity<Hero> updateHero(@PathVariable("id") Long id, @RequestBody Hero hero){

        try{
            Optional<Hero> existantHero = heroRepository.findById(id);
            if (existantHero.isPresent() ){
                Hero heroFound = existantHero.get();
                heroFound.setName(hero.getName());
                heroFound.setAlterEgo(hero.getAlterEgo());
                heroFound.setPower(hero.getPower());
                heroFound.setPicture(hero.getPicture());
                return new ResponseEntity<>(heroRepository.save(heroFound),HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/heroes/{id}")
    public ResponseEntity<HttpStatus> deleteHero(@PathVariable("id") Long id) {
        try{
                heroRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/heroes/{id}")
    public ResponseEntity<Hero> getHeroById(@PathVariable("id") Long id){

        Optional<Hero> hero = heroRepository.findById(id);
        if (hero.isPresent()){
            return new ResponseEntity<>(hero.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}

