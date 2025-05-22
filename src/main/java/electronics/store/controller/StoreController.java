package electronics.store.controller;

import electronics.store.model.Store;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/stores")
public class StoreController {
    private List<Store> stores = new ArrayList<>();

    // Получение всех книг
    @GetMapping
    public List<Store> getAllStores() {
        return stores;
    }

    // Добавление книги
    @PostMapping
    public ResponseEntity<Store> addStore(@RequestBody Store store) {
        stores.add(store);
        return new ResponseEntity<>(store, HttpStatus.CREATED);
    }

    // Обновление книги
    @PutMapping("/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable int id, @RequestBody Store updatedStore) {
        for (Store store : stores) {
            if (store.getId() == id) {
                store.setName(updatedStore.getName());
                return new ResponseEntity<>(store, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Удаление книги
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable int id) {
        stores.removeIf(store -> store.getId() == id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


