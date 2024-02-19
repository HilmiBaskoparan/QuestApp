package com.baskoparan.QuestApp.repos;

import com.baskoparan.QuestApp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findByUserId(Long userId);

    List<Like> findByPostId(Long postId);

    List<Like> findByUserIdAndPostId(Long userId, Long postId);
}
