package electronics.store.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Store {
    private static final AtomicInteger idCounter = new AtomicInteger();
    private final int id;
    private String name;

    // Пустой конструктор
    public Store() {
        this.id = idCounter.incrementAndGet(); // Генерация ID
    }

    public Store(String name) {
        this.id = idCounter.incrementAndGet();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
