package com.klef.jfsd.springboot.repo;

import com.klef.jfsd.springboot.model.Users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByEmail(String email);
    boolean existsByEmail(String email);
    
    @Query("SELECT COUNT(u) FROM Users u WHERE u.paid = :isPaid")
    int countByPaid(boolean isPaid);
    

    default Map<String, Integer> findCityWiseUserCount() {
        List<Object[]> results = findCityWiseUserCountRaw();
        Map<String, Integer> cityWiseCount = new HashMap<>();
        for (Object[] result : results) {
            cityWiseCount.put((String) result[0], ((Number) result[1]).intValue());
        }
        return cityWiseCount;
    }

    @Query("SELECT u.city AS city, COUNT(u) AS count FROM Users u GROUP BY u.city")
    List<Object[]> findCityWiseUserCountRaw();
}
