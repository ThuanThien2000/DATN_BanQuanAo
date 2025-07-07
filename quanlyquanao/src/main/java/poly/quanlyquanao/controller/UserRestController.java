package poly.quanlyquanao.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.model.User;
import poly.quanlyquanao.service.Impl.IUserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserRestController {
    @Autowired
    IUserService _userService;

//    @GetMapping("/page")
//    public Page<User> getPageUser(
//        @RequestParam(defaultValue = "0") int page,
//        @RequestParam(defaultValue = "3") int size){
//        Pageable pageable = PageRequest.of(page, size);
//        return _userService.getPageUser(pageable);
//    }

    @GetMapping("/list")
    public List<User> list() {
        return _userService.findAll();
    }
    
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try {
            User saveUser = _userService.add(user);
            return ResponseEntity.ok(saveUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            User updatedUser = _userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/statusOne")
    public List<User> user(){
        return _userService.findByStatusOne();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        User user = _userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        _userService.deleteUser(id);
        return ResponseEntity.ok("Đã xóa thành công");
    }

}