/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dvtd.service;

import java.util.List;

/**
 *
 * @author ACER
 */
public interface EmailSenderService {
    boolean sendEmail(String to, String subject, String message);
    boolean sendEmailMultiRecepient(List<String> recipients, String subject, String message);
}
