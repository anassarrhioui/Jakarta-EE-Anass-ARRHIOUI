package me.anass.versionannotations.injection;

import me.anass.versionannotations.annotations.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EntityRepository {
    Map<Integer, Entity> entityMap = new HashMap<>();

    EntityRepository() {
        entityMap.put(1, new Entity(1L, "Entity1"));
        entityMap.put(2, new Entity(2L, "Entity2"));
        entityMap.put(3, new Entity(3L, "Entity3"));
        entityMap.put(4, new Entity(4L, "Entity4"));
        entityMap.put(5, new Entity(5L, "Entity5"));
        entityMap.put(6, new Entity(6L, "Entity6"));
        entityMap.put(7, new Entity(7L, "Entity7"));
    }

    public Entity getById(Integer orderId) {
        return entityMap.get(orderId);
    }

    public List<Entity> findAll(){
        return new ArrayList<>(this.entityMap.values());
    }

}
