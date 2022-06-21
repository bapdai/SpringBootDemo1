package com.example.demoSpringBoot1.restapi;

import com.example.demoSpringBoot1.entity.Account;
import com.example.demoSpringBoot1.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountApi {
//    CRUD
    @Autowired
    AccountService accountService;
//    Lay thong tin(C)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getList(){
        return ResponseEntity.ok(accountService.fillAll());
    }
//    Doc thong tin(R)
    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable Long id){
        Optional<Account> optionalAccount = accountService.findById(id);
        if (!optionalAccount.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalAccount.get());
    }
//    Them moi thong tin
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Account> create(@RequestBody Account product){
        return ResponseEntity.ok(accountService.save(product));
    }
//    Sua thong tin(U)
    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Account> update(@PathVariable Long id, @RequestBody Account account) {
        Optional<Account> optionalAccount = accountService.findById(id);
        if (!optionalAccount.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Account exitsAccount = optionalAccount.get();
        //     map object
        exitsAccount.setFirstName(account.getFirstName());
        exitsAccount.setLastName(account.getLastName());
        return ResponseEntity.ok(accountService.save(exitsAccount));
    }
//        Xoa thong tin
    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
            if (!accountService.findById(id).isPresent()) {
                ResponseEntity.badRequest().build();
            }
            accountService.deleteById(id);
            return ResponseEntity.ok().build();
    }
}
