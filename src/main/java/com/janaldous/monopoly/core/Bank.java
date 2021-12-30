package com.janaldous.monopoly.core;


/**
 * Write a description of interface Bank here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface Bank
{
    void deposit(int amount);
    int withdraw(int amount);
    House buyHouse(int amount);
    Hotel buyHotel(int amount);
}
