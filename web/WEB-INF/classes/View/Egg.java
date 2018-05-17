package View;

import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class Egg {
    // propriedades da conta
    private String login;
    private String senha;

    // propriedades do ovo
    private int hunger;
    private int health;
    private int happy;
    private int state;
    private Calendar lastTime;
    private int gameTouch;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHappy() {
        return happy;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Calendar getLastTime() {
        return lastTime;
    }

    public void setLastTime(Calendar lastTime) {
        this.lastTime = lastTime;
    }

    public int getGameTouch() {
        return gameTouch;
    }

    public void setGameTouch(int gameTouch) {
        this.gameTouch = gameTouch;
    }
}
