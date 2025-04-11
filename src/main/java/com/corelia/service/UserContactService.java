package com.corelia.service;

import com.corelia.base.SearchRequest;
import com.corelia.error.BadRequestException;
import com.corelia.error.RecordNotFoundException;
import com.corelia.model.UserContact;
import com.corelia.repository.UserContactReps;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserContactService {

    private final UserContactReps userContactReps;

    @Transactional
    public UserContact insert(UserContact entity) {
        checkIfTheUserExists(entity);
        return userContactReps.save(entity);
    }

    private void checkIfTheUserExists(UserContact entity) {
        if(entity.getUser() == null) {
            throw new BadRequestException("User is required");
        }
    }

    public List<UserContact> findAll() {
        return userContactReps.findAll();
    }

    public Page<UserContact> findAllByUserId(Optional<SearchRequest> request,Long userId) {
        SearchRequest searchRequest = request.orElse(new SearchRequest());
        Sort sort = searchRequest.getSortableType() == 1 ? Sort.by(searchRequest.getSortableColumn()).ascending() : Sort.by(searchRequest.getSortableColumn()).descending();
        Pageable pageable = PageRequest.of(searchRequest.getPageNumber(), searchRequest.getPageSize(),sort);

        return userContactReps.findAll(userId,pageable);
    }

    public UserContact findById(Long id) {
        return userContactReps.findById(id).orElseThrow(() -> new RecordNotFoundException("User contact not found"));
    }

    @Transactional
    public void deleteById(Long id) {
        UserContact userContact = findById(id);
        userContactReps.delete(userContact);
    }
}
