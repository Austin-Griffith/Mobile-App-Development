//
//  Scene2ViewController.swift
//  favorites
//
//  Created by Austin Griffith on 10/2/18.
//  Copyright © 2018 Austin Griffith. All rights reserved.
//

import UIKit

class Scene2ViewController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var userBook: UITextField!
    @IBOutlet weak var userAuthor: UITextField!
    

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        
        //"doneFavs" is the identifier for the unwindSegue function
        if segue.identifier == "doneFavs" {
            
            let scene1ViewController = segue.destination as! ViewController
            
            //check to see that text was entered in the textfields
            if userBook.text!.isEmpty == false{
                scene1ViewController.user.favBook = userBook.text
            }
            if userAuthor.text!.isEmpty == false {
                scene1ViewController.user.favAuthor = userAuthor.text 
            }
        }
    }
 
    
    override func viewDidLoad() {
        userBook.delegate = self
        userAuthor.delegate = self
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
        
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}
