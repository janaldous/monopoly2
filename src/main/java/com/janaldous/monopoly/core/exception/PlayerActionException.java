package com.janaldous.monopoly.core.exception;


/**
 * Write a description of class PlayerActionException here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PlayerActionException extends Exception
{
    public PlayerActionException(Throwable t)
    {
        super(t);
    }
    
    public PlayerActionException(String msg) {
        super(msg);
    }
}
