//
//  GameViewController.swift
//  GalaxyInvaders
//
//  Created by Austin Griffith on 10/2/18.
//  Copyright © 2018 Austin Griffith. All rights reserved.
//

import UIKit
import SpriteKit
import GameplayKit

class GameViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        if let view = self.view as! SKView? {
            // Load the SKScene from 'GameScene.sks'
            //initilizing the game scene to the app view 
//            if let scene = SKScene(fileNamed: "GameScene") {
//                // Set the scale mode to scale to fit the window
////                scene.scaleMode = .aspectFill
//                scene.scaleMode = .aspectFit
//
//                // Present the scene
//                view.presentScene(scene)
//            }
            
            //IMPLEMENTING THE HOMESCREEN as the starting scene for this application 
            if let scene = SKScene(fileNamed: "HomeScreen") {
                // Set the scale mode to scale to fit the window
                scene.scaleMode = .aspectFit
                
                // Present the scene
                view.presentScene(scene)
            }
            view.ignoresSiblingOrder = true
            view.showsFPS = true
            view.showsNodeCount = true
        }
    }

    override var shouldAutorotate: Bool {
        return true
    }

    override var supportedInterfaceOrientations: UIInterfaceOrientationMask {
        if UIDevice.current.userInterfaceIdiom == .phone {
            return .allButUpsideDown
        } else {
            return .all
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Release any cached data, images, etc that aren't in use.
    }

    override var prefersStatusBarHidden: Bool {
        return true
    }
}
