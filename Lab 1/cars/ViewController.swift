//
//  ViewController.swift
//  cars
//
//  Created by Austin Griffith on 9/4/18.
//  Copyright © 2018 Austin Griffith. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    

    @IBOutlet weak var artImage1: UIImageView!
    
    @IBOutlet weak var car: UIButton!
    
    @IBAction func chooseArt(_ sender: UIButton) {

        if sender.tag == 1 {
            artImage1.image=UIImage(named: "m3")
            sender.setTitle("M3 BMW", for: .normal)
        }
        else if sender.tag == 2 {
            artImage1.image=UIImage(named: "raptor")
            sender.setTitle("Ford Raptor", for: .normal)
        }

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

