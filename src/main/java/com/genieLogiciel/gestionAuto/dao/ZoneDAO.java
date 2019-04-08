/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.dao;

import com.genieLogiciel.gestionAuto.model.ZoneModel;
import java.util.List;

/**
 *
 * @author dissirama
 */
public interface ZoneDAO {

    public void addZone(ZoneModel fonction);
    public void updateZone(ZoneModel fonction);
    public void deleteZone(long idfonction);
    public ZoneModel findByZoneId(long fonctId);
    public ZoneModel findByZoneId2(long fonctId);
    public List<ZoneModel> tousZones();
    public List<ZoneModel> tousZones2();
    public String findZoneLibelleById(long fonctId);
    public int findTotalZoneModel();
    public void insertBatchSQL(String sql);
}
