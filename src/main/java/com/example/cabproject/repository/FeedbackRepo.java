package com.example.cabproject.repository;
import com.example.cabproject.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface FeedbackRepo extends JpaRepository<Feedback,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM feedback WHERE order_id = :orderId ORDER BY feedback_id DESC")
    List<Feedback> findFeedbacksByOrderId(Long orderId);

}
