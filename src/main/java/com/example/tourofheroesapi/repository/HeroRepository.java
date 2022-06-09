package com.example.tourofheroesapi.repository;

import com.example.tourofheroesapi.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeroRepository extends JpaRepository<Hero, Long> {
    Hero getHeroByName(String name);
    List<Hero> getHeroesByPower(String power);

}
