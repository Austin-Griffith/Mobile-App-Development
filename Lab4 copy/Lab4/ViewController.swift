//
//  ViewController.swift
//  Lab4
//
//  Created by Austin Griffith on 10/16/18.
//  Copyright © 2018 Austin Griffith. All rights reserved.
//

//Sources used in Lab4:
//https://www.raywenderlich.com/7569-getting-started-with-core-data-tutorial
//https://www.youtube.com/watch?v=AcTsgZkfuTU
//https://kodechamp.com/ios-todolist-core-data-xcode-9-2-swift-4/


import UIKit
import CoreData

class ViewController: UIViewController {

    @IBOutlet var tableView: UITableView!
   // @IBOutlet weak var inputText: UITextField!
    
//    var toDoArray: [String] = []

    var toDoArray: [NSManagedObject] = []
    // this array will hold instances of Items entity rather than strings of toDo things

    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        //title = "To Do List"
//        guarantees your table view will return a cell of the correct type when the Cell reuseIdentifier is provided to the dequeue method //
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "Cell")
        
    }
    
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        //getting the application delegate
        guard let appDelegate = UIApplication.shared.delegate as? AppDelegate else {
            return
        }
        //getting a reference to the data container
        let managedContext = appDelegate.persistentContainer.viewContext
        
        //NSFetchRequest is getting the data from Core Data  -->  required qualifer for fetch request --> NSEntityDescription  -->  "Items"
        let fetchRequest = NSFetchRequest<NSManagedObject>(entityName: "Items")
        
        // giving the fetch request to the managed object context which return an array of managed objects
        do {
            toDoArray = try managedContext.fetch(fetchRequest)
        } catch let error as NSError {
            print("Could not fetch. \(error), \(error.userInfo)")
        }
    }
    
    
    
    
    
    @IBAction func addToDo(_ sender: Any) {
        let alert = UIAlertController(title: "To Do List", message: "Add a new to do item", preferredStyle: .alert)
        
        // takes the text from the input text field and passes it to addItem function
        let saveAction = UIAlertAction(title: "Save", style: .default) {
            [unowned self] action in
            
            guard let textField = alert.textFields?.first,
                let nameToSave = textField.text else {
                    return
            }
            
            self.save(name: nameToSave)
            self.tableView.reloadData()
        }
        
        
        let cancelAction = UIAlertAction(title: "Cancel", style: .cancel)
        
        alert.addTextField()
        alert.addAction(saveAction)
        alert.addAction(cancelAction)
        
        present(alert, animated: true)
    }
    
        // was addName()
//    @IBAction func addToDoItem(_ sender: UIBarButtonItem) {
//
//        let alert = UIAlertController(title: "To Do List", message: "Add a new to do item", preferredStyle: .alert)
//
//        // takes the text from the input text field and passes it to addItem function
//        let saveAction = UIAlertAction(title: "Save", style: .default) {
//                [unowned self] action in
//
//                guard let textField = alert.textFields?.first,
//                    let nameToSave = textField.text else {
//                        return
//                }
//
//                self.save(name: nameToSave)
//                self.tableView.reloadData()
//            }
//
//
//        let cancelAction = UIAlertAction(title: "Cancel", style: .cancel)
//
//        alert.addTextField()
//        alert.addAction(saveAction)
//        alert.addAction(cancelAction)
//
//        present(alert, animated: true)
//
//    } // end of addToDoItem

    
    func save(name: String) {
        
        guard let appDelegate = UIApplication.shared.delegate as? AppDelegate
            else {
                return
            }
        
        // 1
        let managedContext = appDelegate.persistentContainer.viewContext
        
        // 2
        let entity = NSEntityDescription.entity(forEntityName: "Items", in: managedContext)!
        
        let listItem = NSManagedObject(entity: entity, insertInto: managedContext)
        
        // 3
        listItem.setValue(name, forKeyPath: "toDoItem")
        
        // 4
        do {
            try managedContext.save()
            toDoArray.append(listItem)
        } catch let error as NSError {
            print("Could not save. \(error), \(error.userInfo)")
        }
    }
    



    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}


// MARK: - UITableViewDataSource
extension ViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return toDoArray.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
            let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)
            let item = toDoArray[indexPath.row]

            //getting the toDoItem attribute from the NSManagedObject where things are stored with key-value coding (KVC)
            cell.textLabel?.text = item.value(forKeyPath: "toDoItem") as? String
           // cell.accessoryType = item.completed ? .checkmark : none
        
            return cell
    }
}





