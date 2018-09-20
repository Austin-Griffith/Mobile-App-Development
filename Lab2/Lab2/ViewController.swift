//
//  ViewController.swift
//  Lab2
//
//  Created by Austin Griffith on 9/18/18.
//  Copyright © 2018 Austin Griffith. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

   
    @IBOutlet weak var titleBanner: UILabel!
    @IBOutlet weak var dogImage: UIImageView!
    @IBOutlet weak var imageSwitch: UISegmentedControl!
    @IBOutlet weak var capitalSwitch: UISwitch!
    @IBOutlet weak var fontSizeBanner: UILabel!
    @IBOutlet weak var fontSlider: UISlider!
    
    
    func changeImage(){
        if imageSwitch.selectedSegmentIndex == 0 {
            titleBanner.text="Thats a good dog"
            dogImage.image=UIImage(named: "goodDog")
        }
        else if imageSwitch.selectedSegmentIndex == 1 {
            titleBanner.text="Not so good dog"
            dogImage.image=UIImage(named: "badDog")
        }
    }
    
    func makeCapital(){
        if capitalSwitch.isOn {
            titleBanner.text=titleBanner.text?.uppercased()
        } else {
            titleBanner.text=titleBanner.text?.lowercased()
        }
    }
    
    @IBAction func changeInfo(_ sender: UISegmentedControl) {
        changeImage()
        makeCapital()
    }
    
    @IBAction func updateCaps(_ sender: UISwitch) {
        makeCapital()
    }
    
    
    @IBAction func changeFontSize(_ sender: UISlider) {
        let fontSize=sender.value   //this has to be a float value
        
        fontSizeBanner.text = String(format: "%.0f", fontSize) //convert float to String
        
        let fontSizeCGFloat=CGFloat(fontSize) //convert float to CGFloat
        
        titleBanner.font = UIFont.systemFont(ofSize: fontSizeCGFloat) //create a UIFont object and assign to the font property

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

