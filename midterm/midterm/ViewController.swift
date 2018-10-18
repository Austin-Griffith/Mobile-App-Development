//
//  ViewController.swift
//  midterm
//
//  Created by Austin Griffith on 10/18/18.
//  Copyright © 2018 Austin Griffith. All rights reserved.
//

import UIKit


extension UIViewController{        //extention of the Created class below
    func hideKeyboard() {
        let tap:UITapGestureRecognizer = UITapGestureRecognizer( target: self, action: #selector(dismissKeyboard) )
        view.addGestureRecognizer(tap)
    }
    @objc func dismissKeyboard() {
        view.endEditing(true)
    }
}

class ViewController: UIViewController, UITextFieldDelegate {


    @IBOutlet weak var workoutTimeTextField: UITextField!
    
    @IBOutlet weak var milesOutput: UILabel!
    @IBOutlet weak var caloriesOutput: UILabel!
    
    @IBAction func switchAction(_ sender: Any) {
        
//        if ((sender as AnyObject).isOn == true) {
//            Double(workoutTimeTextField) * 7.0
//        }
    }
    
    @IBOutlet weak var segmentedControl: UISegmentedControl!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        //workoutTimeTextField.delegate = self
        self.hideKeyboard()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        self.view.endEditing(true)
        return true
    }

       @IBAction func calculateWorkout(_ sender: Any) {
        
        
        var workoutTime = Double(workoutTimeTextField.text!)
        
        if Double(workoutTime!) < 30 {
            let alert = UIAlertController(title: "Wrong Input", message: "workout must be 30 mins", preferredStyle: .alert)
            
           
             let okAction = UIAlertAction(title: "OK", style: .default) {
                [unowned self] action in
                let minWorkoutTime: Double = 30

                if (workoutTime! < minWorkoutTime ) {
                    //Double(workoutTimeTextField.text) = minWorkoutTime

                return
            }
                
        }
            
            let cancelAction = UIAlertAction(title: "Cancel", style: .cancel)
            
            //alert.addTextField()
            alert.addAction(okAction)
            alert.addAction(cancelAction)
            
            self.present(alert, animated: true)
        
            
    }
        
        let miles = workoutTime! / 10
        milesOutput.text = String(miles)
        
        let calories = (workoutTime! / 60) * 600
        caloriesOutput.text = String(calories)
        
    }
    
}

