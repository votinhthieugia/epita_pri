package fr.epita.pri.rackrepresentator.main;

import fr.epita.pri.rackrepresentator.settings.Setting;
import fr.epita.pri.rackrepresentator.view.ViewController;

public class Main {
    public static void main(String[] args) {
    	Setting.Instance().load();
    	ViewController.Instance().start();
    }
}