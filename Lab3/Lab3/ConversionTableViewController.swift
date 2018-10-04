//
//  ConversionTableViewController.swift
//  Lab3
//
//  Created by Austin Griffith on 10/1/18.
//  Copyright © 2018 Austin Griffith. All rights reserved.
//

import UIKit
extension ConversionTableViewController{        //extention of the Created class below
    func hideKeyboard() {
        let tap:UITapGestureRecognizer = UITapGestureRecognizer( target: self, action: #selector(dismissKeyboard) )
        view.addGestureRecognizer(tap)
    }
    @objc func dismissKeyboard() {
        view.endEditing(true)
    }
}

//   online tutorial https://www.youtube.com/watch?v=u19XJTActMQ



class ConversionTableViewController: UITableViewController, UITextFieldDelegate {
    
    @IBOutlet weak var dollarAmountTextField: UITextField!
    @IBOutlet weak var euroConversion: UILabel!
    @IBOutlet weak var pesoConversion: UILabel!
    @IBOutlet weak var poundConversion: UILabel!
    @IBOutlet weak var yenConversion: UILabel!
    @IBOutlet weak var canadaConversion: UILabel!
    
    
    
    @IBAction func convertDollarAmount(_ sender: UIButton) {
        //currency conversion function
        let dollarAmount = Double(dollarAmountTextField.text!)
        //converting to euro
        let euro = dollarAmount! * 0.86
        euroConversion.text = String(euro)
        
        //converting to peso
        let peso = dollarAmount! / 0.053
        pesoConversion.text = String(peso)
        
        let pound = dollarAmount! * 0.77
        poundConversion.text = String(pound)
        
        let yen = dollarAmount! * 113.58
        yenConversion.text = String(yen)
        
        let canada = dollarAmount! * 1.28
        canadaConversion.text = String(canada)
        
    }
    
    
    override func viewDidLoad() {
        dollarAmountTextField.delegate = self
        tableView.delegate = self
        tableView.dataSource = self
        super.viewDidLoad()
        self.hideKeyboard()
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        let allowedInput = CharacterSet.decimalDigits
        let inputSet = CharacterSet(charactersIn: string)
        return allowedInput.isSuperset(of: inputSet)
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
//    func touchesBegan(_ touches: Set<UITouch>, with event: UITextField?) {
//        self.view.endEditing(true)
//    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

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
