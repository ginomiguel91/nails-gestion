package com.sis.nailsgestion.daos;

import com.sis.nailsgestion.models.SchedulerTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface SchedulerTaskRepository extends JpaRepository<SchedulerTask, Long> {
    /*Devuelve el total en  monto en arreglo por tarea pla*/
    @Query(value = "select sum(at2.price) from scheduler_task st left outer join  public.scheduler_arrangement sa on (st.id = sa.scheduler_id) left outer join public.arrangement_type at2 on( at2.id = sa.arrangement_id) where st.id=:id", nativeQuery = true)
    Double getTotalAmountBySchedulerTask(@PathVariable Long id);
}
