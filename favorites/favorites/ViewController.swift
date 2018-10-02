//
//  ViewController.swift
//  favorites
//
//  Created by Austin Griffith on 10/2/18.
//  Copyright © 2018 Austin Griffith. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var favBookLabel: UILabel!
    @IBOutlet weak var favAuthorLabel: UILabel!
    
    var user=Favorite()
    
    
    
    @IBAction func unwindSegue(_ segue:UIStoryboardSegue) {
        
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

