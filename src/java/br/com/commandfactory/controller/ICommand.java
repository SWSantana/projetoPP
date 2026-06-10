/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.commandfactory.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.coyote.BadRequestException;

/**
 *
 * @author PICHAU
 */
public interface ICommand {
    
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception;
    
}
