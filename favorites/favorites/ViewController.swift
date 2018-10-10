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
    
    var user = Favorite()
    
    let fileInput = "test.plist"
    
    func dataFileURL() {
        //returns an array of URL's for the document directory in the user's home directory
        let urls = FileManager.default.urls(for: documentDirectory, in: .userDomainMask)
        let url : URLS?
        //append the file name to the first item in the array which is the document directory
        url = urls.first?.appendPathComponent(fileInput)
        //return the URL
    }
    
    
    @IBAction func unwindSegue(_ segue:UIStoryboardSegue) {
        favBookLabel.text = user.favBook
        favAuthorLabel.text = user.favAuthor
        
    }

    override func viewDidLoad() {
        
        //url of data file
        let fileURL = dataFileURL(fileInput)
        
        //if the data file exists, use it
        if FileManager.default.fileExists(atPath: (fileURL?.path)!)
        {
            let url = fileURL!
            do {
                //creates a data buffer with the contents of the plist
                let data = try Data(contentsOf: url)
                
                //create an instance of PropertyListDecoder
                let decoder = PropertyListDecoder()
                
                //decode the data using the structure of the Favorite class
                user = try decoder.decode(Favorite.self, from: data)
                
                //assign data to textfields
                favBookLabel.text = user.favBook
                favAuthorLabel.text = user.favAuthor
            }
            
            catch {
                print("no file")
            }
            
        }
        else
        {
            print("file does not exist")
        }
        
        
        //application instance
        let app = UIApplication.shared
        //subscribe to the UIApplicationWindowWillResignActive notification
        NotificationCenter.default.addObserver(self, selector: #selector(self.applicationWillResignActive(_:)), name: Notification.Name.UIApplicationWillResignActive, object: app)
        
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
    }
    
    //called when the UIApplicationWillResignActiveNotification notification is posted
    //all notification methods take a single NSNotification instance as their argument
    @objc func applicationWillResignActive(_ notification: Notification){
        //url of data file
        let fileURL = dataFileURL(fileInput)
        //create an instance of PropertyListEncoder
        let encoder = PropertyListEncoder()
        //set format type to xml
        encoder.outputFormat = .xml
        do {
            //encode the data using the structure of the Favorite class
            let plistData = try encoder.encode(user)
            //write encoded data to the file
            try plistData.write(to: fileURL!)
        } catch {
            print("write error")
        }
    }
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

