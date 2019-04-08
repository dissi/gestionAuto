/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.dao;

import com.genieLogiciel.gestionAuto.model.AgentModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dissirama
 */
public interface AgentDAO {

    public void addAgent(AgentModel agent);
    public void updateAgent(AgentModel agent);
    public void deleteAgent(long idagent);
    public AgentModel findByAgentId(long agtId);
    public AgentModel findByAgentId2(long agtId);
    public List<AgentModel> tousAgents();
    public List<AgentModel> tousAgents2();
  
    public List<AgentModel> tousAgentsFonction(long fonction_id);
        
    public String findAgentLibelleById(long agtId);
    public int findTotalAgentModel();
    public void insertBatchSQL(String sql);
    public List<AgentModel> allChauffeursByDate(Date datedebut);
}
