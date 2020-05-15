/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zhiyi
 */
public class TestObj {
    int i = 1;
    public void inc(){
        System.out.println("incremented i");
        i++;
    }
    
    public String toString(){
        return "" + i;
    }
}
