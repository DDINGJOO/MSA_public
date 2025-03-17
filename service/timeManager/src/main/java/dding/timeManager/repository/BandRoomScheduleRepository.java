package dding.timeManager.repository;


import dding.timeManager.entity.BandRoomSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BandRoomScheduleRepository extends JpaRepository<BandRoomSchedule, Long> {


    List<BandRoomSchedule> findByBandRoomIdAndDateBetween(Long bandRoomId, LocalDate from, LocalDate to);

    void deleteByBandRoomIdAndDate(Long bandRoomId, LocalDate parse);


    //  가장 많이 운영된 시간대 찾기 (대시보드 통계용)
    @Query("""
        SELECT s.hour, COUNT(s) FROM BandRoomSchedule s
        WHERE s.bandRoomId = :roomId AND s.available = true
        GROUP BY s.hour ORDER BY COUNT(s) DESC
        """)
    List<Object[]> getMostActiveHours(@Param("roomId") Long roomId);

}

