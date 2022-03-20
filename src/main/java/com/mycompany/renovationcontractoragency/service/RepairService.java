package com.mycompany.renovationcontractoragency.service;

import com.mycompany.renovationcontractoragency.entity.Repair;

import java.time.LocalDateTime;
import java.util.List;

public interface RepairService extends Service<Repair> {
    List<Repair> getRepairByDate(LocalDateTime date);
    List<Repair> getRepairByDateRange(LocalDateTime dateFrom, LocalDateTime dateTo);
    List<Repair> getRepairByOwnerId(long id);
}
