package com.sis.nailsgestion.daos;

import com.sis.nailsgestion.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    /* Lista de clientes por fecha de cita */
    @Query(value = "select c.* from customer c left outer join public.scheduler_customer sc on (c.id = sc.customer_id )left outer join public.scheduler_task st on (st.id = sc.scheduler_id) where st.appointment_date=:appointmentDate order by c.name asc ", nativeQuery = true)
    List<Customer> findCustomersByAppointmentDate(@Param("appointmentDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date appointmentDate);

    /*Lista de clientes que se hicieron un tratamiento */


    /* select
	*
from
	customer c
left outer join scheduler_customer sc on
	(sc.customer_id = c.id)
left outer join scheduler_arrangement sa on
	(sa.scheduler_id = sc.scheduler_id)
left outer join
arrangement_type at2 on
	(at2.id = sa.arrangement_id)
where
	at2.description = 'nail leveling with rubber base'; */

    @Query(value = "select c.* from customer c left outer join public.scheduler_customer sc on (c.id = sc.customer_id) left outer join public.scheduler_arrangement sa on (sc.scheduler_id = sa.scheduler_id) left outer join public.arrangement_type a on (a.id = sa.arrangement_id) where a.description=:description", nativeQuery = true)
    List<Customer> findCustomersByArrangement(@Param("description") String description);

    /*Lista de clientes que tuvieron cita en un rango de fechas*/

  /*select * from customer c left outer join scheduler_customer sc on(sc.customer_id=c.id)
left outer join scheduler_task st on(st.id=sc.scheduler_id) where st.appointment_date between
'2023-12-04' and '2023-12-11' ;*/

    @Query(value = "select c.* from customer c left outer join public.scheduler_customer sc on (c.id = sc.customer_id) left outer join public.scheduler_task st on (st.id = sc.scheduler_id) where st.appointment_date between :startDate and  :endDate", nativeQuery = true)


    List<Customer> findCustomersByDateRange(@Param("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @Param("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate);
}
