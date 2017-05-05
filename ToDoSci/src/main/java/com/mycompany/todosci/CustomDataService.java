/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.todosci;


import org.scijava.service.SciJavaService;

/**
 *
 * @author Sapho
 */

public interface CustomDataService extends SciJavaService {
    void addT(String getText);
    void selectAll(String btnTxt);
    void removeTask();
}
