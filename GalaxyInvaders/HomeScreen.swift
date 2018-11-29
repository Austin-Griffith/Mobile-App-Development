//
//  HomeScreen.swift
//  GalaxyInvaders
//
//  Created by Austin Griffith on 11/28/18.
//  Copyright © 2018 Austin Griffith. All rights reserved.
//

import UIKit
import SpriteKit

class HomeScreen: SKScene {
    
    var galaxyBackground:SKEmitterNode!
    var startGameButton:SKSpriteNode!
    
    override func didMove(to view: SKView) {
        
        // linking the assets in HomeScreen.sks
        // using sprite kit scene to make the homescreen page
        galaxyBackground = self.childNode(withName: "galaxyBackground") as! SKEmitterNode
        
        // starts the scrolling of the galaxayBackground 10 seconds early so no gaps in screen upon starting
        galaxyBackground.advanceSimulationTime(10)
        
        //withName: ""  is reffering to the name given in HomeScreen.sks
        startGameButton = self.childNode(withName: "startGameButton") as! SKSpriteNode
        
        
        
    }


}
