package me.anass.versionannotations.injection;

import me.anass.versionannotations.annotations.Component;
import me.anass.versionannotations.annotations.AutoWired;

import java.util.List;

@Component
public class EntityMetier {
    @AutoWired
    private EntityRepository repository;

    public Entity getEntityDetails(Integer orderId) {
        return repository.getById(orderId);
    }

    public List<Entity> getAll() {
        return repository.findAll();
    }
}
