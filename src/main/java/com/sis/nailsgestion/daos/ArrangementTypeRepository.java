package com.sis.nailsgestion.daos;

import com.sis.nailsgestion.models.ArrangementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrangementTypeRepository extends JpaRepository<ArrangementType,Long> {
}
