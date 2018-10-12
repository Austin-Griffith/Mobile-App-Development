//
//  ViewController.swift
//  Lab4
//
//  Created by Austin Griffith on 10/11/18.
//  Copyright © 2018 Austin Griffith. All rights reserved.
//

//sources used in project:
//    https://cocoacasts.com/working-with-managed-objects-in-core-data
//    https://www.youtube.com/watch?v=orkHnbhjBJs
//    https://cocoacasts.com/working-with-managed-objects-in-core-data
//    https://developer.apple.com/documentation/coredata/nsentitydescription

import UIKit
import CoreData
import Foundation

class TodoViewController: UITableViewController {

    var items = [Items]()
    
     let context = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    
     //let managedObjectContext = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return items.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Item", for: indexPath)
        let item = items[indexPath.row]
        cell.textLabel?.text = item.name
        cell.accessoryType = item.completed ? .checkmark: .none
        
        return cell
    }
    
    //MARK:  Table View Delegate Methods needed
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        items[indexPath.row].completed = !items[indexPath.row].completed
        saveToDoItems()
    }
    
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        return true
    }
    
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if (editingStyle == .delete) {
            let item = items[indexPath.row]
            items.remove(at: indexPath.row)
            context.delete(item)
            
            do {
                try context.save()
            }
            catch {
                print("Error deleting item with \(error)")
            }

            tableView.deleteRows(at: [indexPath], with: .automatic)

            
        }
    }
    
    
    
    @IBAction func addButtonPressed(_ sender: UIBarButtonItem) {
        
        //let item = NSEntityDescription.insertNewObject(forEntityName: "ToDoList", into: managedObjectContext) as! ToDoList

        var textField = UITextField()
        var alert = UIAlertController(title: "Add New Item", message: "", preferredStyle: .alert)
        let action = UIAlertAction(title: "Save Item", style: .default) { (action) in

            let newItem = Items(context: self.context)
            newItem.name = textField.text!
            self.items.append(newItem)
            self.saveToDoItems()
        }

        alert.addAction(action)
        alert.addTextField{ (field) in
            textField = field
            textField.placeholder = "Add a new to do item"
        }

        present(alert, animated: true, completion: nil)

    }

    func saveToDoItems() {

        do {
            try context.save()
        }
        catch {
            print("Error adding to do item \(error)")
        }
        
        tableView.reloadData()
    }
    
    
    
    func loadToDoItems() {
        
        let result = NSFetchRequest<NSFetchRequestResult>(entityName:"Items")
        
//        let request: NSFetchRequest<Items> = Items.fetchRequest()
        
        do {
//            items = try context.fetch(request)
            items = result as! [Items]
        }
        catch {
            print("Error fetching data from context \(error)")
        }
        
        tableView.reloadData()
        
    }

    

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

