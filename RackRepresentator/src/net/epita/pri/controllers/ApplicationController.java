package net.epita.pri.controllers;

public class ApplicationController {

	public static void main(String[] args) {
		IViewController viewController = ViewController.Instance();
		viewController.start();
	}

}