package com.corelia.controller;

import com.corelia.base.SearchRequest;
import com.corelia.base.SuccessResponse;
import com.corelia.base.SuccessResponsePage;
import com.corelia.dto.UserContactDto;
import com.corelia.mapper.UserContactMapper;
import com.corelia.mapper.UserMapper;
import com.corelia.model.UserContact;
import com.corelia.service.AuthService;
import com.corelia.service.UserContactService;
import com.corelia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user-contacts")
@RequiredArgsConstructor
public class UserContactController {

    private final UserContactService userContactService;
    private final UserContactMapper userContactMapper;


    @PostMapping
    public ResponseEntity<?> insert(@RequestBody UserContactDto dto) {
        UserContact entity = userContactMapper.unMap(dto);
        UserContact result = userContactService.insert(entity);
        return ResponseEntity.ok(new SuccessResponse<UserContactDto>(userContactMapper.map(result)));
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<?> findAll(@PathVariable Long userId, @RequestBody Optional<SearchRequest> searchRequest) {
        Page<UserContact> userContacts = userContactService.findAllByUserId(searchRequest,userId);
        return ResponseEntity.ok(new SuccessResponsePage<>(userContacts.map(userContactMapper::map)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        UserContact userContacts = userContactService.findById(id);
        return ResponseEntity.ok(new SuccessResponse<>(userContactMapper.map(userContacts)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        userContactService.deleteById(id);
        return ResponseEntity.ok(new SuccessResponse<>(null));
    }

}
