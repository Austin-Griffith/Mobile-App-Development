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
    
    @IBAction func convertDollarAmount(_ sender: UIButton) {
        //currency conversion function
        let dollarAmount = Double(dollarAmountTextField.text!)
        
        //converting to euro
        let euro = dollarAmount! * 0.86
        euroConversion.text = String(euro)
        
        //converting to peso
        let peso = dollarAmount! * 0.053
        pesoConversion.text = String(peso)
    }
    
    
    override func viewDidLoad() {
        dollarAmountTextField.delegate = self
        tableView.delegate = self
        tableView.dataSource = self
        super.viewDidLoad()
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source
//
//    override func numberOfSections(in tableView: UITableView) -> Int {
//        // #warning Incomplete implementation, return the number of sections
//        return 2
//    }

//    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
//        // #warning Incomplete implementation, return the number of rows
//        return 0
//    }





}
