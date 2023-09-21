/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.service.impl;

import com.dvtd.repository.StatsRepository;
import com.dvtd.service.StatsService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private StatsRepository statsRepo;

    @Override
    public List<List<Map<String, Integer>>> statsRevenue(Map<String, String> params) {
        List<Object[]> data = this.statsRepo.statsRevenue(params);

        List<Map<String, Integer>> resultList = new ArrayList<>();

        for (Object[] item : data) {
            Map<String, Integer> map = new HashMap<>();
            map.put("x", (Integer) item[0]);
            map.put("y", (Integer) item[1]);
            resultList.add(map);
        }
        List<List<Map<String,Integer>>> list = new ArrayList<List<Map<String,Integer>>>();
        list.add(resultList);

        return list;
    }

}
