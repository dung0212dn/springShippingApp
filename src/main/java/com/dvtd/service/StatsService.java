/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dvtd.service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author ACER
 */

public interface StatsService {
   List<List<Map<String, Integer>>> statsRevenue(Map<String, String> params);
}
