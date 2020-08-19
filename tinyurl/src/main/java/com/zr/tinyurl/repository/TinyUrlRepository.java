package com.zr.tinyurl.repository;

import com.zr.tinyurl.vo.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TinyUrlRepository extends JpaRepository<Url,Integer> {
    @Query(value="select old_url from tinyurl where new_url=:newUrl and unix_timestamp(current_timestamp(3))*1000-create_time-min*60*1000<0", nativeQuery = true )
    String findOldUrl(@Param("newUrl") String newUrl);
    @Query(value="select * from tinyurl where unix_timestamp(current_timestamp(3))*1000-create_time-min*60*1000<0", nativeQuery = true )
    List<Url> findAllByMin();
}
