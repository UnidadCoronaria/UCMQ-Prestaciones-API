package com.unidadcoronaria.prestaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.unidadcoronaria.prestaciones.domain.MedicalServiceResource;

public interface MedicalServiceResourceRepository extends CrudRepository<MedicalServiceResource,Long> {
	
	@Query("SELECT msr FROM MedicalServiceResource msr WHERE msr.resource.resourceId = :resourceId AND msr.canceled = 0 AND msr.closedMedicalService = 'F'")
	public List<MedicalServiceResource> findByResourceId(@Param("resourceId") Integer resourceId);
	
	public MedicalServiceResource findByMedicalServiceResourceId(Integer medicalServiceResourceId);
	
    @Query(value = "Select * From PrestRec_PDC_GetEstadoUbicacion(:medicalServiceResourceId)", nativeQuery = true) 
    public Integer getMedicalServicesResourceCurrentState(@Param("medicalServiceResourceId") Integer medicalServiceResourceId);
    
    @Query(value = "Select * From PrestRec_PDC_GetEventos(:medicalServiceResourceId)", nativeQuery = true) 
    public List<Integer> getMedicalServicesResourceAuthorizedStates(@Param("medicalServiceResourceId") Integer medicalServiceResourceId);
    
    @Query(value = "Execute Procedure PrestRec_PDC_SetEvento(:medicalServiceResourceId, :state)", nativeQuery = true) 
    public void setMedicalServicesResourceState(@Param("medicalServiceResourceId") Integer medicalServiceResourceId, @Param("state") Integer state);

}