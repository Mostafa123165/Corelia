package com.corelia.repository;

import com.corelia.model.UserContact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContactReps extends JpaRepository<UserContact, Long> {


    @Query(value = """
    select contact
    FROM UserContact contact
    JOIN contact.user user
    WHERE user.id =:userId
    """)
    Page<UserContact> findAll(Long userId,Pageable pageable);

}
