package ru.optima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.optima.repr.WorkRepr;
import ru.optima.persist.model.Work;
import ru.optima.persist.repo.WorkRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkServiceImpl implements WorkService, Serializable {

    private WorkRepository workRepository;

    @Autowired
    public WorkServiceImpl(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    @Override
    public void save(WorkRepr workRepr) {
        Work work = new Work();
        work.setId(workRepr.getId());
        work.setClientName(workRepr.getClientName());
        work.setCustomer(workRepr.getCustomer());
        work.setNumberContract(workRepr.getNumberContract());
        work.setObjectName(workRepr.getObjectName());
        work.setRegistrationDate(workRepr.getRegistrationDate());
        work.setUsers(workRepr.getUsers());
        work.setWorkStatus(workRepr.getWorkStatus());
        workRepository.save(work);
    }

    @Override
    public List<WorkRepr> findAll() {
        return workRepository.findAll().stream()
                .map(WorkRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<WorkRepr> findById(Long id) {
        return workRepository.findById(id).map(WorkRepr::new);
    }

    @Override
    public void delete(Long id) {
        workRepository.deleteById(id);
    }

    public WorkRepr findWorkById(Long id) {
        return new WorkRepr(workRepository.findById(id).orElse(new Work()));
    }

    public List<Work> findAllWorksByUserIdWithStatusName(Long id, String statusName) {
        return workRepository.findAllWorksByUserIdWithStatusName(id, statusName);
    }

    public List<Work> findAllWorksByUserIdWithStatusName(Long id, String statusNameOne, String statusNameTwo) {
        return workRepository.findAllWorksByUserIdWithStatusName(id, statusNameOne, statusNameTwo);
    }
}
