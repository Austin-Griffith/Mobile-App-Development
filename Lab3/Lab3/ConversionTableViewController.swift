//
//  ConversionTableViewController.swift
//  Lab3
//
//  Created by Austin Griffith on 10/1/18.
//  Copyright © 2018 Austin Griffith. All rights reserved.
//

import UIKit

class ConversionTableViewController: UITableViewController, UITextFieldDelegate {

    @IBOutlet weak var dollarAmountTextField: UITextField!
    
    @IBOutlet weak var euroConversion: UILabel!
    @IBOutlet weak var pesoConversion: UILabel!
    
    // button for return key on number pad
    //let button = UIButton(type: UIButtonType.custom)
    
    
    @IBAction func convertButtonPressed(_ sender: Any) {
        //currency conversion function
        let dollarAmount = Double(dollarAmountTextField.text!)
        
        //converting to euro
        let euro = dollarAmount! * 1.16
        euroConversion.text = String(euro)
        
        //converting to peso
        let peso = dollarAmount! * 0.053
        pesoConversion.text = String(peso)
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //setting custom return button on keypad input to textField
//        button.setTitle("Return", for: UIControlState.normal)
//        button.setTitleColor(UIColor.black, for: UIControlState.normal)
//        button.frame = CGRect(0,163,186,53)
//        button.adjustsImageWhenHighlighted = false
//        button.addTarget(self, action: Selector(("Done")), for: UIControlEvents.touchUpInside)

    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 0
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return 0
    }





}
