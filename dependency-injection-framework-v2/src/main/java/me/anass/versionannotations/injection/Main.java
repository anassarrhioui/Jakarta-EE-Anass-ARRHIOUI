package me.anass.versionannotations.injection;

public class Main {
    public static void main(String[] args) {
        try {
            ApplicationContext applicationContext = new ApplicationContext(ContextConfguration.class);
            EntityRepository entityRepository = applicationContext.getInstance(EntityRepository.class);
            System.out.println("entityRepository = " + entityRepository);
            EntityMetier entityMetier = applicationContext.getInstance(EntityMetier.class);
            Entity entity = entityMetier.getEntityDetails(1);
            System.out.println(entity);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
